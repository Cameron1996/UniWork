package com.budgee.budgeev1;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.Random;

/**
 * Creator : Cameron Smith.
 * Purpose : Used in the budgee application, 03/2017.
 */

public class User
{
    private String name = "DEFAULT_NAME";
    private String securityQuestion = "What was the name of your first pet?";
    private String password = null; // will need to be encrypted when stored in database
    private String securityAnswer; // will need to be encrypted when stored in database
    private String email = null;
    private String hint = null;
    Budget usersBudget = new Budget();
    
	
	// To be used for the first time setup, I am not sure how yous are taking the input..
    public BudgeeUser(String name, Budget usersBudget, String password, String email, String securityAnswer) {
		super();
		this.name = name;
		this.usersBudget = usersBudget;
		this.password = password;
	    	this.email = email;
	    	this.securityAnswer = securityAnswer;
    }
	
   public char[] encrypt(char[] toEncypt){
	char[] word = toEncypt;
	char[] word2 = new char[toEncypt.length];
	for(int i=0; i<word.length; i++)
	{
		char tempChar;
		tempChar = word[i];
		tempChar++;
		word2[i]=tempChar;
	}
	return word2;
}

public char[] decrypt(char[] toDecrypt){
	char[] word = toDecrypt;
	char[] word2 = new char[toDecrypt.length];
	for(int i=0; i<word.length; i++)
	{
		char tempChar;
		tempChar = word[i];
		tempChar--;
		word2[i]=tempChar;
	}
	return word2;}
}

    public void saveToDataBase(String name, String password, String hint){
    
    }
 
    private void printUsersInfo()
    {
        System.out.println("Name is " + name + ". Email is " + email + ".");
    }

    private boolean signIn(String userName, String Password)
    {
       if((userName.equals(name)) && (password.equals(decrypt(password))))
       {
           return true;
       }

           return  false;
    }

    private void resetPassword()
    {
	 if (securityCheck()){
	 String tempPassword = generatePassword();
 	 System.out.println("temporary password is " + tempPassword + ". Please reset your password as soon as you login");
	 setPassword(tempPassword);
	 }
        //Will need to output generated password to display for the user to see
    }

    private String encrypt(String password)
    {
        char[] encrpytedArray;
        char[] passwordArray = password.toCharArray();
        private String encrpytedPassword = null;
        for(int i=0;i<passwordchar.length;i++)
        {
            encrpytedArray[i]=(passwordArray[i])++;
        }
        encrpytedPassword = new String(encrpytedArray);
        return encrpytedPassword;
    }

    private String decrypt(String password)
    {
        char[] encryptedPasswordArray = password.toCharArray();
        char[] unencryptedPasswordArray;
        private String unencryptedPassword = null;
        for(int i=0; i<encryptedPasswordArray.length();i++)
        {
            unencryptedPasswordArray[i]=encryptedPasswordArray[i]--;
        }
        unencrpytedPassword = new String(unencryptedPasswordArray);
        return password;
    }

    private Boolean SecurityCheck(String answer){
		if (answer.equalsIgnoreCase(decrypt(securityAnswer)))
		{
		return true;
		}
		return false;
	}

    private void createBudget(double budgetLimit)
    {
        usersBudget = new Budget(budgetLimit);
    }

    public static String genaratePassword(){
		char[] letters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		int i,j,k,l;
		
		Random random = new Random();

		i = random.nextInt(26);
		j = random.nextInt(26);
		k = random.nextInt(26);
		l = random.nextInt(26);
		
		char charOne = letters[i];
		char charTwo = letters[j];
		char charThree = letters[k];
		char charFour = letters[l];
		
		String newPassword = ""+charOne+charTwo+charThree+charFour;
		return newPassword;
		
	}


    //Constructor/s
    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
