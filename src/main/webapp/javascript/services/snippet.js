'use strict';

angular.module("snippetShare")
    .factory("Snippet", function SnippetFactory($http){
        var rootUrl ="/boards/";
        return {
            all: function(bid){
                return $http({method: "GET", url:rootUrl+ "/"+bid+"/snippets/"});
            },
            create: function(bid, snippet){
                return $http.post(rootUrl+"/"+bid+"/snippets/", snippet, {
                    //transfer $http send data format to request param
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
                    transformRequest: function(snippet){
                        return $.param(snippet);
                    }
                });
            },
            find: function (bid, id) {
                return $http({method: "GET", url:rootUrl + "/"+bid+"/snippets/" + id});
            },
            update: function (bid, id, snippet) {
                return $http.post(rootUrl+"/"+bid+"/snippets/" + id, snippet, {
                    //transfer $http send data format to request param
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
                    transformRequest: function(snippet){
                        return $.param(snippet);
                    }
                });
            },
            remove: function (bid, id) {
                return $http({method: "DELETE", url:rootUrl + "/"+bid+"/snippets/"+ id});
            }

        }


    });
