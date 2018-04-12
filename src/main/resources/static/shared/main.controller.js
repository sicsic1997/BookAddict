(function() {

    'use strict';

    angular
        .module("bookAddict")
        .controller("MainController", Controller);

    Controller.$inject = [
        '$scope',
        '$rootScope',
        '$transitions',
        '$state',
        '$cookies',
        '$http',
        '$timeout',
        'AuthenticationService'
    ];

    function Controller($scope,
                        $rootScope,
                        $transitions,
                        $state,
                        $cookies,
                        $http,
                        $timeout,
                        AuthenticationService) {

        var vm = this,
            nonRestrictedRoutes = ['login', 'register'];

        $rootScope.USERCOOKIE = $cookies.getObject('USERCOOKIE') || {};

        (function init() {
            if ($rootScope.USERCOOKIE.loggedUser) {
                $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.USERCOOKIE.loggedUser.authdata;
                AuthenticationService.setUserDetails($rootScope.USERCOOKIE.loggedUser.userDetails);
            }
        })();

        $transitions.onBefore({}, function(transition) {
            var isUserLogged = $rootScope.USERCOOKIE.loggedUser !== undefined,
                isRestrictedRoute = !nonRestrictedRoutes.find(function(state) {
                    return transition.to().name === state;
                });
            if (isRestrictedRoute && !isUserLogged) {
                $timeout(function(){
                    $state.go('login', true);
                });
            }
        });

    }

})();