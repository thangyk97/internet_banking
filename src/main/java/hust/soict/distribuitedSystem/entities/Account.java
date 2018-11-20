package hust.soict.distribuitedSystem.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int ac_no;
	
	private int cus_id;
	
	private String password;
	
	private double balance;
	
	private String openDate;
	
	public Account(int cus_id, String password, double balance, String openDate) {
		this.cus_id = cus_id;
		this.password = password;
		this.balance = balance;
		this.openDate = openDate;
	}

	public int getAc_no() {
		return ac_no;
	}

	public void setAc_no(int ac_no) {
		this.ac_no = ac_no;
	}

	public Integer getCus_id() {
		return cus_id;
	}

	public void setCus_id(Integer cus_id) {
		this.cus_id = cus_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	
	
}
