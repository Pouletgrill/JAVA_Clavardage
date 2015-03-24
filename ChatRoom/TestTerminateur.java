//TestTerminateur
//----------------
//Xavier Brosseau
//Charlie Laplante
import java.io.*;
public class TestTerminateur
{
   public static void main ( String args[] )
   {
      int Sieste = 500;
      String Caractere = ".";
       try
       {
         //On déclare et start le Thread
         Terminateur Liseur = new Terminateur();
         Thread threadLiseur = new Thread(Liseur);
         threadLiseur.start(); 
      
         //Tant que le thread n'a pas trouver de Q (est en vie)
         while (threadLiseur.isAlive())
         {           
               System.out.print(Caractere);
               Thread.sleep(Sieste);          
         } 
       }
       catch (InterruptedException e)
       {  
          System.err.println(e);
          System.exit(1);  
       }
     
   }
}