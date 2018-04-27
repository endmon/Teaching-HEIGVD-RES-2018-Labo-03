/*
 * FileName : SmtpClientInterface
 * Authors : Remy Nasserzare, Miguel Gouveia
 * 
 */
package ch.heigvd.res.smtp;

import ch.heigvd.res.model.Message;
import java.io.IOException;
import java.net.InetAddress;


public interface SmtpClientInterface
{
   public void sendMessage(Message message) throws IOException;
}
