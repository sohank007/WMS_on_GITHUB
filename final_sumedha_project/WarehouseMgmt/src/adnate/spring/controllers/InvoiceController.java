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
import adnate.spring.pojos.OrderDetails;
import adnate.spring.pojos.Sequence;
import adnate.spring.pojos.TaxInfo;
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
	private ServletContext context;

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ MAIL SENDER +++++++++++++++++++++++++++++++++++++
	
	@Autowired(required=true)
    private JavaMailSender mailSender;
	
	public void setMail(JavaMailSender mailSender)
    {
        this.mailSender = mailSender;
    }
	
/*	private static String FILE = "D:/eclipse_workspace/WarehouseMgmt123/Resources/";*/
	
	private static String fileResource = "/Resources/";
	
	
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	@RequestMapping("/invoiceDetails")
    public ModelAndView contractorDetails(){ 
		System.out.println("in contractorDetails()");
         //command is a reserved request attribute name, now use <form> tag to show object data  
        return new ModelAndView("contractorDetails","comd",new Contractor()); 
	}
	
	@RequestMapping(value="/invoiceByOrgId/{orgId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Invoice>> getInvoiceByOrgId(@PathVariable("orgId") String OrganisationId) {
		List<Invoice> invoiceList = service.getInvoiceByOrgId(OrganisationId);
		System.out.println("getInvoiceByOrgId() called");
		if(invoiceList.isEmpty()){
			System.out.println("MaterialListByOrgId is Empty");
			
			return new ResponseEntity<List<Invoice>>(invoiceList, HttpStatus.OK);
		}
/*		for (Invoice invoice : invoiceList) 
		{
			//System.out.println(whouse.toString());
		
		}*/
		return new ResponseEntity<List<Invoice>>(invoiceList, HttpStatus.OK);			
	}
	
	@RequestMapping(value= "/viewInvoiceJson", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Invoice>>getInvoiceObject() {
		List<Invoice> invList = service.getInvoiceObject();
		System.out.println("getContractors() called");
		if(invList.isEmpty()){
			System.out.println("invoice List Empty");
			return new ResponseEntity<List<Invoice>>(HttpStatus.NO_CONTENT);
		}
	/*	for (Invoice inv : invList) 
			{
				System.out.println(inv.toString());
			}*/
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
	/*	for (InvoiceDetails inv : invDetailsList) 
			{
				System.out.println(inv.toString());
			}*/
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
	/*	for (InvoiceDetails inv : invDetailsList) 
			{
				System.out.println(inv.toString());
			}*/
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
/*		for (Object ordrDet : invDetList) {
			System.out.println(ordrDet.toString());
		}*/
		return new ResponseEntity<List<InvoiceDetails>>(invDetList,
				HttpStatus.OK);
	}
	
	
	@RequestMapping(value= "addInvoiceDetails", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE,
			consumes = "Application/Json")
	public @ResponseBody int addInvoiceDetails(@RequestBody String json) throws JSONException, ParseException {
/*		JSONObject jsonObject = new JSONObject(json);*/
		System.out.println("addInvoiceJson:" + json);
		
		List<Integer> mtrlIdList = new ArrayList<Integer>();
		List<Integer> mtrlIdListInObjList = new ArrayList<Integer>(); 
		
		JSONObject obj=new JSONObject(json);
		
		String ctxPath = context.getContextPath();
		System.out.println("ctxPath : " + ctxPath);
		//System.out.println("/WarehouseMgmt");
		String realPath = context.getRealPath("/");
		System.out.println("ReailPath passing fileResrc : " + realPath);
		//
		
		JSONObject Invoicejson= obj.getJSONObject("invoice");
		System.out.println(Invoicejson);
		
		Invoice invo=new Invoice();
		/*InvoiceDetails invoiceDetails=new InvoiceDetails();*/
		
		double total=Invoicejson.getDouble(("total"));
		invo.setInvoiceAmtTotal(total);
		String pdfPath=Invoicejson.getString("pdfPath");
		invo.setPdfPath(pdfPath);
		int contractorId=Invoicejson.getInt("contractorId");
		Contractor contractor= ctrService.findContractor(contractorId);
		invo.setContractor(contractor);
		String purchaseOrder=Invoicejson.getString("purchaseOrder");
		invo.setPurchaseOrder(purchaseOrder);
		double discount=Invoicejson.getDouble("discount");
		invo.setDiscount(discount);
		String invoiceStatus=Invoicejson.getString("invoiceStatus");
		invo.setInvoiceStatus(invoiceStatus);
		String sequenceId=Invoicejson.getString("sequenceId");
		System.out.println("get seq id:" + sequenceId);
		invo.setSequenceId(sequenceId);
		System.out.println("sertting seq id into invo obj");
		String date=Invoicejson.getString("invoiceDate");
		String date1=Invoicejson.getString("validDate");
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Date parsedDate1 = df.parse(date);	
		Date parsedDate2 = df.parse(date1);	
		
		java.sql.Date invoiceDate = new java.sql.Date(parsedDate1.getTime());
		invo.setInvoiceDate(invoiceDate);
		java.sql.Date validDate = new java.sql.Date(parsedDate2.getTime());
		invo.setValidDate(validDate);
		double discountPt=Invoicejson.getDouble("discountPt");
		invo.setDiscountPercentage(discountPt);
		String orgId=Invoicejson.getString("orgId");
		invo.setOrgId(orgId);
		int invoiceId=service.insertInvoice(invo);
		
		JSONArray invoiceDetailObj=obj.getJSONArray("invoiceDetails");

		for(int i=0;i<invoiceDetailObj.length();i++){
			JSONObject invDe=invoiceDetailObj.getJSONObject(i);

			InvoiceDetails invoiceDetails=new InvoiceDetails();
			Invoice invoice1=service.findInvoice(invoiceId);
			invoiceDetails.setInvoice(invoice1);
			
			int m_id=invDe.getInt("materialId");
			mtrlIdList.add(m_id);
			System.out.println(mtrlIdList);
			
			Material m=mtrlService.findMaterial(m_id);
			invoiceDetails.setMaterial(m);
			double quantity=invDe.getDouble("orderQty");
			invoiceDetails.setQuantity(quantity);
			double sub_total=invDe.getDouble("mTotal");
			invoiceDetails.setTotalAmount(sub_total);
			
			service.insertInvoiceDetails(invoiceDetails);
			
		}
		
		JSONArray tax= obj.getJSONArray("invoiceTax");
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
		
		String[] arr = invo.getPurchaseOrder().split(",");
		
		List<Integer> intList=new ArrayList<Integer>();
		for(int i=0;i<arr.length;i++){
			intList.add(Integer.parseInt(arr[i]));
		}
		
		
		List<OrderDetails> objList = ordservice.findOrderDetailsById(intList);
		System.out.println("\n object List: to check materialIds against it \n" + objList.toString());

		for(Integer number : mtrlIdList){
			System.out.println("number in mtrlIdList: -> " + number);
		}
		
		for(int i=0; i<objList.size(); i++){
			mtrlIdListInObjList.add(objList.get(i).getMaterial().getMaterialID());
			System.out.println("mtrlIdListInObjList in InvoiceController: " + mtrlIdListInObjList);
		}
		
		if(mtrlIdList.size()!=mtrlIdListInObjList.size()){
			System.out.println("inside If Condition: ");
			System.out.println("mtrlIdList size NOT SAME as mtrlIdListInObjList");
			
			List<OrderDetails> orderDetList = ordservice.getOrderDetailsInvoiceStatus(mtrlIdList, intList);
			
			for(int u=0; u<orderDetList.size(); u++){
				String ordDetInvStatus = orderDetList.get(u).getInvoiceStatus();
				
				if(ordDetInvStatus.equals("Delivered")){
					ordservice.updateInvoiceStatusOrderDetails("Invoiced", mtrlIdList, intList);
				}
							
				else {
					if (ordDetInvStatus.equals("Delivered")){
						List<Object> incompleteInvoiceList = ordservice.completeInvoiceOrderDetails("Delivered", mtrlIdList, intList);
						
						System.out.println("incompleteInvoiceList: -- > " + incompleteInvoiceList.toString());
						
							ordservice.updateInvoiceStatusOrderDetails("Invoiced", mtrlIdList, intList);
							System.out.println("executing method above: -->");
							System.out.println("will execute the method below: -->");
					}

				}
				
			}
			
			
		}
		
		else if(mtrlIdList.size()==mtrlIdListInObjList.size()){
			System.out.println("inside else If Condition: ");
			System.out.println("mtrlIdList size same as mtrlIdListInObjList");
			ordservice.updateInvoiceStatusOrderDetails("Invoiced", mtrlIdList, intList);
			
		}
		
		
		
/*		service.insertInvoice(invoice);*/

		
		List<InvoiceDetails> invDetailsList = service.findInvoiceDetails(invoiceId);
		List<InvoiceTax> taxDetailsList = taxService.findTaxByInvoiceId(invoiceId);
/*		File file = new File(realPath + fileResource + sequenceId + ".pdf");*/
		File filePath = new File(realPath + fileResource);
		boolean success = filePath.mkdir();
		if(success)
		System.out.println("fileDirectory created");
		System.out.println("file :" + filePath);
		//+ sequenceId + ".pdf"
		File newPdfFile = new File(filePath, sequenceId + ".pdf");
		System.out.println("newPdfFile : " + newPdfFile);
/*		
		File adnateImg = new File(realPath + fileResource);
/*		System.out.println("taxDetList:" + taxDetailsList);
		System.out.println("invoiceDetList:" + invDetailsList);*/
		
		try {
			System.out.println("before call to pdf create");
			PDFBuilder.pdfCreation(invDetailsList, taxDetailsList, newPdfFile);
			//String updatedPdfPath = "D:/eclipse_workspace/WarehouseMgmt123/Resources/" +sequenceId + ".pdf";
			System.out.println("\n pdf gen send\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("b4 call to emalio send");	
		//System.out.println("ReailPath using fileSeperator : D:\Java_WorkSpace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\WarehouseMgmt");
		String subject = "Invoice with invoice No " + sequenceId +" ";
		String msg = "<html>" + "<body>" + " " 
        		+ "<p> Dear  " + contractor.getCtrName() + ", You can make payment for the "+ sequenceId + " through this link.</p>"
        		+ "<p> You can email us at gaurav.gupta@adnatesolutions.com or call us at 7507500582 for any clarifications. </p>"
        		+ "<p> Regards</p>"
        		+ "<p>" + contractor.getCtrName() + "</p>"
        		+ "<p>" + contractor.getCtrEmailId() + "</p>"
        		+ "<p> Adnate IT Solutions </p>"
        		+ "<img src='adnateImg/adnate.png' alt='adnate' style='width:64px;height:64px'>"
        		+ "</body></html>";
		
		sendEmail(contractor.getCtrEmailId(), subject, msg, newPdfFile );
		
		return invoiceId;
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
	String realPath = context.getRealPath("/");
	System.out.println("ReailPath passing fileResrc : " + realPath);
	
	File file = new File(realPath + fileResource + fileName + ".pdf");
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

}
