import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private Socket socket = null;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    private Scanner sc;

    public Client(String serverName, int serverPort) {
        try {
            socket = new Socket(serverName, serverPort);
            System.out.println("Connected: " + socket);
            sc = new Scanner(System.in);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());    
        } catch (UnknownHostException uhe) {
            System.out.println("Host unknown : " + uhe.getMessage());
        } catch (IOException ioe) {
            System.out.println("Unexpected exception: " + ioe.getMessage());
        }
    }

    public void run() {
        try {
            byte[] recvHeader = new byte[4];
            dis.read(recvHeader);

            if (recvHeader[1] == 1) {
                System.out.println("로그인 요청");
            }

            Protocol protocol = new Protocol();
            protocol.getLoginPacket(Message.MESSAGE_TYPE_RESPONSE, Message.NULL_HEADER);

            protocol.setIntToByte(sc.nextInt());
            protocol.setStrToByte(sc.next());

            System.out.println("Packet data : " + new String(protocol.getPacket()));

            dos.write(protocol.getPacket());
            dos.flush();

            recvHeader = new byte[4];
            dis.read(recvHeader);

            if (recvHeader[2] == 1) {
                System.out.println("로그인 성공");
            } else {
                System.out.println("로그인 실패");
            }

            while (true) {
                    protocol = new Protocol();
                    protocol.getLoginPacket(Message.MESSAGE_TYPE_RESPONSE, Message.NULL_HEADER);
                    protocol = new Protocol(1, 1, 1);

                    protocol.getAdminRequestPacket(Message.FUNCTION_TYPE_MAKE_PROFESSOR_ACCOUNT, 2);

                    protocol.setStrToByte(sc.next());
                    protocol.setIntToByte(sc.nextInt());
                    protocol.setStrToByte(sc.next());
                    protocol.setStrToByte(sc.next());
                    protocol.setStrToByte(sc.next());
                    protocol.setStrToByte(sc.next());

                    System.out.println(new String(protocol.getPacket()));
                    dos.write(protocol.getPacket());
                    dos.flush();

                    byte[] ttt = new byte[5000];
                    dis.read(ttt);
                    System.out.println(new String(ttt));
                }
            }
        catch (IOException ioe) {
            System.out.println("Sending eorror : " + ioe.getMessage());
        }
    }

    public static void main(String[] args) {
        Client client = null;
        if (args.length != 2)
            System.out.println("Usage: host port");
        else
            client = new Client(args[0], Integer.parseInt(args[1]));
        client.run();
    }
}
