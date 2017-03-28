<div  style="width:100%; height:100%;">
<form name="generateInvoiceForm">
<div style="text-align: center;font-size:xx-large; color:white;
		background-color: #357EBD;">Invoice</div>
<br>
<div>

	<table class="invoiceClass">
		<tr>
			<td style="text-align: left;"><label style="font-size:medium;
	font-weight: bold; color: gray;">Bill To</label>&nbsp;&nbsp;&nbsp;&nbsp;	
		{{invoicemaster.contractorName}}</td>		
			<td style="width: 200px;"></td>
			<td></td>
			<td style="width: 120px;min-width: 120px;"><label style="font-weight: bold;color: gray;">Invoice No</label></td>
			<td  style="text-align: right;padding-right: 40px;">{{invoicemaster.sequenceId}}</td>
		</tr>
		<tr>
		<td></td>
		<td style="width: 200px;"></td>
		<td></td>
			<td style="width: 120px;min-width: 120px;"><label style="font-weight: bold;color: gray;">Invoice Date</label></td>
			<td style="text-align: right;padding-right: 20px;">
			<md-datepicker ng-model="invoicemaster.invoiceDate" id="invoiceDate" disabled></md-datepicker>
			
		<!-- 	<label>{{invoicemaster.invoiceDate | date:'MM/dd/yyyy'}}</label> --></td>
		</tr>
		<tr>
		<td></td>
		<td style="width: 200px;"></td>
					<td></td>
			<td style="width: 120px;min-width: 120px;"><label style="font-weight: bold;color: gray;">Due Date</label></td>
			<td style="text-align: right;padding-right: 20px;">
			<md-datepicker ng-change="sendValidTill(invoicemaster.validDate)"  
			ng-model="invoicemaster.validDate " md-placeholder="Enter date"  md-min-date="myDate" required></md-datepicker>
			<br>
			
<!-- 			<input ng-change="sendValidTill(invoicemaster.validDate)" ng-model="invoicemaster.validDate"
			type="date" style="border: none; border-bottom: 1px solid gray;font-size: medium;"> --><!-- {{invoiceDetail.validDate | date:'dd-MM-yyyy'}} --></td>
		</tr>
		<tr>
			<td></td>
			<td style="width: 200px;"></td>
						<td></td>
			<td style="width: 120px;min-width: 120px;"><label style="font-weight: bold;color: gray;text-align: right;" >PO</label></td>
			<td style="text-align: right;padding-right: 20px;"><label style="margin-right: 20px;font-weight:100;">{{invoicemaster.purchaseOrder}}</label></td>
		</tr>
	</table>
</div>
	<table class="invoiceDetClass" >
		<thead>
			<tr>
				<th style="text-align: left;width: 5%;"></th>
				<th style="text-align: left;width: 20%;">Item</th>
				<th style="text-align: left;width:15%;">Unit of Measure</th>
				<th style="text-align: right;width:15%;">Unit Price</th>
				<th style="text-align: right;">Order Quantity</th>
				<th style="text-align: right;">Total</th>
			</tr>
		</thead>
		<tbody>
			<tr ng-repeat="inv in invoiceDetails">
				<td style="text-align: left;width: 5%">
				<md-icon md-svg-icon="scripts/svgImgs/remove_icon_alert.svg"
				ng-click="removeMaterial(inv)"></md-icon></td>
				<td style="text-align: left;width: 20%;">{{inv.materialName}}</td>
				<td style="text-align: left;width:15%;">{{inv.unitOfMeasure}}</td>	
				<td style="text-align: right;width:15%;">{{inv.mUnitPrice | number:2}}</td>
				<td style="text-align: right;">{{inv.orderQty| number:2}}</td>
				<td style="text-align: right;">{{inv.mTotal | number:2}}</td>
			</tr>
		</tbody>
	</table>


	<table class="totalClass">
		<tr style="background-color: lightgray;">
		<td></td>
		<td></td>
		<td></td>
			<td>Sub-total</td>
			<td>{{subtotalInv | number:2}}</td>
		</tr>
		<tr>
		<td></td>
		<td></td><td></td>
			<td>Discount(%) 
			<input ng-model="discountapplied" style="border: none;border-bottom: 1px solid gray;
			font-size: small;width:50px; text-align: right;"
			 ng-change="sendDiscount(discountapplied)"
			ng-disabled="disableDiscountSelector" type="number" id="discount" required>
			</td>
			<td>{{discount | number:2}} 
			<!-- <input ng-model="discount" style="outline: 5px;border-bottom-color:gray; border-width:0.5px;
			border-top:hidden;border-left:hidden;border-right:hidden;border-bottom-style:solid;
			background-color: white;width: 50%;"> -->
		</tr>

		<tr ng-hide="taxes.length==0">
				<td></td>
		<td></td><td></td>
			<td><md-icon md-svg-icon="scripts/svgImgs/plus_icon.svg" ng-click="addTaxObj()"></md-icon>Tax
			<select ng-model="taxName" ng-change="sendTax(taxName)" ng-show="showSelect"
			 style="font-size: small; border-radius: 4px;">
			<option ng-repeat="tax in taxes" >{{tax}}</option>
			</select>
			<!-- {{taxValue}}% -->
			</td>
			<td></td>
			<!--<td  style="text-align: left;">
			 {{taxapplied | number:2}} 
			 <input ng-model="taxapplied" style="outline: 5px;border-bottom-color:gray; border-width:0.5px;
			border-top:hidden;border-left:hidden;border-right:hidden;border-bottom-style:solid;
			background-color: white;width: 50%;" disabled> </td>-->
		</tr>
		
		<tr ng-repeat="row1 in rows">
				<td></td>
		<td></td><td></td>
		<td><md-icon md-svg-icon="scripts/svgImgs/remove_icon.svg" ng-click="send(row1)"></md-icon>
		{{row1.taxName}}({{row1.taxValue}}%) 
		</td>
		<td>{{row1.taxAmt | number:2}}</td>		
		</tr>
		<tr style="background-color: lightgray;">
				<td></td>
		<td></td><td></td>
			<td>Total</td>
			<td>{{total | number:2}}</td>
		</tr>
		<tr ><td colspan="5" style="text-align: center;"><md-button ng-click="submitDetails(invoice.invoiceId)"
		  ng-disabled="generateInvoiceForm.$invalid" class="md-raised">Submit</md-button>
		<md-button ng-click="cancel()" class="md-raised">Cancel</md-button></td></tr>
	</table>
</form>
</div>
