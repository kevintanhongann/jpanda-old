var jpanda = angular.module('jpanda', ['ngRoute']);

jpanda.config(['$routeProvider', function($route){
    $route
        .when('/', { templateUrl:'/admin/partial/index', controller: 'IndexCtrl' })
        .when('/content/pages', { templateUrl:'/admin/partial/pages', controller: 'PagesCtrl' })
        .when('/content/announcement', { templateUrl:'/admin/partial/announcement', controller: 'AnnouncementCtrl' })
        .when('/admin/users', { templateUrl:'/admin/partial/users', controller: 'UsersCtrl' })
        .when('/admin/groups', { templateUrl:'/admin/partial/groups', controller: 'GroupsCtrl' })
        .when('/admin/redirects', { templateUrl:'/admin/partial/redirects', controller: 'RedirectsCtrl' })
        .when('/analytics', { templateUrl:'/admin/partial/analytics', controller: 'AnalyticsCtrl' })
        .otherwise({ redirectTo: '/' });
}]);

jpanda.controller('NavCtrl', ['$scope', function ($scope) {
    $scope.$root.$on('nav:head', function(event, header){
        $scope.header = header;
    });
    $scope.is = function(header) {
        return $scope.header === header;
    };
}]);

jpanda.controller('IndexCtrl', ['$scope', function ($scope) {
    $scope.$root.$emit('nav:head', 'index');
}]);

jpanda.controller('PagesCtrl', ['$scope', function ($scope) {
    $scope.$root.$emit('nav:head', 'content');
}]);

jpanda.controller('AnnouncementCtrl', ['$scope', function ($scope) {
    $scope.$root.$emit('nav:head', 'content');
}]);

jpanda.controller('UsersCtrl', ['$scope', function ($scope) {
    $scope.$root.$emit('nav:head', 'admin');
}]);

jpanda.controller('GroupsCtrl', ['$scope', function ($scope) {
    $scope.$root.$emit('nav:head', 'admin');
}]);

jpanda.controller('RedirectsCtrl', ['$scope', function ($scope) {
    $scope.$root.$emit('nav:head', 'admin');
}]);

jpanda.controller('AnalyticsCtrl', ['$scope', function ($scope) {
    $scope.$root.$emit('nav:head', 'analytics');
}]);