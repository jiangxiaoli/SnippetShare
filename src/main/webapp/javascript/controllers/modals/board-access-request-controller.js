"use strict";

angular.module("snippetShare").controller("BoardAccessRequestCtrl", function ($scope, $modalInstance, board, user) {

    console.log("board access request modal is open. board/user", board, user);
    $scope.user = user;
    $scope.board = board;

    $scope.ok = function () {
        $modalInstance.close(true);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss("cancel");
    };
});