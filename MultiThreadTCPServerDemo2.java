package ThreadServer;
 
import java.io.*;
import java.net.*;
 
public class MultiThreadTCPServerDemo2
 {
    public static void main(String[] args)
    {
        ServerSocket serverSocket = null;
        int port = 7000;
         
        try {
            // Create TCP Server Socket
            serverSocket = new ServerSocket(port);
            System.out.println("[TCP Server says] TCP Server created on port " + port);
        } catch (IOException e) {
            System.out.println("[TCP Server says] Error: TCP Server cannot be created on port " + port);
            System.exit(1);
        }
         
        // Start an endless loop
        while (true)
        {
           Socket clientSocket = null;
 
           try {
               // Start listening to incoming client request (blocking function)
               System.out.println("[TCP Server says] Waiting for connection.....");
               clientSocket = serverSocket.accept();
           } catch (IOException e) {
               System.err.println("[TCP Server says] Error: Cannot accept client request.");
               System.exit(1);
           }
 
           try {
               // Create a new thread for each incoming message
               new Thread(new MultiProcessEachClient2(clientSocket)).start();
           } catch (Exception e) {
               System.err.println("[TCP Server says] Error: Cannot accept client request.");
               System.exit(1);
           }
        }
    }
 }
    