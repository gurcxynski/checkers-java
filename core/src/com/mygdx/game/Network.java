package com.mygdx.game;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Network {
    Socket connectedSocket;

    private boolean isServer;

    public Network(boolean isServer) {
        this.isServer = isServer;
        try {
            initialize(12345, InetAddress.getByName("127.0.0.1"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void initialize(int port, InetAddress ip) {
        if (isServer)
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                System.out.println("Server waiting for clients at port " + port);

                connectedSocket = serverSocket.accept();
                System.out.println("Client connected.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        else {
            try {
                connectedSocket = new Socket(ip, port);
                System.out.println("connnected");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMove(Move move) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(this.connectedSocket.getOutputStream());
            out.writeObject(new Packet(move));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void recieveMove() {
        if (Globals.machine.state == StateMachine.GameState.AWATING_ENEMY_MOVE) {
            try {
                ObjectInputStream in = new ObjectInputStream(this.connectedSocket.getInputStream());
                Move move = null;

                Packet packet = (Packet) in.readObject();
                move = packet.move;

                Globals.machine.executeMove(move);// is server
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}