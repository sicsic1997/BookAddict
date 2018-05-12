(function() {

    'use strict';

    angular
        .module("bookDashboard")
        .controller("BookDashboardController", Controller);

    Controller.$inject = [
        '$scope',
        '$state',
        'CoreApiService'
    ];

    function Controller($scope,
                        $state,
                        CoreApiService) {

        var vm = this;

        vm.priceSlider = {
            minValue: 0,
            maxValue: 0,
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
        vm.booksLoadingStatus = false;

        vm.categories = [];
        vm.typheadOptions = [];
        vm.typheadSelection = '';

        vm.orderOptions = [
            {id: 'ALPHABETICAL', description: 'Alphabetical Order'},
            {id: 'PRICE_LOW_TO_HIGH', description: 'Price Low to High'},
            {id: 'PRICE_HIGH_TO_LOW', description: 'Price High to Low'}
        ];

        vm.books = [
            {id: 1, category: ['Adventure', 'Fantasy'], author: 'Paulo Coelho', title: 'The Alchemist', isSelected: false, backgroundUrl: ""},
            {id: 2, category: ['Crime', 'Drama', 'Mystery'], author: 'Agatha Christie', title: 'Murder on the Orient Express', isSelected: false, backgroundUrl: ""},
            {id: 3, category: ['Adventure', 'Fantasy'], author: 'J. R. R. Tolkien', title: 'The Lord of the Rings', isSelected: false, backgroundUrl: ""},
            {id: 4, category: ['Crime', 'Mystery'], author: 'Michael Connelly', title: 'The Lincoln Lawyer', isSelected: false, backgroundUrl: ""},
            {id: 5, category: ['Fantasy', 'Thriller'], author: 'Joakim Zander', title: 'The Brother', isSelected: false, backgroundUrl: ""},
            {id: 6, category: ['Adventure', 'Fantasy'], author: 'Paulo Coelho', title: 'The Alchemist', isSelected: false, backgroundUrl: ""},
            {id: 7, category: ['Crime', 'Drama', 'Mystery'], author: 'Agatha Christie', title: 'Murder on the Orient Express', isSelected: false, backgroundUrl: ""},
            {id: 8, category: ['Adventure', 'Fantasy'], author: 'J. R. R. Tolkien', title: 'The Lord of the Rings', isSelected: false, backgroundUrl: ""},
            {id: 9, category: ['Crime', 'Mystery'], author: 'Michael Connelly', title: 'The Lincoln Lawyer', isSelected: false, backgroundUrl: ""},
            {id: 10, category: ['Fantasy', 'Thriller'], author: 'Joakim Zander', title: 'The Brother', isSelected: false, backgroundUrl: ""},
            {id: 11, category: ['Adventure', 'Fantasy'], author: 'Paulo Coelho', title: 'The Alchemist', isSelected: false, backgroundUrl: ""},
            {id: 12, category: ['Crime', 'Drama', 'Mystery'], author: 'Agatha Christie', title: 'Murder on the Orient Express', isSelected: false, backgroundUrl: ""},
            {id: 13, category: ['Adventure', 'Fantasy'], author: 'J. R. R. Tolkien', title: 'The Lord of the Rings', isSelected: false, backgroundUrl: ""},
            {id: 14, category: ['Crime', 'Mystery'], author: 'Michael Connelly', title: 'The Lincoln Lawyer', isSelected: false, backgroundUrl: ""},
            {id: 15, category: ['Fantasy', 'Thriller'], author: 'Joakim Zander', title: 'The Brother', isSelected: false, backgroundUrl: ""}
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
            vm.orderSelection = vm.orderOptions[0];


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
                    vm.categories = response.data;
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
                    vm.priceSlider.maxValue = vm.priceSlider.options.ceil;
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
            var data = {};

            data.minPrice = vm.priceSlider.minValue;
            data.maxPrice = vm.priceSlider.maxValue;
            data.bookOrAuthorName = vm.typheadSelection.text;
            data.categoryDTOList = _getSelectedCategories();
            data.field = vm.orderSelection.id;

            return data;
        }

        function _getSelectedCategories() {
            var selectedCategories = vm.categories.filter(function(category) {
                if(category.isSelected === true) {
                    return category.idCategory;
                }
            });

            return selectedCategories;
        }

        function getTypeheadOptions() {
            if(vm.typheadSelection === '') {
                _loadBooks();
                return;
            }
            CoreApiService
                .getTextFilterEntries(vm.typheadSelection)
                .then(function(response) {
                    vm.typheadOptions = response.data;
                }, function() {
                    console.log("failed to load options for text filter");
                });
        }
    }

})();