'use strict';

angular.module("snippetShare")
    .factory("Comment", function SnippetFactory($http){
        var rootUrl ="/snippets";
        return {
            all: function(sid){
                return $http({method: "GET", url:rootUrl+ "/"+sid+"/comments/"});
            },
            create: function(sid, comment){
                return $http.post(rootUrl+"/"+sid+"/comments/", comment, {
                    //transfer $http send data format to request param
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
                    transformRequest: function(comment){
                        return $.param(comment);
                    }
                });
            },
            find: function (sid, id) {
                return $http({method: "GET", url:rootUrl + "/"+sid+"/comments/" + id});
            },
            remove: function (sid, id) {
                return $http({method: "DELETE", url:rootUrl + "/"+sid+"/comments/"+ id});
            },
            isWritableTo: function(comment, user) {
                if (!user) {
                    return false;
                }
                return comment.user && comment.user.userid === user.userid;
            },
            isReadableTo: function(comment, user) {
                if(!user) {
                    return false;
                }
                return true;
            }

        }


    });
