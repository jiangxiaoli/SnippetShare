'use strict';

angular.module("snippetShare")
    .controller("SnippetsShowController", function ($scope, Snippet, $routeParams, $location) {

        //request GET a board from server
        Snippet.find($routeParams.bid,$routeParams.id)
            .success(function (data) {
                console.log("get Snippet "+ $routeParams.id+ " success");
                console.log(data);
                $scope.snippet = data;
            });

        $scope.isDeleting = false;

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

    });

