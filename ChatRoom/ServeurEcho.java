//ServeurEcho
//----------------
//Xavier Brosseau
//Charlie Laplante
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.ListIterator;

public class ServeurEcho
{ 
   ArrayList<Connexion> ListeConnexion = new ArrayList<>();
   public void Serveur()
   {
     
      ServerSocket clientServer;
      final int MAXCONNECT = 5;
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
               if (ListeConnexion.size() < MAXCONNECT)
               {
                  
                  clientServer.setSoTimeout(500);
                  Socket client = clientServer.accept();                  
                  Connexion connexion = new Connexion(client,this);
                  Thread t = new Thread(connexion);
                  t.setDaemon(true);
                  t.start();
                  ListeConnexion.add(connexion);
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
   
   public synchronized void Distribuer(String Message)
   {
   	for(int i= 0 ;  i < ListeConnexion.size(); i ++)
	{
		ListeConnexion.get(i).Ecrire(Message);
        }
   }
   public static void main ( String args[]) throws IOException
   { 
      new ServeurEcho().Serveur();
   }  
}