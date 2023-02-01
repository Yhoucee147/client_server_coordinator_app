/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverbook;

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
public class ServerBook {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Thread td = new Thread(() -> {
            int count = 0;
            try {
                // TODO code application logic here
                ServerSocket servsoc = new ServerSocket(1122);
                while(true){
                    System.out.println("Book server running, Listening at designated port");
                    count++;
                    Socket soc = servsoc.accept();
                    ObjectInputStream inst = new ObjectInputStream(soc.getInputStream());
                    ObjectOutputStream obst = new ObjectOutputStream(soc.getOutputStream());
                    obst.flush();
                    List<String> ord;
                    ord = (ArrayList)inst.readObject();
                    BookOrder book = new BookOrder();
                    book.setUnitPrice(Double.parseDouble(ord.get(1)));
                    book.setQuantity(Integer.parseInt(ord.get(2)));
                    System.out.println("Book order recieved from Control Server");
                    book.executeTask();
                    List<String> feed = new ArrayList<>();
                    feed.add(String.valueOf(book.getUnitPrice()));
                    feed.add(String.valueOf(book.getQuantity()));
                    feed.add(String.valueOf(book.getTotalBill()));
                    obst.writeObject(feed);
                    System.out.println("Book order report sent to coordinating server");
                    System.out.println(count + "book order recieved ");
                    inst.close();
                    obst.close();
                    soc.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ServerBook.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServerBook.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        td.start();
    }
    
}
