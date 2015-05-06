'use strict';

angular.module("snippetShare")
    .controller("SnippetCreateAceCtrl", function ($scope) {

        // The modes
        $scope.modes = ['C and C++', 'C#', 'CoffeeScript', 'CSS', 'EJS', 'GO', 'Groovy', 'Haskell', 'HTML',
            'Jade', 'Java', 'JavaScript', 'JSON', 'JSP', 'LESS', 'Markdown', 'MATLAB', 'MySQL', 'Objective-C', 'Perl',
            'PHP', 'Python', 'R', 'Ruby', 'SASS', 'Scala', 'VBScript', 'XML'];
        $scope.themes = ['Chrome', 'Eclipse', 'Github', 'Terminal', 'TextMate', 'Xcode', 'Ambiance', 'Chaos',
            'Cobalt', 'krTheme', 'Merbivore', 'Monokai', 'Twilight'];


        $scope.mode = $scope.modes[10];

        $scope.theme = $scope.themes[4];


        // The ui-ace option
        $scope.aceOption = {
            mode: $scope.mode.toLowerCase(),
            theme: $scope.theme.toLowerCase(),
            onLoad: function (_ace) {

                // HACK to have the ace instance in the scope...
                $scope.modeChanged = function () {
                    console.log("mode change");
                    _ace.getSession().setMode("ace/mode/" + $scope.mode.toLowerCase());
                };

                $scope.themeChanged = function () {
                    _ace.renderer.setTheme("ace/theme/" + $scope.theme.toLowerCase());
                };

            }
        };


    });

