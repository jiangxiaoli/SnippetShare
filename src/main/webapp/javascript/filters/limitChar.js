'use strict';

angular.module("snippetShare")
    .filter('limitChar', function () {
        return function (item, limit) {
            var filtered = "";
            if(item.length > limit+1) {
                filtered = item.slice(0, limit);
                filtered = filtered + "...";
                return filtered;
            } else {
                return item;
            }
        }
    });