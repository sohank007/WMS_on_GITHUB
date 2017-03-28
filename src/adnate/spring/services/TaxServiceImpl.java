package adnate.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adnate.spring.daos.TaxDao;
import adnate.spring.pojos.InvoiceTax;
import adnate.spring.pojos.TaxInfo;


@Service
public class TaxServiceImpl implements ITaxService{

	@Autowired
	private TaxDao taxdao;
	
	@Override
	public List<String> getAllTax() {
		return taxdao.getAllTax();
	}

	@Override
	public List<Integer> getTaxAmt(String taxName, String TaxRegion) {
		return taxdao.getTaxAmt(taxName, TaxRegion);
	}

	@Override
	public boolean insertInvoiceTax(InvoiceTax invoiceTax) {
		return taxdao.insertInvoiceTax(invoiceTax);
	}

	@Override
	public int findTaxByName(String taxName, String taxRegion) {
		return taxdao.findTaxByName(taxName, taxRegion);
	}

	@Override
	public int updateTotalNDiscount(int invoice_id, double total,
			double discount) {
		return taxdao.updateTotalNDiscount(invoice_id, total, discount);
	}

	@Override
	public List<InvoiceTax> findTaxByInvoiceId(int invoiceId) {
		// TODO Auto-generated method stub
		return taxdao.findTaxByInvoiceId(invoiceId);
	}

	@Override
	public TaxInfo findTaxById(int tax_id) {
		return taxdao.findTaxById(tax_id);
	}

	@Override
	public List<TaxInfo> findTaxByRegion(String tax_region) {
		return taxdao.findTaxByRegion(tax_region);
	}

}
