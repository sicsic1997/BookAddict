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

        vm.typeheadOnSelect = typeheadOnSelect;
        vm.sortBooks = sortBooks;

        //////////

        _initCtrl();

        function typeheadOnSelect($item, $model) {
            console.log(vm.typheadSelection.name);
        }

        function sortBooks() {
            console.log(vm.orderSelection);
        }

        function _initCtrl() {
            vm.orderSelection = vm.orderOptions[0];
        }

        $scope.state = 'CA';

    }

})();