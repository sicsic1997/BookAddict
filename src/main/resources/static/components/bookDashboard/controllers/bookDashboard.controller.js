(function() {

    'use strict';

    angular
        .module("bookDashboard")
        .controller("BookDashboardController", Controller);

    Controller.$inject = [
        '$scope',
        '$state',
        'CoreApiService',
        'BookSearchData'
    ];

    function Controller($scope,
                        $state,
                        CoreApiService,
                        BookSearchData) {

        var vm = this;

        vm.bookSearchData = BookSearchData;

        vm.priceSlider = {
            options: {
                floor: 0,
                ceil: 150,
                translate: function(value) {
                    return value + ' RON' + '\n';
                },
                onChange: _loadBooks
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

        function toggleBookSelection(book) {
            book.isSelected = !book.isSelected;
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
    }

})();