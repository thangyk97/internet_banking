package soict.distribuitedSystem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import soict.distribuitedSystem.entities.Account;
import soict.distribuitedSystem.entities.Deposit;
import soict.distribuitedSystem.entities.User;
import soict.distribuitedSystem.entities.Withdraw;
import soict.distribuitedSystem.repositoriesA.AccountRepositoryA;
import soict.distribuitedSystem.repositoriesA.DepositRepositoryA;
import soict.distribuitedSystem.repositoriesA.UserRepositoryA;
import soict.distribuitedSystem.repositoriesA.WithdrawRepositoryA;
import soict.distribuitedSystem.repositoriesB.AccountRepositoryB;
import soict.distribuitedSystem.repositoriesB.DepositRepositoryB;
import soict.distribuitedSystem.repositoriesB.UserRepositoryB;
import soict.distribuitedSystem.repositoriesB.WithdrawRepositoryB;

@Component
public class BackupDB {
	
	@Autowired
	private AccountRepositoryA accountRepositoryA;
	@Autowired
	private AccountRepositoryB accountRepositoryB;
	@Autowired
	private UserRepositoryA userRepositoryA;
	@Autowired
	private UserRepositoryB userRepositoryB;
	@Autowired
	private WithdrawRepositoryA withdrawRepositoryA;
	@Autowired
	private WithdrawRepositoryB withdrawRepositoryB;
	@Autowired
	private DepositRepositoryA depositRepositoryA;
	@Autowired
	private DepositRepositoryB depositRepositoryB;

    @Scheduled(cron = "0 1 0 * * ?")
    public void create() {
        
        String dateBackup = getYesterdayDateString();
//        String dateBackup = LocalDate.now().toString();
        System.out.println("backup for " + dateBackup);
        
        backup_account(dateBackup);
        backup_deposit(dateBackup);
        backup_user(dateBackup);
        backup_withdraw(dateBackup);
    }
    
    private void backup_account(String dateBackup) {
    	List<Account> accounts = accountRepositoryA.getByDate(dateBackup);
    	accountRepositoryB.saveAll(accounts);
    }
    
    private void backup_user(String dateBackup) {
    	List<User> users = userRepositoryA.getByDate(dateBackup);
    	userRepositoryB.saveAll(users);
    }
    
    private void backup_deposit(String dateBackup) {
    	List<Deposit> deposits = depositRepositoryA.getByDate(dateBackup);
    	depositRepositoryB.saveAll(deposits);
    }
    
    private void backup_withdraw(String dateBackup) {
    	List<Withdraw> withdrawsA = withdrawRepositoryA.getByDate(dateBackup);
    	for (Withdraw withdraw : withdrawsA) {
			withdraw.setUpdatedAt(LocalDate.now().toString());
		}
    	withdrawRepositoryB.saveAll(withdrawsA);
    	
    	List<Withdraw> withdrawsB = withdrawRepositoryB.getByDate(dateBackup);
    	for (Withdraw withdraw : withdrawsB) {
			withdraw.setUpdatedAt(LocalDate.now().toString());
		}
    	withdrawRepositoryB.saveAll(withdrawsB);
    }
    
    private Date yesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }
    
    private String getYesterdayDateString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(yesterday());
    }
}
