'use strict';

angular.module("snippetShare")
    .factory("Tag", function SnippetFactory($http){
        var rootUrl ="/snippets";
        return {
            all: function(sid){
                return $http({method: "GET", url:rootUrl+ "/"+sid+"/tags/"});
            },
            create: function(sid, content){
                var reqBody = { content: content };
                return $http.post(rootUrl+"/"+sid+"/tags/", reqBody, {
                    //transfer $http send data format to request param
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
                    transformRequest: function(tag){
                        return $.param(tag);
                    }
                });
            },
            remove: function (sid, id) {
                return $http({method: "DELETE", url:rootUrl + "/"+sid+"/tags/"+ id});
            }

        }


    });
