"use strict";

angular.module("snippetShare").controller("ShowRequestsModalCtrl", function ($scope, $modalInstance,Board,board,requestors) {

    console.log("ShowRequestsModal is open. board/requestors", board, requestors);
    $scope.requestors = requestors;

    $scope.approveRequest = function(requestor) {
        Board.approveRequest(board, requestor)
            .then(function(updatedBoard) {
                board = updatedBoard;
                $scope.requestors = board.requestors;
            });
    }

    $scope.denyRequest = function(requestor) {
        Board.denyRequest(board, requestor)
            .then(function(updatedBoard) {
                board = updatedBoard;
                $scope.requestors = board.requestors;
            });
    }

    $scope.done = function () {
        $modalInstance.dismiss("done");
    };

    $scope.cancel = function () {
        $modalInstance.dismiss("cancel");
    };
});