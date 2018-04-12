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

        /////////

        function _initCtrl() {
            vm.userDetails = AuthenticationService.getUserDetails();
        }

        _initCtrl();
    }

})();