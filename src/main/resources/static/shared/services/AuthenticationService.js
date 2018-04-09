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

        this.authenticate = authenticate;
        this.setCredentials = setCredentials;
        this.clearCredentials = clearCredentials;
        this.registerUser = registerUser;

        var baseUrl = "http://localhost:8080/bookAddict/";

        ////////////////

        function authenticate(username, password) {
            return $http.get(baseUrl + "user/login?userName=" + username + "&password=" + password);
        }

        function setCredentials(username, password) {
            var cookieExp = new Date(),
                authdata = BaseEncodingService.encode(username + ':' + password);

            $rootScope.USERCOOKIE = {
                loggedUser: {
                    username: username,
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
            $http.defaults.headers.common.Authorization = 'Basic';
        }

        function registerUser(registerData) {
            return $http.post(baseUrl + "user/register", registerData);
        }

    }
})();