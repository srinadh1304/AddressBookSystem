package com.bridgelabz.addressbookgradle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static org.junit.Assert.assertTrue;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.junit.Test;

import com.bridgelabz.addressbookgradle.IOServiceEnum.IOService;


public class AddressBookDBTest {
	@Test
	public void givenContactsInDB_WhenRetrieved_ShouldMatchContactCount(){
		AddressBookDBService addressBookService = new AddressBookDBService();
		long contactList = addressBookService.readData();
		System.out.println(contactList);
		Assert.assertEquals(6, contactList);
	}
	@Test
	public void givenAContact_WhenInserted_IntoContact_ShouldGetUpdatedSize()
	{
		String date1 = "2018-01-02";
		Contact contactObject=new Contact("chandra", "t", "8463934331", "chandra@gmail.com",date1);
		AddressBookDBService addressBook = new AddressBookDBService();
		long initialSize  = addressBook.readData();
		addressBook.addContact(contactObject);
		long updatedSize=addressBook.readData();
		Assert.assertEquals(initialSize+1, updatedSize);
	}
	@Test
	public void givenContactIdAndPhonenumber_WhenUpdated_shouldReturnOne()
	{
		AddressBookDBService addressBook = new AddressBookDBService();
		addressBook.readData();
		int count=addressBook.updatePhonenumberOfContact("9290090032", 1);
		Assert.assertEquals(1,count);
	}
	@Test
	public void givenDateRange_FindContactsAddedInThatRange_ShouldReturnCount() {
		AddressBookDBService addressBook = new AddressBookDBService();
		String date1 = "2017-08-02";
		String date2 = "2019-09-09";
		long count = addressBook.getContactsInDateRange(date1,date2);
		Assert.assertEquals(5, count);
	}
	@Test
	public void givenCity_FindContactsInThatCity_ShouldReturnCount() {
		AddressBookDBService addressBook = new AddressBookDBService();
		HashMap<String,ArrayList<String>> contactsByAddressBook = addressBook.getContactsByCity("Bengaluru");
		System.out.println(contactsByAddressBook);
		Assert.assertEquals(2, contactsByAddressBook.get("address_book1").size());
	}
	@Test
	public void givenState_FindContactsInThatState_ShouldReturnCount() {
		AddressBookDBService addressBook = new AddressBookDBService();
		HashMap<String,ArrayList<String>> contactsByAddressBook = addressBook.getContactsByState("Maharashtra");
		System.out.println(contactsByAddressBook);
		Assert.assertEquals(2, (contactsByAddressBook.get("address_book1").size()+contactsByAddressBook.get("address_book2").size()));
	}
	@Test
	public void givenEmployeePayrollInDB_whenAddedShouldMatchDB() {
		Contact contact = new Contact("Mark","Wade","7894561230","m@m.com");
		AddressBookDBService address=new AddressBookDBService();
		long initialSize  = address.readData();
		address.addContact(contact);
		long updatedSize=address.readData();
		Assert.assertEquals(initialSize+1, updatedSize);
	}
}
