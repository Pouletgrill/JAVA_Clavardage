//ServeurEcho
//----------------
//Xavier Brosseau
//Charlie Laplante
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ServeurEcho
{
   static ArrayList<Thread> PitBull = new ArrayList<>();
   public static void main ( String args[]) throws IOException
   {
      ServerSocket clientServer;
      final int MAXCONNECT = 2;
      try
      {
         clientServer = new ServerSocket(50000);
         
         Terminateur QStop = new Terminateur();
         Thread threadLiseur = new Thread(QStop);
         threadLiseur.setDaemon(true);
         threadLiseur.start(); 

         while (threadLiseur.isAlive())
         {
            try
            {
               if (PitBull.size() < MAXCONNECT)
               {
                  clientServer.setSoTimeout(500);
                  Socket client = clientServer.accept();
                  
                  Connexion connexion = new Connexion(client);
                  Thread t = new Thread(connexion);
                  t.setDaemon(true);
                  t.start();
                  PitBull.add(t);
               }
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