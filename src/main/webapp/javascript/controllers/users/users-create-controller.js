'use strict';

angular.module("snippetShare")
    .controller("UsersCreateController", function ($http) {
        var controller = this;
        this.saveUser = function (user) {

            //handel errors
            controller.errors = null;
            //$http({ method:"POST", url: "/players", data: user })
            //    .success(function (data) {
            //        console.log("in success");
            //        console.log(data);
            //    })
            //    .catch(function (user) {
            //        console.log("in error");
            //        console.log(user);
            //        controller.errors.user.error;
            //    });
        }
    });