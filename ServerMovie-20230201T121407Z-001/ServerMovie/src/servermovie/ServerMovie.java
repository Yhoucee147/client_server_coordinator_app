/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servermovie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
public class ServerMovie {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Thread td = new Thread(() -> {
            int count = 0;
            try {
                // TODO code application logic here
                ServerSocket servsoc = new ServerSocket(1133);
                while (true){
                    System.out.println("Movie server running, Listening at designated port");
                    count++;
                    Socket soc = servsoc.accept();
                    ObjectInputStream inst = new ObjectInputStream(soc.getInputStream());
                    ObjectOutputStream obst = new ObjectOutputStream(soc.getOutputStream());
                    obst.flush();
                    List<String> ord;
                    ord = (ArrayList)inst.readObject();
                    MovieOrder mov = new MovieOrder();
                    mov.setUnitPrice(Double.parseDouble(ord.get(1)));
                    mov.setQuantity(Integer.parseInt(ord.get(2)));
                    System.out.println("Movie order Recieved");
                    mov.executeTask();
                    List<String> feed = new ArrayList<>();
                    feed.add(String.valueOf(mov.getUnitPrice()));
                    feed.add(String.valueOf(mov.getQuantity()));
                    feed.add(String.valueOf(mov.getTotalBill()));
                    obst.writeObject(feed);
                    System.out.println("Movie order report Sent to Coordinating server");
                    System.out.println(count + "movie order recieved ");
                    inst.close();
                    obst.close();
                    soc.close();
                }
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(ServerMovie.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        td.start();
    }
    
}
