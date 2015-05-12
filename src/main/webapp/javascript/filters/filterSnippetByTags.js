'use strict';

angular.module("snippetShare")
    .filter('filterSnippetByTags', function () {
        return function (snippets, needle) {

            if(!snippets || !snippets.length || !needle)
                return snippets;

            console.log(needle);

            var filtered = _.filter(snippets, function(snippet) {
                return _.find(snippet.tags, function(tag) {
                    var found = tag.content && tag.content.toLowerCase().indexOf(needle.toLowerCase()) != -1;
                    return found;
                });
            });

            return filtered;
        }
    });
