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
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Network {
    Socket connectedSocket;

    public boolean isWhite;
    public boolean isServer;
    private ServerSocket serverSocket;

    public void connect(boolean isWhite) {
        isServer = true;
        this.isWhite = isWhite;

        initialize(12345);

    }

    public boolean connect(String ip) {
        isServer = false;
        try {
            initialize(12345, InetAddress.getByName(ip));

            if (connectedSocket == null || connectedSocket.isClosed())
                return false;
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Initializes the network connection by creating a socket and establishing a
     * connection to the specified IP address and port.
     * Retrieves the input stream from the socket and reads the boolean value
     * indicating whether the player is white or not.
     *
     * @param port the port number to connect to
     * @param ip   the IP address to connect to
     * @throws IOException
     */
    public void initialize(int port, InetAddress ip) throws IOException {
        connectedSocket = new Socket();
        connectedSocket.connect(new InetSocketAddress(ip, port), 100);
        System.out.println("connnected");

        InputStream inputStream = connectedSocket.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

        this.isWhite = Boolean.parseBoolean(in.readLine());
    }

    /**
     * Initializes the network connection on the specified port.
     * Waits for a client to connect and sends a message indicating whether the
     * server is white or not.
     *
     * @param port the port number to listen on
     */
    public void initialize(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            System.out.println("Server waiting for clients at port " + port);
            try {
                connectedSocket = serverSocket.accept();
                System.out.println("Client connected.");

                OutputStream outputStream = connectedSocket.getOutputStream();
                PrintWriter out = new PrintWriter(outputStream, true);

                out.println(!isWhite);
                System.out.flush();
                serverSocket.close();

            } catch (IOException e) {
            } finally {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cancelHosting() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends a move object over the network.
     *
     * @param move the move object to be sent
     */
    public void sendMove(Move move) {
        try {
            String header = "move";
            ObjectOutputStream out = new ObjectOutputStream(this.connectedSocket.getOutputStream());
            out.writeObject(header);
            out.writeObject(move);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Receives a Move object from the connected socket's input stream.
     *
     * @return the received Move object, or null if no move is available
     */
    public Move recieveMove() {
        Move move = null;
        try {
            if (this.connectedSocket.getInputStream().available() > 0) {
                ObjectInputStream in = new ObjectInputStream(this.connectedSocket.getInputStream());
                String header = (String) in.readObject();
                if (header.equals("move"))
                    move = (Move) in.readObject();
                else
                    // discard the ping
                    in.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
        }
        return move;
    }

    public boolean isDisconnected() {
        try {
            // send a ping
            String header = "ping";

            // Synchronize on pingLock to avoid potential concurrency issues
            ObjectOutputStream out = new ObjectOutputStream(connectedSocket.getOutputStream());
            out.writeObject(header);
            out.writeObject(header);
            System.out.flush();
            return false;
        } catch (IOException e) {
            return true;
        }
    }

    public void disconnect() {
        try {
            connectedSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}