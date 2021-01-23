package com.capgemini.service;
import com.capgemini.beans.Account;
import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientOpeningAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;


public interface AccountService {

	public int withdrawAmount(int accountNumber,int amount) throws InvalidAccountNumberException, InsufficientBalanceException;
	public Account createAccount(int accountNumber,int amount) throws InsufficientOpeningAmountException;
	//public int[] fundTransfer(int senderAccountNumber,int recieverAccountNumber,int transferAmount) throws InvalidAccountNumberException, InsufficientBalanceException;
	public int depositAmount(int accountNumber,int amount)throws InvalidAccountNumberException;

}