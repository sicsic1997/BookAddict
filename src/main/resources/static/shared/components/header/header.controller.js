(function() {

    'use strict';

    angular
        .module("bookAddict")
        .controller("sharedHeaderController", Controller);

    Controller.$inject = [
        '$scope',
        '$state'
    ];

    function Controller($scope,
                        $state) {

        var vm = this;
    }

})();