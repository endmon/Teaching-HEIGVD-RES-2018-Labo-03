/*
 * FileName : Person
 * Authors : Remy Nasserzare, Miguel Gouveia
 */

package ch.heigvd.res.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
   
   
}
