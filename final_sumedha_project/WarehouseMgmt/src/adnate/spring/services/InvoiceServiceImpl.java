package adnate.spring.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adnate.spring.daos.InvoiceDao;
import adnate.spring.pojos.Invoice;
import adnate.spring.pojos.InvoiceDetails;

@Service
public class InvoiceServiceImpl implements IInvoiceServices{

	@Autowired
	private InvoiceDao invoiceDao;
	@Override
	public int insertInvoice(Invoice invoice) {
		try {
			int invoiceId = invoiceDao.insertInvoice(invoice);
			return invoiceId;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public Invoice findInvoice(int id) {
		return invoiceDao.findInvoice(id);
	}
	@Override
	public void insertInvoiceDetails(InvoiceDetails invoiceDetails) {
		invoiceDao.insertInvoiceDetails(invoiceDetails);
		
	}
	@Override
	public List<Invoice> getInvoiceObject() {
		return invoiceDao.getInvoiceObject();
	}
	@Override
	public List<InvoiceDetails> getInvoiceDetails() {
		return invoiceDao.getInvoiceDetails();
	}
	@Override
	public List<InvoiceDetails> findInvoiceDetails(int id) {
		return invoiceDao.findInvoiceDetails(id);
	}
	@Override
	public void updateTotalAmt(double sub_total, int id) {
		invoiceDao.updateTotalAmt(sub_total, id);
		
	}
	@Override
	public int setValidTillDate(Date validDate, int invoiceId) {
		return invoiceDao.setValidTillDate(validDate, invoiceId);
	}
	@Override
	public List<Invoice> getInvoiceByOrgId(String organisationId) {
		return invoiceDao.getInvoiceByOrgId(organisationId);
	}

}
