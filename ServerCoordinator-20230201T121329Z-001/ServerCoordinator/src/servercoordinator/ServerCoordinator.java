/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servercoordinator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author youcee
 */
public class ServerCoordinator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("The Coordinator server is running...");
        SeperateInstance t = new SeperateInstance();
        System.out.println("Ready to listen on a seperate thread");
        t.start();
    
    }
}
 class SeperateInstance extends Thread{
      
      
      
      @Override
      public void run(){
          int count = 0;
          try { 
              ServerSocket listener = new ServerSocket(1144);
              while (true){
                  Socket soc1 = listener.accept();
                  ObjectInputStream ois = new ObjectInputStream(soc1.getInputStream());
                  ObjectOutputStream dos = new ObjectOutputStream(soc1.getOutputStream());
                  List<String> data = (ArrayList)ois.readObject();
                  count++;
                  System.out.println("This is request number : " + count);
              if (data.get(0).equalsIgnoreCase("1")){
                          InetAddress ip = InetAddress.getLocalHost(); 
                          int port = 1122; 
                          Socket s = new Socket(ip, port); 
                          ObjectOutputStream ois2 = new ObjectOutputStream(s.getOutputStream());
                          ObjectInputStream dis2 = new ObjectInputStream(s.getInputStream());
                          ois2.flush();
                          ois2.writeObject(data);
                          List<String> order = (ArrayList)dis2.readObject();
                          dos.writeObject(order);
                          System.out.println("Book order report sent"); 
                          ois2.close();
                          dis2.close();
                          s.close();
              }  
              if (data.get(0).equalsIgnoreCase("2")){
                          InetAddress ip2 = InetAddress.getLocalHost(); 
                          int port2 = 1133; 
                          Socket s1 = new Socket(ip2, port2); 
                          ObjectOutputStream ois3 = new ObjectOutputStream(s1.getOutputStream());
                          ObjectInputStream dis3 = new ObjectInputStream(s1.getInputStream());
                          ois3.flush();
                          ois3.writeObject(data);
                          List<String> ords = (ArrayList)dis3.readObject();
                          dos.writeObject(ords); 
                          System.out.println("Movie order report sent");
                          ois3.close();
                          dis3.close();
                          s1.close();
              }
              ois.close();
              dos.close();
              soc1.close();
                  }
              
              
          } catch (IOException | ClassNotFoundException ex) {
              Logger.getLogger(SeperateInstance.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
  }  


 
