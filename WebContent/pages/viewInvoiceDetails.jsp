<div ng-app="WMS" style="width:100%; height:100%;">
<div style="text-align: center;font-size:xx-large; color:white;
		background-color: #357EBD;">Invoice</div>
<br>
<div>

	<table class="invoiceClass" >
		<tr>
			<td style="text-align: left;"><label style="font-size:medium;
	font-weight: bold; color: gray;">Bill To</label>&nbsp;&nbsp;&nbsp;&nbsp;	
		{{invoicemaster.contractor.ctrName}}</td>
			<td></td>
			<td></td>
			<td ><label style="font-weight: bold;color: gray;">Invoice No</label></td>
			<td>{{invoicemaster.sequenceId}}</td>
		</tr>
		<tr>
		<td></td>
		<td></td>
		<td></td>
			<td><label style="font-weight: bold;color: gray;">Invoice Date</label></td>
			<td><label>{{invoicemaster.invoiceDate | date:'MM/dd/yyyy'}}</label></td>
		</tr>
		<tr>
		<td></td>
		<td></td>
		<td></td>
			<td><label style="font-weight: bold;color: gray;">Due Date</label></td>
			<td><label>{{invoicemaster.validDate| date:'MM/dd/yyyy'}}</label></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
					<td></td>
			<td><label style="font-weight: bold;color: gray;" >PO</label></td>
			<td>{{invoicemaster.purchaseOrder}}</td>
		</tr>
	</table>
</div>
<br>
	<table class="invoiceDetClass">
		<thead>
			<tr>
				<th style="text-align: left;">Item</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Total</th>
			</tr>
		</thead>
		<tbody>
			<tr ng-repeat="inv in data ">
				<td style="text-align: left;">{{inv.material.materialName}}</td>	
				<td>{{inv.material.unitPrice}}</td>
				<td>{{inv.quantity}}</td>
				<td>{{inv.totalAmount}}</td>
			</tr>
		</tbody>
	</table>
<br>

	<table class="totalClass">
		<tr style="background-color: lightgray;">
		<td></td>
		<td></td>
			<td>Sub-total</td>
			<td>{{invoicemaster.subtotal}}</td>
		</tr>
		<tr>
		<td></td>
		<td></td>
			<td >Discount-({{invoicemaster.discountPercentage}}%) 
			</td>
			<td>{{invoicemaster.discount | number:2}} 
			<!-- <input ng-model="discount" style="outline: 5px;border-bottom-color:gray; border-width:0.5px;
			border-top:hidden;border-left:hidden;border-right:hidden;border-bottom-style:solid;
			background-color: white;width: 50%;"> -->
		</tr>

<!-- 		<tr ng-hide="taxes.length==0">
				<td></td>
		<td></td>
			<td><md-icon md-svg-icon="scripts/svgImgs/plus_icon.svg" ng-click="addTaxObj()"></md-icon>Tax
			<select ng-model="taxName" ng-change="sendTax(taxName)" ng-show="showSelect"
			 style="font-size: small; border-radius: 4px;">
			<option ng-repeat="tax in taxes" >{{tax}}</option>
			</select>
			{{taxValue}}%
			</td>
			<td  style="text-align: left;">
			 {{taxapplied | number:2}} 
			 <input ng-model="taxapplied" style="outline: 5px;border-bottom-color:gray; border-width:0.5px;
			border-top:hidden;border-left:hidden;border-right:hidden;border-bottom-style:solid;
			background-color: white;width: 50%;" disabled> </td>
		</tr> -->
		
		<tr ng-repeat="row1 in rows">
				<td></td>
		<td></td>
		<td>{{row1.TaxName}}({{row1.TaxPt}}%)</td>
		<td>{{row1.TaxAmt | number:2}}</td>		
		</tr>
		<tr style="background-color: lightgray;">
				<td></td>
				<td></td>
				<td>Total</td>
				<td>{{invoicemaster.invoiceAmtTotal | number:2}}</td>
		</tr>
		<tr>
		<td colspan="4" style="text-align: center;"><md-button  style="background-color:lightgray;" 
		ng-click="downloadPDF()">Download</md-button>
		<md-button  style="background-color:lightgray;" ng-click="back()">Cancel</md-button>
		</td></tr>
	</table>

</div>
