package com.capgemini.test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.beans.Account;
import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientOpeningAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.repo.AccountRepo;
import com.capgemini.service.AccountService;
import com.capgemini.service.AccountServiceImpl;

 public class AccountTest {
	 @Mock
	 AccountRepo accountRepo;
	 AccountService accountService;

	@Before
	 public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		accountService= new AccountServiceImpl(accountRepo);
	}

	@Test(expected=com.capgemini.exceptions.InsufficientOpeningAmountException.class)
	public void whenTheAmountIsLessThanFiveHundredSystemShouldThrowException() throws InsufficientOpeningAmountException
	{
		accountService.createAccount(101, 400);
	}
	@Test(expected=com.capgemini.exceptions.InsufficientOpeningAmountException.class)
	public void whenTheValidInfoIsPassedAccountShouldBeCreatedSuccessfully() throws InsufficientOpeningAmountException
	{
		Account account =new Account(101,5000);
		when(accountRepo.save(account)).thenReturn(true);
	    assertEquals(account, accountService.createAccount(101, 5000));
	}
	@Test(expected=com.capgemini.exceptions.InvalidAccountNumberException.class)
	public void whenInvalidAccountSystemShouldThrowException() throws InvalidAccountNumberException
	{
		Account account=new Account(101,5000);
		when(accountRepo.searchAccount(101)).thenReturn(account);
		accountService.depositAmount(102, 700);
	}	
    
	@Test(expected=com.capgemini.exceptions.InvalidAccountNumberException.class)
	public void whenInsufficientBalanceWhileWithdrawShouldThrowException() throws InvalidAccountNumberException,InsufficientBalanceException
	{
		Account account =new Account(101,5000);
		when(accountRepo.searchAccount(101)).thenReturn(account);
		accountService.withdrawAmount(101, 5500);
	}
	
	@Test(expected=com.capgemini.exceptions.InvalidAccountNumberException.class)
	public void whenSufficientBalanceWithdrawShouldSuccessfull() throws InvalidAccountNumberException,InsufficientBalanceException
	{
		Account account =new Account(101,5000);
		when(accountRepo.searchAccount(101)).thenReturn(account);
		accountService.withdrawAmount(101, 4500);
	}
	
	@Test(expected=com.capgemini.exceptions.InvalidAccountNumberException.class)
	public void whenInvalidAccountNumberWithrawShouldThrowException() throws InvalidAccountNumberException,InsufficientBalanceException
	{
		Account account =new Account(101,5000);
		when(accountRepo.searchAccount(101)).thenReturn(account);
		accountService.withdrawAmount(102, 4500);
	}
	
	@Test(expected=com.capgemini.exceptions.InvalidAccountNumberException.class)
	public void whenValidAccountNumberDepositShouldSuccess() throws InvalidAccountNumberException
	{
		Account account=new Account(101,5000);
		when(accountRepo.searchAccount(101)).thenReturn(account);
		accountService.depositAmount(101, 500);
	}
}