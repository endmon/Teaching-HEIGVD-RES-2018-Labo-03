/*
 * FileName : ConfigurationManagerInterface.java
 * Authors : Remy Nasserzare, Miguel Lopes Gouveia
 */
package ch.heigvd.res.config;

import ch.heigvd.res.model.Message;
import ch.heigvd.res.model.Person;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public interface ConfigurationManagerInterface
{
   public String getAddress();
   public int getPort();
   public  int getNumberOfGroup();
   public List<Person> getWitnessToCC();
   public List<String> getMessages();
   public List<Person> getVictimes();
}
