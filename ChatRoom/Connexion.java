//Connexion
//----------------
//Xavier Brosseau
//Charlie Laplante

import java.io.*;
import java.net.*;
import java.lang.Object;

public class Connexion implements Runnable
{
   String E = null;
   PrintWriter writer = null;
   BufferedReader reader = null;
   String Nom;
   String Ip;
   ServeurEcho serveurEcho_;
   public Connexion(Socket client,  ServeurEcho serveurEcho) 
   {     
      try
      {
         writer = new PrintWriter(
                     new OutputStreamWriter(
                     client.getOutputStream()));
         reader = new BufferedReader(
                     new InputStreamReader(
                     client.getInputStream()));
                     
         Ip = client.getInetAddress().getHostAddress();
         serveurEcho_ = serveurEcho;           

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
     boolean vide = false;
      try
      {
         writer.print("Username :");
         writer.flush();
         Nom = reader.readLine(); 
         
         if(Nom.length() < 1)
         {
              Nom = Ip;         
         }      
         
         if(Nom.length() > 8)
         {
            Nom  =  Nom.substring(0,8);        
         }    
         
         do 
         {         
           
            E = reader.readLine();
            
            if(E.length() > 80)
            {
               E = E.substring(0,80);
            }
            if(E.length() == 0)
            {
               vide = true;               
            }
            else
            {
                E = Nom + ": " + E;
            
                serveurEcho_.Distribuer(E);                
            }
                     
            
         }while (!vide);
         
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
