
'use strict';

angular.module("snippetShare")
    .controller("BoardsEditController", function ($scope, Board, User, $routeParams, $location) {

        //request GET the current board from server
        Board.find($routeParams.id)
            .success(function (data) {
                console.log("get board "+ $routeParams.id+ " success");
                console.log(data);
                $scope.board = data;
            });

        //find all user for choose member list
        //User.all()
        //    .success(function (data) {
        //        console.log("get users success");
        //        console.log(data);
        //        $scope.users = data;
        //    });

        $scope.isSubmitting = false;

        $scope.updateBoard = function (board) {
            $scope.isSubmitting = true;

            Board.update(board, $routeParams.id)
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
                    $location.path("/boards/" + $routeParams.id);
                });
        };


    });
