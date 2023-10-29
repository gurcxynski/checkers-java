package com.mygdx.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Network {
    boolean isServer;
    boolean isServerTurn = true;

    BufferedReader in;
    PrintWriter out;

    public Network(boolean isServer) {
        this.isServer = isServer;
        initialize(12345, "localhost");
    }

    public void initialize(int port, String ip) {
        if (isServer)
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                System.out.println("Server waiting for clients at port " + port);

                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected.");

                this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                this.out = new PrintWriter(clientSocket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        else {
            try {
                Socket socket = new Socket(ip, port);
                this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                this.out = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMove(String move, boolean changeActivePlayer) {
        if (changeActivePlayer)
            isServerTurn = !isServerTurn;
        out.println(move + " " + changeActivePlayer);

    }

    public void recieveMove() {
        if ((!isServer && isServerTurn) || (isServer && !isServerTurn)) {
            while (true) {
                String move = "";
                boolean changeActivePlayer = false;
                try {
                    String[] line = in.readLine().split(" ", 2);
                    move = line[0];
                    changeActivePlayer = Boolean.parseBoolean(line[1]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(move);
                Globals.machine.executeMove(new Move(move, true));// is server

                if (changeActivePlayer)
                    this.isServerTurn = !this.isServerTurn;

                if (move != "")
                    break;

            }
        }
    }
}