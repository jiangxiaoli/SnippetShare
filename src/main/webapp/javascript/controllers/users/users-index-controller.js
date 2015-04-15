'use strict';

angular.module("snippetShare")
    .controller("UsersIndexController", function ($scope, User) {

        //request GET all players from server
        User.all()
            .success(function (data) {
                console.log("get all users success");
                console.log(data);
                $scope.players = data;
            });

    });