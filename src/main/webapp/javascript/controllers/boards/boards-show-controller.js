'use strict';

angular.module("snippetShare")
    .controller("BoardsShowController", function ($scope, Board, $routeParams, $modal, $location) {

        //$scope.board = {
        //    bid: 2,
        //    title: "board 2",
        //    category: "cat2",
        //    isPublic: false,
        //    createdAt: "",
        //    updatedAt: "",
        //    description:"board for sharing",
        //    numOfSnippets: 3,
        //    owner:{
        //        userid: 1,
        //        username: "Xiaoli",
        //        userAvatarId: 10
        //    },
        //    members:[
        //        {
        //            userid: 3,
        //            username: "Tom",
        //            userAvatarId: 5
        //        }
        //    ],
        //    requestors:[
        //        {
        //            userid: 4,
        //            username: "Jerry",
        //            userAvatarId: 6
        //        }
        //    ],
        //    snippets:[
        //        {
        //            sid: 1,
        //            title: "snippet 1",
        //            content: "<html></html>",
        //            url:"http://xxx.com/s1.html",
        //            language:"html",
        //            author: {
        //                userid: 3,
        //                username: "Tom",
        //                userAvatarId: 5
        //            },
        //            createdAt: 1400956671914,
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
        //            createdAt: 1400956671914,
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
        //            createdAt: 1400956671914,
        //            updatedAt: "",
        //            numOfComments: 0
        //        }
        //    ]
        //};


        //WIP - for model member
        //$scope.open = function () {
        //
        //    var modalInstance = $modal.open({
        //        templateUrl: 'myModalContent.html',
        //        controller: 'ModalInstanceCtrl',
        //        resolve: {
        //            memebers: function () {
        //                return $scope.board.memebers;
        //            }
        //        }
        //    });
        //
        //    modalInstance.result.then(function (selectedItem) {
        //        $scope.selected = selectedItem;
        //    }, function () {
        //        $log.info('Modal dismissed at: ' + new Date());
        //    });
        //};


        //request GET a board from server
        Board.find($routeParams.userid, $routeParams.bid)
            .success(function (data) {
                console.log("get board "+ $routeParams.bid+ " success");
                console.log(data);
                $scope.board = data;
            });


        $scope.isDeleting = false;

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

