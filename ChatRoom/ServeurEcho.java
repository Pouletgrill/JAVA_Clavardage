//ServeurEcho
//----------------
//Xavier Brosseau
//Charlie Laplante
import java.io.*;
import java.net.*;
public class ServeurEcho
{
   public static void main ( String args[]) throws IOException
   {
      ServerSocket clientServer;
      
      try
      {
         clientServer = new ServerSocket(7);
         
         Terminateur QStop = new Terminateur();
         Thread threadLiseur = new Thread(QStop);
         threadLiseur.setDaemon(true);
         threadLiseur.start(); 

         while (threadLiseur.isAlive())
         {
            try
            {
            clientServer.setSoTimeout(500);
            Socket client = clientServer.accept();
            
            Connexion connexion = new Connexion(client);
            Thread t = new Thread(connexion);
            t.setDaemon(true);
            t.start();   
            }
            catch (IOException ey)
            {
            }
         }        
      }
      catch(IOException ez)
      {
        System.err.println(ez);
        ez.printStackTrace();
        System.exit(1);  
      }
   }  
}