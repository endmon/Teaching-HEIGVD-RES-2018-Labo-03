/*
 * FileName : ConfigurationManager.java
 * Authors : Remy Nasserzare, Miguel Gouveia
 */

package ch.heigvd.res.config;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.List;
import ch.heigvd.res.model.Person;



public class ConfigurationManager implements ConfigurationManagerInterface
{
   private String smtpAddress;
   private int smtpPort;
   private final List<Person> victimes;
   private final List<String> messages;
   private int numberOfGroups;
   private List<Person> witnessesToCC;
   
   public ConfigurationManager() throws IOException
   {
      messages = loadMessagesFromFile("src\\ch\\heigvd\\res\\config\\messages");
      victimes = loadAddressesFromFile("src\\ch\\heigvd\\res\\config\\victimes");
      
      loadProperties("src\\ch\\heigvd\\res\\config\\config.properties");
   }
   
   private void loadProperties(String filename) throws IOException
   {
      FileInputStream file = new FileInputStream(filename);
      Properties properties = new Properties();
      properties.load(file);
      this.smtpAddress = properties.getProperty("smtpAddress");
      this.smtpPort = Integer.parseInt(properties.getProperty("smtpPort"));
      this.numberOfGroups = Integer.parseInt(properties.getProperty("numberOfGroups"));
      
      this.witnessesToCC = new ArrayList<>();
      String witnesses = properties.getProperty("witnessesToCC");
      String[] witnessesAddresses = witnesses.split(".");
      
      for (String address : witnessesAddresses)
      {
         this.witnessesToCC.add(new Person(address));
      }
   }
   
   private List<Person> loadAddressesFromFile(String fileName) throws IOException
   {
      List<Person> result;
      
      try (FileInputStream file = new FileInputStream(fileName))
      {
         InputStreamReader isr = new InputStreamReader(file, "UTF-8");
         
         try (BufferedReader reader = new BufferedReader(isr))
         {
            result = new ArrayList<>();
            String address = reader.readLine();
            
            while (address != null)
            {
               result.add(new Person(address));
               address = reader.readLine();
            }
         }
      }
      return result;
   }
   
   private List<String> loadMessagesFromFile(String filename) throws IOException
   {
      List<String> result;
      
      try (FileInputStream file = new FileInputStream(filename))
      {
         InputStreamReader isr = new InputStreamReader(file, "UTF-8");
         
         try (BufferedReader reader = new BufferedReader(isr))
         {
            result = new ArrayList<>();
            String line = reader.readLine();
            
            while (line != null)
            {
               StringBuilder body = new StringBuilder();
               
               while ((line != null) && (!line.equals("****")))
               {
                  body.append(line);
                  body.append("\r\n");
                  line = reader.readLine();
               }
               result.add(body.toString());
               line = reader.readLine();
            }
         }
      }
      return result;
   }
   
   @Override
   public String getAddress()
   {
      return smtpAddress;
   }
   
   @Override
   public int getPort()
   {
      return smtpPort;
   }
   
   @Override
   public  int getNumberOfGroup()
   {
      return numberOfGroups;
   }

   @Override
   public List<Person> getWitnessToCC()
   {
      return witnessesToCC;
   }
   
   @Override
   public List<String> getMessages()
   {
      return messages;
   }
   
   @Override
   public List<Person> getVictimes()
   {
      return victimes;
   }
}
