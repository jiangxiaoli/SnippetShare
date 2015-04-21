'use strict';

angular.module("snippetShare")
    .controller("BoardsShowController", function ($scope, Board, $routeParams) {
        $scope.board = {
            bid: 2,
            title: "board 2",
            category: "cat2",
            isPublic: false,
            createdAt: "",
            updatedAt: "",
            description:"board for sharing",
            numOfSnippets: 3,
            owner:{
                userid: 1,
                username: "Xiaoli",
                userAvatarId: 10
            },
            members:[
                {
                    userid: 3,
                    username: "Tom",
                    userAvatarId: 5
                }
            ],
            requestors:[
                {
                    userid: 4,
                    username: "Jerry",
                    userAvatarId: 6
                }
            ],
            snippets:[
                {
                    sid: 1,
                    title: "snippet 1",
                    content: "<html></html>",
                    url:"http://xxx.com/s1.html",
                    language:"html",
                    author: {
                        userid: 3,
                        username: "Tom",
                        userAvatarId: 5
                    },
                    createdAt: 1400956671914,
                    updatedAt: "",
                    numOfComments: 2
                },
                {
                    sid: 2,
                    title: "snippet 3",
                    content: "var s=1;",
                    url:"",
                    language:"javascript",
                    author: {
                        userid: 3,
                        username: "Tom",
                        userAvatarId: 5
                    },
                    createdAt: 1400956671914,
                    updatedAt: "",
                    numOfComments: 1
                },
                {
                    sid: 3,
                    title: "snippet 3",
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
                    numOfComments: 0
                }
            ]
        };


        //$scope.board = {
        //    bid: 2,
        //    title: "board 2",
        //    category: "cat2",
        //    isPublic: true,
        //    createdAt: "",
        //    updatedAt: "",
        //    description:"board for sharing",
        //    numOfSnippets: 3,
        //    owner:{
        //        userid: 1,
        //        username: "Xiaoli",
        //        userAvatarId: 10
        //    },
        //    members:[],
        //    requestors:[],
        //    snippets:[
        //        {
        //            sid: 1,
        //            title: "snippet 1",
        //            content: "<html></html>",
        //            url:"",
        //            language:"html",
        //            author: {
        //                userid: 3,
        //                username: "Tom",
        //                userAvatarId: 5
        //            },
        //            createdAt: "",
        //            updatedAt: "",
        //            numOfComments: 2
        //        },
        //        {
        //            sid: 2,
        //            title: "snippet 3",
        //            content: "var s=1;",
        //            url:"",
        //            language:"javascript",
        //            author: {
        //                userid: 3,
        //                username: "Tom",
        //                userAvatarId: 5
        //            },
        //            createdAt: "",
        //            updatedAt: "",
        //            numOfComments: 1
        //        },
        //        {
        //            sid: 3,
        //            title: "snippet 3",
        //            content: "<html></html>",
        //            url:"",
        //            language:"html",
        //            author: {
        //                userid: 4,
        //                username: "Jerry",
        //                userAvatarId: 6
        //            },
        //            createdAt: "",
        //            updatedAt: "",
        //            numOfComments: 0
        //        }
        //    ]
        //};



        //request GET a board from server
        //Board.find($routeParams.id)
        //    .success(function (data) {
        //        console.log("get board "+ $routeParams.id+ " success");
        //        console.log(data);
        //        $scope.board = data;
        //    });

    });

