package adnate.spring.controllers;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/*import org.springframework.web.bind.annotation.ResponseBody;*/
import org.springframework.web.servlet.ModelAndView;

import adnate.spring.pdf.PDFBuilder;
import adnate.spring.pojos.Contractor;
import adnate.spring.pojos.Invoice;
import adnate.spring.pojos.InvoiceDetails;
import adnate.spring.pojos.InvoiceTax;
import adnate.spring.pojos.Material;
import adnate.spring.pojos.Sequence;
import adnate.spring.pojos.TaxInfo;
import adnate.spring.services.FcmServices;
import adnate.spring.services.IContractorServices;
import adnate.spring.services.IInvoiceServices;
import adnate.spring.services.IMaterialServices;
import adnate.spring.services.IOrderServices;
import adnate.spring.services.ITaxService;
import adnate.spring.services.IWarehouseServices;
import adnate.spring.services.SequenceService;

@Controller
@RequestMapping(value="/invoice")
public class InvoiceController {
	@Autowired
	private IOrderServices ordservice;
	@Autowired
	private IInvoiceServices service;
	@Autowired
	private IMaterialServices mtrlService;
	@Autowired
	private IWarehouseServices wrhService;
	@Autowired
	private IContractorServices ctrService;
	@Autowired
	private SequenceService seqService;
	@Autowired
	private ITaxService taxService;
	@Autowired
	FcmServices fcmService;
	
	@Autowired
	private ServletContext context;

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ MAIL SENDER +++++++++++++++++++++++++++++++++++++
	
	@Autowired(required=true)
    private JavaMailSender mailSender;
	
	public void setMail(JavaMailSender mailSender)
    {
        this.mailSender = mailSender;
    }
	
	private static String FILE = "E:/FinalWms/WarehouseMgmt123/Resources/";
	
	
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	@RequestMapping("/invoiceDetails")
    public ModelAndView contractorDetails(){ 
		System.out.println("in contractorDetails()");
         //command is a reserved request attribute name, now use <form> tag to show object data  
        return new ModelAndView("contractorDetails","comd",new Contractor()); 
	}
	
	
	
