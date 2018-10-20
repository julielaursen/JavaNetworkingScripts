package NetworkingDemo;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientDemo {
	
	public static void main(String args[]) throws UnknownHostException, IOException
	{
		//refactor so client can enter all three numbers at once
		int number1;
		String quit;
		//, number2, number3, \
		int temp;
		int serverPort = 1000;
		Scanner sc = new Scanner(System.in);
		Socket clientSocket = new Socket("127.0.0.1", serverPort);
		Scanner sc1 = new Scanner(clientSocket.getInputStream());
		InetAddress serverIPAddress = InetAddress.getByName("localhost");
		System.out.println("Attempting to send data to " +  serverIPAddress);
		
		System.out.println("Enter first number");
		number1 = sc.nextInt();
		//pass number to server
		
		PrintStream p = new PrintStream(clientSocket.getOutputStream());
		
		//System.out.println("Enter second number");
		//number2 = sc.nextInt();
		//System.out.println("Enter third number. Third number must be 1 or 2");
		//number3 = sc.nextInt();
		 
		
		p.println(number1);
		//p.println(number2);
		//p.println(number3);
		
		temp = sc1.nextInt();
		System.out.println(temp);
		
		
		System.out.println("Enter bye to indicate no more data");
		quit = sc.nextLine();
		PrintStream p2 = new PrintStream(clientSocket.getOutputStream());
		if (quit == "Bye" || quit == "bye") {
			p2.println(quit);
			clientSocket.close();
		}
		
	}

}
