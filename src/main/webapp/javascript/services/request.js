'use strict';

angular.module("snippetShare")
    .factory("Request", function RequestFactory($http){
        var rootUrl ="/snippetshare/request/";
        return {
            sendRequest: function(bid, userid){
                return $http({method: "POST", url:rootUrl+bid+ "/" +userid});
            },
            approveRequest: function(bid, userid){
                return $http({method: "PUT", url:rootUrl+bid+ "/" +userid+"/approve"});
            },
            denyRequest: function(bid, userid){
                return $http({method: "PUT", url:rootUrl+bid+ "/" +userid+"/deny"});
            },
            deleteAccess: function(bid, userid){
                return $http({method: "DELETE", url:rootUrl+bid+ "/" +userid});
            }
        }

    });
