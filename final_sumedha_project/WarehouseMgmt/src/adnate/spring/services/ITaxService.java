package adnate.spring.services;

import java.util.List;

import adnate.spring.pojos.InvoiceTax;
import adnate.spring.pojos.TaxInfo;


public interface ITaxService {
	public   List<String> getAllTax();
	public List<Integer> getTaxAmt(String taxName,String TaxRegion);
	public boolean insertInvoiceTax(InvoiceTax invoiceTax);
	public int findTaxByName(String taxName,String taxRegion);
	public int updateTotalNDiscount(int invoice_id,double total,double discount);
	public List<InvoiceTax> findTaxByInvoiceId(int invoiceId);
	public TaxInfo findTaxById(int tax_id);
	public List<TaxInfo> getTaxDetails();
	public List<String> getTaxNames(String taxRegion);
}
