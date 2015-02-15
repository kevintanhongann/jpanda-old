var panda = angular.module('panda', ['ngRoute']);

panda.config(['$routeProvider', function($route){
    $route
        .when('/', { templateUrl:'/admin/partial/index', controller: 'IndexCtrl' })
        .when('/profile', { templateUrl:'/admin/partial/profile', controller: 'ProfileCtrl' })
        .when('/content/pages', { templateUrl:'/admin/partial/pages', controller: 'PagesCtrl' })
        .when('/content/announcement', { templateUrl:'/admin/partial/announcement', controller: 'AnnouncementCtrl' })
        .when('/admin/users', { templateUrl:'/admin/partial/users', controller: 'UsersCtrl' })
        .when('/admin/groups', { templateUrl:'/admin/partial/groups', controller: 'GroupsCtrl' })
        .when('/admin/redirects', { templateUrl:'/admin/partial/redirects', controller: 'RedirectsCtrl' })
        .when('/analytics', { templateUrl:'/admin/partial/analytics', controller: 'AnalyticsCtrl' })
        .otherwise({ redirectTo: '/' });
}]);

panda.controller('NavCtrl', ['$scope', function ($scope) {
    $scope.$root.$on('nav:head', function(event, header){
        $scope.header = header;
    });
    $scope.is = function(header) {
        return $scope.header === header;
    };
}]);

panda.controller('IndexCtrl', ['$scope', function ($scope) {
    $scope.$root.$emit('nav:head', 'index');
}]);

panda.controller('ProfileCtrl', ['$scope', function ($scope) {
}]);

panda.controller('PagesCtrl', ['$scope', function ($scope) {
    $scope.$root.$emit('nav:head', 'content');
}]);

panda.controller('AnnouncementCtrl', ['$scope', function ($scope) {
    $scope.$root.$emit('nav:head', 'content');
}]);

panda.controller('UsersCtrl', ['$scope', function ($scope) {
    $scope.$root.$emit('nav:head', 'admin');
}]);

panda.controller('GroupsCtrl', ['$scope', function ($scope) {
    $scope.$root.$emit('nav:head', 'admin');
}]);

panda.controller('RedirectsCtrl', ['$scope', function ($scope) {
    $scope.$root.$emit('nav:head', 'admin');
}]);

panda.controller('AnalyticsCtrl', ['$scope', function ($scope) {
    $scope.$root.$emit('nav:head', 'analytics');
}]);