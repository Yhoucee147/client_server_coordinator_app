/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderclient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author youcee
 */
public class OrderClient {

    /**
     * @param args
     */
    
    boolean repeat = true;
    
    public void runApp(){
        try{
            System.out.println("Welcome to the order portal");
            System.out.println("***************************************************************");
            System.out.println("Please enter appropriate integer for the required option");
            System.out.println("1. Order a book");
            System.out.println("2. Order a movie");
            System.out.println("3. Exit");
            System.out.println("***************************************************************");
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Integer here : ");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    {
                        InetAddress ip = InetAddress.getLocalHost();
                        int port = 1144;
                        List<String> order = new ArrayList<>();
                        order.add(("1"));
                        Scanner sc1 = new Scanner(System.in);
                        System.out.println("Please Enter book Price : ");
                        order.add(String.valueOf(sc1.nextDouble()));
                       System.out.println("Please enter book Quantity : ");
                       order.add(String.valueOf(sc1.nextInt()));
                    try (Socket s2 = new Socket(ip, port)) {
                        ObjectInputStream dis;
                            try (ObjectOutputStream oos = new ObjectOutputStream(s2.getOutputStream())) {
                                oos.flush();
                                oos.writeObject(order);
                                dis = new ObjectInputStream(s2.getInputStream());
                                List<String> feed = (ArrayList)dis.readObject();
                                BookOrder book = new BookOrder();
                                book.setUnitPrice(Double.parseDouble(feed.get(0)));
                                book.setQuantity(Integer.parseInt(feed.get(1)));
                                book.setTotalBill(Double.parseDouble(feed.get(2)));
                                System.out.println(book.getResult());
                            }
                    }
                        break;
                    }

                case 2:
                    {
                        InetAddress ip = InetAddress.getLocalHost();
                        int port = 1144;
                        List<String> order = new ArrayList<>();
                        order.add("2");
                        Scanner sc3 = new Scanner(System.in);
                        System.out.println("Please Enter movie Price : ");
                        order.add(String.valueOf(sc3.nextDouble()));
                        System.out.println("Please enter movie Quantity : ");
                        order.add(String.valueOf(sc3.nextInt()));
                    try (Socket s2 = new Socket(ip, port)) {
                        ObjectInputStream dis2;
                            try (ObjectOutputStream oos2 = new ObjectOutputStream(s2.getOutputStream())) {
                                oos2.flush();
                                oos2.writeObject(order);
                                dis2 = new ObjectInputStream(s2.getInputStream());
                                List<String> feedback = (ArrayList)dis2.readObject();
                                MovieOrder movie = new MovieOrder();
                                movie.setUnitPrice(Double.parseDouble(feedback.get(0)));
                                movie.setQuantity(Integer.parseInt(feedback.get(1)));
                                movie.setTotalBill(Double.parseDouble(feedback.get(2)));
                                System.out.println(movie.getResult());
                            }    
                    }
                        break;
                    }

                case 3:
                    System.out.println("You have chosen to terminate the Program");
                    System.out.println("Thanks for your time, bye !!!");
                    System.exit(0);
                default:
                    System.out.println("You have entered an invalid option, Please try again by typing Y  for a fresh order");
                    break;
            }
         Scanner sc2 = new Scanner(System.in); 
         System.out.println("Do you wish to order fresh Merchandise? Enter Y or N");
         String decision = sc2.next();
         repeat = decision.equalsIgnoreCase("Y");
        
        }catch (IOException | ClassNotFoundException | NumberFormatException e){
            
        }
    }
    
    public void loopApp(){
        while(repeat){
            runApp();
        }
    }
    
    public static void main(String[] args) {
        
       OrderClient orderclient = new OrderClient();
       orderclient.loopApp();
        
    }
    
}
