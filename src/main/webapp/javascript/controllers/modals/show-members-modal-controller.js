"use strict";

angular.module("snippetShare").controller("ShowMembersModalCtrl", function ($scope, $modalInstance,Board,board,members) {

    console.log("ShowRequestsModal is open. board/members", board, members);
    $scope.members = members;

    $scope.cancel = function () {
        $modalInstance.dismiss("cancel");
    };
});