package th.mfu;

import java.io.*;
import java.net.*;

public class MockWebClient {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            // เชื่อมต่อไปยัง server ที่ localhost บน port 8080
            socket = new Socket("localhost", 8080);

            // สร้าง stream สำหรับส่งข้อมูลไป server และรับข้อมูลจาก server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // ส่ง HTTP GET request ไปยัง server
            String request = "GET / HTTP/1.1\r\nHost: localhost\r\n\r\n";
            out.println(request);

            // อ่าน response ที่ server ส่งกลับมา และแสดงผลใน console
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
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
