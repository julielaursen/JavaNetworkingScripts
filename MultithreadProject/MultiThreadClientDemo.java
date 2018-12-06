package ThreadServer;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MultiThreadClientDemo {
	public static void main(String[] args) throws IOException {

		String serverHostname = new String("127.0.0.1");
		int port = 7000;
		Socket clientSocket = null;

		System.out.println("[TCP Client says] Attemping to connect to host " + serverHostname + " on port " + port);

			try {
				System.out.println("[TCP Client says] Attempting to connect to the TCP server.");
				clientSocket = new Socket(serverHostname, port);
				//port is closing prematurely
				System.out.println("Socket hostname and port is" + clientSocket);
			} catch (IOException e) {
				System.out.println("[TCP Client says] Error: Cannot connect to the TCP server.");
				System.exit(1);
			}

			OutputStream output = clientSocket.getOutputStream();
			InputStream input = clientSocket.getInputStream();

			PrintStream printStream = new PrintStream(output);
			InputStreamReader inputStream = new InputStreamReader(input);

			System.out.println("Sending message ");
			String message = "\n";

			long time1 = System.currentTimeMillis();
			printStream.println(message + "\n");
			
			BufferedReader bufferedReader = new BufferedReader(inputStream);
			message = bufferedReader.readLine();
			long time2 = System.currentTimeMillis();
			
			System.out.printf("TCP Client says: Message {%s} received from server after %d msecs%n", message, time2-time1);

			clientSocket.close();
			printStream.close();
		//}
		System.out.println("We are all done.");
	}
}