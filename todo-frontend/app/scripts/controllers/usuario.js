'use strict';

/**
 * @ngdoc function
 * @name todoFrontendApp.controller:UsuarioCtrl
 * @description
 * # UsuarioCtrl
 * Controller of the todoFrontendApp
 */
angular.module('todoFrontendApp')
  .controller('UsuarioCtrl', ['$scope', '$location', 'USER_ROLES', 'usuarioFactory',
    function ($scope, $location, USER_ROLES, usuarioFactory) {

    $scope.validacaoOk = true;
    $scope.validacaoMensagem = '';

    var usuarioDefault = {
      "nome":'',
      "email":'',
      "senha":''
    };

    $scope.salvarUsuario=function(){

      $scope.validacaoOk = false;
      $scope.validacaoMensagem = '';
      if($scope.usuario.senha !== $scope.usuario.senhaConfirmacao){
        $scope.validacaoOk = true;
        $scope.validacaoMensagem = 'As senhas estão diferentes!';
        return;
      }

      var u = usuarioDefault;
      u.nome = $scope.usuario.nome;
      u.email = $scope.usuario.email;
      u.senha = $scope.usuario.senha;

      usuarioFactory.inserirUsuario(u)
        .then(function (response) {
          if(response.data.codigo == 1){
            $location.path("/login", USER_ROLES);
          }
      })

    }

  }]);
