(function() {

    'use strict';

    angular
        .module("bookDashboard")
        .controller("BookDialogController", Controller);

    Controller.$inject = [
        '$scope',
        '$state',
        '$mdDialog',
        'CoreApiService',
        'book'
    ];

    function Controller($scope,
                        $state,
                        $mdDialog,
                        CoreApiService,
                        book) {

        var vm = this;

        vm.book = book;

        vm.close = close;
        vm.cancel = cancel;

        //////////

        function close() {
            $mdDialog.hide();
        }

        function cancel() {
            $mdDialog.cancel();
        }

    }

})();