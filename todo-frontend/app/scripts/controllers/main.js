'use strict';

/**
 * @ngdoc function
 * @name todoFrontendApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the todoFrontendApp
 */
angular.module('todoFrontendApp')
  .controller('MainCtrl', ['$scope', '$location', '$anchorScroll', '$routeParams', 'tarefaFactory', 'Session',
    function ($scope, $location, $anchorScroll, $routeParams, tarefaFactory, Session) {

    $scope.indexTarefa = -1;
    $scope.tarefas = [];

    var tarefaDefault = {
      "id":0,
      "idUsuario":0,
      "descricao":'',
      "concluido":0
    };

    $scope.adicionarTarefa=function(){
      var t = tarefaDefault;
      t.idUsuario = Session.get().id;
      t.descricao = $scope.tarefa;
      tarefaFactory.inserirTarefa(t);
    }

    $scope.editarTarefa=function(index){
      if($scope.indexTarefa >= 0 && index < 0){
        $scope.tarefas[$scope.indexTarefa].descricao = $scope.tarefa;
        tarefaFactory.editarTarefa($scope.tarefas[$scope.indexTarefa]);
        $scope.indexTarefa = -1;
      }else{
        $scope.indexTarefa = index;
        $scope.tarefa = $scope.tarefas[$scope.indexTarefa].descricao;
        $scope.gotoBottom();
      }
      console.log($scope.indexTarefa);
    }

    $scope.concluirTarefa=function(index){
      $scope.tarefas[index].concluido = 1;
      tarefaFactory.editarTarefa($scope.tarefas[index]);
      tarefaFactory.obterTarefas();
    }

    $scope.salvarTarefa=function(){
      console.log($scope.indexTarefa);
      if($scope.indexTarefa >= 0){
        $scope.editarTarefa(-1);
      }else{
        $scope.adicionarTarefa();
      }

      $scope.obterTarefas();
      $scope.tarefa = undefined;
    }

    $scope.deletarTarefa=function(index){
      tarefaFactory.deletarTarefa($scope.tarefas[index].id);
      tarefaFactory.obterTarefas();
    }

    $scope.obterTarefas=function(){

      console.log($routeParams.status);

      var status = 0;
      if($routeParams.status == 'pendente'){
        status = 0;
      }else if($routeParams.status == 'concluido'){
        status = 1;
      }else{
        status = -1;
      }

      tarefaFactory.obterTarefas(Session.get().id, status)
      .then(function (response) {
          //$scope.status = 'Retrieved orders!';
          $scope.tarefas = response.data;
      }, function (error) {
          //$scope.status  = 'Error retrieving customers! ' + error.message;
      });

    }

    $scope.gotoBottom = function() {
      // set the location.hash to the id of
      // the element you wish to scroll to.
      $location.hash('bottom');

      // call $anchorScroll()
      $anchorScroll();
    };

    var init = function(){
      $scope.obterTarefas();
    }
    init();

  }]);
