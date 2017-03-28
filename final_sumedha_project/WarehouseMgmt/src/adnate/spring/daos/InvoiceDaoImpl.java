package adnate.spring.daos;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import adnate.spring.pojos.Invoice;
import adnate.spring.pojos.InvoiceDetails;

@Transactional
@Repository
public class InvoiceDaoImpl implements InvoiceDao{

	@Autowired(required=true)
	private SessionFactory sFactory;
	
	@Override
	public int insertInvoice(Invoice invoice) {
		Session hbSession = sFactory.getCurrentSession();
		hbSession.save(invoice);
		int invoice_inId = invoice.getInvoiceId();
		return invoice_inId;
	}

	@Override
	public Invoice findInvoice(int id) {
		Session hbSession = sFactory.getCurrentSession();
		return (Invoice) hbSession.get(Invoice.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<InvoiceDetails> findInvoiceDetails(int id) {
		Session hbSession = sFactory.getCurrentSession();
		Query query=hbSession.createSQLQuery("SELECT InD.* FROM invoice_details InD WHERE invoice_id=:id")
		.addEntity("InD",InvoiceDetails.class);
		query.setInteger("id",id);
		List<InvoiceDetails> invDetails =(List<InvoiceDetails>)query.list();
		return invDetails;
	}

	@Override
	public void insertInvoiceDetails(InvoiceDetails invoiceDetails) {
		Session hbSession = sFactory.getCurrentSession();
		hbSession.save(invoiceDetails);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<InvoiceDetails> getInvoiceDetails() {
			Session hbSession = sFactory.getCurrentSession();
			System.out.println("InvoiceDetails hbSession Created");
			Criteria cr = hbSession.createCriteria(InvoiceDetails.class);
			System.out.println("returning InvoiceDetlist");
			return cr.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Invoice> getInvoiceObject() {
		Session hbSession = sFactory.getCurrentSession();
		System.out.println("InvoiceObject hbSession Created");
		Criteria cr = hbSession.createCriteria(Invoice.class);
		System.out.println("returning OrderObject list");
		System.out.println(cr.list().toString());
		return cr.list();
	}

	@Override
	public void updateTotalAmt(double sub_total,int id) {
		System.out.println("sub:"+sub_total+" id:"+ id);
		Session hbSession = sFactory.getCurrentSession();
		Query query=hbSession.createSQLQuery("UPDATE invoicemaster SET total_invoice_amt=:sub_total WHERE invoice_id=:id");
		query.setDouble("sub_total", sub_total);
		query.setInteger("id", id);
		query.executeUpdate();
		System.out.println("updateTotalAmt called");
	}

	@Override
	public int setValidTillDate(Date validDate,int invoiceId) {
		Session hbSession = sFactory.getCurrentSession();
		Query query=hbSession.createSQLQuery("UPDATE invoicemaster SET valid_till=:validDate WHERE invoice_id=:invoiceId");
		query.setInteger("invoiceId", invoiceId);
		query.setDate("validDate", validDate);
		int affectedrow=query.executeUpdate();
		return affectedrow;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Invoice> getInvoiceByOrgId(String organisationId) {
		System.out.println("inside getInvoiceByOrgId() method");
		Session hbSession = sFactory.getCurrentSession();
		Query query = hbSession.createSQLQuery("select * from invoicemaster where organisation_id=:organisationId")
				.addEntity(Invoice.class);
		query.setString("organisationId", organisationId);
		return query.list();
	}



}
