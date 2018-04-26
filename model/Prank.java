/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.res.model;

import ch.heigvd.res.model.Message;
import ch.heigvd.res.model.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author 
 */
public class Prank
{
   private Person victimeSender;
   private final List<Person> victimeRecipients = new ArrayList<>();
   private final List<Person> witnessRecipients = new ArrayList<>();
   private String message;
   
   public Person getVictimeSender()
   {
      return victimeSender;
   }
   
   public void setVictimeSender(Person victimeSender)
   {
      this.victimeSender = victimeSender;
   }
   
   public String getMessage()
   {
      return message;
   }
   
   public void setMessage(String message)
   {
      this.message = message;
   }
   
   public void addVictimeRecipients(List<Person> victimes)
   {
      victimeRecipients.addAll(victimes);
   }
   
   public void addWitnessRecipients(List<Person> witnesses)
   {
      witnessRecipients.addAll(witnesses);
   }
   
   public List<Person> getVictimeRecipients()
   {
      return new ArrayList(victimeRecipients);
   }
   
   public List<Person> getWitnessRecipients()
   {
      return new ArrayList(witnessRecipients);
   }
   
   public Message generateMailMessage()
   {
      Message message = new Message();
      
      message.setBody(this.message + "\r\n" + victimeSender.getFirstName());
      
      String[] to = victimeRecipients
              .stream()
              .map(p -> p.getMail())
              .collect(Collectors.toList())
              .toArray(new String []{});
      message.setTo(to);
      
      String[] cc = witnessRecipients
              .stream()
              .map(p -> p.getMail())
              .collect(Collectors.toList())
              .toArray(new String []{});
      message.setFrom(victimeSender.getMail());
      
      return message;
   }
}
