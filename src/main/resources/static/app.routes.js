(function () {

    'use strict';

    angular
        .module('bookAddict')
        .config(Config);

    Config.$inject = [
        '$stateProvider',
        '$urlRouterProvider',
        '$locationProvider'
    ];

    function Config($stateProvider,
                    $urlRouterProvider,
                    $locationProvider) {

        $stateProvider
            .state('main', {
                abstract: true
            })
            .state('login', {
                url: '/login',
                parent: 'main',
                templateUrl: './components/login/views/login.view.html',
                controller: 'BookAddictLoginController as LoginController',
                params: {
                    isLogin: true
                }
            })

    }

})();