package adnate.spring.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="rolemenu")
public class RoleMenu{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="serial_id")
	private int serialId;
	
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL)	
	@JoinColumn(name="menu_id")
	private MenuInfo menuInfo;
	
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL)	
	@JoinColumn(name="role_id")
	private RoleInfo roleInfo;
	
	public RoleMenu(){
		
	}

	

	



	public RoleMenu(int serialId, MenuInfo menuInfo, RoleInfo roleInfo) {
		super();
		this.serialId = serialId;
		this.menuInfo = menuInfo;
		this.roleInfo = roleInfo;
	}







	public int getSerialId() {
		return serialId;
	}

	public void setSerialId(int serialId) {
		this.serialId = serialId;
	}

	

	

	public MenuInfo getMenuInfo() {
		return menuInfo;
	}







	public void setMenuInfo(MenuInfo menuInfo) {
		this.menuInfo = menuInfo;
	}







	public RoleInfo getRoleInfo() {
		return roleInfo;
	}



	public void setRoleInfo(RoleInfo roleInfo) {
		this.roleInfo = roleInfo;
	}







	@Override
	public String toString() {
		return "RoleMenu [serialId=" + serialId + ", menuInfo=" + menuInfo + ", roleInfo=" + roleInfo + "]";
	}



	

	
	
}		
