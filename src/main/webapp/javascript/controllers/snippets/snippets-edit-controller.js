'use strict';

angular.module("snippetShare")
    .controller("SnippetsEditController", function ($scope, Snippet, $location, $routeParams, User) {
        $scope.isSubmitting = false;

        //request GET the current board from server
        Snippet.find($routeParams.bid, $routeParams.id)
            .success(function (data) {
                console.log("get snippet "+ $routeParams.id+ " success");
                console.log(data);
                $scope.snippet = data;

                if(!Sinppet.isWritableTo($scope.snippet, User.currentUser)) {
                    alert("You are trying to edit a snippet that is not accessible to you!");
                    $location.url("/users/"+data.author.userid +"/boards/" + data.board.bid);
                }

            });

        $scope.updateSnippet = function (snippet) {
            $scope.isSubmitting = true;
            snippet.userid = snippet.userid || User.currentUser.userid;
            console.log(snippet);

            Snippet.update($routeParams.bid, $routeParams.id, snippet)
                .success(function(data, status, headers, config) {
                    console.log("update snippet success");
                    console.log(data);
                    console.log(status);
                    $location.path("/boards/"+$routeParams.bid+"/snippets/"+$routeParams.id);
                })
                .error(function(data, status, headers, config) {
                    console.log("in create snippet error");
                    console.log(data);
                    console.log(status);
                }).finally(function () {
                    $scope.isSubmitting = false;
                });

        }
    });

