"use strict";

angular.module("snippetShare").controller("CommentEditCtrl", function ($scope, $modalInstance,Comment, comment) {

    console.log("CommentEdit modal is open. comment", comment);
    $scope.comment = comment || {};

    $scope.save = function() {
        $modalInstance.close($scope.comment);
    }

    $scope.cancel = function () {
        $modalInstance.dismiss("cancel");
    };
});