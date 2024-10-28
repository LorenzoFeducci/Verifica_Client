package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        Scanner scannerOpzione = new Scanner(System.in);
        Socket socket = new Socket("localhost", 3000);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        do {
            System.out.println("\n" + "Scegli un opzione" + "\n");
            System.out.println("Nota da memorizzare");
            System.out.println("Lista delle note: LISTA");
            System.out.println("Terminare la connessione: ESCI" + "\n");
            System.out.println("Inserisci una nota, Lista o ESCI");
            String opzione = scannerOpzione.nextLine();

            String stringaRicevuta = "";

            if(opzione.equals("ESCI")){
                out.writeBytes("!" + "\n");
                System.out.print("chiusura in corso");
                socket.close();
                break;
            }else if(opzione.equals("LISTA")){
                out.writeBytes("?" + "\n");
                do {
                    stringaRicevuta = in.readLine();
                    System.out.println(stringaRicevuta);
                } while (! stringaRicevuta.equals("@"));
            }else{
                out.writeBytes(opzione + "\n");
                stringaRicevuta = in.readLine();
                System.out.println(stringaRicevuta);
            }
            
        } while (true);
    }
}