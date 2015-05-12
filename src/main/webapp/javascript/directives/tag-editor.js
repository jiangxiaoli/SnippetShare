'use strict';

angular.module("snippetShare")
    .directive("tagEditor", function (Tag){
        var DDO = {
            restrict: "E",
            scope: {
                editable: "=",
                snippet: "="
            },
            templateUrl: "templates/directives/tag-editor.html",
            link: link
        };

        function link(scope, elem, attr) {
            console.log("tagselector editable/snippet:", scope.editable, scope.snippet);

            scope.viewModel= {
                httpBusy: false,
                tagInput: "",
                errMsg: ""
            };

            scope.addTag = function() {
                var content = scope.viewModel.tagInput;

                scope.viewModel.httpBusy = true;

                Tag.create(scope.snippet.sid, content).then(function(res) {
                    console.log("addTag res in tag dir:", res);
                    scope.snippet.tags.push(res.data);
                    scope.viewModel.httpBusy = false;
                    scope.viewModel.tagInput = "";
                    scope.viewModel.errMsg = "";

                }, function(err) {
                    console.log("addtag err in tag dir:", err);
                    scope.viewModel.httpBusy = false;
                    scope.viewModel.errMsg = err;
                });
            }

            scope.removeTag = function(tag) {
                scope.viewModel.httpBusy = true;

                Tag.remove(scope.snippet.sid, tag.tid).then(function(res) {
                    console.log("removeTag res in tag dir:", res);
                    scope.snippet.tags.splice( scope.snippet.tags.indexOf(tag),1);
                    scope.viewModel.httpBusy = false;
                    scope.viewModel.errMsg = "";
                }, function(err) {
                    console.log("addTag err in tag dir:", err);
                    scope.viewModel.httpBusy = false;
                    scope.viewModel.errMsg = err;

                });
            };
        }

        return DDO;
    });
