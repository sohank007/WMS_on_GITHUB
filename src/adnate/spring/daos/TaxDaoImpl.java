package adnate.spring.daos;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import adnate.spring.pojos.InvoiceTax;
import adnate.spring.pojos.TaxInfo;


@Transactional
@Repository
public class TaxDaoImpl implements TaxDao{
	@Autowired
	private SessionFactory sFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllTax() {
		Session hbSession = sFactory.getCurrentSession();
		Query query=hbSession.createSQLQuery("SELECT DISTINCT(tax_name) FROM tax_details");
		List<String> q=query.list();
		System.out.println("in getAllTax()");
		System.out.println("hbSession created!");
		
		return q;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getTaxAmt(String taxName, String TaxRegion) {
		Session hbSession = sFactory.getCurrentSession();
		Query query=hbSession.createSQLQuery("SELECT tax_value FROM tax_details "
				+ "WHERE tax_name=:taxName AND tax_region=:region");
				query.setString("taxName",taxName );
				query.setString("region",TaxRegion);
				List<Integer> list=	query.list();
				return list;
	}

	@Override
	public boolean insertInvoiceTax(InvoiceTax invoiceTax) {
		Session hbSession = sFactory.getCurrentSession();
		hbSession.save(invoiceTax);
		return true;
	}

	@Override
	public int findTaxByName(String taxName,String taxRegion) {
		Session hbSession = sFactory.getCurrentSession();
		Query query=hbSession.createSQLQuery("SELECT tax_id FROM tax_details WHERE "
				+ "tax_name=:taxName AND tax_region=:taxRegion");
		query.setString("taxName", taxName);
		query.setString("taxRegion", taxRegion);
		int tax_id=	(int)query.uniqueResult();
		return tax_id;
	}

	@Override
	public int updateTotalNDiscount(int invoice_id, double total,
			double discount) {
		Session hbSession = sFactory.getCurrentSession();
		Query query=hbSession.createSQLQuery("UPDATE invoicemaster SET total_invoice_amt=:total,discount=:discount"
				+ " WHERE invoice_id=:invoice_id");
		query.setDouble("total", total);
		query.setDouble("discount", discount);
		query.setInteger("invoice_id", invoice_id);
		int rowsAffected=query.executeUpdate();
		return rowsAffected;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InvoiceTax> findTaxByInvoiceId(int invoiceId) {
		Session hbSession = sFactory.getCurrentSession();
		Query q = hbSession.createSQLQuery("Select * from invoice_tax where invoice_id=:invoice_id")
				.addEntity(InvoiceTax.class);
		q.setInteger("invoice_id", invoiceId);
		return q.list();
	}

	@Override
	public TaxInfo findTaxById(int tax_id) {
		Session hbSession = sFactory.getCurrentSession();
		return	(TaxInfo) hbSession.get(TaxInfo.class,tax_id);
	}

	@Override
	public List<TaxInfo> findTaxByRegion(String tax_region) {
		Session hbSession=sFactory.getCurrentSession();
		Query query=hbSession.createSQLQuery("SELECT * FROM tax_details WHERE tax_region=:tax_region")
				.addEntity(TaxInfo.class);
		query.setString("tax_region", tax_region);
		return  query.list();
	}
	
	

}
