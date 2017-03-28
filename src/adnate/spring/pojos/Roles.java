package adnate.spring.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


	@Component
	@Entity
	@Table(name="rolemaster")
	public class Roles {

		@Id
		@Column(name="role_id")
		private int role_id;
		
		@Column(name="role")
		private String role;
		
		@Column(name="role_code")
	    private String role_code;
		
		public int getRole_id() {
			return role_id;
		}

		public void setRole_id(int role_id) {
			this.role_id = role_id;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}


		public String getRole_code() {
			return role_code;
		}

		public void setRole_code(String role_code) {
			this.role_code = role_code;
		}

		@Override
		public String toString() {
			return "Roles [role_id=" + role_id + ", role=" + role + ", role_code="
					+ role_code + "]";
		}
		
		
}
