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
        '$timeout'
    ];

    function Controller($scope,
                        $rootScope,
                        $transitions,
                        $state,
                        $cookies,
                        $http,
                        $timeout) {

        var vm = this,
            nonRestrictedRoutes = ['login', 'register'];

        $rootScope.USERCOOKIE = $cookies.getObject('globals') || {};

        (function init() {
            if ($rootScope.USERCOOKIE.loggedUser) {
                $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.USERCOOKIE.loggedUser.authdata;
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