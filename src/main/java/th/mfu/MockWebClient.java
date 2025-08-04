package th.mfu;

import java.io.*;
import java.net.*;

public class MockWebClient {
    public static void main(String[] args) {

        // TODO Create a server socket bound to specified port
        Socket socket = null;
        try {
            
            socket = new Socket("localhost", 8080);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
          
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // ส่ง HTTP GET Request ไปยัง server
            String request = "GET / HTTP/1.1\r\nHost: localhost\r\n\r\n";
            out.println(request);

            // อ่าน response ที่ server ส่งกลับมา และแสดงผลใน console
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                // ถ้าอยากหยุดตอนเจอบรรทัดว่าง (end of headers) ก็ใช้ break; ได้
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // ปิด socket เมื่อเสร็จงาน (เหมือนเลิกคุยแล้วปิดประตู)
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
