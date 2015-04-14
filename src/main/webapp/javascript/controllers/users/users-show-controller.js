'use strict';

angular.module("snippetShare")
    .controller("UsersShowController", function ($http, $routeParams) {
        var controller = this;

        //request GET all players from server
        //$http({method: "GET", url:"/users/" + $routeParams.id})
        //    .success(function (data) {
        //        console.log("get user "+ $routeParams.id+ " success");
        //        console.log(data);
        //        controller.user = data;
        //    });

    });
