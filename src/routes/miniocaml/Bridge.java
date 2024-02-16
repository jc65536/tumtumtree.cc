import java.net.*;
import java.net.http.*;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.io.*;

import com.sun.net.httpserver.*;

public class Bridge {
    final static WebSocket.Listener wsListener = new WebSocket.Listener() {

    };

    static <T> T sync(Object lock, Supplier<T> f) {
        synchronized (lock) {
            return f.get();
        }
    }

    public static void main(String[] args) throws Exception {
        final var socketServer = new ServerSocket(6969);
        final var httpServer = HttpServer.create(new InetSocketAddress(5000), 0);

        final var socket = socketServer.accept();
        final var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        final var out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        System.out.println("Parser connected");

        httpServer.createContext("/", exchange -> {
            final var body = new String(exchange.getRequestBody().readAllBytes());
            final var sanitized = body.replaceAll("\n", " ");

            System.out.printf("Request: %s%n", sanitized);

            final var parsed = sync(socket, () -> {
                try {
                    out.write(sanitized);
                    out.newLine();
                    out.flush();

                    return in.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                    return "err";
                }
            });

            System.out.printf("Response: %s%n", parsed);

            final var pretty = parsed.replaceAll("\0", "\n");

            exchange.getResponseHeaders()
                    .add("Access-Control-Allow-Origin", "*");
            exchange.sendResponseHeaders(200, 0);
            final var respOut = exchange.getResponseBody();
            respOut.write(pretty.getBytes());
            respOut.flush();
            exchange.close();
        });

        httpServer.start();
    }
}
