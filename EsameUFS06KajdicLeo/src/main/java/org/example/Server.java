package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    static int portNumber = 1234;
    static List<Shoes> shoes = new ArrayList<Shoes>();


    static void buildList() {
        shoes.add(new Shoes(13,"Woman shoes for winter",225.94, "woman"));
        shoes.add(new Shoes(14,"Football shoes 2009",133.0, "man"));
        shoes.add(new Shoes(124,"Black ceremony shoes Walch 2018",423.0, "man"));
        System.out.println(shoes);
    }

    public static void main(String[] args) {

        buildList();
        System.out.println("Server in ascolto sulla porta: " + portNumber);
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket, shoes);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            System.out.println("Connessione fallita");
            //e.printStackTrace();
        }
    }
}
