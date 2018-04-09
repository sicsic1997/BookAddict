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

        vm.actionInProgress = false;
        vm.isLogin = $stateParams.isLogin === undefined || $stateParams.isLogin;
        vm.actionButtonText = vm.isLogin ? 'login' : 'register';
        vm.toggleInfoText = vm.isLogin ? 'register' : 'login';
        vm.isValidData = true;
        vm.actionTextError = "";

        vm.login = login;
        vm.register = register;
        vm.toggleAction = toggleAction;



        /////////

        (function() {
            AuthenticationService.clearCredentials();
        })();

        function login() {
            vm.isValidData = _checkValidData();
            if(!vm.isValidData || vm.actionInProgress) {
                return false;
            }

            vm.actionInProgress = true;
            AuthenticationService.authenticate(vm.userName, vm.password)
                .then(function (response) {
                    vm.actionInProgress = false;
                    AuthenticationService.setCredentials(vm.userName, vm.password);
                    $state.go('viewBookDashboard');
                }, function(response) {
                    vm.actionInProgress = false;
                    vm.actionTextError = "wrong user or password";
                    vm.isValidData = false;
                });
        }

        function register() {
            vm.isValidData = _checkValidData();

            if(!vm.isValidData || vm.actionInProgress) {
                return false;
            }

            vm.actionInProgress = true;
            var registerData = {
                userName: vm.userName,
                password: vm.password,
                firstName: vm.firstName,
                lastName: vm.lastName
                
            };
            AuthenticationService.registerUser(registerData)
                .then(function (response) {
                    vm.actionInProgress = false;
                    vm.toggleAction();
                }, function(response) {
                    vm.actionInProgress = false;
                    vm.actionTextError = "user already exists";
                    vm.isValidData = false;
                });
        }

        function toggleAction() {
            $state.go('login', {isLogin: !vm.isLogin}, {reload: true});
        }

        function _checkValidData() {
            var isValid = true;

            vm.actionTextError = "empty fields";

            if(vm.userName === "" || vm.userName === undefined ||
               vm.password === "" || vm.password === undefined) {
                isValid = false;
            }
            if(vm.isLogin) {
                return isValid;
            }
            if(vm.firstName === "" || vm.firstName === undefined ||
                vm.lastName === "" || vm.lastName === undefined) {
                isValid = false;
            }
            return isValid;
        }

    }

})();