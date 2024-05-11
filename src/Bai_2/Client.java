package Bai_2;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends JFrame {
    private JLabel timeLabel;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public Client() {
        super("Clock Client");
        setSize(381, 239);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        timeLabel = new JLabel();
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(timeLabel, BorderLayout.CENTER);

        String hostName = "localhost";
        int portNumber = 4444;

        try {
            socket = new Socket(hostName, portNumber);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.err.println("Không thể kết nối");
            System.exit(1);
        }

        updateTime();
    }

    public void updateTime() {
        new Thread(() -> {
            try {
                while (true) {
                    out.println("time");

                    String response = in.readLine();
                    SwingUtilities.invokeLater(() -> {
                        timeLabel.setText(response);
                    });

                    Thread.sleep(1000);
                }
            } catch (IOException e) {
                System.err.println("Lỗi");
            } catch (InterruptedException e) {
                System.err.println("Lỗi");
            }
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Client client = new Client();
            client.setVisible(true);
        });
    }
}
