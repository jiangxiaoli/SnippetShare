'use strict';

angular.module("snippetShare")
    .controller("AceCtrl", function ($scope) {

        // The modes
        $scope.modes = ['Scheme', 'Java', 'Javascript', 'HTML', 'CSS'];
        $scope.themes = ['Ambiance', 'Chrome', 'Eclipse', 'Github', 'Terminal', 'Textmate', 'Twilight','Xcode'];


        $scope.mode = $scope.modes[0];

        $scope.theme = $scope.themes[3];


        // The ui-ace option
        $scope.aceOption = {
            mode: $scope.mode.toLowerCase(),
            theme: $scope.theme.toLowerCase(),
            onLoad: function (_ace) {

                // HACK to have the ace instance in the scope...
                $scope.modeChanged = function () {
                    _ace.getSession().setMode("ace/mode/" + $scope.mode.toLowerCase());
                };

                $scope.themeChanged = function () {
                    _ace.renderer.setTheme("ace/theme/" + $scope.theme.toLowerCase());
                };

            }
        };

        // Initial code content...
        $scope.aceModel = ';; Scheme code in here.\n' +
        '(define (double x)\n\t(* x x))\n\n\n' +
        '<!-- XML code in here. -->\n' +
        '<root>\n\t<foo>\n\t</foo>\n\t<bar/>\n</root>\n\n\n' +
        '// Javascript code in here.\n' +
        'function foo(msg) {\n\tvar r = Math.random();\n\treturn "" + r + " : " + msg;\n}';

    });

