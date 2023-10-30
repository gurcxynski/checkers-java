package com.mygdx.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Network {
    Socket connectedSocket;

    public boolean isWhite;
    public boolean isServer;

    public Network(boolean isServer, boolean isWhite) {
        this.isServer = isServer;
        try {
            initialize(12345, InetAddress.getByName("127.0.0.1"), isWhite);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void initialize(int port, InetAddress ip, boolean isWhite) {
        if (isServer)
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                System.out.println("Server waiting for clients at port " + port);

                connectedSocket = serverSocket.accept();
                System.out.println("Client connected.");

                this.isWhite = isWhite;

                OutputStream outputStream = connectedSocket.getOutputStream();
                PrintWriter out = new PrintWriter(outputStream, true);
                out.println(!isWhite);
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        else {
            try {
                connectedSocket = new Socket(ip, port);
                System.out.println("connnected");
                InputStream inputStream = connectedSocket.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
                this.isWhite = Boolean.parseBoolean(in.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMove(Move move) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(this.connectedSocket.getOutputStream());
            out.writeObject(move);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Move recieveMove() {
        Move move = null;
        try {
            if (this.connectedSocket.getInputStream().available() > 0) {
                ObjectInputStream in = new ObjectInputStream(this.connectedSocket.getInputStream());
                move = (Move) in.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return move;
    }
}