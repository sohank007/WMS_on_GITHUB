package adnate.spring.pojos;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="sequence")
public class Sequence {
	@Id
	@GenericGenerator(name="gen", strategy="increment")
	@GeneratedValue(generator="gen")
	@Column(name="serial_id")
	private int serial_id;
	
	@Column(name="tablename")
	private String tablename;
	
	@Column(name="prefix")
	private String prefix;
	
	@Column(name="sequence")
	private int sequence;
	
	public Sequence() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Sequence(int serial_id, String tablename, String prefix, int sequence) {
		super();
		this.serial_id = serial_id;
		this.tablename = tablename;
		this.prefix = prefix;
		this.sequence = sequence;
	}

	public int getSerial_id() {
		return serial_id;
	}

	public void setSerial_id(int serial_id) {
		this.serial_id = serial_id;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	@Override
	public String toString() {
		return "Sequence [serial_id=" + serial_id + ", tablename=" + tablename
				+ ", prefix=" + prefix + ", sequence=" + sequence + "]";
	}


	
	

}
