(function() {

    'use strict';

    angular
        .module("bookDashboard")
        .controller("BookDashboardController", Controller);

    Controller.$inject = [
        '$scope',
        '$state'
    ];

    function Controller($scope,
                        $state) {

        var vm = this;
    }

})();