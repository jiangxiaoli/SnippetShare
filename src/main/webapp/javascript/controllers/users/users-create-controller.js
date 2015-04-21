'use strict';

angular.module("snippetShare")
    .controller("UsersCreateController", function ($scope, User, $location) {
        $scope.isSubmitting = false;

        $scope.saveUser = function (user) {
            $scope.isSubmitting = true;

            User.create(user)
                .success(function(data, status, headers, config) {
                    console.log("in create user success");
                    console.log(data);
                    console.log(status);
                    $location.path("/users");
                })
                .error(function(data, status, headers, config) {
                    console.log("in error");
                    console.log(data);
                    console.log(status);
                }).finally(function () {
                    $scope.isSubmitting = false;
                });

        }
    });