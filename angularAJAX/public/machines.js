var app = angular.module('appMachines', []);

app.controller('machineCtrl', function machineCtrl($scope, $http, $interval) {
	verifierMachines();
	$interval(function() {
		verifierMachines();
	}, 1000);
	function verifierMachines() {
		$http.get('machines.json').success(function(response) {
			$scope.machines = response.machines;

		});

	}
	;
});