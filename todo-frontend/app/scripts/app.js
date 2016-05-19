'use strict';

/**
 * @ngdoc overview
 * @name todoFrontendApp
 * @description
 * # todoFrontendApp
 *
 * Main module of the application.
 */
angular
  .module('todoFrontendApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ])
  .config(function ($routeProvider, USER_ROLES) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main',
        data: {
          authorizedRoles: [USER_ROLES.admin, USER_ROLES.editor]
        }
      })
      .when('/status/:status', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main',
        data: {
          authorizedRoles: [USER_ROLES.admin, USER_ROLES.editor]
        }
      })
      .when('/status/:status', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main',
        data: {
          authorizedRoles: [USER_ROLES.admin, USER_ROLES.editor]
        }
      })
      .when('/usuario', {
        templateUrl: 'views/usuario.html',
        controller: 'UsuarioCtrl',
        controllerAs: 'usuario'
      })
      .when('/login', {
        templateUrl: 'views/login.html',
        controller: 'LoginController',
        controllerAs: 'login'
      })
      .otherwise({
        redirectTo: '/'
      });
  })
  .run(function ($rootScope, $location, AuthService, AUTH_EVENTS) {
    $rootScope.$on('$routeChangeStart', function (event, next) {
      var authorizedRoles = next.data.authorizedRoles;
      if (!AuthService.isAuthorized(authorizedRoles)) {
        event.preventDefault();
        if (AuthService.isAuthenticated()) {
          // user is not allowed
          $rootScope.$broadcast(AUTH_EVENTS.notAuthorized);
        } else {
          // user is not logged in
          $rootScope.$broadcast(AUTH_EVENTS.notAuthenticated);
          $location.path("/login", authorizedRoles);
        }
      }
    });
  })
  .controller('ApplicationController', function ($scope,
                                               USER_ROLES,
                                               AuthService, Session) {
    $scope.currentUser = null;
    $scope.userRoles = USER_ROLES;
    $scope.isAuthorized = AuthService.isAuthorized;

    $scope.setCurrentUser = function (user) {
      $scope.currentUser = user;
    };

    var init = function(Session){
      Session.destroy();
    }

    init( Session );

  });
