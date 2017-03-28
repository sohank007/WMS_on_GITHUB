package adnate.spring.pdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



import adnate.spring.pojos.InvoiceDetails;
import adnate.spring.pojos.InvoiceTax;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFBuilder {
	

			private static String FILE = "E:/FinalWms/WarehouseMgmt123/Resources/";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 10,
             Font.BOLD);
	 private static Font batFont=new Font(Font.FontFamily.TIMES_ROMAN,10);
	  private static Font Title = new Font(Font.FontFamily.TIMES_ROMAN, 12,
	             Font.BOLD);
	

    public static void pdfCreation(List<InvoiceDetails> invoiceDetails, List<InvoiceTax> taxDetailsList,String uploadPath) throws MalformedURLException, IOException {
    	
    	 String invoiceId="";
    	    String sequenceId="";
    	    for(InvoiceDetails invoice:invoiceDetails){
    	    	invoiceId=String.valueOf(invoice.getInvoice().getInvoiceId());
    	    	sequenceId = String.valueOf(invoice.getInvoice().getSequenceId());
    	    }
	Document document=new Document();
	//document.setPageSize(PageSize.A3.rotate());
	try {
		    File file = new File("/Resources/"+sequenceId+".pdf");
           PdfWriter pdfWriter=PdfWriter.getInstance(document, new FileOutputStream(FILE+sequenceId+".pdf"));
          Rectangle rectangle = new Rectangle(30, 30, 550, 800);
	      pdfWriter.setBoxSize("rectangle", rectangle);
		HeaderFooter headerFooter=new HeaderFooter();
		pdfWriter.setPageEvent(headerFooter);
	    document.open();
	    
	    addContent(document,invoiceDetails, taxDetailsList);
	    
	    document.close();
	  
	    
	} catch (FileNotFoundException | DocumentException e) {
	
		e.printStackTrace();
	}
	
	
   }
    
    public static class HeaderFooter extends PdfPageEventHelper{
    	 public void onStartPage(PdfWriter pdfWriter, Document document) {
    	      System.out.println("onStartPage() method > Writing header in file");
    	      Rectangle rect = pdfWriter.getBoxSize("rectangle");
    	      
    	      // TOP LEFT
    	      
    	      try {
				Image image=Image.getInstance("E:/FinalWms/WarehouseMgmt123/Resources/adnate.png");
				document.add(image);
			} catch (BadElementException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	        
    	     
    	     
    	 
    	      // TOP MEDIUM
    	      ColumnText.showTextAligned(pdfWriter.getDirectContent(),
    	               Element.ALIGN_CENTER, new Phrase("Adnate IT Solutions India",Title),
    	               rect.getRight() / 2, rect.getTop(), 0);
    	 
    	     
    	  }
    	 
    	  public void onEndPage(PdfWriter pdfWriter, Document document) {
    	      System.out.println("onEndPage() method > Writing footer in file");
    	      Rectangle rect = pdfWriter.getBoxSize("rectangle");
    	       
    	   
    	 
    	      ColumnText.showTextAligned(pdfWriter.getDirectContent(),
   	               Element.ALIGN_CENTER, new Phrase("#404,Pride Purple Square Kalewadi Chowk, Pune, 411057",catFont),
   	               rect.getRight()-260, rect.getBottom(), 0);
    	     
    	  }
    }
	
	 private static void addContent(Document document,List<InvoiceDetails> invoiceDetails, List<InvoiceTax> taxDetailsList) throws DocumentException, MalformedURLException, IOException{
		    
		    Paragraph title=new Paragraph("Adnate IT Solutions India",Title);
		    float[] columnWidths = new float[]{35f, 25f, 25f, 25f};
		    title.setAlignment(Element.ALIGN_CENTER);
		     
		    String invoiceId="";
		     String sequenceId="";
		     Date invoiceDate1;
		     Date validDate;
		     String contractorName="";
		     String po="";
		     String subTotal1="";
		     String Date2="";
		     String Date3="";
		     String discount1="";
		     String total1="";
		     String tax_id = "";
		     for(InvoiceDetails invoice:invoiceDetails){
		    	 invoiceId=String.valueOf(invoice.getInvoice().getInvoiceId());
			    	sequenceId = String.valueOf(invoice.getInvoice().getSequenceId());
			    	SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			    	invoiceDate1=invoice.getInvoice().getInvoiceDate();
			    	Date2=sdf.format(invoiceDate1);
			    	validDate=invoice.getInvoice().getValidDate();
			    	Date3=sdf.format(validDate);
			    	contractorName=invoice.getInvoice().getContractor().getCtrName();
			    	po=invoice.getInvoice().getPurchaseOrder();
			    	subTotal1=String.valueOf(invoice.getInvoice().getInvoiceAmtTotal());
			    	discount1=String.valueOf(invoice.getInvoice().getDiscount());
			    }
		     
		     
		     
		     
		    
		    /*Header Table*/
		    PdfPTable headerTable=new PdfPTable(4);
		    headerTable.setWidths(columnWidths);
		    headerTable.setHorizontalAlignment(0);
		    headerTable.setWidthPercentage(100);
		    headerTable.setHeaderRows(1);
		   
		    PdfPCell blank=new PdfPCell();
		    blank.setBorder(Rectangle.NO_BORDER);
		    headerTable.addCell(blank);
		    headerTable.addCell(blank);
		    
		   
		    
		    PdfPCell invoiceCell=new PdfPCell();
		    
		    invoiceCell.setBorder(Rectangle.NO_BORDER);
		    
		    headerTable.addCell(invoiceCell);
		    headerTable.addCell(blank);
		    
		  
		    
		    PdfPCell invoiceNo=new PdfPCell(new Phrase("Invoice No",catFont));
		    PdfPCell billTo=new PdfPCell(new Phrase("Bill To",catFont));
		    billTo.setBorder(Rectangle.NO_BORDER);
		    invoiceNo.setHorizontalAlignment(Element.ALIGN_LEFT);
		    invoiceNo.setBorder(Rectangle.NO_BORDER);
		    
		    
		    
		    headerTable.addCell(billTo);
		    
		    PdfPCell billTodata=new PdfPCell(new Phrase(contractorName,catFont));
		    billTodata.setBorder(Rectangle.NO_BORDER);
		    
		    headerTable.addCell(billTodata);
		    headerTable.addCell(invoiceNo);
		   
		    
		    PdfPCell invDataCell=new PdfPCell(new Phrase(sequenceId,batFont));
		    invDataCell.setBorder(Rectangle.NO_BORDER);
		    headerTable.addCell(invDataCell);
		    
		  
		   
		    
		    PdfPCell invoiceDate=new PdfPCell(new Phrase("Invoice Date",catFont));
		    invoiceDate.setHorizontalAlignment(Element.ALIGN_LEFT);
		    invoiceDate.setBorder(Rectangle.NO_BORDER);
		    headerTable.addCell(blank);
		    headerTable.addCell(blank);
		    headerTable.addCell(invoiceDate);
		   
		    PdfPCell invDate=new PdfPCell(new Phrase(Date2,batFont));//date2 invoice Date
		    invDate.setBorder(Rectangle.NO_BORDER);
		    headerTable.addCell(invDate);
		    
		    
		    PdfPCell terms=new PdfPCell(new Phrase("Terms",catFont));
		    terms.setHorizontalAlignment(Element.ALIGN_LEFT);
		    terms.setBorder(Rectangle.NO_BORDER);
		    headerTable.addCell(blank);
		    headerTable.addCell(blank);
		    headerTable.addCell(terms);
		    
		    PdfPCell invTerms=new PdfPCell(new Phrase("Due end of month",batFont));
		    invTerms.setBorder(Rectangle.NO_BORDER);
		    headerTable.addCell(invTerms);
		    
		    PdfPCell dueDate=new PdfPCell(new Phrase("Due Date",catFont));
		    dueDate.setHorizontalAlignment(Element.ALIGN_LEFT);
		    dueDate.setBorder(Rectangle.NO_BORDER);
		    headerTable.addCell(blank);
		    headerTable.addCell(blank);
		    headerTable.addCell(dueDate);
		   
		    PdfPCell invDueDate=new PdfPCell(new Phrase(Date3,batFont));//Date3 validDate
		    invDueDate.setBorder(Rectangle.NO_BORDER);
		    headerTable.addCell(invDueDate);
		   
		    
		    
		    PdfPCell poDate=new PdfPCell(new Phrase("PO ",catFont));
		    poDate.setHorizontalAlignment(Element.ALIGN_LEFT);
		    poDate.setBorder(Rectangle.NO_BORDER);
		    headerTable.addCell(blank);
		    headerTable.addCell(blank);
		    headerTable.addCell(poDate);
		    
		    PdfPCell invPo=new PdfPCell(new Phrase(po,batFont));
		    invPo.setBorder(Rectangle.NO_BORDER);
		    headerTable.addCell(invPo);
		   
		    
		    
		  
		   
		    /* Child Table*/
		    PdfPTable childTable=new PdfPTable(4);
		    childTable.setWidths(columnWidths);
		    childTable.setWidthPercentage(100);
		    PdfPCell item=new PdfPCell(new Phrase("Item",catFont));
		    item.setHorizontalAlignment(Element.ALIGN_CENTER);
		    item.setBackgroundColor(BaseColor.LIGHT_GRAY);
		    childTable.addCell(item);
		   
		    PdfPCell price=new PdfPCell(new Phrase("Price",catFont));
		    price.setHorizontalAlignment(Element.ALIGN_CENTER);
		    price.setBackgroundColor(BaseColor.LIGHT_GRAY);
		    childTable.addCell(price);
		    
		    PdfPCell quantity=new PdfPCell(new Phrase("Quantity",catFont));
		    quantity.setHorizontalAlignment(Element.ALIGN_CENTER);
		    quantity.setBackgroundColor(BaseColor.LIGHT_GRAY);
		    childTable.addCell(quantity);
		    
		    PdfPCell totalCost=new PdfPCell(new Phrase("Total Cost",catFont));
		    totalCost.setHorizontalAlignment(Element.ALIGN_CENTER);
		    totalCost.setBackgroundColor(BaseColor.LIGHT_GRAY);
		    childTable.addCell(totalCost);
		    
		   
		    double subTot=0.0;
		    	for(InvoiceDetails invoice:invoiceDetails){
		    		 PdfPCell itemdata=new PdfPCell(new Phrase(invoice.getMaterial().getMaterialName(),batFont));
			 		    itemdata.setHorizontalAlignment(Element.ALIGN_LEFT);
			             childTable.addCell(itemdata);
			             
			             PdfPCell priceData=new PdfPCell(new Phrase(String.valueOf(invoice.getMaterial().getUnitPrice()),batFont));
			             priceData.setHorizontalAlignment(Element.ALIGN_RIGHT);
			             childTable.addCell(priceData);
			             
			             PdfPCell quantitydata=new PdfPCell(new Phrase(String.valueOf(invoice.getQuantity()),batFont));
			             quantitydata.setHorizontalAlignment(Element.ALIGN_RIGHT);
			             childTable.addCell(quantitydata);
			             
			             PdfPCell totalCostdata=new PdfPCell(new Phrase(String.valueOf(invoice.getTotalAmount()),batFont));
			             totalCostdata.setHorizontalAlignment(Element.ALIGN_RIGHT);
			             childTable.addCell(totalCostdata);

		    		subTot+=invoice.getTotalAmount();
		    	}
		    	
		    	
		    	
		   
		    
		    
		    /*Sub child Table*/
		  
		    PdfPTable subChildTable=new PdfPTable(4);
		    subChildTable.setWidths(columnWidths);
		    subChildTable.setWidthPercentage(100);
		    subChildTable.addCell(blank);
		    subChildTable.addCell(blank);
		   
		    PdfPCell subTotal=new PdfPCell(new Phrase("Sub Total",batFont));
		    subTotal.setHorizontalAlignment(Element.ALIGN_RIGHT);
		    subTotal.setBorder(Rectangle.NO_BORDER);
		    subChildTable.addCell(subTotal);
		   
		    PdfPCell subTotaldata=new PdfPCell(new Phrase(String.valueOf(subTot),batFont));
		    subTotaldata.setHorizontalAlignment(Element.ALIGN_RIGHT);
		    subTotaldata.setBorder(Rectangle.NO_BORDER);
		    subChildTable.addCell(subTotaldata);
		    
		   
		    
		    subChildTable.addCell(blank);
		    subChildTable.addCell(blank);
		    
		    PdfPCell discount=new PdfPCell(new Phrase("Discount",batFont));
		    discount.setHorizontalAlignment(Element.ALIGN_RIGHT);
		    discount.setBorder(Rectangle.NO_BORDER);
		    subChildTable.addCell(discount);
		    
		    PdfPCell discountData=new PdfPCell(new Phrase(discount1,batFont));
		    discountData.setHorizontalAlignment(Element.ALIGN_RIGHT);
		    discountData.setBorder(Rectangle.NO_BORDER);
		    subChildTable.addCell(discountData);
		    
		   
		    
		    for(InvoiceTax invoiceTax:taxDetailsList){
		    	if(invoiceTax.getTax().getTaxName().equals("CST")){
		    		subChildTable.addCell(blank);
				    subChildTable.addCell(blank);
				    
				    PdfPCell cst=new PdfPCell(new Phrase("CST("+String.valueOf(invoiceTax.getTax().getTaxValue())+")",batFont));
				    cst.setHorizontalAlignment(Element.ALIGN_RIGHT);
				    cst.setBorder(Rectangle.NO_BORDER);
				    subChildTable.addCell(cst);
				   
				    
				    PdfPCell cstData=new PdfPCell(new Phrase(String.valueOf(invoiceTax.getTaxableAmt()),batFont));
				    cstData.setHorizontalAlignment(Element.ALIGN_RIGHT);
				    cstData.setBorder(Rectangle.NO_BORDER);
			        subChildTable.addCell(cstData);
		    	}
		    	
		    	if(invoiceTax.getTax().getTaxName().equals("Swachh_Bharat")){
		    		System.out.println("faskb" + invoiceTax.getTax().getTaxName());
		    		subChildTable.addCell(blank);
				    subChildTable.addCell(blank);
				    
				    PdfPCell sb=new PdfPCell(new Phrase("Swachh Bharat("+String.valueOf(invoiceTax.getTax().getTaxValue())+")",batFont));
				    sb.setHorizontalAlignment(Element.ALIGN_RIGHT);
				    sb.setBorder(Rectangle.NO_BORDER);
				    subChildTable.addCell(sb);
				   
				    
				    PdfPCell sbData=new PdfPCell(new Phrase(String.valueOf(invoiceTax.getTaxableAmt()),batFont));
				    sbData.setHorizontalAlignment(Element.ALIGN_RIGHT);
				    sbData.setBorder(Rectangle.NO_BORDER);
			        subChildTable.addCell(sbData);
		    	}
		    	
		    	if(invoiceTax.getTax().getTaxName().equals("VAT")){
		    		subChildTable.addCell(blank);
				    subChildTable.addCell(blank);
				    
				    PdfPCell vat=new PdfPCell(new Phrase("VAT("+String.valueOf(invoiceTax.getTax().getTaxValue())+")",batFont));
				    vat.setHorizontalAlignment(Element.ALIGN_RIGHT);
				    vat.setBorder(Rectangle.NO_BORDER);
				    subChildTable.addCell(vat);
				   
				    
				    PdfPCell vatData=new PdfPCell(new Phrase(String.valueOf(invoiceTax.getTaxableAmt()),batFont));
				    vatData.setHorizontalAlignment(Element.ALIGN_RIGHT);
				    vatData.setBorder(Rectangle.NO_BORDER);
			        subChildTable.addCell(vatData);
		    	}
		    }
		    
		    
		    
		    subChildTable.addCell(blank);
		    subChildTable.addCell(blank);
		    
		    PdfPCell total=new PdfPCell(new Phrase("Total",catFont));
		    total.setHorizontalAlignment(Element.ALIGN_RIGHT);
		    total.setBorder(Rectangle.NO_BORDER);
		    subChildTable.addCell(total);
		    
		    PdfPCell totalData=new PdfPCell(new Phrase(subTotal1,catFont));
		    totalData.setHorizontalAlignment(Element.ALIGN_RIGHT);
		    totalData.setBorder(Rectangle.NO_BORDER);
		    subChildTable.addCell(totalData);
		    
		    
		  
		    subChildTable.addCell(blank);
		    subChildTable.addCell(blank);
		    
		    PdfPCell balanceDue=new PdfPCell(new Phrase("Balance Due",catFont));
		    balanceDue.setHorizontalAlignment(Element.ALIGN_RIGHT);
		    balanceDue.setBorder(Rectangle.NO_BORDER);
		   
		    subChildTable.addCell(balanceDue);
		   
		    
		    PdfPCell balanceDueData=new PdfPCell(new Phrase(subTotal1,catFont));
		    balanceDueData.setHorizontalAlignment(Element.ALIGN_RIGHT);
		    balanceDueData.setBorder(Rectangle.NO_BORDER);
		    subChildTable.addCell(balanceDueData);
		   
           
		    //document.add(title);
		    //document.add(image);
		    document.add(new Paragraph("------------------------------------------------------- INVOICE ------------------------------------------------------------"));
		    
		    document.add(new Paragraph(" "));
		    document.add(headerTable);
		   
		    document.add(new Paragraph(" "));
		    document.add(childTable);
		    document.add(new Paragraph(" "));
		    document.add(subChildTable);  
		    
	 }	

}

