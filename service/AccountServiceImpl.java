package com.capgemini.service;
import java.util.LinkedList;

import com.capgemini.beans.*;
import com.capgemini.beans.Account;
import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientOpeningAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.repo.*;

public class AccountServiceImpl implements AccountService{
        AccountRepo accountRepo;
        LinkedList<Account> accounts=new LinkedList<>();

		public AccountServiceImpl(AccountRepo accountRepo) {
			super();
			this.accountRepo = accountRepo;
		}
		@Override
		public  Account createAccount(int accountNumber,int amount) throws InsufficientOpeningAmountException
		{
			if(amount<500) 
				throw new InsufficientOpeningAmountException();
			
				Account account = new Account();
				account.setAccountNumber(accountNumber);
				account.setAmount(amount);
			    accounts.add(account);
			if(accountRepo.save(account))
			{  return account; }
             return null;
        }

		public Account searchAccount(int accountNumber)throws InvalidAccountNumberException
		{
			
			for(Account account : accounts)
			{
				if(account.getAccountNumber()==accountNumber)
				{
					return account;
				}
			}
			throw new InvalidAccountNumberException();
			
		}
		
		
		
		public int withdrawAmount(int accountNumber,int amount) throws InvalidAccountNumberException,InsufficientBalanceException
		{
			Account account=searchAccount(accountNumber);
			
			if((account.getAmount()-amount)>=0)
			{
				account.setAmount(account.getAmount()-amount);
				return account.getAmount();
			}
			
			throw new InsufficientBalanceException();
		}
		
		public int depositAmount(int accountNumber,int amount)throws InvalidAccountNumberException
		{
			Account account=searchAccount(accountNumber);
			account.setAmount(account.getAmount()+amount);
			return account.getAmount();
		}
}
