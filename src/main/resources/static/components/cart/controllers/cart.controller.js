(function() {

    'use strict';

    angular
        .module("cart")
        .controller("CartController", Controller);

    Controller.$inject = [
        '$scope',
        '$state',
        '$mdDialog',
        'CoreApiService',
        'BookData'
    ];

    function Controller($scope,
                        $state,
                        $mdDialog,
                        CoreApiService,
                        BookData) {

        var vm = this;

        vm.bookData = BookData;

        vm.makeBooking = makeBooking;
        vm.goToDashboard = goToDashboard;

        //////////

        _initCtrl();

        function _initCtrl() {

        }

        function viewBook(book) {
            $mdDialog.show({
                controller: 'BookDialogController as dialogController',
                templateUrl: 'components/bookDashboard/views/bookDialog.view.html',
                parent: angular.element(document.body),
                clickOutsideToClose: true,
                customFullscreen: true,
                locals: {book: book}
            }).then(function() {

            }, function() {

            });
        }

        function makeBooking() {
            var data = {
                bookingItemDTOList: _getBookingData()
            };

            CoreApiService
                .makeNewBooking(data)
                .then(function() {
                    _promptUser({isSuccess: true});
                }, function() {
                    _promptUser();
                });
        }

        function _promptUser(args) {
            var success = args !== undefined && args.isSuccess,
                dialogTitle =
                    success ? 'Your are now a Book Addict!' : 'Something went wrong',
                dialogDescription =
                    success ? 'You can come in any store to pick your delivery.' : 'Please contact a member from support team';
            var alert = $mdDialog.alert()
                .title(dialogTitle)
                .textContent(dialogDescription)
                .ok('Got it!');

            $mdDialog.show(alert).then(function() {
                vm.bookData.selectedBooks = [];
                vm.goToDashboard();
            });
        }

        function _getBookingData() {
            var data = vm.bookData.selectedBooks;

            data = data.map(function (el) {
               return {
                   bookId: el.book.idBook,
                   quantity: el.selectedQuantity
               }
            });

            return data;
        }

        function goToDashboard() {
            $state.go('viewBookDashboard', {}, {reload: true});
        }
    }

})();