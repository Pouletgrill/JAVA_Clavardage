//Connexion
//----------------
//Xavier Brosseau
//Charlie Laplante

import java.io.*;
import java.net.*;

public class Connexion implements Runnable
{
   String E = null;
   PrintWriter writer = null;
   BufferedReader reader = null;
   public Connexion(Socket client) 
   {
      try
      {
         writer = new PrintWriter(
                     new OutputStreamWriter(
                     client.getOutputStream()));
         reader = new BufferedReader(
                     new InputStreamReader(
                     client.getInputStream()));
      }
      catch (IOException ioe)
      {
         System.err.println(ioe);
         System.exit(1);  
      }           
   }
   public void run()
   {
      System.out.println("Client connecte");
      try
      {
         do 
         {            
            E = reader.readLine();
            if (E != null)
            {
               writer.println(E);
               writer.flush(); 
            }
         }while (E.length()!=0);
      writer.close();
      reader.close();
      System.out.println("Client deconnecte");      
      }
      catch (IOException ez)
      {         
         System.err.println(ez);         
      }
      catch (NullPointerException nue)
      {
         System.err.println("Client deconnecte abruptement");
      }
   }   
}
