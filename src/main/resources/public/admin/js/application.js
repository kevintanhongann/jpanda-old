var panda = angular.module('panda', ['panda.config', 'ngRoute', 'ngResource']);

panda.config(['$routeProvider', '$httpProvider', 'Security', function($route, $http, Security){
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
    $http.defaults.headers.common = { 'X-CSRF-TOKEN': Security.token };
}]);

panda.controller('NavCtrl', ['$scope', function ($scope) {
    $scope.$root.$on('nav:head', function(event, header){
        $scope.header = header;
    });
    $scope.$root.$on('nav:crumb', function(event, breadcrumb){
        $scope.breadcrumb = breadcrumb;
    });
    $scope.is = function(header) {
        return $scope.header === header;
    };
}]);

panda.controller('IndexCtrl', ['$scope', function ($scope) {
    $scope.$root.$emit('nav:head', 'index');
    $scope.$root.$emit('nav:crumb');
}]);

panda.controller('ProfileCtrl', ['$scope', function ($scope) {
    $scope.$root.$emit('nav:crumb', ['Profile']);
}]);

panda.controller('PagesCtrl', ['$scope', function ($scope) {
    $scope.$root.$emit('nav:head', 'content');
    $scope.$root.$emit('nav:crumb', ['Content', 'Pages']);
}]);

panda.controller('AnnouncementCtrl', ['$scope', function ($scope) {
    $scope.$root.$emit('nav:head', 'content');
    $scope.$root.$emit('nav:crumb', ['Content', 'Announcement']);
}]);

panda.controller('UsersCtrl', ['$scope', function ($scope) {
    $scope.$root.$emit('nav:head', 'admin');
    $scope.$root.$emit('nav:crumb', ['Administration', 'Users']);
}]);

panda.controller('GroupsCtrl', ['$scope', function ($scope) {
    $scope.$root.$emit('nav:head', 'admin');
    $scope.$root.$emit('nav:crumb', ['Administration', 'Groups']);
}]);

panda.controller('RedirectsCtrl', ['$scope', 'Redirects', function ($scope, Redirects) {
    $scope.$root.$emit('nav:head', 'admin');
    $scope.$root.$emit('nav:crumb', ['Administration', 'Redirects']);
    
    $scope.sizes = [10, 20, 50, 100];
    $scope.size = 10;
    
    $scope.setSize = function(size) {
        $scope.size = size;
        $scope.reload();
    };
    
    $scope.remove = function(item){
        Redirects.remove({id:item.id}).$promise.then($scope.reload);
    };
    
    $scope.reload = function() {
        $scope.loading = true;
        Redirects.get({ size: $scope.size }, function(data){
             $scope.redirects = data;
             $scope.loading = false;
        });
    };

    $scope.reload();
    $('.dropdown').dropdown();
}]);

panda.controller('AnalyticsCtrl', ['$scope', function ($scope) {
    $scope.$root.$emit('nav:head', 'analytics');
    $scope.$root.$emit('nav:crumb', ['Analytics']);
}]);

panda.factory('Redirects',['$resource', function($resource){
    return $resource('/api/v1/redirects/:id', { id: '@id' });
}]);