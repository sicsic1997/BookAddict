(function() {

    'use strict';

    angular
        .module("bookDashboard")
        .controller("BookDashboardController", Controller);

    Controller.$inject = [
        '$scope',
        '$state',
        '$mdDialog',
        'CoreApiService',
        'BookSearchData',
        'BookData'
    ];

    function Controller($scope,
                        $state,
                        $mdDialog,
                        CoreApiService,
                        BookSearchData,
                        BookData) {

        var vm = this;

        vm.bookSearchData = BookSearchData;
        vm.bookData = BookData;

        vm.priceSlider = {
            options: {
                floor: 0,
                ceil: 150,
                translate: function(value) {
                    return value + ' RON' + '\n';
                },
                onEnd: _loadBooks
            }
        };

        vm.categoryLoadingStatus = false;
        vm.priceLoadingStatus = false;
        vm.booksLoadingStatus = true;

        vm.books = [];
        vm.bookSearchData.orderOptions = [
            {id: 'ALPHABETICAL', description: 'Alphabetical Order'},
            {id: 'PRICE_LOW_TO_HIGH', description: 'Price Low to High'},
            {id: 'PRICE_HIGH_TO_LOW', description: 'Price High to Low'}
        ];

        vm.typeheadOnSelect = typeheadOnSelect;
        vm.sortBooks = sortBooks;
        vm.toggleCheckbox = toggleCheckbox;
        vm.toggleBookSelection = toggleBookSelection;
        vm.getTypeheadOptions = getTypeheadOptions;
        vm.viewBook = viewBook;

        //////////

        _initCtrl();

        function typeheadOnSelect($item, $model) {
            _loadBooks();
        }

        function sortBooks() {
            _loadBooks();
        }

        function toggleCheckbox(element) {
            element.isSelected = !element.isSelected;
            _loadBooks();
        }

        function _initCtrl() {
            vm.bookSearchData.orderSelection = vm.bookSearchData.orderOptions[0];


            _loadCategories();
            _loadMaxPrice();
        }

        function toggleBookSelection(book, event) {
            book.isSelected = !book.isSelected;
            _updateSelection(book);
            event.stopPropagation();
        }


        function _loadCategories() {
            vm.categoryLoadingStatus = true;

            CoreApiService
                .getCategories()
                .then(function(response) {
                    vm.bookSearchData.categories = response.data;
                    vm.categoryLoadingStatus = false;
                    _loadBooks();
                }, function() {
                    console.log("failed to load categories");
                    vm.categoryLoadingStatus = false;
                });
        }

        function _loadMaxPrice() {
            vm.priceLoadingStatus = true;

            CoreApiService
                .getMaxPrice()
                .then(function(response) {
                    vm.priceSlider.options.ceil = response.data;
                    vm.bookSearchData.maxPrice = vm.priceSlider.options.ceil;
                    vm.priceLoadingStatus = false;
                    _loadBooks();
                }, function() {
                    console.log("failed to load max price");
                    vm.priceLoadingStatus = false;
                });
        }

        function _loadBooks() {
            var data = _getSelectedFilters();

            vm.booksLoadingStatus = true;
            CoreApiService
                .getBooksByFilter(data)
                .then(function(response) {
                    vm.books = response.data;
                    vm.booksLoadingStatus = false;
                }, function() {
                    console.log("failed to load books");
                    vm.booksLoadingStatus = false;
                });
        }

        function _getSelectedFilters() {
            var data = vm.bookSearchData.getDTO();
            data.categoryDTOList = _getSelectedCategories(data.categoryDTOList);
            return data;
        }

        function _getSelectedCategories(categories) {
            var selectedCategories = categories.filter(function(category) {
                if(category.isSelected === true) {
                    return category.idCategory;
                }
            });

            return selectedCategories;
        }

        function getTypeheadOptions() {
            if(vm.bookSearchData.typheadSelection === '') {
                _loadBooks();
                return;
            }
            CoreApiService
                .getTextFilterEntries(vm.bookSearchData.typheadSelection)
                .then(function(response) {
                    vm.typheadOptions = response.data;
                }, function() {
                    console.log("failed to load options for text filter");
                });
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

        function _updateSelection(element) {
            var index = _getIndex(element);
            if(index === -1 && element.isSelected) {
                vm.bookData.selectedBooks.push({book: element, selectedQuantity: 1});
            } else {
                vm.bookData.selectedBooks.splice(index, 1);
            }
        }

        function _getIndex(element) {
            var idx = -1;
            for(var i = 0; i < vm.bookData.selectedBooks.length; i++) {
                if(vm.bookData.selectedBooks[i].book.idBook === element.idBook) {
                    idx = i;
                    break;
                }
            }
            return idx;
        }
    }

})();