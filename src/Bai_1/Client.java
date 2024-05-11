package Bai_1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public Client() throws Exception {
		Socket socket = new Socket("localhost", 12345);
		Scanner sc = new Scanner(System.in);

		DataOutputStream toServer = new DataOutputStream(socket.getOutputStream());
		BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		BufferedReader fromUser = new BufferedReader(new InputStreamReader(System.in));

		String serverMessage;
		String clientMessage;

		while (true) {
			System.out.print("Client: ");
			clientMessage = fromUser.readLine();
			toServer.writeBytes(clientMessage + "\n");

			serverMessage = fromServer.readLine();
			if (serverMessage != null) {
				System.out.println("Server: " + serverMessage);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		new Client();
	}
}
