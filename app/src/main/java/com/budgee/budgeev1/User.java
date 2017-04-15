package com.budgee.budgeev1;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 * Creator : Cameron Smith.
 * Purpose : Used in the budgee application, 03/2017.
 */

public class User
{
    private String name = "DEFAULT_NAME";
    private String password = null;
    private String email = null;
    private boolean firstTimeSetup = true;
    Budget usersBudget = new Budget();

    /*
    //Email variables
    private String from = null;
    private String host = "localhost";
    Properties properties = System.getProperties();
    properties.setProperty("mail.smtp.host", host);
    Session session = Session.getDefaultInstance(properties);
    */




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
        //Will need to be done in its own class
        //Create a new randomly generated password
        String tempPassword = generatePassword();

        //send password to email address
        sendEmail(getEmail(),tempPassword);

        //set the password to the temp password
        setPassword(tempPassword);
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



    private void createBudget(double budgetLimit)
    {
        usersBudget = new Budget(budgetLimit);
    }

    private String generatePassword()
    {
        //test
        return "helloworld!";
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
