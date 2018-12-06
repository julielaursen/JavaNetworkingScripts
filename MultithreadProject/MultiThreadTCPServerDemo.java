package ThreadServer;

import java.io.*;
import java.net.*;

public class MultiThreadTCPServerDemo {
	
	MultiThreadTCPServerDemo serverObj = new MultiThreadTCPServerDemo();
	
	public static void main(String[] args) {
		
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

		while (true) {
		Socket connection = null;
		try {
			System.out.println("[TCP Server says] Waiting for connection.....");
			connection = serverSocket.accept();
			new Thread(new ProcessEachClient(connection)).start();
		} catch (IOException e1) {
			System.err.println("[TCP Server says] Error: Cannot accept client request.");
			System.exit(1);
		}
		}
	}
//			try {
//				PrintWriter output = new PrintWriter(connection.getOutputStream(), true);
//				//Only had PrintWriter output and BufferedReader input before
//				//BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//	            OutputStream outputStream = connection.getOutputStream();
//	            InputStream input = connection.getInputStream();
//	            InputStreamReader inputStream = new InputStreamReader(input);
//				BufferedReader bufferedReader = new BufferedReader(inputStream);
//				String serverMessage = "";
//				while ((serverMessage = bufferedReader.readLine()) != null) {
//					if (serverMessage.equals("Bye.")) {
//						serverObj.write(output, "Bye.");
//						break;
//					}
//					if (serverMessage.contains("number=")) {
//						serverObj.write(output, serverObj.calcSum(serverMessage));
//					}
//				}
//			} catch (IOException e) {
//				System.err.println("[TCP Server says] Error: Cannot accept client request.");
//				System.exit(1);
//				//this catch is being called
//			}
//		}
//	}
	public void processClientRequest(Socket connection) throws IOException
    {
        System.out.println("[TCP Server says] Server processing client request.");
        try {
            OutputStream output1 = connection.getOutputStream();

			PrintWriter output = new PrintWriter(connection.getOutputStream(), true);
            InputStream input = connection.getInputStream();
             
            PrintStream printStream = new PrintStream(output1);
            InputStreamReader inputStream = new InputStreamReader(input);
             
            // Read the received message
            BufferedReader bufferedReader = new BufferedReader(inputStream);
            String serverMessage = "";
			while ((serverMessage = bufferedReader.readLine()) != null) {
				if (serverMessage.equals("Bye.")) {
					serverObj.write(output, "Bye.");
					break;
				}
				if (serverMessage.contains("number=")) {
					serverObj.write(output, serverObj.calcSum(serverMessage));
				}
			}
            System.out.println("TCP Server says: Message received from client - " + serverMessage);
 
            Thread.sleep(2000);
             
            // Send the response back
            String messageSend = serverMessage + "\n";
            printStream.println(messageSend);
            printStream.close();            
        }
        catch (Exception e) {
            System.out.println("[TCP Server says] Error: The server cannot send the message");
        }
    }

	public void write(PrintWriter output, String serverMessage) {
		System.out.println("Sending: " + serverMessage);
		output.println(serverMessage);
	}
	
	
	public String calcSum(String number) {
		int sum = 0;
		try {
			   
		       String[]  array = number.split(" ", 3);
		       String number1 = array[0].substring(7);
		       String number2 = array[1];
		       String number3 = array[2];
		       System.out.println("Numbers receved: " + number1 + ", " + number2 + ", " + number3 + "#");
		       int numberOne = Integer.parseInt(number1);	
		       int numberTwo = Integer.parseInt(number2);
		       int numberThree = Integer.parseInt(number3);
		       
			if (numberOne < numberTwo) {
				if (numberThree % 2 == 0) {
					System.out.println("Printing sum of even numbers");
					if (numberOne % 2 == 0) {
						for (int i = numberOne; i <= numberTwo; i += 2) {
							sum = sum + i;
						}
					} else {
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
					} else {
						for (int i = numberOne; i <= numberTwo; i += 2) {
							sum = sum + i;
						}
					}
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
}
