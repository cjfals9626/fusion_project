import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        Socket socket = new Socket("localhost", 3000);
        Protocol protocol = new Protocol();

        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        DataInputStream din = new DataInputStream(socket.getInputStream());

        protocol.getLoginPacket(Message.MESSAGE_TYPE_RESPONSE);

        protocol = new Protocol(1, 1, 1);

        protocol.getAdminRequestPacket(Message.FUNCTION_TYPE_MAKE_PROFESSOR_ACCOUNT, 2);

        System.out.println("-----------------------");
        System.out.println(protocol.getPacket().length);
        System.out.println((protocol.getPacket()[0]));
        System.out.println((protocol.getPacket()[1]));
        System.out.println((protocol.getPacket()[2]));
        System.out.println((protocol.getPacket()[3]));
        System.out.println("-----------------------");

        protocol.setStrToByte(sc.next());
        protocol.setIntToByte(sc.nextInt());
        protocol.setStrToByte(sc.next());
        protocol.setStrToByte(sc.next());
        protocol.setStrToByte(sc.next());
        protocol.setStrToByte(sc.next());

        dos.write(protocol.getPacket());
        dos.flush();
    }
}
