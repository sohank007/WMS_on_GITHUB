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
@Table(name="menumaster")
public class MenuInfo {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="menu_id")
	private int menuId;
	
	@Column(name="menu_name")
	private String menuName;
	
	@Column(name="menu_icon")
	private String menuIcon;
	
	@Column(name="organisation_id")
	private String organisationId;
	
	
	
	public MenuInfo(){
		
	}

	public MenuInfo(int menuId, String menuName, String menuIcon, String organisationId) {
		super();
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuIcon = menuIcon;
		this.organisationId = organisationId;
	}

	public int getMenuId() {
		return menuId;
	}


	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}


	public String getMenuName() {
		return menuName;
	}


	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}


	public String getMenuIcon() {
		return menuIcon;
	}


	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(String organisationId) {
		this.organisationId = organisationId;
	}

	@Override
	public String toString() {
		return "MenuInfo [menuId=" + menuId + ", menuName=" + menuName + ", menuIcon=" + menuIcon + ", organisationId="
				+ organisationId + "]";
	}

	
	
	
}
