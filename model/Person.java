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
   
   private final String address;
   
   public Person(String firstName, String lastName, String address)
   {
      this.firstName = firstName;
      this.lastName = lastName;
      this.address = address;
   }
   
   public Person(String address)
   {
      this.firstName = firstName;
      this.lastName = lastName;
      this.address = address;
   }
}
