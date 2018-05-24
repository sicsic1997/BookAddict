(function () {

    'use strict';

    angular
        .module('cart')
        .config(Config);

    Config.$inject = [
        '$stateProvider'
    ];

    function Config($stateProvider) {

        $stateProvider
            .state('viewMyCart', {
                parent: 'main',
                url: '/cart',
                templateUrl: 'components/cart/views/cart.view.html',
                controller: 'CartController as CartCtrl'
            })

    }

})();