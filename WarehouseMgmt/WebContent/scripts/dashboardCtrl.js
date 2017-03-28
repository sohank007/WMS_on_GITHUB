angular.module("WMS").controller("dashboardCtrl",["$http","$scope","$mdDialog",'$window',"AllFactory",function($http,$scope,$mdDialog,$window,AllFactory){
	$scope.objMaterial=[];
	$scope.objSellers=[];
	$scope.objBuyers=[];
	$scope.objTodaysInward=[];
	$scope.objTodaysOrder=[];
    $scope.sMaterials=[];
    $scope.sMaterial={};  
    $scope.tMaterials=[];
    $scope.tMaterial={};
	$scope.showMaterialShortageDiv=false;
	$scope.hiddenObj=false;
/*	$scope.getGivenDateDetails=false;*/
/*	$scope.inwardShow=false;*/
	
	

    //------------------------------- show warehouses in md-select ------------------//
	$http.get('/WarehouseMgmt/warehouse/warehouseDetailsJson')
.then(function(response){
		$scope.warehouses=response.data;
/*		 $scope.warehouseN=$scope.warehouses[0].warehouseName;*/
	      $scope.warehouseName=$scope.warehouses[0].warehouseName;
	})
	
	
	$scope.wNames=[];
	
	//------------------------show Material Shortage-----------------------//
		$http.get("/WarehouseMgmt/inventory/materialShortageJson")
		.then(function(response){
			$scope.ShortageMaterials=response.data;
			
			for(i=0;i<$scope.ShortageMaterials.length;i++){
				if($scope.wNames.indexOf($scope.ShortageMaterials[i][0])==-1){
				$scope.wNames.push($scope.ShortageMaterials[i][0]);	
				}
			}
		/*	console.log($scope.wNames);*/
			if($scope.wNames.length==0){
				$scope.hiddenObj=true;
			}
		})

		
	
				//------------------------- show inventory Details(pie-chart)-------------------------//
        $scope.materials=[];
        $scope.material={};

    $http.get('/WarehouseMgmt/inventory/inventoryDetailsJson')
      .then(function(response){
      $scope.inventoryDetails=response.data; 
       for (var i = 0; i < $scope.inventoryDetails.length; i++) {

        if($scope.inventoryDetails[i].warehouse.warehouseName=="birla"){
        $scope.material={label: $scope.inventoryDetails[i].material.materialName,
                         value: $scope.inventoryDetails[i].availableQty
                      };
        $scope.materials.push($scope.material);
}}


      });

$scope.passW=function(id){
$scope.materials.length=0;
    $http.get('/WarehouseMgmt/inventory/inventoryDetailsJson')
      .then(function(response){
      $scope.inventoryDetails=response.data; 
       for (var i = 0; i < $scope.inventoryDetails.length; i++) {

        if(id==$scope.inventoryDetails[i].warehouse.warehouseName){
        $scope.material={label: $scope.inventoryDetails[i].material.materialName,
                         value: $scope.inventoryDetails[i].availableQty
                      };
        $scope.materials.push($scope.material);
}}

      });
					}


	$scope.dataSource = {
							"chart": {
							"caption": "Materials in Warehouse",
							"chartLeftMargin": "0",
							"chartRightMargin": "0",
							"chartTopMargin": "0",
							"chartBottomMargin": "0",
							"startingAngle": "90",
							"showValues": "1",
							"showLabels": "0",
							"showLegend": "1",
							"plotTooltext": "<b>$label<br/>Available Qty:$value</b>"
							},

// data array
"data":$scope.materials
};
	
	
					//------------------------ Show Top Materials -----------------------//
	$http.get('/WarehouseMgmt/order/topMaterialJson')
    .then(function(response){
    $scope.topMaterials=response.data;
	$scope.objMaterial.length=0;
    for (var j = 0; j < $scope.topMaterials.length; j++) {
    	$scope.sellerMaterial={label: $scope.topMaterials[j][2],
                		 value: $scope.topMaterials[j][0]
             };

    	$scope.objMaterial.push($scope.sellerMaterial);
	}

    });
    $scope.dataSource1 = {							//top material bar chart datasource
    		 "chart": {
    			 "caption": "Top materials",
    			 "chartLeftMargin": "0",
    		        "chartRightMargin": "0",
    		        "chartBottomMargin": "0",
    		        "xAxisName": "Materials",
    		        "yAxisName": "Quantity",
    		        "yAxisMaxValue": "200",
    		        "placevaluesInside": "0",
    		        "valueFontColor": "000000",
    		        "palettecolors": "008ae6",
    		        "rotateValues": "0",
    		        "rotateLabels":"0", 
    		        "showValues": "1",
    		        "showLabels": "0",
    		        "showLegend": "1",
    		        "divLineAlpha": "30",
    		        "plotTooltext": "<b>$label<br/>Total Quantity:$value</b>"
    		 },
    		 
    		 // data array
    		 "data":$scope.objMaterial
    		};
    
    				//----------------------Show Top Seller---------------------//
	$http.get('/WarehouseMgmt/order/topSellerJson')
    .then(function(response){
    $scope.topSellers=response.data;
	$scope.objSellers.length=0;
    for (var j = 0; j < $scope.topSellers.length; j++) {
    	$scope.seller={label: $scope.topSellers[j][1],
                		 value: $scope.topSellers[j][0]
             };

    	$scope.objSellers.push($scope.seller);
	}

    });
    $scope.dataSource2 = {											//top seller bar chart datasource
    		 "chart": {
    			 "caption": "Top Sellers",
    			 "chartLeftMargin": "0",
    		        "chartRightMargin": "0",
    		        "chartBottomMargin": "0",
    		        "xAxisName": "Sellers",
    		        "yAxisName": "Quantity",
    		        "yAxisMaxValue": "200",
    		        "placevaluesInside": "0",
    		        "valueFontColor": "000000",
    		        "palettecolors": "008ae6",
    		        "rotateValues": "0",
    		        "rotateLabels":"0", 
    		        "showValues": "1",
    		        "showLabels": "0",
    		        "showLegend": "1",
    		        "divLineAlpha": "30",
    		        "plotTooltext": "<b>$label<br/>Total Quantity:$value</b>"

    			 
    		 },
    		 
    		 // data array
    		 "data":$scope.objSellers
    		};
    
    				//----------------------show top buyers-----------------------//
	$http.get('/WarehouseMgmt/order/topBuyerJson')
    .then(function(response){
    $scope.topBuyers=response.data;
	$scope.objBuyers.length=0;

    for (var j = 0; j < $scope.topBuyers.length; j++) {
    	$scope.buyerMaterial={label: $scope.topBuyers[j][2],
                		 value: $scope.topBuyers[j][0]
             };

    	$scope.objBuyers.push($scope.buyerMaterial);
	}

    });
    $scope.dataSource3 = {										//top buyers bar chart datasource
    		 "chart": {
    			 "caption": "Top Contractors",
    			 "chartLeftMargin": "0",
    		        "chartRightMargin": "0",
    		        "chartBottomMargin": "0",
    		        "xAxisName": "Contractors",
    		        "yAxisName": "Quantity",
    		        "yAxisMaxValue": "200",
    		        "placevaluesInside": "0",
    		        "valueFontColor": "000000",
    		        "palettecolors": "008ae6",
    		        "rotateValues": "0",
    		        "rotateLabels":"0", 
    		        "showValues": "1",
    		        "showLabels": "0",
    		        "showLegend": "1",
    		        "divLineAlpha": "30",
    		        "plotTooltext": "<b>$label<br/>Total Quantity:$value</b>"
    		 },
    		 
    		 // data array
    		 "data":$scope.objBuyers
    		};

    //-----------------------get 1st feb 2017 inward and outward-----------------//
    $scope.GetGivenDateData=[];   
    var myDate =new Date();
    myDate.setDate(1);
    myDate.setMonth(1);
    myDate.setFullYear(2017);
    
	$scope.givenDate=myDate;
    $scope.GetGivenDateData.length=0;
	$scope.myDate=myDate;
	var obj={mydate:{myNewDate:$scope.givenDate}};

	$http({
        method  : 'POST',
        url     : '/WarehouseMgmt/inventory/todaysOrdersJson',
        data    : JSON.stringify(obj),
        headers : {'Content-Type': 'application/json; charset=utf-8'}
        
       }).then(function(response){
          var todaysOrders=response.data;
           $scope.GetGivenDateData.push({"outward":todaysOrders});
			       	$http({
			            method  : 'POST',
			            url     : '/WarehouseMgmt/inventory/todaysInwardsJson',
			            data    : JSON.stringify(obj),
			            headers : {'Content-Type': 'application/json; charset=utf-8'}
			            
			           }).then(function(response){
              var todaysInwards=response.data;
              $scope.GetGivenDateData.push({"inward":todaysInwards});
         	  $scope.sMaterials.length=0;
        	  $scope.tMaterials.length=0;

        	  todaysInwards=$scope.GetGivenDateData[1].inward; 
        		  
                 for (var i = 0; i < todaysInwards.length; i++) {
                  if("birla"==todaysInwards[i][0]){
                  $scope.sMaterial={label: todaysInwards[i][1],
                                   value: todaysInwards[i][2]
                                };
                  $scope.sMaterials.push($scope.sMaterial);
        /*              $scope.inwardShow=true;*/
        }}
                 
                 todaysOrders=$scope.GetGivenDateData[0].outward;   
                  for (var i = 0; i < todaysOrders.length; i++) {

                   if("birla"==todaysOrders[i][0]){
                   $scope.tMaterial={label: todaysOrders[i][1],
                                    value: todaysOrders[i][2]
                                 };
                   $scope.tMaterials.push($scope.tMaterial);
                   									}
                   }
                 
           });
       });


	//-------------------------------------send date function-------------------//
    $scope.sendDate=function(myDate){
    	$scope.GetGivenDateData.length=0;
    	$scope.givenDate=myDate;
    	var obj={mydate:{myNewDate:$scope.givenDate}};
		$http({
	        method  : 'POST',
	        url     : '/WarehouseMgmt/inventory/getGivenDateOrders',
	        data    : JSON.stringify(obj),
	        headers : {'Content-Type': 'application/json; charset=utf-8'}
	        
	       }).then(function(response){
	           $scope.todaysOrders=response.data;
	           $scope.GetGivenDateData.push({"outward":$scope.todaysOrders});

	       });
		$http({
	        method  : 'POST',
	        url     : '/WarehouseMgmt/inventory/getGivenDateInwards',
	        data    : JSON.stringify(obj),
	        headers : {'Content-Type': 'application/json; charset=utf-8'}
	        
	       }).then(function(response){
	           $scope.todaysInwards=response.data;
	           $scope.GetGivenDateData.push({"inward":$scope.todaysInwards});

	       });

    }
    
    
    
 //----------------------------------------------   
    $scope.TWInward=function(wName){
    	  $scope.sMaterials.length=0;
    	  $scope.tMaterials.length=0;
    	  $scope.todaysInwards=$scope.GetGivenDateData[1].inward; 

    		  
             for (var i = 0; i < $scope.todaysInwards.length; i++) {
              if(wName==$scope.todaysInwards[i][0]){
              $scope.sMaterial={label: $scope.todaysInwards[i][1],
                               value: $scope.todaysInwards[i][2]
                            };
              $scope.sMaterials.push($scope.sMaterial);
/*              $scope.inwardShow=true;*/
  }}

    
          $scope.todaysOrders=$scope.GetGivenDateData[0].outward;   

           for (var i = 0; i < $scope.todaysOrders.length; i++) {

            if(wName==$scope.todaysOrders[i][0]){
            $scope.tMaterial={label: $scope.todaysOrders[i][1],
                             value: $scope.todaysOrders[i][2]
                          };
            $scope.tMaterials.push($scope.tMaterial);
            									}
            }

          
    }										//--------------------------------
    $scope.dataSource5 = {
   		 "chart": {
   			 "caption": "Inward",
   			 "chartLeftMargin": "0",
   		        "chartRightMargin": "0",
   		        "chartBottomMargin": "0",
   		        "xAxisName": "Materials",
   		        "yAxisName": "Quantity",
   		        "yAxisMaxValue": "200",
   		        "placevaluesInside": "0",
   		        "valueFontColor": "000000",
   		        "palettecolors": "008ae6",
   		        "rotateValues": "0",
   		        "rotateLabels":"0", 
   		        "showValues": "1",
   		        "rotateLabels":"0",
   		        "showLabels": "0",
   		        "showLegend": "1",
   		        "divLineAlpha": "30",
   		        "plotTooltext": "<b>$label<br/>Total Quantity Sold:$value</b>"
   		 },
   		 
   		 // data array
   		 "data":$scope.sMaterials
   		};
    $scope.dataSource6 = {
      		 "chart": {
      			 "caption": " Order",
      			 "chartLeftMargin": "0",
      		        "chartRightMargin": "0",
      		        "chartBottomMargin": "0",
      		        "xAxisName": "Materials",
      		        "yAxisName": "Quantity",
      		        "yAxisMaxValue": "200",
      		        "placevaluesInside": "0",
      		        "valueFontColor": "000000",
      		        "palettecolors": "008ae6",
      		        "rotateValues": "0",
      		        "rotateLabels":"0", 
      		        "showValues": "1",
      		        "showLabels": "0",
      		        "showLegend": "1",
      		        "divLineAlpha": "30",
      		        "plotTooltext": "<b>$label<br/>Total Quantity Sold:$value</b>"
      		 },
      		 
      		 // data array
      		 "data":$scope.tMaterials
      		};


}]);	

