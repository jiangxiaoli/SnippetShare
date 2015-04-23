'use strict';

angular.module("snippetShare")
    .controller("BoardsCreateController", function ($scope, Board, $location) {
        $scope.isSubmitting = false;

        $scope.categories = [' ', 'cat1', 'cat2', 'cat3', 'cat4'];

        $scope.saveBoard = function (board) {
            $scope.isSubmitting = true;

            if(!board.isPublic) {
                board.isPublic = false;
            }

            console.log(board);

            //Board.create(board)
            //    .success(function(data, status, headers, config) {
            //        console.log("in create board success");
            //        console.log(data);
            //        console.log(status);
            //        $location.path("/boards");
            //    })
            //    .error(function(data, status, headers, config) {
            //        console.log("in create board error");
            //        console.log(data);
            //        console.log(status);
            //    }).finally(function () {
            //        $scope.isSubmitting = false;
            //    });

        }
    });