(function() {

    'use strict';

    angular
        .module("bookAddict")
        .controller("BookAddictLoginController", Controller);

    Controller.$inject = [
        '$scope',
        '$state',
        '$stateParams',
        'AuthenticationService'
    ];

    function Controller($scope,
                        $state,
                        $stateParams,
                        AuthenticationService) {

        var vm = this;

        vm.loginInProgress = false;
        vm.isLogin = $stateParams.isLogin === undefined || $stateParams.isLogin;
        vm.actionButtonText = vm.isLogin ? 'login' : 'register';
        vm.toggleInfoText = vm.isLogin ? 'register' : 'login';

        vm.username = "sebastian";
        vm.password = "siebi";

        vm.login = login;
        vm.register = register;
        vm.toggleAction = toggleAction;

        /////////

        (function() {
            AuthenticationService.clearCredentials();
        })();

        function login() {
            vm.loginInProgress = true;
            AuthenticationService.authenticate(vm.username, vm.password)
                .then(function (response) {
                    vm.loginInProgress = false;
                    AuthenticationService.setCredentials(vm.username, vm.password);
                    $state.go('viewBookDashboard');
                }, function(response) {
                    vm.loginInProgress = false;
                });
        }

        function register() {
        }

        function toggleAction() {
            $state.go('login', {isLogin: !vm.isLogin}, {reload: true});
        }

    }

})();