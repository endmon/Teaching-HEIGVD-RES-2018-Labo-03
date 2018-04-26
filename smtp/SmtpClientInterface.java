/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.res.smtp;

import ch.heigvd.res.model.Message;
import java.io.IOException;
import java.net.InetAddress;

/**
 *
 * @author 
 */
public interface SmtpClientInterface
{
   public void sendMessage(Message message) throws IOException;
}
