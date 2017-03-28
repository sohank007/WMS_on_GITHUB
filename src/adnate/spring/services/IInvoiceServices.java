package adnate.spring.services;

import java.util.Date;
import java.util.List;

import adnate.spring.pojos.Invoice;
import adnate.spring.pojos.InvoiceDetails;

public interface IInvoiceServices {
	public int insertInvoice(Invoice invoice);
	public Invoice findInvoice(int id);
	public List<InvoiceDetails> findInvoiceDetails(int id);
	public void insertInvoiceDetails(InvoiceDetails invoiceDetails);
	public List<Invoice> getInvoiceObject();
	public List<InvoiceDetails> getInvoiceDetails();
	public void updateTotalAmt(double sub_total,int id);
	public int setValidTillDate(Date validDate,int invoiceId);
}
