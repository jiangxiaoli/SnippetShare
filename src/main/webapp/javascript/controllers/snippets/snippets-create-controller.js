'use strict';

angular.module("snippetShare")
    .controller("SnippetsCreateController", function ($scope, Snippet, $location, $routeParams) {
        $scope.isSubmitting = false;
        
        $scope.saveSnippet = function (snippet) {
            $scope.isSubmitting = true;

            console.log(snippet);

            Snippet.create($routeParams.bid, snippet)
                .success(function(data, status, headers, config) {
                    console.log("in create snippet success");
                    console.log(data);
                    console.log(status);
                    $location.path("/boards/"+$routeParams.bid+"/snippets/"+data.sid);
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
