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

        vm.priceSlider = {
            minValue: 30,
            maxValue: 90,
            options: {
                floor: 0,
                ceil: 150,
                translate: function(value) {
                    return value + ' RON' + '\n';
                }
            }
        };

        vm.typheadOptions = [
            {name: "Absalom, Absalom!", type: 'Book'},
            {name: "An Evil Cradling", type: 'Book'},
            {name: "Far From the Madding Crowd", type: 'Book'},
            {name: "In Death Ground", type: 'Book'},
            {name: "The Little Foxes", type: 'Book'},
            {name: "Mother Night", type: 'Book'},
            {name: "Of Human Bondage", type: 'Book'},
            {name: "A Scanner Darkly", type: 'Book'},
            {name: "Thrones, Dominations", type: 'Book'},
            {name: "The Wealth of Nations", type: 'Book'},
            {name: "The Yellow Meads of Asphodel", type: 'Book'}
        ];

        vm.orderOptions = [
            {id: 0, description: 'Newest Arrivals'},
            {id: 1,description: 'Relevance'},
            {id: 2,description: 'Price Low to High'},
            {id: 3,description: 'Price High to Low'}
        ];

        vm.categories = [
            {id: 0,description: 'Adventure', isSelected: false},
            {id: 1,description: 'Cooking', isSelected: false},
            {id: 2,description: 'Crime', isSelected: false},
            {id: 11,description: 'Drama', isSelected: false},
            {id: 3,description: 'Educational', isSelected: false},
            {id: 10,description: 'Fantasy', isSelected: false},
            {id: 4,description: 'History', isSelected: false},
            {id: 5,description: 'Mystery', isSelected: false},
            {id: 6,description: 'Religion', isSelected: false},
            {id: 7,description: 'Romance', isSelected: false},
            {id: 8,description: 'Sports', isSelected: false},
            {id: 9,description: 'Thriller', isSelected: false},
            {id: 9,description: 'Travel', isSelected: false}
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

        //////////

        _initCtrl();

        function typeheadOnSelect($item, $model) {
            console.log(vm.typheadSelection.name);
        }

        function sortBooks() {
            console.log(vm.orderSelection);
        }

        function toggleCheckbox(element) {
            element.isSelected = !element.isSelected;
            console.log(vm.categories);
        }

        function _initCtrl() {
            vm.orderSelection = vm.orderOptions[0];
        }

        function toggleBookSelection(book) {
            book.isSelected = !book.isSelected;
            console.log(vm.books);
        }

        $scope.state = 'CA';

    }

})();