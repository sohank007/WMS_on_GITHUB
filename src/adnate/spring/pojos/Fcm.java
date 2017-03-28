package adnate.spring.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Component
@Entity
@Table(name="fcm")
public class Fcm {

	
	@Id
	@Column(name="serial_id")
    private int serial_id;
	
	
	@Column(name="token_id")
	private String token_id;
	
	@Column(name="imei")
	private String imei;

	
	@Column(name="contractor_id")
	private int contractor_id;
	
	
	public int getSerial_id() {
		return serial_id;
	}

	public void setSerial_id(int serial_id) {
		this.serial_id = serial_id;
	}

	public String getToken_id() {
		return token_id;
	}

	public void setToken_id(String token_id) {
		this.token_id = token_id;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	
	@Override
	public String toString() {
		return "Fcm [serial_id=" + serial_id + ", token_id=" + token_id
				+ ", imei=" + imei + ", contractor_id=" + contractor_id + "]";
	}

	public int getContractor_id() {
		return contractor_id;
	}

	public void setContractor_id(int contractor_id) {
		this.contractor_id = contractor_id;
	}

	
	
	
	
	
	
	
	
}
