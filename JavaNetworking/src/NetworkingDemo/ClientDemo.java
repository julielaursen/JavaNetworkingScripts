package NetworkingDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientDemo {
	
	public static void main(String args[]) throws UnknownHostException, IOException
	{
		int number1;
		String quit;
		int number2, number3;
		int sum;
		int serverPort = 1000;
		Scanner sc = new Scanner(System.in);
		PrintWriter out = null;
		BufferedReader in = null;
		
		Socket clientSocket = new Socket("127.0.0.1", serverPort);
		//Socket connection = clientSocket.accept();
		Scanner sc1 = new Scanner(clientSocket.getInputStream());
		InetAddress serverIPAddress = InetAddress.getByName("localhost");
		System.out.println("Attempting to send data to " +  serverIPAddress);
		System.out.println("This progam will calculate the sum of integers between two numbers.\nThe first number should be less than the second number");
		System.out.println("Enter first number");
		number1 = sc.nextInt();
		PrintStream p1 = new PrintStream(clientSocket.getOutputStream());
		System.out.println("Enter second number");
		number2 = sc.nextInt();
		PrintStream p2 = new PrintStream(clientSocket.getOutputStream());
	    System.out.println("Enter 1 to sum all odd numbers between the two inputs. Enter 2 to sum all even numbers between the two inputs");
		number3 = sc.nextInt();
		PrintStream p3 = new PrintStream(clientSocket.getOutputStream());
	
		p1.println(number1);
		p2.println(number2);
		p3.println(number3);

		//get sum back from server
		//sum = sc1.nextInt();
		//System.out.println(sum);

		
//		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
//		String userInput;
//
//		System.out.println("Type Message (\"Bye.\" to quit)");
//		System.out.print("Input: ");
//		while ((userInput = stdIn.readLine()) != null)
//		{
//			out.println(userInput);
//			System.out.println("Client: " + in.readLine());
//			// end loop if client sends "Bye."
//			if (userInput.equals("Bye."))
//				break;
//
//			System.out.print("Input: ");
//		}
	}

}
