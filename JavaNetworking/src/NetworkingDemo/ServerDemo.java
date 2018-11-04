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
	int port = 1000;

	public static void main(String args[]) {
		System.out.println("starting server");
		ServerDemo serverObj = new ServerDemo();
		try {
			ServerSocket server = new ServerSocket(1000);

			//while (true) {
				System.out.println("Listening on port 1000");
				Socket connection = server.accept();
				
				try {
					PrintWriter output = new PrintWriter(connection.getOutputStream(), true);
					BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					System.out.println("Connection accepted");
					String serverMessage = "";
					while ((serverMessage = input.readLine()) != null) {
						if (serverMessage.equals("Bye.")) {
							serverObj.write(output, "Bye.");
							break;
						}
						if (serverMessage.contains("number=")) {
							serverObj.write(output, serverObj.calcSum(serverMessage));
						}
					}
					System.out.print("Closing server socket.\n\n");
					//server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		//	}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String calcSum(String number) {
		int sum = 0;
		try {
			   
		       String[]  array = number.split(";", 3);
		       String number1 = array[0].substring(7);
		       String number2 = array[1];
		       String number3 = array[2];
		       System.out.println("Numbers receved: " + number1 + ", " + number2 + ", " + number3 + "#");
		       int numberOne = Integer.parseInt(number1);	
		       System.out.println("Numbers 1 converted");
		       int numberTwo = Integer.parseInt(number2);
		       System.out.println("Numbers 2 converted");
		       int numberThree = Integer.parseInt(number3);//.substring(7));
		       System.out.println("Numbers 3 converted");
		       
			if (numberOne < numberTwo) {
				if (numberThree % 2 == 0) {
					System.out.println("Printing sum of even numbers");
					if (numberOne % 2 == 0) {
						for (int i = numberOne; i <= numberTwo; i += 2) {
							sum = sum + i;
						}
					} else {// if (numberOne % 2 != 0) {
						for (int i = numberOne + 1; i <= numberTwo; i += 2) {
							sum = sum + i;
						}
					}
					System.out.println("The sum of the two numbers is " + sum);
				}

				if (numberThree % 2 != 0) {
					System.out.println("Printing sum of odd numbers");
					if (numberOne % 2 == 0) {
						for (int i = numberOne +1; i <= numberTwo; i += 2) {
							sum = sum + i;
						}
					} else {// if (numberOne % 2 != 0) {
						for (int i = numberOne; i <= numberTwo; i += 2) {
							sum = sum + i;
						}
					}
//					for (int i = numberOne; i <= numberTwo; i += 2) {
//						sum = sum + i;
//					}
					System.out.println("The sum of the two numbers is " + sum);
				}
			} else {
				System.out.println(
				"Program could not calculate. Number 1 must be less than number 2 for the program to calculate the difference.");
			}
			System.out.println("Calculation finished");
			
		} catch (NumberFormatException e) {
			return ("answer=Invalid number");
		}
		return "answer=" + Integer.toString(sum);
	}

	public void write(PrintWriter output, String message) {
		System.out.println("Sending: " + message);
		output.println(message);
	}
}
