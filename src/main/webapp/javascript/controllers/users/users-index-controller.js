'use strict';

angular.module("snippetShare")
    .controller("UsersIndexController", function ($scope, User, Board, $modal) {
        $scope.User = User;
        $scope.Board = Board;
        $scope.categories = ['All', 'cat1', 'cat2', 'cat3', 'cat4'];

        //$scope.users = [
        //    {
        //        userid: 1,
        //        username: "Xiaoli",
        //        email: "xiaoli@gmail.com",
        //        pwd:"1234",
        //        profilepic: "software engineering student",
        //        userAvatarId: 10,
        //        boards: [
        //            {
        //                bid: 1,
        //                title: "board 1",
        //                category: "cat1",
        //                isPublic: false,
        //                createdAt: "",
        //                updatedAt: "",
        //                description:"board for coding",
        //                numOfSnippets: 5,
        //                members:[
        //                    {
        //                        userid: 2,
        //                        username: "Alice",
        //                        userAvatarId: 3
        //                    },
        //                    {
        //                        userid: 3,
        //                        username: "Tom",
        //                        userAvatarId: 4
        //                    }
        //                ],
        //                requestors:[
        //                    {
        //                        userid: 4,
        //                        username: "Jerry",
        //                        userAvatarId: 5
        //                    }
        //                ]
        //            },
        //            {
        //                bid: 2,
        //                title: "board 2",
        //                category: "cat2",
        //                isPublic: true,
        //                createdAt: "",
        //                updatedAt: "",
        //                description:"board for sharing, board for sharing, board for sharing, " +
        //                "board for sharing, this is a good baord, really groood, very nice nice",
        //                numOfSnippets: 3,
        //                members:[],
        //                requestors:[]
        //            },
        //            {
        //                bid: 3,
        //                title: "board 3",
        //                category: "cat3",
        //                isPublic: false,
        //                createdAt: "",
        //                updatedAt: "",
        //                description:"board for coding",
        //                numOfSnippets: 5,
        //                members:[
        //                    {
        //                        userid: 2,
        //                        username: "Alice",
        //                        userAvatarId: 3
        //                    },
        //                    {
        //                        userid: 3,
        //                        username: "Tom",
        //                        userAvatarId: 4
        //                    }
        //                ],
        //                requestors:[
        //                    {
        //                        userid: 4,
        //                        username: "Jerry",
        //                        userAvatarId: 5
        //                    }
        //                ]
        //            },
        //            {
        //                bid: 4,
        //                title: "board 4",
        //                category: "cat4",
        //                isPublic: true,
        //                createdAt: "",
        //                updatedAt: "",
        //                description:"board for sharing, board for sharing, board for sharing, " +
        //                "board for sharing, this is a good baord, really groood, very nice nice",
        //                numOfSnippets: 3,
        //                members:[],
        //                requestors:[]
        //            }
        //        ]
        //    },
        //    {
        //        userid: 2,
        //        username: "Alice",
        //        email: "alice@gmail.com",
        //        pwd:"1234",
        //        profilepic: "software engineering student",
        //        userAvatarId: 8,
        //        boards: [
        //            {
        //                bid: 3,
        //                title: "board 3",
        //                category: "cat3",
        //                isPublic: false,
        //                createdAt: "",
        //                updatedAt: "",
        //                description:"board for coding",
        //                numOfSnippets: 5,
        //                members:[
        //                    {
        //                        userid: 1,
        //                        username: "Xiaoli",
        //                        userAvatarId: 10
        //                    },
        //                    {
        //                        userid: 3,
        //                        username: "Tom",
        //                        userAvatarId: 5
        //                    }
        //                ],
        //                requestors:[
        //                    {
        //                        userid: 4,
        //                        username: "Jerry",
        //                        userAvatarId: 6
        //                    }
        //                ]
        //            }
        //        ]
        //    },
        //    {
        //        userid: 3,
        //        username: "Jerry",
        //        email: "jerry@gmail.com",
        //        pwd:"1234",
        //        profilepic: "software engineering student",
        //        userAvatarId: 6,
        //        boards: [
        //            {
        //                bid: 3,
        //                title: "board 4",
        //                category: "cat4",
        //                isPublic: false,
        //                createdAt: "",
        //                updatedAt: "",
        //                description:"board for coding",
        //                numOfSnippets: 5,
        //                members:[
        //                    {
        //                        userid: 1,
        //                        username: "Xiaoli",
        //                        userAvatarId: 10
        //                    },
        //                    {
        //                        userid: 3,
        //                        username: "Tom",
        //                        userAvatarId: 5
        //                    }
        //                ],
        //                requestors:[
        //                    {
        //                        userid: 4,
        //                        username: "Jerry",
        //                        userAvatarId: 6
        //                    }
        //                ]
        //            },
        //            {
        //                bid: 3,
        //                title: "board 5",
        //                category: "cat4",
        //                isPublic: true,
        //                createdAt: "",
        //                updatedAt: "",
        //                description:"piblic board for coding",
        //                numOfSnippets: 2,
        //                members:[],
        //                requestors:[]
        //            }
        //        ]
        //    }
        //];

        $scope.showDesc = false;
        //hover show board description
        $scope.hover = function(board) {
            // Shows/hides the delete button on hover
            console.log("hover triggered");
            return board.showDesc = ! board.showDesc;
        };

        $scope.onClickRequest = function(board, user) {
            console.log("click request. board/user:", board, user);
            var modalInstance = $modal.open({
                templateUrl: 'templates/pages/modals/board-access-request-modal.html',
                controller: 'BoardAccessRequestCtrl',
                size: "sm",
                resolve: {
                    items: function () {
                        return board;
                    },
                    user: function () {
                        return user;
                    }
                }
            });

            modalInstance.result.then(function (res) {
                console.log("modal closed. res:", res);
            }, function () {
                console.log('Modal dismissed at: ' + new Date());
            });
        };

        //request GET all players from server
        User.all()
            .success(function (data) {
                console.log("get all users success");
                console.log(data);
                $scope.users = data;
            });

    });