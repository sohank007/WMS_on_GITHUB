package adnate.spring.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="categorymaster")	
public class Category {
	
	@Id
	@Column(name="category_id")
	private int categoryId;
	@Column(name="category_name")
	private String categoryName;
	@Column(name="category_description")
	private String categoryDesc;
	@Column(name="category_status")
	private String categoryStatus;
	
	@Column(name="organisation_id")
	private String organisationId;
	
	
	public Category(){
		
	}
	
	
	public Category(int categoryId, String categoryName, String categoryDesc, String categoryStatus,
			String organisationId) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryDesc = categoryDesc;
		this.categoryStatus = categoryStatus;
		this.organisationId = organisationId;
	}


	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	public String getCategoryStatus() {
		return categoryStatus;
	}

	public void setCategoryStatus(String categoryStatus) {
		this.categoryStatus = categoryStatus;
	}
	
	



	public String getOrganisationId() {
		return organisationId;
	}


	public void setOrganisationId(String organisationId) {
		this.organisationId = organisationId;
	}


	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", categoryDesc="
				+ categoryDesc + ", categoryStatus=" + categoryStatus + ", organisationId=" + organisationId + "]";
	}


	
	

	
	
	
	

}
