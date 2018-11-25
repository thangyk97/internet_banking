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
public class Deposit {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private int ac_no;
	private int cus_id;
	private double amount;
	
	@Column(name="deposit_date", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date depositDate;
	
	@Column(name="close_date", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date closeDate;
	
	private int status;

	public Deposit(int ac_no, int cust_id, double amount, Date depositDate, Date closeDate, int status) {
		super();
		this.ac_no = ac_no;
		this.cus_id = cust_id;
		this.amount = amount;
		this.depositDate = depositDate;
		this.closeDate = closeDate;
		this.status = status;
	}

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

	public Date getDepositDate() {
		return depositDate;
	}

	public void setDepositDate(Date depositDate) {
		this.depositDate = depositDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
