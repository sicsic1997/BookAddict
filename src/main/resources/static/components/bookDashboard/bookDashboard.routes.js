(function () {

    'use strict';

    angular
        .module('bookDashboard')
        .config(Config);

    Config.$inject = [
        '$stateProvider',
        '$urlRouterProvider',
        '$locationProvider'
    ];

    function Config($stateProvider,
                    $urlRouterProvider,
                    $locationProvider) {

        $urlRouterProvider.otherwise('/book-dashboard/view');

        $stateProvider
            .state('bookDashboard', {
                parent: 'main',
                abstract: true,
                url: '/book-dashboard'
            })
            .state('viewBookDashboard', {
                parent: 'bookDashboard',
                url: '/view',
                templateUrl: 'components/bookDashboard/views/bookDashboard.view.html',
                controller: 'BookDashboardController'
            })

    }

})();