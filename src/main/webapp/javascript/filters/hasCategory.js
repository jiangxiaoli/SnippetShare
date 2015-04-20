'use strict';

angular.module("snippetShare")
    .filter('hasCategory', function () {
        return function (users, category) {
            if(category == "All") return users;

            var filtered = [];
            for(var i = 0; i< users.length; i++){
                var hasCat = false;
                var user = users[i];
                for(var j =0; j< user.boards.length; j++){

                    if(user.boards[j].category == category){
                        hasCat = true;
                    }
                }
                if(hasCat) filtered.push(user);
            }
            return filtered;
        }
    });
