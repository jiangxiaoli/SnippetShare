'use strict';

angular.module("snippetShare")
    .factory("User", function UserFactory($http){
        var rootUrl ="/users/";
        return {
            all: function(){
                return $http({method: "GET", url:rootUrl});
            },
            create: function(user){
                return $http.post(rootUrl, user, {
                    //transfer $http send data format to request param
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
                    transformRequest: function(user){
                        return $.param(user);
                    }
                });
            },
            find: function (id) {
                return $http({method: "GET", url:rootUrl + id});
            },
            update: function (user, id) {
                return $http.post(rootUrl+id, user, {
                    //transfer $http send data format to request param
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
                    transformRequest: function(user){
                        return $.param(user);
                    }
                });
            }
        }


    });