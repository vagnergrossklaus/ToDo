'use strict';

/**
 * @ngdoc function
 * @name todoFrontendApp.controller:LoginController
 * @description
 * # LoginController
 * Controller of the todoFrontendApp
 */
angular.module('todoFrontendApp')

  .controller('LoginController', ['$scope', '$rootScope', '$location', 'AUTH_EVENTS', 'USER_ROLES', 'AuthService',
    function ($scope, $rootScope, $location, AUTH_EVENTS, USER_ROLES, AuthService) {
    $scope.credentials = {
      username: '',
      password: ''
    };

    $scope.login = function (credentials) {
      AuthService.login(credentials).then(
        function (user) {
          $rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
          $scope.setCurrentUser(user);
          $location.path("/", USER_ROLES);
        },
        function () {
          $rootScope.$broadcast(AUTH_EVENTS.loginFailed);
        }
      );
    };

    var init = function(){
      AuthService.logout();
      $scope.setCurrentUser(undefined);
    }

    init();

  }])
