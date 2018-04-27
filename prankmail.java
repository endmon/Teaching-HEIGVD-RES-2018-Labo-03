/*
 * FileName : prankmail.java
 * Authors : Miguel Gouveia, Remy Nasserzare
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


public class prankmail {

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) {
      
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
         
      } catch (IOException e)
      {
         System.err.println(e.getMessage());
      }
      
   }
   
}
