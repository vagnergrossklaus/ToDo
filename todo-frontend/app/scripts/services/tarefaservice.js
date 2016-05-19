angular.module('todoFrontendApp')
    .factory('tarefaFactory', ['$http', function($http) {

    var urlBase = 'http://127.0.0.1:8080/todo-api/rest/tarefa';
    var tarefaFactory = {};

    tarefaFactory.obterTarefas = function (id, status) {
        return $http.get(urlBase + '/' + id + '/' + status);
    };

    tarefaFactory.inserirTarefa = function (tarefa) {
        return $http.post(urlBase, tarefa);
    };

    tarefaFactory.editarTarefa = function (tarefa) {
        return $http.put(urlBase + '/' + tarefa.id, tarefa)
    };

    tarefaFactory.deletarTarefa = function (id) {
        return $http.delete(urlBase + '/' + id);
    };

    return tarefaFactory;
}]);
