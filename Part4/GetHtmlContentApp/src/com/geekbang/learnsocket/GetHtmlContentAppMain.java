package com.geekbang.learnsocket;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class GetHtmlContentAppMain {
    public static void main(String[] args) {
        try {

            InetAddress inetAddress = InetAddress.getByName("www.baidu.com");
            System.out.println(inetAddress);

            Socket s = new Socket();
            SocketAddress sa = new InetSocketAddress(inetAddress, 80);
            s.connect(sa, 10000);
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream(), StandardCharsets.UTF_8));

            StringBuffer stringBuffer = new StringBuffer();

            // TODO >>> 设置 http 请求头
            stringBuffer.append("GET /index.html HTTP/1.1\n");

            pw.write(stringBuffer.toString());
            pw.flush();
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream(), StandardCharsets.UTF_8));
            br.lines().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
