package NetworkingDemo;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ServerDemo {
	
	public static void main(String args[]) throws IOException
	{  
		int port = 1000;
		int sum = 0;
		int number;
		int number2, number3;
		
		ServerSocket server = new ServerSocket(port, 100);		
		System.out.println("Server Socket started on port " + port);
		System.out.println("Waiting for connection");
		Socket connection = server.accept();
		//System.out.println("Connection received from " + connection.getInetAddress().getHostName());
		Scanner sc = new Scanner(connection.getInputStream());
		
		number = sc.nextInt();
		number2 = sc.nextInt();
		number3 = sc.nextInt();
		String clientMessage;
		InputStream input = connection.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		
		//do {
		//clientMessage = reader.readLine();
		if(number < number2) {
		if(number3 % 2 == 0 ) {
			System.out.println("Printing sum of even numbers");
			if(number % 2 == 0){
				for(int i = number; i <= number2; i+=2) {
				sum = sum + i;}
				}
				else if(number %2 != 0) {
				for(int i = number - 1; i <= number2; i+=2){
				sum = sum + i;}
				}
			PrintStream p = new PrintStream(connection.getOutputStream());
			System.out.println("The sum of the two numbers is " + sum);
			}
		
		if(number3 % 2 != 0 ) {
			System.out.println("Printing sum of odd numbers");
			for(int i = number; i <= number2; i+=2)
			{
				sum = sum + i;
			}
			PrintStream p1 = new PrintStream(connection.getOutputStream());
			System.out.println("The sum of the two numbers is " + sum);
		}
		}
		else {
			System.out.println("Program could not calculate. Number 1 must be less than number 2 for the program to calculate the difference.");
		}
		//} while (!clientMessage.equals("bye"));
		PrintStream p2 = new PrintStream(connection.getOutputStream());
		System.out.println("Calculation finished");

		

//		PrintWriter out = new PrintWriter(connection.getOutputStream(), true);
//		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//		
//		String inputLine;
//		
//		while ((inputLine = in.readLine()) != null) {
//			System.out.println("Server: " + inputLine);
//			out.println("From server " + inputLine);
//			
//			if (inputLine.equals("Bye."))
//				break;
//		}
		//server.close();
		//sc.close();
	}

}


