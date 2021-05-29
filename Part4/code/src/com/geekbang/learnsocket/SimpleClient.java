package com.geekbang.learnsocket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import static com.geekbang.learnsocket.SimpleServer.SERVER_PORT;

public class SimpleClient {
    public static void main(String[] args) {
        commWithServer();
    }

    public static void commWithServer() {
        try (
                Socket socket = new Socket("localhost", SERVER_PORT);
        ) {
            // Chat chat = new Chat("服务器端", null, socket);
            // chat.Chatting();
            System.out.println("客户端连接到：" + socket.getRemoteSocketAddress());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("程序退出！");
    }
}
