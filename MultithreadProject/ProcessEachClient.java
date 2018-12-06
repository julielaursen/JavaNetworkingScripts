package ThreadServer;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class ProcessEachClient implements Runnable {
	
	//public static void main(String[] args) {
	
	protected Socket clientSocket = null;
	protected String serverText = null;

	public ProcessEachClient(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	
	@Override
	public void run() {
		try {
			OutputStream output = clientSocket.getOutputStream();
            InputStream input = clientSocket.getInputStream();
            
            PrintStream printStream = new PrintStream(output);
			
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			String clientMessage = "";
			System.out.print("This progam will calculate the sum of integers between two numbers.\nThe first number should be less than the second number");
			System.out.print("\nEnter number or \"Bye.\"  to quit\n");
			
			clientMessage = stdIn.readLine() + ' ' + stdIn.readLine() + ' ' + stdIn.readLine();
				System.out.println("TCP Server says: Message received from client - " + clientMessage);
	            Thread.sleep(1000);
	            System.out.println("TCP Server says: Sending back the same message - " + clientMessage);
			//}
            String messageSend = clientMessage + "\n";
            printStream.println(messageSend);
            printStream.close();			

			output.close();
			input.close();
			
		} catch (Exception e) {
            e.printStackTrace();
		}
		
	}
   //}
	//}
 }

