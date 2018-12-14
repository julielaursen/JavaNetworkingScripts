package ThreadServer;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
 
public class MultiProcessEachClient2 implements Runnable {
    protected Socket clientSocket = null;
    protected String serverText = null;
 
    public MultiProcessEachClient2(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
 
    @Override
    public void run() {
        try {
            OutputStream output = clientSocket.getOutputStream();
            InputStream input = clientSocket.getInputStream();
 
            PrintStream printStream = new PrintStream(output);
            InputStreamReader inputStream = new InputStreamReader(input);
 
            BufferedReader bufferedReader = new BufferedReader(inputStream);
            String message = null;
            message = bufferedReader.readLine();
            //String[] clientArray = message.split(" ", -1);
            System.out.println("TCP Server says: Message received from client - " + message);
            Thread.sleep(1000);
            System.out.println("TCP Server says: Sending back the same message - " + message);
 
            String messageSend = message + "\n";
            //processClientRequest(clientSocket);
            //String newMessage = calcSum("number= 1 5 2");
            //System.out.println(newMessage);
            printStream.println(messageSend);
            printStream.close();            
 
           // output.close();
           // input.close();
             
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//
//    		public void processClientRequest(Socket clientSocket) throws IOException{
//    		try {
//    			System.out.println("MultithreadTCPServer says: Server processing client request");
//    			Thread.sleep(1000);
//    			if(bufferedReader == null) {
//    				System.out.println("Reader is null");
//    			}
//    			
//    			System.out.println("MultithreadTCPServer Message Received from Client");
//    		}
//    		catch (Exception e) {
//    			System.out.println("TCP Server says [ERROR]");
//    			e.printStackTrace();   			
//    		}
//    		}
    

    		public String calcSum(String number) {
    			int sum = 0;
    			try {
    				   
    			       String[]  array = number.split(" ", 8);
    			       String number0 = array[0].substring(7);
    			       //String number1 = array[0].substring(7);
    			       String number1 = array[1];
    			       String number2 = array[2];
    			       String number3 = array[3];
    			       //String number2 = array[1];
    			       //String number3 = array[2];
    			       System.out.println("Numbers receved: " + number1 + ", " + number2 + ", " + number3 + "#");
    			       System.out.println(number1);
    			       System.out.println(number2);
    			       System.out.println(number3);
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

    		public void write(PrintWriter output, String message) {
    			System.out.println("Sending: " + message);
    			output.println(message);
    		}
    	 }
	
			
			/*
			System.out.print("This progam will calculate the sum of integers between two numbers.\nThe first number should be less than the second number");
			System.out.print("\nEnter number or \"Bye.\"  to quit\n");
			while ((clientMessage = stdIn.readLine() + ';' + stdIn.readLine() + ';' + stdIn.readLine()) != null) { 
				System.out.println("TCP Server says: Message received from client - " + clientMessage);
	            Thread.sleep(1000);
	            System.out.println("TCP Server says: Sending back the same message - " + clientMessage);
				if (clientMessage.equals("Bye.")) {
					write(output, clientMessage);
				} else {
					write(output, "number=" + clientMessage);
					System.out.println("number: " + bufferedReader.readLine());
				}
				System.out.println("Closing client socket ");
					clientSocket.close();
					break;
			}
            String messageSend = clientMessage + "\n";
            printStream.println(messageSend);
            printStream.close();			

			outputStream.close();
			inputStream.close();
			
		} catch (Exception e) {
            e.printStackTrace();
		}
		
	}


*/