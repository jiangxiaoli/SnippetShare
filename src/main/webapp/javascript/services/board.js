'use strict';

angular.module("snippetShare")
    .factory("Board", function BoardFactory($http){
        var rootUrl ="/snippetshare/users/";
        return {
            all: function(userid){
                return $http({method: "GET", url:rootUrl+userid+"/boards"});
            },
            create: function(userid, board){
                return $http.post(rootUrl+userid+"/boards", board, {
                    //transfer $http send data format to request param
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
                    transformRequest: function(board){
                        return $.param(board);
                    }
                });
            },
            find: function (userid, bid) {
                return $http({method: "GET", url:rootUrl+userid+"/boards/"+ bid});
            },
            update: function (userid, bid, board) {
                return $http.post(rootUrl+userid+"/boards/"+bid, board, {
                    //transfer $http send data format to request param
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
                    transformRequest: function(board){
                        return $.param(board);
                    }
                });
            },
            remove: function (userid, bid) {
                return $http({method: "DELETE", url:rootUrl+userid+"/boards/"+bid});
            }

        }


    });
