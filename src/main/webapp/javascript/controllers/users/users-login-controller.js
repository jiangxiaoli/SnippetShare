'use strict';

angular.module("snippetShare")
    .controller("UsersLoginController", function ($scope, User, $location) {

        console.log("User login controller invoked");

        $scope.isSubmitting = false;
        $scope.user = {};

        $scope.loginUser = function (user) {
            $scope.errMsg = '';

            if (!user.email || !user.password) {
                $scope.errMsg = "Email and Password can not be empty.";
                return;
            }

            $scope.isSubmitting = true;

            User.login(user)
                .success(function(data, status, headers, config) {

                    console.log("in login success");
                    console.log(data);
                    console.log(status);
                    User.currentUser = data;
                    $location.path("/users");
                })
                .error(function(data, status, headers, config) {
                    console.log("in error");
                    console.log(data);
                    console.log(status);
                    $scope.errMsg = data;
                }).finally(function () {
                    $scope.isSubmitting = false;
                });

        }
    });