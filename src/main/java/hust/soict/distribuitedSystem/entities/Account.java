package hust.soict.distribuitedSystem.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Account {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int ac_no;
	
	private int cus_id;
		
	private double balance;
	
	@Column(name="openDate", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date openDate;

	public Account(int cus_id, double balance, Date openDate) {
		super();
		this.cus_id = cus_id;
		this.balance = balance;
		this.openDate = openDate;
	}

	public int getAc_no() {
		return ac_no;
	}

	public int getCus_id() {
		return cus_id;
	}

	public void setCus_id(int cus_id) {
		this.cus_id = cus_id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
}
