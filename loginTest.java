package test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import com.google.gson.JsonObject;

import model.fetcher.CredentialsCSVLoader;


public class loginTest {

	// Login attempt with correct username and password combination
	@Test
	public void correctLogin() throws FileNotFoundException {
		CredentialsCSVLoader csv = new CredentialsCSVLoader();
		
		Boolean userResult = csv.Search("JohnDoe", "12345");
		
		System.out.println("--------------------------------------");
		
		assertTrue(userResult);
	}
	
	// Login attempt with non-existing username and password
	@Test
	public void incorrectLogin() throws FileNotFoundException {
		CredentialsCSVLoader csv = new CredentialsCSVLoader();
		
		Boolean userResult = csv.Search("Bob", "456905");
		
		System.out.println("--------------------------------------");
		
		assertFalse(userResult);
	}
	
	
	// Login attempt with correct password but non-existing username
	@Test
	public void incorrectUserLogin() throws FileNotFoundException {
		CredentialsCSVLoader csv = new CredentialsCSVLoader();
		
		Boolean userResult = csv.Search("Yugi", "12345");
		
		System.out.println("--------------------------------------");
		
		assertFalse(userResult);
	}
	
	// Login attempt with correct username but non-existing password
	@Test
	public void incorrectPasswordLogin() throws FileNotFoundException {
		CredentialsCSVLoader csv = new CredentialsCSVLoader();
		
		Boolean userResult = csv.Search("JaneDoe", "71902");
		
		System.out.println("--------------------------------------");
		
		assertFalse(userResult);
	}

}
