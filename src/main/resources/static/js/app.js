var appLatihan = angular.module('appLatihan', []);

appLatihan.controller('AlamatController', function($http, $scope){
	$scope.dataAlamat = {};
	//get
	$scope.updateDataAlamat = function(){
		$http.get('/api/alamat').then(sukses, gagal);
		function sukses (response){
			$scope.dataAlamat = response.data;
			console.log(response);
		};
		function gagal (response){
			console.log(response);
			alert('Error = '+ response);
		};
	};
	$scope.updateDataAlamat();
	
	//hapus
	$scope.hapusAlamat = function(alamat){
	 	$http.delete('/api/alamat/'+alamat.id).then(sukses, gagal);
	 	function sukses(response){
	 		$scope.updateDataAlamat();
	 	};
	 	function gagal(response){
	 		console.log(response);
	 		alert('Error : '+response)
	 	};
	 };
	 
	 //simpan 
	 $scope.simpanAlamat = function(){
		 	$http.post('/api/alamat/', $scope.alamat).then(sukses, gagal);
		 	function sukses(response){
		 		$scope.updateDataAlamat();
		 		alert('berhasil');
		 	};
		 	function gagal(response){
		 		alert('gagal ya');
		 		console.log(response);
		 		alert('Error : '+response);
		 	};
		 };
});


