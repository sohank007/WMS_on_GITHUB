package adnate.spring.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;

@Component
@Entity
@Table(name="contractormaster")
public class Contractor {
	
	@Id
	@GenericGenerator(name="gen", strategy="increment")
	@GeneratedValue(generator="gen")
	@Column(name="contractor_id")
	private int ctrId;
	@Column(name="contractor_name")
	private String ctrName;
	@Column(name="contractor_number")
	private String ctrNum;
	@Column(name="contractor_address")
	private String ctrAddress;
	@Column(name="contractor_email")
	private String ctrEmailId;
	@Column(name="contractor_code")
	private String ctrCode;
	@Column(name="contractor_status")
	private String ctrStatus;
	
	
	@Column(name="organisation_id")
	private String organisationId;

	
	public String getCtrEmailId() {
		return ctrEmailId;
	}

	public void setCtrEmailId(String ctrEmailId) {
		this.ctrEmailId = ctrEmailId;
	}
	
	

	public String getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(String organisationId) {
		this.organisationId = organisationId;
	}

	public Contractor(){
		
	}
	
	

	public String getCtrCode() {
		return ctrCode;
	}

	public void setCtrCode(String ctrCode) {
		this.ctrCode = ctrCode;
	}

	public String getCtrStatus() {
		return ctrStatus;
	}

	public void setCtrStatus(String ctrStatus) {
		this.ctrStatus = ctrStatus;
	}

	

	public Contractor(int ctrId, String ctrName, String ctrNum, String ctrAddress, String ctrEmailId, String ctrCode,
			String ctrStatus, String organisationId) {
		super();
		this.ctrId = ctrId;
		this.ctrName = ctrName;
		this.ctrNum = ctrNum;
		this.ctrAddress = ctrAddress;
		this.ctrEmailId = ctrEmailId;
		this.ctrCode = ctrCode;
		this.ctrStatus = ctrStatus;
		this.organisationId = organisationId;
	}

	public int getCtrId() {
		return ctrId;
	}

	public void setCtrId(int ctrId) {
		this.ctrId = ctrId;
	}

	public String getCtrName() {
		return ctrName;
	}

	public void setCtrName(String ctrName) {
		this.ctrName = ctrName;
	}

	public String getCtrNum() {
		return ctrNum;
	}

	public void setCtrNum(String ctrNum) {
		this.ctrNum = ctrNum;
	}

	public String getCtrAddress() {
		return ctrAddress;
	}

	public void setCtrAddress(String ctrAddress) {
		this.ctrAddress = ctrAddress;
	}

	@Override
	public String toString() {
		return "Contractor [ctrId=" + ctrId + ", ctrName=" + ctrName + ", ctrNum=" + ctrNum + ", ctrAddress="
				+ ctrAddress + ", ctrEmailId=" + ctrEmailId + ", ctrCode=" + ctrCode + ", ctrStatus=" + ctrStatus
				+ ", organisationId=" + organisationId + "]";
	}

	
	
	
	

}
