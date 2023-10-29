package com.mygdx.game;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Network {
    Socket connectedSocket;

    private boolean isServer;
    private boolean isYourTurn;

    public Network(boolean isServer) {
        this.isServer = isServer;
        initialize(12345, "localhost");
    }

    public void initialize(int port, String ip) {
        if (isServer)
            try {
                isYourTurn = true;
                ServerSocket serverSocket = new ServerSocket(port);
                System.out.println("Server waiting for clients at port " + port);

                connectedSocket = serverSocket.accept();
                System.out.println("Client connected.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        else {
            try {
                isYourTurn = false;
                connectedSocket = new Socket(ip, port);
                System.out.println("connnected");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMove(Move move, boolean changeActivePlayer) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(this.connectedSocket.getOutputStream());
            out.writeObject(new Packet(move, changeActivePlayer));
            out.flush();
            if (changeActivePlayer) {
                this.isYourTurn = !this.isYourTurn;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void recieveMove() {
        if (!this.isYourTurn) {
            try {
                ObjectInputStream in = new ObjectInputStream(this.connectedSocket.getInputStream());
                Move move = null;
                boolean changeActivePlayer = false;

                Packet packet = (Packet) in.readObject();
                move = packet.move;
                changeActivePlayer = packet.changeActivePlayer;

                Globals.machine.executeMove(move, changeActivePlayer);// is server
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}