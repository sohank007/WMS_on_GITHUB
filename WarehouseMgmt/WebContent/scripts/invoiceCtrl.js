var wms=angular.module("WMS");
wms.controller("generateInvoiceCtrl",["$http","$scope","AllFactory","$filter",'$window','$mdDialog',
                                      function($http,$scope,AllFactory,$filter,$window,$mdDialog){
	var orgId=AllFactory.getOrganisationId();
	$scope.myDate=new Date();
	var obj=AllFactory.getGivenId();
	$scope.disableDiscountSelector=false;
	$scope.invoicemaster={};
	$scope.invoicemaster.invoiceDate=new Date();
	$scope.invoicemaster.invoiceStatus="pending";
	$scope.invoicemaster.purchaseOrder=obj.toString();
	$scope.invoicemaster.pdfPath="";
	$scope.subtotalInv=0;
	$scope.row={taxName:"",
			taxValue:null,
			taxAmt:null,
			taxRegion:null,
			};

	$scope.rows=[];
	$scope.taxes=[];
	$scope.showSelect=false;
	
	$http.post("/WarehouseMgmt/invoice/oDetailsByIdJson",obj.toString())
	.then(function(response){
		$scope.data=response.data;
		console.log(response.data);
		$scope.invoicemaster.contractorId=$scope.data[0][1];
		$scope.invoicemaster.contractorName=$scope.data[0][2];
		$scope.invoicemaster.sequenceId=$scope.data[response.data.length-1][0];
		$scope.region=$scope.data[0][8];
		$scope.invoiceDetails=[];
	
		for(var i=0;i<$scope.data.length-1;i++){
			$scope.invoiceDetail={};
			$scope.invoiceDetail.materialName=$scope.data[i][4];
			$scope.invoiceDetail.materialId=$scope.data[i][3];
			$scope.invoiceDetail.mUnitPrice=$scope.data[i][5];
			$scope.invoiceDetail.orderQty=$scope.data[i][6];
			$scope.invoiceDetail.mTotal=$scope.data[i][7];
			$scope.invoiceDetail.unitOfMeasure=$scope.data[i][9];
			$scope.subtotalInv+=$scope.invoiceDetail.mTotal;
			$scope.invoiceDetails.push($scope.invoiceDetail);
			
		}		
		
		$scope.removeMaterial=function(invoiceDetails){
			console.log(invoiceDetails);
			indexOfRow=$scope.invoiceDetails.indexOf(invoiceDetails);
			$scope.invoiceDetails.splice(indexOfRow,1);
		}
		//$scope.invoicemaster.subtotal=$scope.subtotalInv;
		$scope.total=$scope.subtotalInv;
		
		//names of taxes list 
		console.log($scope.region);
		$http.get("/WarehouseMgmt/tax/taxNamesByRegion/"+$scope.region)
		.then(function(response){
			console.log(response.data);
			for(var i=0;i<response.data.length;i++){
			$scope.taxes.push(response.data[i]);
			}
		});
	});


	// send discount entered by user to backend
	$scope.sendDiscount=function(discountapplied){
		$scope.total=$scope.subtotalInv;
		$scope.subtotal=$scope.total;
		$scope.discount=$scope.subtotalInv*discountapplied/100;
		$scope.invoicemaster.discount=$scope.discount;
		$scope.invoicemaster.discountPt=discountapplied;
		//$scope.invoicemaster.discountPt=discountapplied;// comented
		$scope.total=$scope.subtotalInv-$scope.discount;
		$scope.subtotal=$scope.total;
	}

	//add new tax to invoice
	$scope.addTaxObj=function(){
		$scope.showSelect=true;
		$scope.disableDiscountSelector=true;
		$scope.taxName=null;
	}
	
	//select tax and send to rows function
	$scope.sendTax=function(taxName){
	if(taxName==null){
		
	}else{
		obj={
				taxName:taxName,
				region:$scope.region
			}
		$http.post("/WarehouseMgmt/tax/sendTax",obj)
		.then(function(response){
			$scope.taxAmt=response.data[0];//tax amount in %
			$scope.taxapplied=$scope.subtotal*$scope.taxAmt/100;
			$scope.row={taxName:$scope.taxName,
					taxValue:$scope.taxAmt,
					taxAmt:$scope.taxapplied,
					taxRegion:$scope.region,
					};
			$scope.rows.push($scope.row);
			$scope.total=$scope.total+$scope.taxapplied;
			var taxIndex=$scope.taxes.indexOf(taxName);
			
			$scope.taxes.splice(taxIndex,1);
			$scope.showSelect=false;
		})
	}	}
	//to reduce that tax from rows
	$scope.send=function(selectrow){
		indexOfRow=$scope.rows.indexOf(selectrow);
		$scope.taxes.push(selectrow.taxName);
		$scope.total=$scope.total-selectrow.taxAmt;
		$scope.rows.splice(indexOfRow,1);
console.log("aa"+$scope.taxes);
	}
	
	$scope.sendValidTill=function(validDate){
		$scope.invoicemaster.validDate=validDate;
		$scope.invoicemaster.validDate.setDate($scope.invoicemaster.validDate.getDate() + 1);
	}

	$scope.submitDetails=function(){
			debugger;

		$scope.invoicemaster.total= Math.round(($scope.total) * 100) / 100;
		$scope.invoicemaster.orgId=AllFactory.getOrganisationId();
			$scope.total={"invoice":$scope.invoicemaster,
							"invoiceDetails":$scope.invoiceDetails,
							"invoiceTax":$scope.rows
					     }
			console.log(JSON.stringify($scope.total));
			AllFactory.setContractor($scope.total);
			$http.post("/WarehouseMgmt/invoice/addInvoiceDetails",JSON.stringify($scope.total))
			.then(function(response){
				 $mdDialog.show({
          		   template: "<md-dialog id='alertAddMaterial'>" +   
          		  
          		   " <div style='margin: 10px 10px 0px;'>" +"<label style='font-weight:100'>Your invoice with id " 
          		   +response.data +" has been inserted successfully.</label> </div>"+
          		   "<div style='margin: 10px 10px;border-radius:4px;'" +
          		   " layout='row' layout-align='center center'>" +
          		   "    <md-button class='md-raised' ng-click='closeDialog()' >" +
          		   '      Ok' +
          		   '    </md-button>' +
          		   '  </div>' +
          		   '</md-dialog>',
          		   controller: function($scope, $mdDialog,$window){
          		      $scope.closeDialog = function(){
          		       $mdDialog.hide();
          		 	$window.location.href="#!/viewInvoice";
          		     };
          		   },
          		  
          		});
			});

		
		
	}
	$scope.cancel=function(){
		$window.location.href="#!/ViewOrder";
	}
	
/*



	
// to get invoiceJson data
$http.get("http://localhost:9090/WarehouseMgmt/invoice/invoiceDetails/"+id)
.success(function(data){
	$scope.invoiceDetails=data;
	for(var i=0;i<$scope.invoiceDetails.length;i++){
		$scope.invoiceDetail=$scope.invoiceDetails[i].invoice;
		$scope.invoiceDetail.validDate=new Date($scope.invoiceDetails[i].invoice.validDate);
		$scope.invoiceDetail.validDate=$filter('date')($scope.invoiceDetails[i].invoice.validDate, "MM/dd/yyyy");  // for type="date" binding

		$scope.subtotal=$scope.invoiceDetail.invoiceAmtTotal;		//set subtotal=invoiceAmt 
	}
	
});

	$scope.submitDetails=function(){
		$scope.dataToBeSent=$scope.rows;
		$scope.dataToBeSent.push({"total":$scope.total,"discount":$scope.discount});
		console.log(JSON.stringify($scope.dataToBeSent));
		$http.post("http://localhost:9090/WarehouseMgmt/tax/insertToInvoiceTax",JSON.stringify($scope.dataToBeSent))
		.success(function(){
			console.log("record inserted");
		})
	}
*/
	
}]);
wms.controller("viewInvoiceDetailsCtrl",["$http","$scope","AllFactory",'$window', function($http,$scope,AllFactory, $window){
	$scope.invoiceId=AllFactory.getInvoiceId();
	$scope.rows=[];
	$scope.row={
			TaxName:"",
			TaxPt:null,
			TaxAmt:null
	}
/*	console.log($scope.total);

	$scope.invoicemaster=$scope.total[0].invoice;
	console.log($scope.invoicemaster);
	$scope.invoiceDetails=$scope.total[1].invoiceDetails;
	console.log($scope.invoiceDetails);
	$scope.rows=$scope.total[2].invoiceTax;
	console.log($scope.rows);*/
	
	console.log($scope.invoiceId);
	
	$http
	.get(
			'/WarehouseMgmt/invoice/invoiceDetails/'+$scope.invoiceId)
	.then(
			function(response) {				
				$scope.data = response.data;
				$scope.invoicemaster=$scope.data[0].invoice;
				$scope.downloadlink="/WarehouseMgmt/invoice/files/"+ $scope.invoicemaster.sequenceId+".pdf";
				console.log($scope.downloadlink);
//				$scope.invoiceDetails=$scope.data[1].invoiceDetails;
				console.log($scope.invoicemaster);
				$scope.invoicemaster.subtotal=0;
				for (var i = 0; i < $scope.data.length; i++) {
					$scope.invoicemaster.subtotal+=$scope.data[i].totalAmount
						
				}
		/*		$scope.discountPt=$scope.invoicemaster.subtotal-($scope.invoicemaster.subtotal*$scope.data[0]);*/
				$http.get("/WarehouseMgmt/tax/taxDetailsById/"+$scope.invoiceId)
				.then(function(response){
					data=response.data;
					for(var i=0;i<data.length;i++){
						$scope.row={
								TaxName:data[i].tax.taxName,
								TaxPt:data[i].tax.taxValue,
								TaxAmt:data[i].taxableAmt
						}
						$scope.rows.push($scope.row);
					}
				})
			});
	
	$scope.back=function(){
		$window.location.href="#!/viewInvoice";
	}

/*	$scope.downloadPDF= function(){
				$window.location.href
			}*/
	
	
	
	
}])
                                                                      
wms.controller("invoiceCtrl",["$http","$scope","AllFactory",'$window', function($http, $scope, AllFactory, $window) {
	var orgId=AllFactory.getOrganisationId();
	$scope.whenInvoiceClicked=false;
	$http.get("/WarehouseMgmt/invoice/invoiceByOrgId/"+orgId)
	.then(function(response){
		$scope.invoices=response.data;
	});
	
	$scope.sendInvoiceId=function(invoiceId){
		$scope.whenInvoiceClicked=true;
		AllFactory.setInvoiceId(invoiceId);
		$window.location.href="#!/viewInvoiceDetails/";

	}
	
	$scope.sorting=true;
	vari="invoiceId";
	$scope.reverse=true;
	$scope.sortingMaterial=function(vari){
		$scope.variable=vari;
		if($scope.sorting==true){//true for up arrow
			$scope.sorting=false;
			$scope.reverse=true;

		}
		else{//false for down arrow
			$scope.sorting=true;
			$scope.reverse=false;
		}
	}
	
	
}

])

