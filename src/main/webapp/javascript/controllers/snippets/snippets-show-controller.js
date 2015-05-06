'use strict';

angular.module("snippetShare")
    .controller("SnippetsShowController", function ($scope, Snippet, $routeParams, $location, User, $modal) {

        //request GET a board from server
        Snippet.find($routeParams.bid,$routeParams.id)
            .success(function (data) {
                console.log("get Snippet "+ $routeParams.id+ " success");
                console.log(data);
                $scope.snippet = data;

                if(Sinppet.isReadableTo($scope.snippet,User.currentUser)) {
                    alert("You are trying to access a snippet that is not accessible to you!");
                    $location.url("/users/"+data.author.userid +"/boards/" + data.board.bid);
                }
            });



        $scope.User = User;
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

    });