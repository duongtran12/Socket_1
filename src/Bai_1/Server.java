package Bai_1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public Server() throws Exception {
		ServerSocket serverSocket = new ServerSocket(12345);
		System.out.println("Server is running on port 12345");

		Socket socket = serverSocket.accept();
		System.out.println("Client connected");

		BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());
		BufferedReader fromServer = new BufferedReader(new InputStreamReader(System.in));

		String clientMessage;
		String serverMessage;

		while (true) {

			clientMessage = fromClient.readLine();
			if (clientMessage != null) {
				System.out.println("Đã nhận: " + clientMessage);
			}

			System.out.println("Server: ");
			serverMessage = fromServer.readLine();
			toClient.writeBytes(serverMessage + "\n");
		}
	}

	public static void main(String[] args) throws Exception {
		new Server();
	}
}
