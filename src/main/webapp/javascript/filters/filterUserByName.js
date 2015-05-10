'use strict';

angular.module("snippetShare")
    .filter('filterUserByName', function () {
        return function (users, username) {

            //if no board
            if(!users || !users.length || !username)
                return users;


            console.log(username);

            var filtered = [];
            for(var i = 0; i< users.length; i++){
                if(users[i].username.toLowerCase().indexOf(username.toLowerCase()) != -1){
                    filtered.push(users[i]);
                }
            }
            return filtered;
        }
    });
