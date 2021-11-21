import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Test {
    private Socket socket = null;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    private Scanner sc;

    public Test(String serverName, int serverPort) {
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
            String line;

            while (true) {
                    Protocol protocol = new Protocol();
                    protocol.getLoginPacket(Message.MESSAGE_TYPE_RESPONSE);
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
        Test test = null;
        if (args.length != 2)
            System.out.println("Usage: host port");
        else
            test = new Test(args[0], Integer.parseInt(args[1]));
        test.run();
    }
}



/*DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
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
dos.flush();*/
