'use strict';

angular.module("snippetShare")
    .controller("BoardsCreateController", function ($scope, Board, $location) {
        $scope.isSubmitting = false;

        $scope.saveBoard = function (board) {
            $scope.isSubmitting = true;

            Board.create(board)
                .success(function(data, status, headers, config) {
                    console.log("in create board success");
                    console.log(data);
                    console.log(status);
                    $location.path("/boards");
                })
                .error(function(data, status, headers, config) {
                    console.log("in create board error");
                    console.log(data);
                    console.log(status);
                }).finally(function () {
                    $scope.isSubmitting = false;
                });

        }
    });