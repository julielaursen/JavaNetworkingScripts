package NetworkingDemo;

import java.io.EOFException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ServerDemo {
	
	public static void main(String args[]) throws IOException
	{  
		int port = 1000;
		int number, temp;
		ServerSocket server = new ServerSocket(port, 100);
	
		
		System.out.println("Server Socket started on port " + port);
		//listen for a client connection and return a socket when connection established
		System.out.println("Waiting for connection");
		Socket connection = server.accept();
		//System.out.println("Connection received from " + connection.getInetAddress().getHostName());
		Scanner sc = new Scanner(connection.getInputStream());
		
		number = sc.nextInt();
		
		temp = number * 2;
		
		//server sends information to the client
		PrintStream p = new PrintStream(connection.getOutputStream());
		
		p.println(temp);
	}

}
