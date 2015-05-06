
'use strict';

angular.module("snippetShare")
    .controller("BoardsEditController", function ($scope, Board, User, $routeParams, $location, categories) {

        //request GET the current board from server
        Board.find($routeParams.userid, $routeParams.bid)
            .success(function (data) {
                console.log("get board "+ $routeParams.bid+ " success");
                console.log(data);
                $scope.board = data;
            });

        $scope.Board = Board;
        $scope.User = User;
        $scope.categories = categories;

        $scope.isSubmitting = false;

        $scope.updateBoard = function (board) {
            $scope.isSubmitting = true;

            console.log(board);

            Board.update($routeParams.userid, $routeParams.bid, board)
                .success(function(data, status, headers, config) {
                    console.log("in update board success");
                    console.log(data);
                    console.log(status);
                })
                .error(function(data, status, headers, config) {
                    console.log("in board error");
                    console.log(data);
                    console.log(status);
                }).finally(function () {
                    $scope.isSubmitting = false;
                    $location.path("/users/"+$routeParams.userid+"/boards/" + $routeParams.bid);
                });
        };


    });
