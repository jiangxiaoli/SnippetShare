'use strict';

angular.module("snippetShare")
    .factory("Snippet", function SnippetFactory($http){
        var rootUrl ="/snippets/";
        return {
            all: function(){
                return $http({method: "GET", url:rootUrl});
            },
            create: function(snippet){
                return $http.post(rootUrl, snippet, {
                    //transfer $http send data format to request param
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
                    transformRequest: function(snippet){
                        return $.param(snippet);
                    }
                });
            },
            find: function (id) {
                return $http({method: "GET", url:rootUrl + id});
            },
            update: function (snippet, id) {
                return $http.post(rootUrl+id, snippet, {
                    //transfer $http send data format to request param
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
                    transformRequest: function(snippet){
                        return $.param(snippet);
                    }
                });
            },
            remove: function (id) {
                return $http({method: "DELETE", url:rootUrl + id});
            }

        }


    });
