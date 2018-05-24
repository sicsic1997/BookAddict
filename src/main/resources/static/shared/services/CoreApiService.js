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
            },
            getMaxPrice: function() {
                return $http.get(baseUrl + 'bookDashboard/getMaxBookPrice', null);
            },
            getBooksByFilter: function(data) {
                return $http.post(baseUrl + 'bookDashboard/filter', data);
            },
            getTextFilterEntries: function() {
                return $http.get(baseUrl + 'bookDashboard/getAllEntriesForTextFilter');
            },
            makeNewBooking: function(data) {
                return $http.post(baseUrl + 'bookings/saveBooking?userId=1&description="This is my first booking', data);
            }
        };
    }
})();