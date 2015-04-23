(function() {
	'use strict';

	AuthService.$inject = ['$http'];

	function AuthService($http) {
		var base = '/api/user/';

		var Auth = {
			login: login
		};

		function login(email, password) {
			return $http({
				url: base + 'login',
				dataType: 'json',
				method: 'POST',
				data: {
					email: email,
					password: password
				}
			});
		}

		return Auth;
	}

	angular
	    .module('AuthService', [])
	    .factory('AuthService', AuthService);
})();


