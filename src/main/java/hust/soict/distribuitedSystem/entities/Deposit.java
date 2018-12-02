package hust.soict.distribuitedSystem.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Deposit {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private int ac_no;
	private int cus_id;
	private double amount;
	
	@Column(name="deposit_date")
	private String depositDate;
	
	@Column(name="close_date")
	private String closeDate;
	
	private int status;


	
	
	
	
	
	
	public int getId() {
		return id;
	}

	public int getAc_no() {
		return ac_no;
	}

	public void setAc_no(int ac_no) {
		this.ac_no = ac_no;
	}

	public int getCust_id() {
		return cus_id;
	}

	public void setCust_id(int cust_id) {
		this.cus_id = cust_id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDepositDate() {
		return depositDate;
	}

	public void setDepositDate(String depositDate) {
		this.depositDate = depositDate;
	}

	public String getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