	@RequestMapping(value= "/viewInvoiceJson", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Invoice>>getInvoiceObject() {
		List<Invoice> invList = service.getInvoiceObject();
		System.out.println("getContractors() called");
		if(invList.isEmpty()){
			System.out.println("invoice List Empty");
			return new ResponseEntity<List<Invoice>>(HttpStatus.NO_CONTENT);
		}
		for (Invoice inv : invList) 
			{
				System.out.println(inv.toString());
			}
		return new ResponseEntity<List<Invoice>>(invList, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/invoiceDetailsJson", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InvoiceDetails>>getInvoiceDetails() {
		List<InvoiceDetails> invDetailsList = service.getInvoiceDetails();
		System.out.println("getInvoiceDetails() called");
		if(invDetailsList.isEmpty()){
			System.out.println("invDetailsList List Empty");
			return new ResponseEntity<List<InvoiceDetails>>(HttpStatus.NO_CONTENT);
		}
		for (InvoiceDetails inv : invDetailsList) 
			{
				System.out.println(inv.toString());
			}
		return new ResponseEntity<List<InvoiceDetails>>(invDetailsList, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/invoiceDetails/{invid}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InvoiceDetails>>getInvoiceDetails(@PathVariable("invid") int id) {
		List<InvoiceDetails> invDetailsList = service.findInvoiceDetails(id);
		System.out.println("getInvoiceDetails() called");
		if(invDetailsList.isEmpty()){
			System.out.println("invDetailsList List Empty");
			return new ResponseEntity<List<InvoiceDetails>>(HttpStatus.NO_CONTENT);
		}
		for (InvoiceDetails inv : invDetailsList) 
			{
				System.out.println(inv.toString());
			}
		return new ResponseEntity<List<InvoiceDetails>>(invDetailsList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/oDetailsByIdJson", method = RequestMethod.POST, consumes = "Application/Json")
	public ResponseEntity<List<Object[]>> oDetailsByIdJson(@RequestBody String json)throws ParseException, JSONException 
	{ 	
		
		System.out.println(json);
		String[] arr= json.split(",");
		List<Integer> intList=new ArrayList<Integer>();
		for(int i=0;i<arr.length;i++){
			intList.add(Integer.parseInt(arr[i]));
		}
		
		List<Object[]> orderDetList = ordservice.findAllInvoiceDetails(intList);
		Sequence sequence=seqService.getSequenceByTableName("invoicemaster");
		String prefix=sequence.getPrefix();
		int seqId=sequence.getSequence()+1;
		System.out.println("updated seq id: adding + 1 -->" + seqId);
		Object[] sequenceId=new Object[]{prefix+seqId};
		orderDetList.add(sequenceId);
/*		sequence.setSequence(seqId);*/
		System.out.println(sequence);
/*		seqService.updateSequence(sequence);*/

		// code from tejas 
		sequence.setPrefix(sequence.getPrefix());
		sequence.setSerial_id(sequence.getSerial_id());
		sequence.setSequence(seqId);
		sequence.setTablename(sequence.getTablename());
	    seqService.updateSequence(sequence);
		
		
		return new ResponseEntity<List<Object[]>>(orderDetList,HttpStatus.OK);
	
	}
	
	@RequestMapping(value = "/oDetailsByIdJsonAnd/{json}", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Object[]>> oDetailsByIdJsonAnd(@PathVariable("json") String json)throws ParseException, JSONException 
	{ 	
		
		json = json.replace("\"", "");
		System.out.println("String json:-"+json);
		String[] arr= json.split(",");
		List<Integer> intList=new ArrayList<Integer>();
		for(int i=0;i<arr.length;i++){
			intList.add(Integer.parseInt(arr[i]));
		}
		System.out.println(intList);
		List<Object[]> orderDetList = ordservice.findAllInvoiceDetails(intList);
		Sequence sequence=seqService.getSequenceByTableName("invoicemaster");
		String prefix=sequence.getPrefix();
		int seqId=sequence.getSequence()+1;
		Object[] sequenceId=new Object[]{prefix+seqId};
		orderDetList.add(sequenceId);
      /*sequence.setSequence(seqId);*/
		System.out.println(sequence);
		sequence.setPrefix(sequence.getPrefix());
		sequence.setSerial_id(sequence.getSerial_id());
		sequence.setSequence(seqId);
		sequence.setTablename(sequence.getTablename());
	    seqService.updateSequence(sequence);
        System.out.println("OrderDetailsList:-"+orderDetList.toString());
		return new ResponseEntity<List<Object[]>>(orderDetList,HttpStatus.OK);
	
	}
	
	
	
	@RequestMapping(value = "/invoiceDetailsByIdJson", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InvoiceDetails>> getInvoiceDetailsById(@RequestBody String json)throws ParseException, JSONException 
	{
		System.out.println("json for invDetByIdJson:" + json);
		int id=Integer.parseInt(json.toString());
		System.out.println(id);//findOrderDetailsById(id);
		List<InvoiceDetails> invDetList = service.findInvoiceDetails(id);
		System.out.println("getInvoiceDetailsById() called");
		if (invDetList.isEmpty()) {
			System.out.println("OrderDetails List Empty");
			return new ResponseEntity<List<InvoiceDetails>>(HttpStatus.NO_CONTENT);
		}
		for (Object ordrDet : invDetList) {
			System.out.println(ordrDet.toString());
		}
		return new ResponseEntity<List<InvoiceDetails>>(invDetList,
				HttpStatus.OK);
	}
	
	/*@RequestMapping(value = "/invoiceDetailsById/{invoiceId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InvoiceDetails>> getInvoiceDetailsByIdPath(@PathVariable("invoiceId") int invoiceId)throws ParseException, JSONException 
	{
		
		
		List<InvoiceDetails> invDetList = service.findInvoiceDetails(invoiceId);
		System.out.println("getInvoiceDetailsById() called");
		if (invDetList.isEmpty()) {
			System.out.println("invoiceDetails List Empty");
			return new ResponseEntity<List<InvoiceDetails>>(HttpStatus.NO_CONTENT);
		}
		for (Object ordrDet : invDetList) {
			System.out.println(ordrDet.toString());
		}
		return new ResponseEntity<List<InvoiceDetails>>(invDetList,
				HttpStatus.OK);
	}*/
	
	
	@RequestMapping(value="/invoiceTaxByInvoiceId/{invoiceId}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InvoiceTax>> getInvoiceTaxByInvoiceId(@PathVariable("invoiceId") int invoiceId ){
		List<InvoiceTax> invoiceTaxs=taxService.findTaxByInvoiceId(invoiceId);
		if(invoiceTaxs.isEmpty()){
			return new ResponseEntity<List<InvoiceTax>>(HttpStatus.NO_CONTENT);
		}
		for(InvoiceTax invoiceTax:invoiceTaxs){
			System.out.println(invoiceTax.toString());
		}
		return new ResponseEntity<List<InvoiceTax>>(invoiceTaxs,HttpStatus.OK);
	}
	
	@RequestMapping(value= "addInvoiceDetails", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE,
			consumes = "Application/Json")
	public @ResponseBody Boolean addInvoiceDetails(@RequestBody String json) throws JSONException, ParseException {

		System.out.println(json);

		JSONObject jsonObject = new JSONObject(json);
		JSONObject invoice= jsonObject.getJSONObject("invoice");
		Invoice invo=new Invoice();

		double total=invoice.getDouble(("total"));
		invo.setInvoiceAmtTotal(total);
		String pdfPath=invoice.getString("pdfPath");
		invo.setPdfPath(pdfPath);
		int contractorId=invoice.getInt("contractorId");
		Contractor contractor= ctrService.findContractor(contractorId);
		invo.setContractor(contractor);
		String purchaseOrder=invoice.getString("purchaseOrder");
		invo.setPurchaseOrder(purchaseOrder);
		double discount=invoice.getDouble("discount");
		invo.setDiscount(discount);
		String invoiceStatus=invoice.getString("invoiceStatus");
		invo.setInvoiceStatus(invoiceStatus);
		String sequenceId=invoice.getString("sequenceId");
		System.out.println("get seq id:" + sequenceId);
		invo.setSequenceId(sequenceId);
		System.out.println("sertting seq id into invo obj");
		String date=invoice.getString("invoiceDate");
		String date1=invoice.getString("validDate");
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Date parsedDate1 = df.parse(date);	
		Date parsedDate2 = df.parse(date1);	
		
		java.sql.Date invoiceDate = new java.sql.Date(parsedDate1.getTime());
		invo.setInvoiceDate(invoiceDate);
		
		java.sql.Date validDate = new java.sql.Date(parsedDate2.getTime());
		invo.setValidDate(validDate);
		double discountPt=invoice.getDouble("discountPt");
		invo.setDiscountPercentage(discountPt);
		
		int invoiceId=service.insertInvoice(invo);
		
		JSONObject invoiceDetailObj=jsonObject.getJSONObject("invoiceDetails");
		JSONArray jsonArrayInDet= invoiceDetailObj.getJSONArray("materialList");
		for(int i=0;i<jsonArrayInDet.length();i++){
			JSONObject invDe=jsonArrayInDet.getJSONObject(i);

			InvoiceDetails invoiceDetails=new InvoiceDetails();
			Invoice invoice1=service.findInvoice(invoiceId);
			invoiceDetails.setInvoice(invoice1);
			
			int m_id=invDe.getInt("materialId");
			Material m=mtrlService.findMaterial(m_id);
			invoiceDetails.setMaterial(m);
			double quantity=invDe.getDouble("orderQty");
			invoiceDetails.setQuantity(quantity);
			double sub_total=invDe.getDouble("mTotal");
			invoiceDetails.setTotalAmount(sub_total);
			
			service.insertInvoiceDetails(invoiceDetails);
			
		}
		
		JSONObject InvoiceTaxObj=jsonObject.getJSONObject("invoiceTax");
		JSONArray tax= InvoiceTaxObj.getJSONArray("taxList");
		for(int i=0;i<tax.length();i++){
			JSONObject taxObj=tax.getJSONObject(i);
			String taxName=taxObj.getString("taxName");
			String taxRegion=taxObj.getString("taxRegion");
			int tax_id=taxService.findTaxByName(taxName, taxRegion);
			double taxAmt=taxObj.getDouble("taxAmt");
			InvoiceTax invTax=new InvoiceTax();
			TaxInfo taxinfo=taxService.findTaxById(tax_id);
			invTax.setTax(taxinfo);
			invTax.setInvoiceId(invoiceId);
			invTax.setTaxableAmt(taxAmt);
			System.out.println(invTax);
			taxService.insertInvoiceTax(invTax);
		}
		
/*		service.insertInvoice(invoice);*/

		
		List<InvoiceDetails> invDetailsList = service.findInvoiceDetails(invoiceId);
		List<InvoiceTax> taxDetailsList = taxService.findTaxByInvoiceId(invoiceId);
		System.out.println("taxDetList:" + taxDetailsList);
		System.out.println("invoiceDetList:" + invDetailsList);
		String contextPath=context.getContextPath();
		String uploadPath=context.getRealPath(FILE);;
				
		System.out.println("uploadPath:-"+uploadPath);
		try {
			System.out.println("before call to pdf create");
			PDFBuilder.pdfCreation(invDetailsList, taxDetailsList,uploadPath);
			String updatedPdfPath = "E:/FinalWms/WarehouseMgmt123/Resources/" +sequenceId + ".pdf";
			//service.updatePdfPath(updatedPdfPath, invoiceId);
			System.out.println("\n pdf gen send\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("b4 call to emalio send");	
		/*EmailController emC = new EmailController();*/
		//System.out.println("mailsender" + mailSender.toString());
		//emC.setMail(mailSender);
		File file = new File(FILE  + sequenceId + ".pdf");
		String subject = "Invoice with invoice No " + sequenceId +"";
		String msg = "<html>" + "<body>" + " " 
        		+ "<p> Dear  " + contractor.getCtrName() + ", You can make payment for the "+ sequenceId + " through this link.</p>"
        		+ "<p> You can email us at gaurav.gupta@adnatesolutions.com or call us at 7507500582 for any clarifications. </p>"
        		+ "<p> Regards</p>"
        		+ "<p>" + contractor.getCtrName() + "</p>"
        		+ "<p>" + contractor.getCtrEmailId() + "</p>"
        		+ "<p> Adnate IT Solutions </p>"
        		+ "<img src='D:/eclipse_workspace/WarehouseMgmt123/Resources/adnate.png' alt='adnate' style='width:64px;height:64px'>"
        		+ "</body></html>";
		
		sendEmail(contractor.getCtrEmailId(), subject, msg, file );
		
		
		
		
		
		
		return true;
	}
	
	
public void sendEmail(String to, String subject, String msg, File file) {
		
		System.out.println("\nmethod ccalled\n");
		
		//System.out.println("ServletContext:" + servletContext.getContextPath());
		
		    try
		    {		    	
		    	
		    	mailSender.send(new MimeMessagePreparator() {
		    		
		    		  public void prepare(MimeMessage mimeMessage) throws MessagingException {
		    		    MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
		    		    //message.setFrom("");
		    		    message.setTo(to);
		    		    message.setSubject(subject);
		    		    message.setText(msg, true);
		    		    String absPath = file.getAbsolutePath();	
				        System.out.println("abspath:" + absPath);
		    		    message.addAttachment(file.getName(), file);
		    		  }
		    		});
		    			        
		         //mailSender.send(Mimemessage); 
		    }
		    	catch (Exception e) {
	            throw new MailParseException(e);
	       }

	        System.out.println("Mail Sent Successfully With Attachment.....!");	   
	}
	
@RequestMapping(value = "/files/{file_name}", method = RequestMethod.GET)
public void getFile(
    @PathVariable("file_name") String fileName, 
    HttpServletResponse response) {
	response.setContentType("application/pdf");
	File file=new File("E:/FinalWms/WarehouseMgmt123/Resources/"+fileName+".pdf");
    try {
    	System.out.println(fileName);
      // get your file as InputStream
    	/*String loadPath="E:\FinalWms\WarehouseMgmt123\Resources\"+fileName;*/
    	
      System.out.println(file);
    	FileInputStream is = new FileInputStream(file);
      // copy it to response's OutputStream
      org.apache.commons.io.IOUtils.copy( is,response.getOutputStream());
      response.flushBuffer();
    } catch (IOException ex) {
     System.out.printf("Error writing file to output stream. Filename was '{}'", file, ex);
      throw new RuntimeException("IOError writing file to output stream");
    }

}

	
/*	@RequestMapping(value = "/oDetailsByIdForInvoiceJson", method = RequestMethod.POST, consumes = "Application/Json")
	public ResponseEntity<Object[]> oDetailsByIdForInvoiceJson(@RequestBody String json)throws ParseException, JSONException 
	{ 	
		
		System.out.println(json);
		String[] arr= json.split(",");
		List<Integer> intList=new ArrayList<Integer>();
		for(int i=0;i<arr.length;i++){
			intList.add(Integer.parseInt(arr[i]));
		}
		
		List<Object[]> orderDetList = ordservice.findAllInvoiceDetails(intList);
		

		
		Invoice invoice=new Invoice();
		invoice.setInvoiceDate(new Date());
		Order ord=ordservice.findOrder(intList.get(0));
		Contractor contractor=ctrService.findContractor(ord.getContractor().getCtrId());
		invoice.setContractor(contractor);
		invoice.setInvoiceAmtTotal(0);
		Calendar c=Calendar.getInstance();
		c.add(Calendar.MONTH,3);
		invoice.setValidDate(c.getTime());
		invoice.setInvoiceStatus("pending");
		invoice.setDiscount(0.0);
		invoice.setPurchaseOrder(json);
		int invoiceId=service.insertInvoice(invoice);
		
		double sub_total=0.0;
		for(Object[] row:orderDetList){
			InvoiceDetails invDetail=new InvoiceDetails();
			Invoice inv=service.findInvoice(invoiceId);
			invDetail.setInvoice(inv);	
			Material m=mtrlService.findMaterial(Integer.parseInt(row[1].toString()));
			invDetail.setMaterial(m);
			Double quantity=Double.parseDouble(row[3].toString());
			invDetail.setQuantity(quantity);
			invDetail.setTotalAmount(m.getUnitPrice()*quantity);
			service.insertInvoiceDetails(invDetail);
			sub_total=sub_total+invDetail.getTotalAmount();
			System.out.println("invoice Details insert calles");
		}
		System.out.println("oDetailsByIdForInvoiceJson() called");
		System.out.println(sub_total);
		service.updateTotalAmt(sub_total, invoiceId);
		for (Object[] ordrDet : orderDetList) {
			System.out.println(ordrDet.toString());
		}
		
		List<InvoiceDetails> invDetailsList =service.findInvoiceDetails(invoiceId);
		
		try {
			PDFBuilder.pdfCreation(invDetailsList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object[] o=new Object[2];
		o[0]=invoiceId;
		
			int	orderid=intList.get(0);
			Order ordr=ordservice.findOrder(orderid);
			o[1]=ordr.getWarehouse().getwRegion();
		
		return new ResponseEntity<Object[]>(o,HttpStatus.OK);
	
	}	*/
	
/*	@RequestMapping(value= "/setValidTillDate", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE,
			 consumes = "Application/Json")
	public @ResponseBody void setValidTillDate(@RequestBody String json) throws JSONException, ParseException {
					JSONObject jsonObject=new JSONObject(json);
					String validDate=jsonObject.getString("validDate");
					int invoiceId=jsonObject.getInt("invoiceId");
					System.out.println("validDate:"+validDate);
					DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
					Date parsedDate = df.parse(validDate);		
					java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
					int result=service.setValidTillDate(sqlDate, invoiceId);
					System.out.println(result);
					
	}
	
	*/

	
	
	

}
