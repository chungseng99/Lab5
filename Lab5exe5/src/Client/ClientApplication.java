package Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientApplication {

	public static void main(String[] args) {
		
		//The server port to which the client socket is going to connect
		final int SERVERPORT = 50001;
		int bufferSize = 1024;
		
		
		try {
						
			//Instantiate client socket
			DatagramSocket clientSocket = new DatagramSocket();
			
			//Get the IP address of the server 
			InetAddress serverAddress = InetAddress.getByName("localhost");
			
			//Create buffer to send data
			byte sendingDataBuffer[] = new byte[bufferSize];
			
			//Create data to bytes and store data in buffer
			System.out.println("Client Side\n");
			String sentence = "This is a message from client side";
			sendingDataBuffer = sentence.getBytes();
			
			// Creating a UDP packet 
			DatagramPacket sendingPacket = new DatagramPacket(sendingDataBuffer,
								sendingDataBuffer.length, serverAddress, SERVERPORT);
			
			// Sending UDP packet to the server
			clientSocket.send(sendingPacket);
			
			//Create buffer to receive data
			byte receivingDataBuffer[] = new byte [bufferSize];
			
			//Receive data packet from the server
			DatagramPacket receivingPacket = new DatagramPacket(receivingDataBuffer,receivingDataBuffer.length);
			clientSocket.receive(receivingPacket);
			
			//Unpack packet
			String numOfWords = new String(receivingPacket.getData());
			System.out.println("Number of word of "+ "'"+ sentence +"'" + " is " + numOfWords + "\n");
			
			//Closing the socket connection with the server
			clientSocket.close();
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
