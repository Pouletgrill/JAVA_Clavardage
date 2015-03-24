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
<<<<<<< HEAD
   
   
=======
   static int NbConnexion = 0; 
   final int MAXCONNECT = 3;
      
>>>>>>> origin/master
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
      catch(SocketTimeoutException ez)
      {
         System.out.println("Trop long innactif");         
      }
      catch (IOException ioe)
      {
         System.err.println(ioe);
         System.exit(1);  
      }           
   }
   public void Ecrire(String Message)
      {
        writer.println(Message);
        writer.flush();
      }
	
   public void run()
<<<<<<< HEAD
   {
     System.out.println("Client connecte");
     
     boolean vide = false;
      try
      {
         writer.println("Veuillez entrer votre nom d'utilisateur");
         writer.flush();
         Nom = reader.readLine(); 
         
         if(Nom.length() > 8)
         {
            Nom  =  Nom.substring(0,8);        
         }    
         
         if(Nom.length() < 1)
         {
              Nom = Ip;         
         }      
         serveurEcho_.Distribuer(Nom + " vient de se joindre a la conversation"); 
         do 
         {         
           
            E = reader.readLine();
=======
   {      
     if (NbConnexion < MAXCONNECT)
     {      
         NbConnexion++;
         System.out.println("Client connecte");
         boolean vide = false;
         try
         {
            writer.print("Username :");
            writer.flush();
            Nom = reader.readLine(); 
>>>>>>> origin/master
            
            if(Nom.length() > 8)
            {
               Nom  =  Nom.substring(0,8);        
            }    
            
            if(Nom.length() < 1)
            {
                 Nom = Ip;         
            }      
            
<<<<<<< HEAD
                serveurEcho_.Distribuer(E);                
            }            
         }while (!vide);         
      }
      catch (NullPointerException nue)
      {       
      }
      catch (IOException ez)
      {         
      }    
      finally
      {          
         try
         {
            writer.close();
            reader.close();
            serveurEcho_.TuerConnexion(this); 
         }
         catch(IOException ez)
         {
         }
            System.out.println("Client deconnecte");
      }
=======
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
         NbConnexion--;
         }
         catch (IOException ez)
         {         
            System.err.println(ez);         
         }
         catch (NullPointerException nue)
         {
            System.err.println("Client deconnecte abruptement");
            NbConnexion--;
         }
     }
     else
     {
       writer.print("Limite de connexion ateint");
       writer.flush();
       System.out.println("Le client ne peu se connecter, Nombre de connexion Max ateint");
     }
>>>>>>> origin/master
   }   
}
