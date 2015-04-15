'use strict';

angular.module("snippetShare")
    .controller("UsersShowController", function ($routeParams, User) {
        var controller = this;

        //request GET all players from server
        User.find($routeParams.id)
            .success(function (data) {
                console.log("get user "+ $routeParams.id+ " success");
                console.log(data);
                $scope.player = data;
            });

        $scope.deleteUser = function(user){
            User.remove($routeParams.id)
                .success(function (data) {
                    console.log("delete user "+ $routeParams.id+ " success");
                    console.log(data);
                    $location.path('/users');
                });
        };

    });
