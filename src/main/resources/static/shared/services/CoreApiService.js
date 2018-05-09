(function () {

    'use strict';

    angular
        .module('bookAddict')
        .factory('CoreApiService', Factory);

    Factory.$inject = ['$http', '$q'];

    function Factory($http, $q) {

        var baseUrl = "http://localhost:8080/bookAddict/";

        return {
            getCategories: function () {
                return $http.get(baseUrl + 'bookDashboard/getAllCategories', null);
            }
        };
    }
})();