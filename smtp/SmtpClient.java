/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.res.smtp;

import ch.heigvd.res.model.Message;
import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

/**
 *
 * @author endmon
 */
public class SmtpClient implements SmtpClientInterface
{
   private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());
   
   private String smtpAddress;
   private int smtpPort = 2525;
   
   private Socket socket;
   private PrintWriter writer;
   private BufferedReader reader;
   
   public SmtpClient(String smtpAddress, int port)
   {
      this.smtpAddress = smtpAddress;
      this.smtpPort = port;
   }
   
   @Override
   public void sendMessage(Message message) throws IOException
   {
      LOG.info("Sending message via SMTP");
      Socket socket = new Socket(smtpAddress, smtpPort);
      writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
      reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
      String line = reader.readLine();
      LOG.info(line);
      writer.printf("EHLO localhost\r\n");
      line = reader.readLine();
      LOG.info(line);
      
      //throws error if we receive a different message than 250 from the server
      if (!line.startsWith("250"))
      {
         throw new IOException("SMTP error: " + line);
      }
      
      while (line.startsWith("250-"))
      {
         line = reader.readLine();
         LOG.info(line);
      }
      
      writer.write("MAIL FROM:");
      writer.write(message.getFrom());
      writer.write("\r\n");
      writer.flush();
      line = reader.readLine();
      LOG.info(line);
      
      for (String to : message.getTo())
      {
         writer.write("RCPT TO:");
         writer.write(to);
         writer.write("\r\n");
         writer.flush();
         line = reader.readLine();
         LOG.info(line);
      }
      
      for (String to : message.getCc())
      {
         writer.write("RCPT TO:");
         writer.write(to);
         writer.write("\r\n");
         writer.flush();
         line = reader.readLine();
         LOG.info(line);
      }
      
      for (String to : message.getBcc())
      {
         writer.write("RCPT TO:");
         writer.write(to);
         writer.write("\r\n");
         writer.flush();
         line = reader.readLine();
         LOG.info(line);
      }
      
      // we send the data
      writer.write("DATA");
      writer.write("\r\n");
      writer.flush();
      line = reader.readLine();
      LOG.info(line);
      writer.write("Content-Type: text/plain; charset=\"utf-8\"\r\n");
      writer.write("From: " + message.getFrom() + "\r\n");
      
      if (message.getTo().length > 0)
      {
         writer.write("To: " + message.getTo()[0]);
         for (int i = 1; i < message.getTo().length; i++)
         {
            writer.write(", " + message.getTo()[i]);
         }
         writer.write("\r\n");
      }
         
      if (message.getCc().length > 0)
      {
         writer.write("Cc: " + message.getCc()[0]);
         for (int i = 1; i < message.getCc().length; i++)
         {
            writer.write(", " + message.getCc()[i]);
         }
         writer.write("\r\n");
      }
      
      writer.flush();
      LOG.info(message.getBody());
      writer.write(message.getBody());
      writer.write("\r\n");
      writer.write(".");
      writer.write("\r\n");
      writer.flush();
      line = reader.readLine();
      LOG.info(line);
      writer.write("QUIT\r\n");
      writer.flush();
      reader.close();
      writer.close();
      socket.close();
   }
}
