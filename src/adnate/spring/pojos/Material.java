package adnate.spring.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;


@Component
@Entity
@Table(name="materialmaster")
public class Material {
	@Id
	@GenericGenerator(name="gen", strategy="increment")
	@GeneratedValue(generator="gen")
	@Column(name="material_id")
	private int materialID;
	@Column(name="material_name")
	private String materialName;
	@Column(name="material_code")
	private String materialCode;
	@Column(name="material_description")
	private String materialDesc;
	@Column(name="unit_of_measure")
	private String unitOfMeasure;
	@Column(name="unit_price")
	private double unitPrice;
	@Column(name="organisation_id")
	private String organisationId;
	@Column(name="material_image")
	private String materialImage;
	
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="category_id")
	private Category category;
	
	
	
	public Material(){
	
	}

	public Material(int materialID, String materialName, String materialCode,
			String materialDesc, String unitOfMeasure, double unitPrice,
			String organisationId, String materialImage, Category category) {
		super();
		this.materialID = materialID;
		this.materialName = materialName;
		this.materialCode = materialCode;
		this.materialDesc = materialDesc;
		this.unitOfMeasure = unitOfMeasure;
		this.unitPrice = unitPrice;
		this.organisationId = organisationId;
		this.materialImage = materialImage;
		this.category = category;
	}

	public String getMaterialImage() {
		return materialImage;
	}

	public void setMaterialImage(String materialImage) {
		this.materialImage = materialImage;
	}

	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getOrganisationId() {
		return organisationId;
	}


	public void setOrganisationId(String organisationId) {
		this.organisationId = organisationId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getMaterialID() {
		return materialID;
	}

	public void setMaterialID(int materialID) {
		this.materialID = materialID;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getMaterialDesc() {
		return materialDesc;
	}

	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Override
	public String toString() {
		return "Material [materialID=" + materialID + ", materialName="
				+ materialName + ", materialCode=" + materialCode
				+ ", materialDesc=" + materialDesc + ", unitOfMeasure="
				+ unitOfMeasure + ", unitPrice=" + unitPrice
				+ ", organisationId=" + organisationId + ", materialImage="
				+ materialImage + ", category=" + category + "]";
	}

}
