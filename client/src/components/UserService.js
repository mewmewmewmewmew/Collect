(function() {
	'use strict';

	UserService.$inject = ['$http'];

	function UserService($http) {
		var User = {
			get: get,
			getPosts: getPosts,
			getAllUsers: getAllUsers
		};

		function get() {
			console.log('This is the cookie', document.cookie);
			return $http({
				url: '/api/user/get-user',
				method: 'GET',
				query: {
					// id: $cookies.get('userId')
				},
				params: {
					// id: $cookies.get('userId')
				}
			});
		}

		function getPosts() {
			console.log('Yo');
		}

		function getAllUsers() {
			return $http({
				url: '/api/user/get-all-users',
				method: 'GET'
			});
		}

		return User;
	}

	angular
	    .module('UserService', [])
	    .factory('UserService', UserService);
})();


