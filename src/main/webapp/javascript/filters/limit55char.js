'use strict';

angular.module("snippetShare")
    .filter('limit55char', function () {
        return function (item) {
            var filtered = "";
            if(item.length > 56) {
                filtered = item.slice(0, 55);
                filtered = filtered + "...";
                return filtered;
            } else {
                return item;
            }
        }
    });