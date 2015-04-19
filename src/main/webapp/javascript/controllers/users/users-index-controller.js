'use strict';

angular.module("snippetShare")
    .controller("UsersIndexController", function ($scope, User) {

        $scope.users = [
            {
                userid: 1,
                username: "Xiaoli",
                email: "xiaoli@gmail.com",
                pwd:"1234",
                profilepic: "software engineering student",
                userAvatarId: 1,
                boards: [
                    {
                        bid: 1,
                        title: "board 1",
                        category: "cat1",
                        isPublic: true,
                        createdAt: "",
                        updatedAt: "",
                        description:"board for coding",
                        numOfSnippets: 5,
                        members:[
                            {
                                userid: 2,
                                username: "Alice",
                                userAvatarId: 5
                            },
                            {
                                userid: 3,
                                username: "Tom",
                                userAvatarId: 9
                            }
                        ],
                        requestors:[
                            {
                                userid: 4,
                                username: "Jerry",
                                userAvatarId: 4
                            }
                        ]
                    },
                    {
                        bid: 2,
                        title: "board 2",
                        category: "cat2",
                        isPublic: true,
                        createdAt: "",
                        updatedAt: "",
                        description:"board for sharing",
                        numOfSnippets: 3,
                        members:[
                            {
                                userid: 3,
                                username: "Tom",
                                userAvatarId: 9
                            }
                        ],
                        requestors:[
                            {
                                userid: 4,
                                username: "Jerry",
                                userAvatarId: 4
                            }
                        ]
                    }
                ]
            },
            {
                userid: 2,
                username: "Alice",
                email: "alice@gmail.com",
                pwd:"1234",
                profilepic: "software engineering student",
                userAvatarId: 10,
                boards: [
                    {
                        bid: 3,
                        title: "board 3",
                        category: "cat3",
                        isPublic: true,
                        createdAt: "",
                        updatedAt: "",
                        description:"board for coding",
                        numOfSnippets: 5,
                        members:[
                            {
                                userid: 1,
                                username: "Xiaoli",
                                userAvatarId: 4
                            },
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
                        ]
                    }
                ]
            }
        ];


        //request GET all players from server
        //User.all()
        //    .success(function (data) {
        //        console.log("get all users success");
        //        console.log(data);
        //        $scope.players = data;
        //    });

    });