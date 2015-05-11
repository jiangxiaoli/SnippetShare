'use strict';

angular.module("snippetShare")
    .filter('filterBoardByTitle', function () {
        return function (boards, title) {

            //if no board
            if(!boards || !boards.length || !title)
                return boards;


            console.log(title);

            var filtered = [];
            for(var i = 0; i< boards.length; i++){
                if(boards[i].title.toLowerCase().indexOf(title.toLowerCase()) != -1){
                    filtered.push(boards[i]);
                }
            }
            return filtered;
        }
    });
