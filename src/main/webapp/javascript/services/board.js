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
                var isMember = _.findWhere(board.members, {"userid":user.userid});
                return isOwner || isMember;
            },
            isWritableTo: function(board, user) {
                var isOwner = user.userid === board.ownerId;
                var isMember = _.findWhere(board.members, {"userid":user.userid});
                return isOwner || isMember;
            },
            addRequestor: function(board, user) {
                console.log("Board.addRequestor board/user", board, user);
                if (board.requestors && board.requestors.length && _.findWhere(board.requestors, {"userid": user.userid})) {
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
                            board.requestors = board.requestors || [];
                            board.requestors.push(user);

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
                        board.members = board.members || [];
                        board.requestors = board.requestors || [];

                        if (!_.findWhere(board.members, {"userid": user.userid})) {
                            board.members.push(user);
                        }

                        board.requestors = _.reject(board.requestors, function(requestor) {
                            return requestor.userid === user.userid;
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
                        board.requestors = board.requestors || [];

                        board.requestors = _.reject(board.requestors, function(requestor) {
                            return requestor.userid === user.userid;
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
                return !!_.findWhere(board.requestors, {"userid": user.userid});
            }
        }


    });
