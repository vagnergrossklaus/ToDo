'use strict';

/**
 * @ngdoc function
 * @name todoFrontendApp.controller:AuthService
 * @description
 * # AuthService
 * Controller of the todoFrontendApp
 */
angular.module('todoFrontendApp')

  .constant('AUTH_EVENTS', {
    loginSuccess: 'auth-login-success',
    loginFailed: 'auth-login-failed',
    logoutSuccess: 'auth-logout-success',
    sessionTimeout: 'auth-session-timeout',
    notAuthenticated: 'auth-not-authenticated',
    notAuthorized: 'auth-not-authorized'
  })
  .constant('USER_ROLES', {
    all: '*',
    admin: 'admin',
    editor: 'editor',
    guest: 'guest'
  })
  .factory('AuthService', function ($http, Session) {

    var urlBase = 'http://127.0.0.1:8080/todo-api/rest/access';
    var authService = {};

    authService.login = function (credentials) {
      return $http
        .post(urlBase + '/login', credentials)
        .then(function (res) {
          Session.create(res.data);
          return res.data;
        });
    };

    authService.isAuthenticated = function () {
      return !!Session.get().id;
    };

    authService.isAuthorized = function (authorizedRoles) {
      if (!angular.isArray(authorizedRoles)) {
        authorizedRoles = [authorizedRoles];
      }
      return (authService.isAuthenticated() && authorizedRoles.indexOf(Session.get().role) !== -1);
    };

    authService.logout = function(){
      Session.destroy();
      var u = { 'id': undefined,
                'user': undefined,
                'role': undefined,
                'name':undefined}
      return u;
    };

    return authService;
  })
  .service('Session', ['$cookieStore', 'localStorageService', function ($cookieStore, localStorageService) {
    this.create = function (user) {
      localStorageService.set("user", user);
    };
    this.get = function(){
      return localStorageService.get("user");
    };
    this.destroy = function () {
      localStorageService.remove("user");
    };

  }])
