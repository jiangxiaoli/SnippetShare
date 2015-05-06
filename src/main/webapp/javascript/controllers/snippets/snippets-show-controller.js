'use strict';

angular.module("snippetShare")
    .controller("SnippetsShowController", function ($scope, Snippet, $routeParams, $location, User, $modal, Comment) {

        //request GET a board from server
        Snippet.find($routeParams.bid,$routeParams.id)
            .success(function (data) {
                console.log("get Snippet "+ $routeParams.id+ " success");
                console.log(data);
                $scope.snippet = data;

                if(!Snippet.isReadableTo($scope.snippet, User.currentUser)) {
                    alert("You are trying to read a snippet that is not accessible to you!");
                    $location.url("/users/"+data.author.userid +"/boards/" + data.board.bid);
                }
            });



        $scope.User = User;
        $scope.Snippet = Snippet;
        $scope.Comment = Comment;
        $scope.isDeleting = false;

        $scope.onClickDeleteSnippet = function() {
            var modalInstance = $modal.open({
                templateUrl: 'templates/pages/modals/board-delete-conf-modal.html',
                controller: 'DeleteBoardConfModalCtrl',
                resolve: {
                    entity: function () {
                        return $scope.snippet;
                    },

                    entityName: function () {
                        return "code snippet";
                    }
                }
            });

            modalInstance.result.then(function(confirm) {
                if(confirm) {
                    $scope.deleteSnippet();
                }
            });
        };

        $scope.deleteSnippet = function () {
            $scope.isDeleting = true;

            Snippet.remove($routeParams.bid,$routeParams.id)
                .success(function (data) {
                    $scope.isDeleting = false;
                    console.log("remove Snippet "+ $routeParams.id+ " success");
                    console.log(data);

                    $location.url("/users/"+data.author.userid +"/boards/" + data.board.bid);

                });
        }

        $scope.onClickAddComment = function() {
            var modalInstance = $modal.open({
                templateUrl: 'templates/pages/modals/comment-edit-modal.html',
                controller: 'CommentEditCtrl',
                resolve: {
                    comment: function () {
                        return null;
                    }
                }
            });

            modalInstance.result.then(function(newComment) {
                newComment.userid = User.currentUser.userid;
                Comment.create($scope.snippet.sid,newComment).then(function(res) {
                    $scope.snippet.comments.push(res.data);
                })
            });
        };

        $scope.onClickDeleteComment = function(comment) {
            var modalInstance = $modal.open({
                templateUrl: 'templates/pages/modals/board-delete-conf-modal.html',
                controller: 'DeleteBoardConfModalCtrl',
                resolve: {
                    entity: function () {
                        return comment;
                    },

                    entityName: function () {
                        return "comment";
                    }
                }
            });

            modalInstance.result.then(function(confirm) {
                if(confirm) {
                    Comment.remove($scope.snippet.sid,comment.cid).then(function() {
                        $scope.snippet.comments = _.reject($scope.snippet.comments, function(c){
                            return c.cid === comment.cid;
                        });
                    })
                }
            });
        };

    });