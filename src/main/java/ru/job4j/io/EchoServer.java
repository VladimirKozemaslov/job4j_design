package ru.job4j.io;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String request = in.readLine();
                    Map<String, String> params = getParams(request);
                    Optional<String> msg = Optional.ofNullable(params.get("msg"));
                    if (msg.isPresent()) {
                        switch (msg.get()) {
                            case "Hello" -> out.write("Hello".getBytes());
                            case "Exit" ->  server.close();
                            default -> out.write("What".getBytes());
                        }
                    }
                    System.out.println(request);
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                    }
                    out.flush();
                }
            }
        }
    }

    private static Map<String, String> getParams(String request) {
        Map<String, String> params = new HashMap<>();
        int paramsBeginIdx = request.indexOf("?");
        if (paramsBeginIdx != -1) {
            String paramStr = request.substring(request.indexOf("?") + 1, request.indexOf(" ", paramsBeginIdx));
            if (!paramStr.isEmpty()) {
                String[] paramArr = paramStr.split("&");
                for (String param : paramArr) {
                    String[] keyValue = param.split("=");
                    params.put(keyValue[0], keyValue[1]);
                }
            }
        }
        return params;
    }
}