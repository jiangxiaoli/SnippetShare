'use strict';

angular.module("snippetShare")
    .controller("NavBarController", function ($scope, User, $location) {

        console.log("NavBarController invoked");

        $scope.user = User.currentUser;

        $scope.$watch(function() { return User.currentUser; },function(newVal, oldVal) {
            $scope.user = newVal;
        });

        $scope.logoutUser = function (user) {

            console.log("logout user is called");

            User.logout(user)
                .success(function(data, status, headers, config) {
                    console.log("in logout success");
                    console.log(data);
                    console.log(status);
                    User.currentUser = null;
                    $location.path("/");
                })
                .error(function(data, status, headers, config) {
                    console.log("in error");
                    console.log(data);
                    console.log(status);
                })

        }
    });