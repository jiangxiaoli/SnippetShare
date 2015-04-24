'use strict';

//specify routes
angular.module("snippetShare")
    .config(function ($routeProvider) {
        $routeProvider
            .when("/",{
                templateUrl: "templates/pages/landing.html"
            })

            /*********** user routes *************/
            .when("/login",{
                templateUrl: "templates/pages/users/login.html",
                //controller: "UsersLoginController"
            })
            .when("/signup",{
                templateUrl: "templates/pages/users/signup.html",
                controller: "UsersCreateController"
            })
            .when("/users",{
                templateUrl: "templates/pages/users/index.html",
                controller: "UsersIndexController"
            })
            .when("/users/:id",{
                templateUrl: "templates/pages/users/show.html",
                controller: "UsersShowController"
            })
            //.when("/editusers/:id",{
            //    templateUrl: "templates/pages/users/edit.html",
            //    controller: "UsersEditController"
            //})

            /*********** boards routes *************/
            .when("/users/:userid/createboards",{
                templateUrl: "templates/pages/boards/create.html",
                controller: "BoardsCreateController"
            })
            .when("/users/:userid/boards/:bid",{
                templateUrl: "templates/pages/boards/show.html",
                controller: "BoardsShowController"
            })
            .when("/users/:userid/editboards/:bid",{
                templateUrl: "templates/pages/boards/edit.html",
                controller: "BoardsEditController"
            })

             /*********** snippet routes *************/
            .when("/boards/:bid/snippets/:id",{
                templateUrl: "templates/pages/snippets/show.html",
                controller: "SnippetsShowController"
            })
            .when("/boards/:bid/createSnippet/",{
                templateUrl: "templates/pages/snippets/create.html",
                controller: "SnippetsCreateController"
            })
            .when("/boards/:bid/editSnippets/:id",{
                templateUrl: "templates/pages/snippets/edit.html",
                controller: "SnippetsEditController"
            })


            .otherwise({ redirectTo:'/'});

    });