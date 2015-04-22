'use strict';

angular.module("snippetShare")
    .filter('filterByCategory', function () {
        return function (items, category) {

            //if null or no category, return iteself
            if(!items || category == "All") return items;

            var filtered = [];
            for(var i = 0; i< items.length; i++){
                if(items[i].category == category){
                    filtered.push(items[i]);
                }
            }
            return filtered;
        }
    });