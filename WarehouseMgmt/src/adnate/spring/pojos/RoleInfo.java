package adnate.spring.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="rolemaster")
public class RoleInfo{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="role_id")
	private int roleId;
	
	@Column(name="role")
	private String role;
	
	@Column(name="role_code")
	private String roleCode;
	
	@Column(name="organisation_id")
	private String organisationId;
	
	
	public RoleInfo() {
	
	}


	public RoleInfo(int roleId, String role, String roleCode, String organisationId) {
		super();
		this.roleId = roleId;
		this.role = role;
		this.roleCode = roleCode;
		this.organisationId = organisationId;
	}

	public int getRoleId() {
		return roleId;
	}


	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getRoleCode() {
		return roleCode;
	}


	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}


	public String getOrganisationId() {
		return organisationId;
	}


	public void setOrganisationId(String organisationId) {
		this.organisationId = organisationId;
	}


	@Override
	public String toString() {
		return "RoleInfo [roleId=" + roleId + ", role=" + role + ", roleCode=" + roleCode + ", organisationId="
				+ organisationId + "]";
	}


	
}		
