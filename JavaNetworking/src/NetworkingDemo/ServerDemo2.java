package NetworkingDemo;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ServerDemo2 {
		private ServerSocket server;
		private Socket connection;
		int port = 5000;
		int number, temp;
		private int counter = 1;
		private ObjectOutputStream output;
		private ObjectInputStream input;
		
		public void runServer() {
		try {
			ServerSocket server = new ServerSocket(port, 100);
			while (true)
			{
				try
				{
				waitForConnection();
				getStreams();
				processConnection();
				}
			catch (EOFException eofException)
				{
				System.out.println("Server terminated");
				}
			finally
			    {
				closeConnection();
				++counter;
			    }
			}
		}
		catch (IOException ioException) {
			ioException.printStackTrace();
		}
		}
		
		private void waitForConnection() throws IOException{
			System.out.println("Waiting for connection\n");
			Socket connection = server.accept();
			System.out.println("Connection " + counter + "received from" + connection.getInetAddress().getHostName());
		}
		
		private void getStreams() throws IOException{
			output = new ObjectOutputStream(connection.getOutputStream());
			output.flush();
			
			input = new ObjectInputStream(connection.getInputStream());
			System.out.println("got I/O Streams");
		}
		
		private void processConnection() throws IOException{
			String message = "Connection successful";
			sendData(message);
			//continue later
		}
		
		private void closeConnection()
		{
			System.out.println("Terminating connection\n");
			try
			{
				output.close();
				input.close();
				connection.close();
			}
			catch(IOException ioException)
			{
				ioException.printStackTrace();
		}
		}
		
		private void sendData(String message) {
			try {
				output.writeObject("Server>>>" + message);
				output.flush();
			}
			catch(IOException ioException)
			{
				ioException.printStackTrace();
		}
		}
		
		}
		