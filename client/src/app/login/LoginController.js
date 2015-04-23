(function() {
    'use strict';

    LoginController.$inject = ['AuthService', '$state'];

    function LoginController(AuthService, $state) {
        var vm = this;

        vm.login = {
        	email: null,
        	password: null
        };
        vm.error = false;
        vm.success = false;
        vm.errorMessage = null;
        vm.successMessage = null;

        vm.submitLogin = submitLogin;
        vm.keypress = keypress;

        function submitLogin() {
            console.log('Submitting');
            AuthService.login(vm.login.email, vm.login.password)
                .then(function(resp) {
                    console.log('This is the resp', resp);
                    if (!resp.data.status) {
                        vm.success = false;
                        vm.error = true;
                        vm.errorMessage = 'Whoops! Try again!';
                    } else {
                        vm.success = true;
                        vm.error = false;
                        vm.successMessage = 'Success!';
                        $state.go('dashboard');
                    }
                });
        }

        function keypress() {
            vm.error = false;
        }

    }

    angular
        .module('app.login', [])
        .controller('LoginController', LoginController);
})();