'use strict';

angular.module("snippetShare")
    .controller("UsersEditController", function ($scope, User, $routeParams, $location) {

        //request GET the current user from server
        User.find($routeParams.id)
            .success(function (data) {
                console.log("get user "+ $routeParams.id+ " success");
                console.log(data);
                $scope.user = data;
            });

        $scope.isSubmitting = false;

        $scope.updateUser = function (user) {
            $scope.isSubmitting = true;

            User.update(user, $routeParams.id)
                .success(function(data, status, headers, config) {
                    console.log("in update success");
                    console.log(data);
                    console.log(status);
                })
                .error(function(data, status, headers, config) {
                    console.log("in error");
                    console.log(data);
                    console.log(status);
                }).finally(function () {
                    $scope.isSubmitting = false;
                    $location.path("/users/" + $routeParams.id);
                });
        };


    });
