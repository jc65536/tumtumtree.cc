import java.net.*;
import java.util.function.*;
import java.io.*;

import com.sun.net.httpserver.*;

public class Bridge {
    static Socket socket;
    static BufferedReader in;
    static BufferedWriter out;

    synchronized static void setSocket(Socket s) throws Exception {
        if (socket != null) {
            socket.close();
        }

        socket = s;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    synchronized static <T> T sync(Supplier<T> f) {
        return f.get();
    }

    public static void main(String[] args) throws Exception {
        final var socketServer = new ServerSocket(6969);
        final var httpServer = HttpServer.create(new InetSocketAddress(5000), 0);

        httpServer.createContext("/", exchange -> {
            final var body = new String(exchange.getRequestBody().readAllBytes());
            final var sanitized = body.replaceAll("\n", " ");

            System.out.printf("Request: %s%n", sanitized);

            final var parsed = sync(() -> {
                if (socket != null) {
                    try {
                        out.write(sanitized);
                        out.newLine();
                        out.flush();

                        return in.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                        socket = null;
                    }
                }

                return "Parser not connected";
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

        while (true) {
            setSocket(socketServer.accept());
            System.out.println("Parser connected");
        }
    }
}
