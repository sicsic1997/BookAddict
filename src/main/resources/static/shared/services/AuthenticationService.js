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

        ////////////////

        function authenticate(username, password) {
            console.log("login in progress");
            return $timeout(function () {
                console.log("login finished");
            }, 2000);
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

    }
})();