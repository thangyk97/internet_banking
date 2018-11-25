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
public class Withdraw {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private int ac_no;
	private int cus_id;
	private double amount;
	
	@Column(name="withdraw_date", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date withdrawDate;
	
	@Column(name="close_date", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date closeDate;
	private int status;
	
	public Withdraw(int ac_no, int cus_id, double amount, Date withdrawDate, Date closeDate, int status) {
		super();
		this.ac_no = ac_no;
		this.cus_id = cus_id;
		this.amount = amount;
		this.withdrawDate = withdrawDate;
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

	public int getCus_id() {
		return cus_id;
	}

	public void setCus_id(int cus_id) {
		this.cus_id = cus_id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getWithdrawDate() {
		return withdrawDate;
	}

	public void setWithdrawDate(Date withdrawDate) {
		this.withdrawDate = withdrawDate;
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
