"use strict";

angular.module("snippetShare").controller("DeleteBoardConfModalCtrl", function ($scope, $modalInstance, board) {

    console.log("board delete conf modal is open. board:", board);;
    $scope.board = board;

    $scope.confirm = function () {
        $modalInstance.close(true);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss("cancel");
    };
});