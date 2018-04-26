/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.res;

import ch.heigvd.res.config.ConfigurationManager;
import ch.heigvd.res.model.Message;
import ch.heigvd.res.model.Person;
import java.io.FileInputStream;
import ch.heigvd.res.model.Prank;
import ch.heigvd.res.model.PrankGenerator;
import ch.heigvd.res.smtp.SmtpClient;
import com.sun.istack.internal.logging.Logger;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author
 */
public class prankmail {

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) {
      // TODO code application logic here
      try
      {
         ConfigurationManager manager = new ConfigurationManager();
         SmtpClient client = new SmtpClient(manager.getAddress(), manager.getPort());
         
         PrankGenerator prankGenerator = new PrankGenerator(manager);
         List<Prank> pranks = prankGenerator.generatePranks();
         Message mail;
         
         for (Prank prank : pranks)
         {
            mail = prank.generateMessage();
            
            client.sendMessage(mail);
         }
         
         
      }catch (IOException e)
      {
         System.err.println(e.getMessage());
      }
      
   }
   
}
