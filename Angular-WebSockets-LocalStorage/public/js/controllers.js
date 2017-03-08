'use strict';

/* Controllers */

angular.module('myApp.controllers', ['ngStorage']).
  controller('AppCtrl', function ($scope, $localStorage) {
    //Valeur par défaut
     $scope.$storage = $localStorage.$default({
          name: 'Simple visiteur'
     });
     //Associe le nom au local storage
    $scope.name = $localStorage.name;

     /*À la soumission du formulaire, enregistre dans le local storage
        et rafraîchit le scope parent */
     $scope.renommer = function($scope) {
        $localStorage.name = this.name;
        this.$parent.name = this.name;
      };
  }).
  controller('NomCtrl', function ($scope) {
    //Rien
  }).
  controller('MyCtrl1', function ($scope, socket) {
    socket.on('send:time', function (data) {
      $scope.time = data.time;
    });
  }).
  controller('MyCtrl2', function ($scope) {
    //Rien
  }).
  controller('MyCtrl3', function ($scope, socket) {
    socket.on('send:message', function (data) {
      $scope.message = data.message;
    });
  });