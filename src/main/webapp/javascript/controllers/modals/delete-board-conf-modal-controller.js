"use strict";

angular.module("snippetShare").controller("DeleteBoardConfModalCtrl", function ($scope, $modalInstance, entity,entityName) {

    console.log("board delete conf modal is open. board:", entity);;
    $scope.entity = entity;
    $scope.entityName = entityName;

    $scope.confirm = function () {
        $modalInstance.close(true);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss("cancel");
    };
});