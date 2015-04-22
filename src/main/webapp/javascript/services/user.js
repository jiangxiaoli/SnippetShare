'use strict';

angular.module("snippetShare")
    .factory("User", function UserFactory($http){
        return {
            all: function(){
                return $http({method: "GET", url:"snippetshare/users"});
            },
            create: function(user){
                return $http.post("snippetshare/users", user, {
                    //transfer $http send data format to request param
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
                    transformRequest: function(user){
                        return $.param(user);
                    }
                });
            },
            find: function (id) {
                return $http({method: "GET", url:"snippetshare/users/" + id});
            },
            update: function (user, id) {
                return $http.post("snippetshare/users/"+id, user, {
                    //transfer $http send data format to request param
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
                    transformRequest: function(user){
                        return $.param(user);
                    }
                });
            }
        }


    });