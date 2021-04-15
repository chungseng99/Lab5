package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerApplication {

	public static void main(String[] args) {

		//initialize port number
		final int serverPort=50001;
				
		
		try {
			
			
				
			//Instantiate a new DatagramSocket to receive responses from the client
			DatagramSocket serverSocket = new DatagramSocket(serverPort);
		
			//Create buffers to hold receiving data
			byte receivingDataBuffer[] = new byte[1024];
			
			//Instantiate a UDP packet to store the client data using buffer for receiving data
			DatagramPacket inputPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);
			System.out.println("Waiting for the client to connect...");
			
			//Receive data from the client and store in inputPacket
			serverSocket.receive(inputPacket);
			
			//Printing out the client sent data
			String receivedData = new String(inputPacket.getData());
			System.out.println("Message form the client: " + receivedData);
			
		
			//Process data - calculate length of text
			String sendingData = Integer.toString(inputPacket.getLength());
						
			//Creating corresponding buffer to send data
			byte sendingDataBuffer[] = sendingData.getBytes();
			
			//Get client's address
			InetAddress senderAddress = inputPacket.getAddress();
			int senderPort = inputPacket.getPort();
			
			//Create new UDP packet with data to send to the client
			DatagramPacket outputPacket = new DatagramPacket(sendingDataBuffer, sendingDataBuffer.length, senderAddress, senderPort);
			
			//Send the created packet to the client 
			serverSocket.send(outputPacket);
			
			//Close the socket connection
			serverSocket.close();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
