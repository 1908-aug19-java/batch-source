package main;
import java.util.ArrayList;
import java.util.List;

public class Account {
	
	public long id;
	public String accountType;
	
	public Account() {
		
		this.id = 1;
		this.accountType = "A";
	}
	
	public Account(long id, String type) {
		
		this.id = id;
		this.accountType = type;
	}
	
	public static List<Account> createAccounts(int num){
		
		List<Account> ret = new ArrayList<Account>();
		
		for(int i = 0; i < num; i++) {
			
			ret.add(new Account(i + 1, "A" + i));
		}
		
		return ret;
	}
}
