/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.res.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author 
 */
public class Person
{
   private String firstName;
   
   private String lastName;
   
   private final String mail;
   
   public Person(String firstName, String lastName, String mail)
   {
      this.firstName = firstName;
      this.lastName = lastName;
      this.mail = mail;
   }
   
   public Person(String mail)
   {
      this.firstName = mail.substring(0, mail.indexOf('.'));
      this.lastName = mail.substring(mail.indexOf('.'), mail.indexOf('@'));
      this.mail = mail;
   }
   
   public String getFirstName()
   {
      return firstName;
   }
   
   public String getLastName()
   {
      return lastName;
   }
   
   public String getMail()
   {
      return mail;
   }
}
