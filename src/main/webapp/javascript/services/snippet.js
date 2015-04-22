'use strict';

angular.module("snippetShare")
    .factory("Snippet", function SnippetFactory($http){
        return {
            all: function(){
                return $http({method: "GET", url:"snippetshare/snippets"});
            },
            create: function(snippet){
                return $http.post("snippetshare/snippets", snippet, {
                    //transfer $http send data format to request param
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
                    transformRequest: function(snippet){
                        return $.param(snippet);
                    }
                });
            },
            find: function (id) {
                return $http({method: "GET", url:"snippetshare/snippets/" + id});
            },
            update: function (snippet, id) {
                return $http.post("snippetshare/snippets/"+id, snippet, {
                    //transfer $http send data format to request param
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
                    transformRequest: function(snippet){
                        return $.param(snippet);
                    }
                });
            },
            remove: function (id) {
                return $http({method: "DELETE", url:"snippetshare/snippets/" + id});
            }

        }


    });
