angular.module('todoFrontendApp')
    .factory('usuarioFactory', ['$http', function($http) {

    var urlBase = 'http://localhost:8080/todo-api/rest/usuario';
    var usuarioFactory = {};

    usuarioFactory.getUsuarios = function () {
        return $http.get(urlBase);
    };

    usuarioFactory.getUsuario = function (id) {
        return $http.get(urlBase + '/' + id);
    };

    usuarioFactory.obterUsuarios = function () {
        return $http.get(urlBase);
    };

    usuarioFactory.inserirUsuario = function (usuario) {
        return $http.post(urlBase, usuario);
    };

    usuarioFactory.editarUsuario = function (usuario) {
        return $http.put(urlBase + '/' + usuario.id, usuario)
    };

    usuarioFactory.deletarUsuario = function (id) {
        return $http.delete(urlBase + '/' + id);
    };

    return usuarioFactory;

}]);
