package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private List<Shoes> shoes;
    private Gson gson;

    public ClientHandler(Socket clientSocket, List<Shoes> shoes) {
        this.clientSocket = clientSocket;
        this.shoes = shoes;
        this.gson = new Gson();

        System.out.println(clientSocket.getInetAddress());
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            while (true) {
                String cmd = in.readLine();
                System.out.println(cmd);

                if (cmd == null) {
                    break;
                }

                String result = executeCommand(cmd);
                out.println(result);
            }

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String executeCommand(String cmd) {
        if ("for_man".equals(cmd)) {
            List<Shoes> manShoes = new ArrayList<>();
            for (Shoes shoe : shoes) {
                if ("man".equals(shoe.getGenre())) {
                    manShoes.add(shoe);
                }
            }
            Answer answer = new Answer(true, gson.toJson(manShoes));
            return answer.asJSON();
        } else if ("for_woman".equals(cmd)) {
            List<Shoes> womanShoes = new ArrayList<>();
            for (Shoes shoe : shoes) {
                if ("woman".equals(shoe.getGenre())) {
                    womanShoes.add(shoe);
                }
            }
            Answer answer = new Answer(true, gson.toJson(womanShoes));
            return answer.asJSON();
        } else if ("sorted_by_name".equals(cmd)) {
            List<Shoes> sortedShoes = new ArrayList<>(shoes);
            sortShoesByName(sortedShoes);
            Answer answer = new Answer(true, gson.toJson(sortedShoes));
            return answer.asJSON();
        } else if ("sorted_by_price".equals(cmd)) {
            List<Shoes> sortedShoes = new ArrayList<>(shoes);
            sortShoesByPrice(sortedShoes);
            Answer answer = new Answer(true, gson.toJson(sortedShoes));
            return answer.asJSON();
        } else {
            Answer answer = new Answer(false, "Comando non valido.");
            return answer.asJSON();
        }
    }

    private void sortShoesByName(List<Shoes> shoes) {
        for (int i = 0; i < shoes.size() - 1; i++) {
            for (int j = i + 1; j < shoes.size(); j++) {
                if (shoes.get(i).getName().compareToIgnoreCase(shoes.get(j).getName()) > 0) {
                    Shoes temp = shoes.get(i);
                    shoes.set(i, shoes.get(j));
                    shoes.set(j, temp);
                }
            }
        }
    }

    private void sortShoesByPrice(List<Shoes> shoes) {
        for (int i = 0; i < shoes.size() - 1; i++) {
            for (int j = i + 1; j < shoes.size(); j++) {
                if (shoes.get(i).getPrice() > shoes.get(j).getPrice()) {
                    Shoes temp = shoes.get(i);
                    shoes.set(i, shoes.get(j));
                    shoes.set(j, temp);
                }
            }
        }
    }

}
