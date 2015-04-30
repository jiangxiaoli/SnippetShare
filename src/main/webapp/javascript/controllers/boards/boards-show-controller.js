'use strict';

angular.module("snippetShare")
    .controller("BoardsShowController", function ($scope, Board, $routeParams, $modal, $location) {

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

