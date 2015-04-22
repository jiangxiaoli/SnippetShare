'use strict';

angular.module("snippetShare")
    .controller("ModalInstanceCtrl", function ($scope, $modalInstance, memebers) {

        $scope.members = memebers;

        $scope.ok = function () {
            $modalInstance.close();
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    });

