(function() {
    'use strict';

    function config($locationProvider, $stateProvider, $urlRouterProvider) {
        // Route config
        $locationProvider.html5Mode(true).hashPrefix('!');
        $urlRouterProvider.otherwise('/');

        // States
        $stateProvider
        // Home
        .state('home', {
            url: '/',
            views: {
                'content@': {
                    controller: 'HomeController',
                    controllerAs: 'vm',
                    template: 'Hello world'
                }
            },
            data: {
                pageTitle: 'Home'
            }
        })
            .state('login', {
                url: '/login',
                views: {
                    'content@': {
                        controller: 'LoginController',
                        controllerAs: 'vm',
                        templateUrl: 'login/login.tpl.html'
                    }
                },
                data: {
                    pageTitle: 'Login'
                }
            })
            .state('dashboard', {
                url: '/dashboard',
                views: {
                    'content@': {
                        controller: 'DashboardController',
                        controllerAs: 'vm',
                        templateUrl: 'dashboard/dashboard.tpl.html'
                    }
                },
                data: {
                    pageTitle: 'Dashboard'
                }
            });
    }

    function run() {}

    function AppController() {}

    angular
        .module('app', [
            'app.home',
            'app.login',
            'app.dashboard',
            'AuthService',
            'UserService',
            'templates-app',
            'ui.router'
        ])
        .config(config) // 1st
    .run(run) // 2nd
    .controller('AppController', AppController); // 3rd

}());