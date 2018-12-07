package soict.distribuitedSystem.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Account {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ac_no;
	private double balance;
	private int flag;
	@Column(name="open_date")
	private String openDate;
	
	@ElementCollection(targetClass=User.class)
	private Set<User> users;

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	public Set<User> getUsers() {
		return users;
	}
	

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public void setAc_no(int ac_no) {
		this.ac_no = ac_no;
	}


	public int getAc_no() {
		return ac_no;
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


	public int getFlag() {
		return flag;
	}


	public void setFlag(int flag) {
		this.flag = flag;
	}
}
