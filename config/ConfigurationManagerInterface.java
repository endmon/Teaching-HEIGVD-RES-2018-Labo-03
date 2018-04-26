/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.res.config;

import ch.heigvd.res.model.Message;
import ch.heigvd.res.model.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author 
 */
public interface ConfigurationManagerInterface
{
   public String getAddress();
   public int getPort();
   public  int getNumberOfGroup();

   public List<Person> getWitnessToCC();
   public List<String> getMessages();
   public List<Person> getVictimes();
}
