"use strict";

angular.module("snippetShare").controller("BoardAccessRequestCtrl", function ($scope, $modalInstance, board, user) {

    console.log("board access request modal is open. board/user", board, user);

    $scope.ok = function () {
        $modalInstance.close(true);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss("cancel");
    };
});