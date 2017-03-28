package adnate.spring.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="rolemenu")

public class RoleMenu {
   
	@Id
    @Column(name="serial_id")
	private int serial_id;
	
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL)	
    @JoinColumn(name="menu_id")
	private Menu menu;
	
	@Column(name="role_code")
	private String role_code;

	public int getSerial_id() {
		return serial_id;
	}

	public void setSerial_id(int serial_id) {
		this.serial_id = serial_id;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	

	public String getRole_code() {
		return role_code;
	}

	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}

	@Override
	public String toString() {
		return "RoleMenu [serial_id=" + serial_id + ", menu=" + menu
				+ ", role_code=" + role_code + "]";
	}
	
	
}
