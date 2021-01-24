package com.capgemini.repo;
import com.capgemini.beans.*;

public interface AccountRepo {
	boolean save(Account account);
	Account searchAccount(int accountNumber);

}
