(function() {

    'use strict';

    angular
        .module("bookAddict")
        .controller("sharedHeaderController", Controller);

    Controller.$inject = [
        '$scope',
        '$state',
        'AuthenticationService'
    ];

    function Controller($scope,
                        $state,
                        AuthenticationService) {

        var vm = this;

        vm.viewMyCart = viewMyCart;
        vm.viewBookDashboard = viewBookDashboard;

        /////////

        function _initCtrl() {
            vm.userDetails = AuthenticationService.getUserDetails();
        }

        function viewMyCart() {
            $state.go("viewMyCart");
        }

        function viewBookDashboard() {
            $state.go('viewBookDashboard', {}, {reload: true});
        }

        _initCtrl();
    }

})();