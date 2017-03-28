package adnate.spring.daos;

import java.util.Date;
import java.util.List;

import adnate.spring.pojos.Invoice;
import adnate.spring.pojos.InvoiceDetails;

public interface InvoiceDao {
	public int insertInvoice(Invoice invoice);
	public Invoice findInvoice(int id);
	public List<InvoiceDetails> findInvoiceDetails(int id);
	public void insertInvoiceDetails(InvoiceDetails invoiceDetails); 
	public List<InvoiceDetails> getInvoiceDetails();
	public List<Invoice> getInvoiceObject();
	public void updateTotalAmt(double sub_total,int id);
	public int setValidTillDate(Date validDate,int invoiceId);
	
	public List<Invoice> getInvoiceByOrgId(String organisationId);
}
