package Client;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import itemProduct.ItemProduct;

public class ClientSideApp {

	public static void main(String[] args) {
		
		System.out.println("Client Side \n");

		// Request data
		ItemProduct product1 = new ItemProduct();
		product1.setName("Mac Chicken Burger");
		
		ItemProduct product2 = new ItemProduct();
		product2.setName("Cheesy Wedges");
		
		ItemProduct product3 = new ItemProduct();
		product3.setName("Popcorn Chicken");
		
		// Add into list
		ArrayList<ItemProduct> product = new ArrayList<ItemProduct>();
		product.add(product1);
		product.add(product2);
		product.add(product3);

		try {

			// Data to establish connection to server
			int portNo = 4228;
			InetAddress serverAddress = InetAddress.getLocalHost();

			// Connect to the server at localhost, port 4228
			Socket socket = new Socket(serverAddress, portNo);

			// Open stream to send object
			ObjectOutputStream objectOS = new ObjectOutputStream(socket.getOutputStream());

			// Send request to server
			System.out.println("Send object to server: " + product);
			objectOS.writeObject(product);
			objectOS.flush();
			
			// Open stream to receive object
			ObjectInputStream objectIS = new ObjectInputStream(socket.getInputStream());
			
			// Get object from stream, cast and display details
			product = (ArrayList<ItemProduct>) objectIS.readObject();
			for (ItemProduct currentProduct:product)
				System.out.println ("Id for " + currentProduct.getName() + " is " + currentProduct.getItemProductId());
			
			// Close all closeable objects
			objectOS.close();
			objectIS.close();
			socket.close();

		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("End of application.\n");

	}

}
