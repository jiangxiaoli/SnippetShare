'use strict';

angular.module("snippetShare")
    .factory("Board", function BoardFactory($http, $q){
        var rootUrl ="/users/";
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
            },
            isReadableTo: function(board, user) {
                if (board.isPublic) {
                    return true;
                }
                var isOwner = user.userid === board.ownerId;
                var isMember = _.contains(board.memberIds, user.userid);
                return isOwner || isMember;
            },
            addRequestor: function(board, user) {
                console.log("Board.addRequestor board/user", board, user);
                if (board.requestorIds && board.requestorIds.length && _.contains(board.requestorIds, user.userid)) {
                    return $q.reject("user already in requestors");
                } else if ( user.userid === board.ownerId) {
                    return $q.reject("owner can't request to access his/her own board");
                }
                else {
                    var requestUrl = "/request/"+board.bid + "/"+user.userid;

                    return $http({
                        url: requestUrl,
                        method: "POST"
                    }).then(
                        function success(res){
                            board.requestorIds = board.requestorIds || [];
                            board.requestorIds.push(user.userid);

                            console.log("add requestor ssuccess. updated board:", board);
                            return board;
                        },
                        function fail(err){
                            console.log("add requestor failed.err:", err);
                            return err;
                        }
                    );

                }
            },
            approveRequest: function(board, user) {
                var requestUrl = "/request/"+board.bid + "/"+user.userid +"/approve";
                return $http({
                    url: requestUrl,
                    method: "PUT"
                }).then(
                    function success(res){
                        board.memberIds = board.memberIds || [];
                        board.requestorIds = board.requestorIds || [];


                        if (!_.contains(board.memberIds, user.userid)) {
                            board.memberIds.push(user.userid);
                        }

                        board.requestorIds = _.reject(board.requestorIds, function(requestorId) {
                            return requestorId = user.userid;
                        });

                        console.log("approve request ssuccess. updated board:", board);
                        return board;
                    },
                    function fail(err){
                        console.log("approve request failed.err:", err);
                        return err;
                    }
                );
            },
            denyRequest: function(board, user) {
                var requestUrl = "/request/"+board.bid + "/"+user.userid +"/deny";
                return $http({
                    url: requestUrl,
                    method: "PUT"
                }).then(
                    function success(res){
                        board.requestorIds = board.requestorIds || [];

                        board.requestorIds = _.reject(board.requestorIds, function(requestorId) {
                            return requestorId = user.userid;
                        });

                        console.log("deny request ssuccess. updated board:", board);
                        return board;
                    },
                    function fail(err){
                        console.log("deny request failed.err:", err);
                        return err;
                    }
                );
            },
            hasRequestor: function(board, user) {
                return !!_.contains(board.requestorIds, user.userid);
            }
        }


    });
