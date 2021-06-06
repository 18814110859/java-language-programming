package com.geekbang.learnsocket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import static com.geekbang.learnsocket.SimpleServer.BYE;
import static com.geekbang.learnsocket.SimpleServer.SERVER_CHARSET;

public class Chat {

    private final String from;
    private final String greetings;
    private Socket socket;

    public Chat(String from, String greetings, Socket socket) {
        this.from = from;
        this.greetings = greetings;
        this.socket = socket;
    }

    public void Chatting() {

        Scanner in = new Scanner(System.in);

        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), SERVER_CHARSET));
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), SERVER_CHARSET));
        ) {

            System.out.println("Socket连接成功！建立输入输出");
            if (greetings != null) {
                pw.println("你好，" + from + "。" + greetings);
                pw.flush();
            }

            while (true) {
                String line = br.readLine();
                if (line.trim().equalsIgnoreCase(BYE)) {
                    System.out.println("对方要求断开连接");
                    pw.println(BYE);
                    pw.flush();
                    break;
                } else {
                    System.out.println("消息：" + line + " 来自于 " + socket.getRemoteSocketAddress());
                }
                String newLine = in.nextLine();
                pw.println(newLine);
                pw.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("聊天结束");
    }
}

