'use strict';

angular.module("snippetShare")
    .controller("SnippetShowController", function ($scope, Snippet, $routeParams) {

        $scope.snippet = {
            sid: 1,
            title: "snippet 1",
            content: "<html></html>",
            url:"",
            language:"html",
            author: {
                userid: 4,
                username: "Jerry",
                userAvatarId: 6
            },
            createdAt: 1400956671914,
            updatedAt: "",
            numOfComments: 2,
            comments: [
                {
                    cid: 1,
                    content: "a useful snippet",
                    createdAt: 1400956671914,
                    author: {
                        userid: 1,
                        username: "xiaoli",
                        userAvatarId: 3
                    }

                },
                {
                    cid: 2,
                    content: "a useful snippet!!!!",
                    createdAt: 1400956671914,
                    author: {
                        userid: 2,
                        username: "Alice",
                        userAvatarId: 4
                    }

                }
            ]
        };

        //request GET a board from server
        //Snippet.find($routeParams.id)
        //    .success(function (data) {
        //        console.log("get Snippet "+ $routeParams.id+ " success");
        //        console.log(data);
        //        $scope.snippet = data;
        //    });

    });

