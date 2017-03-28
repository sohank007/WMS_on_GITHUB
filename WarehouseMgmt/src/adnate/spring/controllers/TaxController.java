package adnate.spring.controllers;

import java.text.ParseException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;
import adnate.spring.pojos.InvoiceTax;
import adnate.spring.pojos.TaxInfo;
import adnate.spring.services.ITaxService;

@Controller
@RequestMapping(value="/tax")
public class TaxController {
	@Autowired
	private ITaxService service;
	
	@RequestMapping("/taxDetails")
    public ModelAndView TaxDetails(){ 
		System.out.println("in TaxDetails()");
         //command is a reserved request attribute name, now use <form> tag to show object data  
        return new ModelAndView("TaxDetails","comd",new TaxInfo()); 
	}

	@RequestMapping(value= "/taxDetailsJson", method = RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<List<String>> getTax() {
		List<String>taxList = service.getAllTax();
		System.out.println("getTax() called");
		if(taxList.isEmpty()){
			System.out.println("Tax List Empty");
			
			return new ResponseEntity<List<String>>(taxList, HttpStatus.OK);
		}
		for (String tax : taxList) 
		{
			System.out.println(tax.toString());
		
		}
	return new ResponseEntity<List<String>>(taxList, HttpStatus.OK);			
	}
	
	
	@RequestMapping(value="/taxNamesByRegion/{region}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> getTaxNames(@PathVariable("region") String taxRegion) {
		List<String> taxList = service.getTaxNames(taxRegion);
		System.out.println("getTax() called");
		if(taxList.isEmpty()){
			System.out.println("Tax List Empty");
			
			return new ResponseEntity<List<String>>(taxList, HttpStatus.OK);
		}
		for (String tax : taxList) 
		{
			System.out.println(tax.toString());
		
		}
	return new ResponseEntity<List<String>>(taxList, HttpStatus.OK);			
	}
	
	
	@RequestMapping(value= "/taxJson", method = RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<List<TaxInfo>> getTaxDetails() {
		List<TaxInfo>taxList = service.getTaxDetails();
		System.out.println("getTax() called");
		if(taxList.isEmpty()){
			System.out.println("Tax List Empty");
			
			return new ResponseEntity<List<TaxInfo>>(taxList, HttpStatus.OK);
		}
		for (TaxInfo tax : taxList) 
		{
			System.out.println(tax.toString());
		
		}
	return new ResponseEntity<List<TaxInfo>>(taxList, HttpStatus.OK);			
	}
	
	@RequestMapping(value="/taxDetailsById/{invoiceId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InvoiceTax>>getInvoiceDetailsById(@PathVariable("invoiceId") int id) {
		System.out.println("id:"+id);
		List<InvoiceTax> taxList = service.findTaxByInvoiceId(id);
		System.out.println("getTax() called");
		if(taxList.isEmpty()){
			System.out.println("Tax List Empty");
			
			return new ResponseEntity<List<InvoiceTax>>(taxList, HttpStatus.OK);
		}
		for (InvoiceTax tax : taxList) 
		{
			System.out.println(tax.toString());
		
		}
	return new ResponseEntity<List<InvoiceTax>>(taxList, HttpStatus.OK);
		
		
	}
	
	@RequestMapping(value= "/sendTax", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Integer>> sendTax(@RequestBody String json)throws ParseException, JSONException {
	System.out.println(json);
	JSONObject jsonObject = new JSONObject(json);
	String taxName=jsonObject.getString("taxName");
	String taxRegion=jsonObject.getString("region");
	List<Integer> list=service.getTaxAmt(taxName, taxRegion);
	
	return new ResponseEntity<List<Integer>>(list, HttpStatus.OK);		
		
	}
	
	
/*	@RequestMapping(value= "/insertToInvoiceTax", method = RequestMethod.POST,consumes = "Application/Json",produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean insertToInvoiceTax(@RequestBody String json) throws ParseException, JSONException 
	{	
		System.out.println(json);
		JSONArray jsonarray=new JSONArray(json);
		System.out.println("jsonarray"+jsonarray);
		int invoiceId=0;
		for(int i=0;i<jsonarray.length()-1;i++){
			JSONObject taxDetails=jsonarray.getJSONObject(i);
			System.out.println("taxDetails:"+taxDetails);
			String taxName=taxDetails.getString("taxName");
			String taxRegion=taxDetails.getString("taxRegion");
			double taxAmt=taxDetails.getDouble("taxAmt");
			invoiceId=taxDetails.getInt("invoiceId");
			int tax_id=service.findTaxByName(taxName, taxRegion);
			InvoiceTax invoiceTax=new InvoiceTax();
			invoiceTax.setInvoiceId(invoiceId);
			invoiceTax.setTax_id(tax_id);;
			invoiceTax.setTaxableAmt(taxAmt);
			boolean invoice_tax_id=service.insertInvoiceTax(invoiceTax);
			System.out.println("invoice_tax_id:"+invoice_tax_id);
			
		}
		JSONObject obj= jsonarray.getJSONObject(jsonarray.length()-1);
		double total=obj.getDouble("total");
		double discount=obj.getDouble("discount");
		int number=service.updateTotalNDiscount(invoiceId, total, discount);
		return (number!=0);
		
		JSONObject jsonObject = new JSONObject(json);
		List<String>taxList = service.getAllTax();
		System.out.println("getTax() called");
		if(taxList.isEmpty()){
			System.out.println("Tax List Empty");
			
			return new ResponseEntity<List<String>>(taxList, HttpStatus.OK);
		}
		for (String tax : taxList) 
		{
			System.out.println(tax.toString());
		
		}
		
		
	}*/
	
}
