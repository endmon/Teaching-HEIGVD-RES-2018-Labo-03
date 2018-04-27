# Teaching-HEIGVD-RES-2018-Labo-03

Authors : Miguel Gouveia, Remy Nasserzare

## This Repository

We made this entire repo for a very cool exercise we have to do !
The purpose is to create a SMTP server and to also create a class in order to send spam randomly.

This random spam we want to send is created using a few classes such as :

* Prank : this class represents the spam e-mail we will send, as well as who it will be sent to.
* Group, Message, Person : these classes are here to help us manage the data and the implementation.
* PrankGenerator : picks the messages and the victims randomly in order to make up the prank.
* ConfigManager : reads the files config.properties, messages.utf8, and victims.utf8 and gives us access to the data.
* SmtpClient : used to connect a mockmock server and send the prank.

We stock the project on a docker container to help with the implementation of the project on other computers, 
and the mockmock server .jar can be found in the docker !

## PrankGenerator Configuration

In order to use it, we have to modify a couple files :

* messages.utf8. we will write all the different messages that you want to use in your prank, as follows :

** put "Subject:MySubject" in the e-mail, or just "Subject:" if you don't want a subject.
** write your message.
** at the end of your message, write a line with just "" in it.
** the empty lines are not considered by the e-mail.

* victims.utf8. we will write all the adresses to prank, which will be split in the number of groups you want in the file 
  config.properties, so you wanna have the number of adresses at least equal to the number of groups.
  
** you have to write one e-mail per line for this prank.

## Docker configuration

In order to run the server, you may want to build and run it.
Here's how you should do it (on a Linux OS) :

* First, make sure you are in the root directory of your projec, where the dockerfile is.

* To build your docker image open a terminal and type :

** sudo docker build -t mock-smtp

this instruction will run the docker containing the smtp server with port 2525 used for the protocol SMTP and
also the port 8080 to access the mockmock server web interface.

** sudo docker run -p 8080:8080 -p 2525:2525 mock-smtp

## Let's Prank People !

Everything is now up and running!
Now, you can build and run the project and it should start the campaign according to the configuration of your files.

# ENJOY ! ;)
