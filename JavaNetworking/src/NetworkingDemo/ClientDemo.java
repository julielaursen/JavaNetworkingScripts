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

	static int serverPort = 1000;
	PrintWriter out = null;
	BufferedReader in = null;

	public static void main(String args[]) throws UnknownHostException, IOException {
		ClientDemo clientObj = new ClientDemo();

		try {
			InetAddress serverIPAddress = InetAddress.getByName("localhost");
			Socket clientSocket = new Socket("127.0.0.1", serverPort);

			PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String clientMessage = "";
			System.out.print("This progam will calculate the sum of integers between two numbers.\nThe first number should be less than the second number");
			System.out.print("\nEnter number or \"Bye.\"  to quit\n");
			
			//shift control d to end line 
			while ((clientMessage = stdIn.readLine() + ';' + stdIn.readLine() + ';' + stdIn.readLine()) != null) {
				if (clientMessage.contains("Bye.")) {
					clientObj.write(output, clientMessage);
				} else {
					clientObj.write(output, "number=" + clientMessage);
					System.out.println("number: " + input.readLine());
				}
				if (clientMessage.contains("Bye."))
				{
					System.out.println("Closing client socket ");
					clientSocket.close();
					break;
				}
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		
	}

	public void write(PrintWriter output, String clientMessage) {
		System.out.println("Sending: " + clientMessage);
		output.println(clientMessage);
	}
}
