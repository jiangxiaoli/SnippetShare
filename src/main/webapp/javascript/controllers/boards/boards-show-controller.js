'use strict';

angular.module("snippetShare")
    .controller("BoardsShowController", function ($scope, Board, $routeParams, $modal, $location,User) {

        $scope.isDeleting = false;
        //request GET a board from server
        Board.find($routeParams.userid, $routeParams.bid)
            .success(function (data) {
                console.log("get board "+ $routeParams.bid+ " success");
                console.log(data);
                $scope.board = data;
            });

        $scope.Board = Board;
        $scope.User = User;

        $scope.onClickHandleRequests = function() {
            console.log("click handle request. $scope.board:", $scope.board);
            $modal.open({
                templateUrl: 'templates/pages/modals/show-requests-modal.html',
                controller: 'ShowRequestsModalCtrl',
                resolve: {
                    board: function() {
                        return $scope.board;
                    },
                    requestors: function() {
                        return $scope.board.requestors;
                    }
                }
            });
        }

        $scope.onClickShowMembers = function() {
            console.log("click show members. $scope.board:", $scope.board);
            $modal.open({
                templateUrl: 'templates/pages/modals/show-members-modal.html',
                controller: 'ShowMembersModalCtrl',
                resolve: {
                    board: function() {
                        return $scope.board;
                    },
                    members: function() {
                        return $scope.board.members;
                    }
                }
            });
        }

        $scope.onClickDeleteBoard = function() {
            var modalInstance = $modal.open({
                templateUrl: 'templates/pages/modals/board-delete-conf-modal.html',
                controller: 'DeleteBoardConfModalCtrl',
                resolve: {
                    board: function() {
                        return $scope.board;
                    }
                }
            });

            modalInstance.result.then(function(confirm) {
                if(confirm) {
                    $scope.deleteBoard();
                }
            })
        }

        $scope.deleteBoard = function(){
            $scope.isDeleting = true;
            Board.remove($routeParams.userid, $routeParams.bid)
                .success(function (data) {
                    console.log("delete board "+ $routeParams.bid+ " success");
                    console.log(data);
                    $scope.isDeleting = false;
                    $scope.board = data;
                    $location.path("/users/"+$routeParams.userid);
                });
        };




    });

