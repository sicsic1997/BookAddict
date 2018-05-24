(function () {

    'use strict';

    angular
        .module('bookAddict')
        .service('AuthenticationService', AuthenticationService);

    AuthenticationService.$inject = [
        '$timeout',
        '$rootScope',
        '$http',
        '$cookies',
        'BaseEncodingService'
    ];

    function AuthenticationService($timeout,
                                   $rootScope,
                                   $http,
                                   $cookies,
                                   BaseEncodingService) {

        var baseUrl = "http://localhost:8080/bookAddict/",
            userDetails = {};

        this.authenticate = authenticate;
        this.setCredentials = setCredentials;
        this.clearCredentials = clearCredentials;
        this.registerUser = registerUser;
        this.getUserDetails = getUserDetails;
        this.setUserDetails = setUserDetails;

        ////////////////

        function authenticate(username, password) {
            return $http.get(baseUrl + "user/login?userName=" + username + "&password=" + password);
        }

        function setCredentials(userData, userName, password) {
            var cookieExp = new Date(),
                authdata = BaseEncodingService.encode(userName + ':' + password);

            setUserDetails(userData);

            $rootScope.USERCOOKIE = {
                loggedUser: {
                    userDetails: userData,
                    authdata: authdata
                }
            };

            $http.defaults.headers.common['Authorization'] = 'Basic ' + authdata;
            cookieExp.setDate(cookieExp.getDate() + 7);
            $cookies.putObject('USERCOOKIE', $rootScope.USERCOOKIE, { expires: cookieExp });
        }

        function clearCredentials() {
            $rootScope.USERCOOKIE = {};
            $cookies.remove('USERCOOKIE');
            setUserDetails({});
            $http.defaults.headers.common['Authorization'] = 'Basic';
        }

        function registerUser(registerData) {
            return $http.post(baseUrl + "user/register", registerData);
        }

        function getUserDetails() {
            return userDetails;
        }

        function setUserDetails(userData) {
            userDetails = angular.copy(userData);
        }

    }
})();