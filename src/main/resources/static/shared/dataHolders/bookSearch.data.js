(function() {

    'use strict';

    angular
        .module('bookAddict')
        .service('BookSearchData', function() {

            this.categories = [];

            this.typeheadSelection = '';
            this.typheadOptions = [];

            this.minPrice = 0;
            this.maxPrice = 0;

            this.orderSelection = {};
            this.orderOptions = [];

            this.getDTO = function() {
                var bookDTO = {
                    minPrice: this.minPrice,
                    maxPrice: this.maxPrice,
                    bookOrAuthorName: this.typeheadSelection.text,
                    categoryDTOList: this.categories,
                    field: this.orderSelection.id
                };

                return bookDTO;
            };

        });

})();