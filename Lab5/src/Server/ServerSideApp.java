package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import itemProduct.ItemProduct;

public class ServerSideApp {

	public static void main(String[] args) {
		
			
			try {
				
				// Port to receive and respond to request
				int portNo = 4228;
				ServerSocket serverSocket = new ServerSocket(portNo);
				
				System.out.println("Ready for request");
				
				// Server need to be alive forever thus the while(true)
				while (true) {
					
					// Accept client request for connection
					Socket socket = serverSocket.accept();
					
					// Create input stream to read object
					ObjectInputStream objectIS = new ObjectInputStream(socket.getInputStream());
					
					// Read object from stream and cast it to ArrayList of Location
					ArrayList<ItemProduct> product = (ArrayList<ItemProduct>) objectIS.readObject();
					
					// Process object - assign location id
					for (int index=0; index < product.size(); index++) {
						
						ItemProduct currentProduct = product.get(index);
						
						currentProduct.setItemProductId(index + 1001);
						
						
					}
					
					// Create output stream to send object
					ObjectOutputStream objectOS = new ObjectOutputStream(socket.getOutputStream());
					objectOS.writeObject(product);
					objectOS.flush();
					
					
					System.out.println("Ready for next request");
					
					// Close all streams
					objectIS.close();
					objectOS.close();
				}
				
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

	}

}
