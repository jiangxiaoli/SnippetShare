'use strict';


//include ngRoute in app module, whole app get access to this service
angular.module('snippetShare', ['ngRoute', 'ui.bootstrap', 'ui.ace','fox.scrollReveal']);

// intercept unauthorized response and redirect to login page
angular.module('snippetShare')
    .config(["$httpProvider", function($httpProvider) {
        $httpProvider.interceptors.push(['$q', '$location', function($q, $location) {
            return {
                'response': function(originalRes) {

                    if(originalRes.status === 401) {
                        return deferred.reject(originalRes.status);
                        $location.url('/login');
                    } else {
                        return originalRes;
                    }
                }
            };
        }]);
    }])
    .value("categories", ["Web", "Mobile", "Algorithms", "Tools", "Others"])
    .run(function(User) {
        User.restore()
            .success(function(data, status, headers, config) {

                console.log("in restore success");
                console.log(data);
                console.log(status);
                User.currentUser = data;
            })
    });

