import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Test2 implements Runnable {
    DataInputStream dis;
    DataOutputStream dos;
    Socket socket;
    Thread thread = null;

    public Test2(Socket socket) { 
            this.socket = socket;
            thread = new Thread(this);
            thread.start();
    }

    @Override
    public void run() {
        try {
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            while (true) {
                byte[] buf = new byte[4];
                dis.read(buf);

                int tmp = 0;

                byte[] recvHeader = new byte[4];

                while (dis.read(recvHeader) != -1) {
                    tmp++;

                    int bytesRcvd;
                    int totalBytesRcvd = 0;  // 지금까지 받은 바이트 수

                    int dataLength = byteArrayToInt(recvHeader);
                    if (dataLength == 0) break;

                    byte[] recvData = new byte[dataLength];

                    if (tmp == 2) {
                        while (totalBytesRcvd < dataLength) {
                            if ((bytesRcvd = dis.read(recvData, totalBytesRcvd, 4 - totalBytesRcvd)) == -1)
                                throw new SocketException("Connection close prematurely1");
                            totalBytesRcvd += bytesRcvd;
                        }

                        int test = byteArrayToInt(recvData);
                        System.out.println("Int : " + test);
                    } else {
                        while (totalBytesRcvd < dataLength) {
                            if ((bytesRcvd = dis.read(recvData, totalBytesRcvd, dataLength - totalBytesRcvd)) == -1)
                                throw new SocketException("Connection close prematurely");
                            totalBytesRcvd += bytesRcvd;
                        }

                        String str = new String(recvData);
                        System.out.println("String : " + str);
                    }
                }
                byte ttt[] = new byte[5000];
                dis.read(ttt);

                String str = "tqtqtqtttqtqtqtqtqtqt";
                byte[] qqq = str.getBytes();
                dos.write(qqq);
                dos.flush();
            }
        } catch(IOException ioe) {
            System.out.println("Sending eorror : " + ioe.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket sSocket = null;
        Socket socket = null;
        try {
            sSocket = new ServerSocket(3000);      // Client와 연결을 담당하는 ServerSocket
            while (true) {
                
                
                System.out.println("클라이언트 접속 대기 중...");
                socket = sSocket.accept();                       // 데이터 주고받는 Socket
                System.out.println("클라이언트 접속됨.");
                
                new Test2(socket);
            }
        }
        catch (IOException e) {
            System.out.println("IOExceptiojn");
        }

        try
        {
            sSocket.close();
        }
        catch(IOException ioException)
        {
            System.err.println("Unable to close. IOexception");
        }
    }
        // while (true) {
        //     DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        //     DataInputStream dis = new DataInputStream(socket.getInputStream());
        //     byte[] buf = new byte[4];
        //     dis.read(buf);

        //     int tmp = 0;

        //     byte[] recvHeader = new byte[4];

        //     while (dis.read(recvHeader) != -1) {
        //         tmp++;

        //         int bytesRcvd;
        //         int totalBytesRcvd = 0;  // 지금까지 받은 바이트 수

        //         int dataLength = byteArrayToInt(recvHeader);
        //         if (dataLength == 0) break;

        //         byte[] recvData = new byte[dataLength];

        //         if (tmp == 2) {
        //             while (totalBytesRcvd < dataLength) {
        //                 if ((bytesRcvd = dis.read(recvData, totalBytesRcvd, 4 - totalBytesRcvd)) == -1)
        //                     throw new SocketException("Connection close prematurely1");
        //                 totalBytesRcvd += bytesRcvd;
        //             }

        //             int test = byteArrayToInt(recvData);
        //             System.out.println("Int : " + test);
        //         } else {
        //             while (totalBytesRcvd < dataLength) {
        //                 if ((bytesRcvd = dis.read(recvData, totalBytesRcvd, dataLength - totalBytesRcvd)) == -1)
        //                     throw new SocketException("Connection close prematurely");
        //                 totalBytesRcvd += bytesRcvd;
        //             }

        //             String str = new String(recvData);
        //             System.out.println("String : " + str);
        //         }
        //     }

/*        dis.read(recvHeader);

        byte[] recvData = new byte[recvHeader[0]];

        System.out.println(recvData.length);

        int dataLength = 1;
        int totalBytesRcvd = 0;
        int bytesRcvd;

        while (totalBytesRcvd < 4) {
            if ((bytesRcvd = dis.read(recvData, totalBytesRcvd, 4 - totalBytesRcvd)) == -1)
                throw new SocketException("Connection close prematurely");
            totalBytesRcvd += bytesRcvd;
        }
        int test =byteArrayToInt(recvData);

        System.out.println("Test : " + test);

        System.out.println("정수 성공! ");*/

/*
        recvHeader = new byte[Message.FIXED_LEN];
        totalBytesRcvd = 0;  // 지금까지 받은 바이트 수
        while (totalBytesRcvd < Message.FIXED_LEN) {
            if ((bytesRcvd = dis.read(recvHeader, totalBytesRcvd, Message.FIXED_LEN - totalBytesRcvd)) == -1)
                throw new SocketException("Connection close prematurely");
            totalBytesRcvd += bytesRcvd;
        }

        dataLength = byteArrayToInt(recvHeader);

        // 데이터 수신
        recvData = new byte[dataLength];
        totalBytesRcvd = 0;
        while (totalBytesRcvd < dataLength) {
            if ((bytesRcvd = dis.read(recvData, totalBytesRcvd, dataLength - totalBytesRcvd)) == -1)
                throw new SocketException("Connection close prematurely");
            totalBytesRcvd += bytesRcvd;
        }
        String str = new String(recvData);
        System.out.println("Test : " + str);
    }
    */

    private static int byteArrayToInt(byte[] data) {
        if (data == null || data.length != 4) return 0x0;
        return (int)( // NOTE: type cast not necessary for int
                (0xff & data[0]) << 24  |
                        (0xff & data[1]) << 16  |
                        (0xff & data[2]) << 8   |
                        (0xff & data[3]) << 0
        );
    }
}