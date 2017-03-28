package adnate.spring.daos;

import java.util.List;

import adnate.spring.pojos.InvoiceTax;
import adnate.spring.pojos.TaxInfo;



public interface TaxDao {
	public   List<String> getAllTax();
	public List<Integer> getTaxAmt(String taxName,String TaxRegion);
	public boolean insertInvoiceTax(InvoiceTax invoiceTax);
	public int findTaxByName(String taxName,String taxRegion);
	public int updateTotalNDiscount(int invoice_id,double total,double discount);
	public List<InvoiceTax> findTaxByInvoiceId(int invoiceId);
	public TaxInfo findTaxById(int tax_id);
	List<TaxInfo> findTaxByRegion(String tax_region);
}
