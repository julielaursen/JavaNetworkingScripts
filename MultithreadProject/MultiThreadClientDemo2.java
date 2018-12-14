package ThreadServer;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MultiThreadClientDemo2 {
	public static void main(String[] args) throws IOException {
		//added
		MultiThreadClientDemo2 clientObj = new MultiThreadClientDemo2();

		String serverHostname = new String("127.0.0.1");
		int port = 7000;
		Socket clientSocket = null;

		System.out.println("[TCP Client says] Attemping to connect to host " + serverHostname + " on port " + port);

			try {
				System.out.println("[TCP Client says] Attempting to connect to the TCP server.");
				clientSocket = new Socket(serverHostname, port);
			} catch (IOException e) {
				System.out.println("[TCP Client says] Error: Cannot connect to the TCP server.");
				System.exit(1);
			}

			OutputStream output = clientSocket.getOutputStream();
			//removed
			InputStream input = clientSocket.getInputStream();
			
			//added printwriter method
			PrintWriter output1 = new PrintWriter(clientSocket.getOutputStream(), true);

			PrintStream printStream = new PrintStream(output);
			//removed InputStreamReader inputStream = new InputStreamReader(input)
			InputStreamReader inputStream = new InputStreamReader(input);

			System.out.println("Sending message ");
			String message =  "\n";

			long time1 = System.currentTimeMillis();
			printStream.print(message + "\n");
			
			//removed BufferedReader bufferedReader = new BufferedREader(inputStream)
			BufferedReader bufferedReader = new BufferedReader(inputStream);
			//removed message = bufferedReader.readline
			message = bufferedReader.readLine();
			
			//added this block
			//BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			//BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
 			
			while ((message = bufferedReader.readLine() + ' ' + bufferedReader.readLine() + ' ' + bufferedReader.readLine()) != null)
			{
				if(message.contains("Bye.")) {
					clientObj.write(output1, message);
				}
				else {
					clientObj.write(output1, "number=" + message);
					System.out.println("number: " + input.read());
				}
				if(message.contains("Bye."))
				{
					System.out.println("Closing client socket");
					clientSocket.close();
					break;
				}
			}

			long time2 = System.currentTimeMillis();
			
			System.out.printf("TCP Client says: Message {%s} received from server after %d msecs%n", message, time2-time1);

			clientSocket.close();
			printStream.close();
		}
		//added this method
		public void write(PrintWriter output, String message) {
			System.out.println("Sending: " + message);
			output.println(message);
		}
	
	
	
}

	
	