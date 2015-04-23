(function() {
    'use strict';

    DashboardController.$inject = ['$http', 'UserService', '$state'];

    function DashboardController($http, UserService, $state) {
    	var vm = this;
    	vm.data = {};
    	vm.data.user = {};

    	UserService.get()
    		.then(function(resp) {
    			vm.data.user = resp.data.user;
    		});

    	vm.logout = function() {
    		$state.go('login');
    	};

		vm.data.posts = [{
			date: new Date().new,
			description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer velit lacus, vehicula id pharetra et, laoreet vel arcu. Curabitur eleifend gravida laoreet.',
			title: 'Lorem Ipsum',
			price: '$' + Math.floor(Math.random() * 100) + 1,
			user: 'Andrea'
		},
		{
			date: new Date().new,
			description: 'am sit amet elementum nunc. Suspendisse potenti. Nulla consequat risus dignissim mauris dictum, et molestie eros euismod.',
			title: 'Lorem Ipsum',
			price: '$' + Math.floor(Math.random() * 100) + 1,
			user: 'Ryan'
		},
		{
			date: new Date().new,
			description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer velit lacus, vehicula id pharetra et, laoreet vel arcu. Curabitur eleifend gravida laoreet.',
			title: 'Lorem Ipsum',
			price: '$' + Math.floor(Math.random() * 100) + 1,
			user: 'Meme'
		},
		{
			date: new Date().new,
			description: 'm vel. Sed vestibulum a nulla id bibendum. Aenean efficitur rutrum ullamcorper. Donec vel suscipit ex. Morbi vel ornare nunc. Sed',
			title: 'Lorem Ipsum',
			price: '$' + Math.floor(Math.random() * 100) + 1,
			user: 'Jonathan'
		},
		{
			date: new Date().new,
			description: 'Aliquam tellus turpis, vulputate nec sodales in, dignissim sit amet nulla. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae',
			title: 'Lorem Ipsum',
			price: '$' + Math.floor(Math.random() * 100) + 1,
			user: 'Ryan'
		},
		{
			date: new Date().new,
			description: 'Morbi commodo et libero eu commodo. Sed pretium risus non porta blandit. Aliquam quis nisl rutrum, dapibus magna ut, congue quam.',
			title: 'Lorem Ipsum',
			price: '$' + Math.floor(Math.random() * 100) + 1,
			user: 'Andrea'
		},
		{
			date: new Date().new,
			description: 'Proin nulla nisi, vestibulum consectetur turpis a, consequat convallis arcu. Ut viverra ligula leo, vitae fringilla lacus aliquam eu',
			title: 'Lorem Ipsum',
			price: '$' + Math.floor(Math.random() * 100) + 1,
			user: 'Meme'
		},
		{
			date: new Date().new,
			description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer velit lacus, vehicula id pharetra e',
			title: 'Lorem Ipsum',
			price: '$' + Math.floor(Math.random() * 100) + 1,
			user: 'Meme'
		}];
    }

    angular
        .module('app.dashboard', [])
        .controller('DashboardController', DashboardController);

})();