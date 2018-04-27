/*
 * FileName : PrankGenerator
 * Authors : Miguel Gouveia, Remy Nasserzare
 */
package ch.heigvd.res.model;

import ch.heigvd.res.config.ConfigurationManager;
import ch.heigvd.res.config.ConfigurationManagerInterface;
import ch.heigvd.res.model.Group;
import ch.heigvd.res.model.Person;
import java.util.*;
import java.util.logging.Logger;


public class PrankGenerator
{
   private static final Logger LOG = Logger.getLogger(PrankGenerator.class.getName());
   private ConfigurationManagerInterface configurationManager;
   
   public PrankGenerator(ConfigurationManagerInterface configurationManageri)
   {
      this.configurationManager = configurationManageri;
   }
   
   public List<Group> generateGroups(List<Person> victimes, int numberOfGroups)
   {
      List<Person> availableVictimes = new ArrayList(victimes);
      Collections.shuffle(availableVictimes);
      List<Group> groups = new ArrayList<>();
      
      for (int i = 0; i < numberOfGroups; i++)
      {
         Group group = new Group();
         groups.add(group);
      }
      int turn = 0;
      Group targetGroup;
      
      while (availableVictimes.size() > 0)
      {
         targetGroup = groups.get(turn);
         turn = (turn + 1) % groups.size();
         Person victime = availableVictimes.remove(0);
         targetGroup.addMember(victime);
      }
      return groups;
   }
   
   public List<Prank> generatePranks()
   {
      List<Prank> pranks = new ArrayList<>();
      List<String> messages = configurationManager.getMessages();
      int messageIndex = 0;
      int numberOfGroups = configurationManager.getNumberOfGroup();
      int numberOfVictimes = configurationManager.getVictimes().size();
      
      if (numberOfVictimes / numberOfGroups < 3)
      {
         numberOfGroups = numberOfVictimes / 3;
         LOG.warning("There are not enough victims to generate the desired number of groups. We can only generate a max of " + numberOfGroups + " groups to have at least 3 victims per groups");
      }
      
      List<Group> groups = generateGroups(configurationManager.getVictimes(), numberOfGroups);
      
      for (Group group : groups)
      {
         Prank prank = new Prank();
   
         List<Person> victimes = group.getMembers();
         Collections.shuffle(victimes);
         Person sender = victimes.remove(0);
         prank.setVictimeSender(sender);
         prank.addVictimeRecipients(victimes);
         prank.addWitnessRecipients(configurationManager.getWitnessToCC());
      
         String message = messages.get(messageIndex);
         messageIndex = (messageIndex + 1) % messages.size();
         prank.setMessage(message);
         
         pranks.add(prank);
      }
      return pranks;
   }
}
