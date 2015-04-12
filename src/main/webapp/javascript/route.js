'use strict';

//specify routes
angular.module("snippetShare")
    .config(function ($routeProvider) {
        $routeProvider
            .when("/",{
                templateUrl: "templates/pages/users/index.html"
            })
            .when("/users",{
                templateUrl: "templates/pages/users/index.html",
                controller: "UsersIndexController",
                controllerAs: "indexCtrl"
            })
            .when("/users/:id",{
                templateUrl: "templates/pages/users/show.html",
                controller: "UsersShowController",
                controllerAs: "showCtrl"
            })
            .when("/users/new",{
                templateUrl: "templates/pages/users/create.html",
                controller: "UsersCreateController",
                controllerAs: "createCtrl"
            })
            .when("/boards",{
                templateUrl: "templates/pages/boards/index.html",
                controller: "BoardsIndexController",
                controllerAs: "indexCtrl"
            })
            .when("/boards/:id",{
                templateUrl: "templates/pages/boards/show.html",
                controller: "BoardsShowController",
                controllerAs: "showCtrl"
            })
            .otherwise({ redirectTo:'/'});

    });