package Server;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import facades.Facade;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Person;
import model.Roleschool;

public class ServerCA {

    //Initialized information for the server.
    static int port = 8080;
    static String ip = "127.0.0.1";
    static String publicFolder = "src/html/";
    static String startFile = "index.html";
    static String filesUri = "/pages";
    Facade facade = new Facade();

    //Created the entitymanager, which is used to communicate with the database.
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("CARestPU");
    EntityManager em = emf.createEntityManager();

    public void run() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(ip, port), 0);
        //REST Routes
        server.createContext("/person", new HandlerPerson());
        //HTTP Server Routes
        server.start();
        System.out.println("Server started, listening on port: " + port);

    }

    public static void main(String[] args) throws IOException {
        if (args.length >= 3) {
            port = Integer.parseInt(args[0]);
            ip = args[1];
            publicFolder = args[2];

        }
        new ServerCA().run();
    }

    class HandlerPerson implements HttpHandler {

        Facade facade;

        public HandlerPerson() {
            facade = Facade.getFacade(false);

        }

        @Override
        public void handle(HttpExchange he) throws IOException {
            String response = "";
            int status = 200;
            String method = he.getRequestMethod().toUpperCase();
            switch (method) {
                //Used to get existing data. 
                case "GET":
                    try {
                        String path = he.getRequestURI().getPath();
                        int lastIndex = path.lastIndexOf("/");
                        if (lastIndex > 0) {  //person/id
                            String idStr = path.substring(lastIndex + 1);
                            Long id = Long.valueOf(idStr);
                            response = facade.getPersonAsJson(id);
                        } else { // person
                            response = facade.getPersonsAsJSON();
                        }
                    } catch (NumberFormatException nfe) {
                        response = "Id is not a number";
                        status = 404;
                    }
                    break;

                //Used to post new data.
                case "POST":
                    try {
                        InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
                        BufferedReader br = new BufferedReader(isr);
                        String jsonQuery = br.readLine();
                        if (jsonQuery.contains("<") || jsonQuery.contains(">")) {
                            //Simple anti-Martin check :-)
                            throw new IllegalArgumentException("Illegal characters in input");
                        }
                        Person p = facade.addPersonFromGson(jsonQuery);
                        if (p.getPhone().length() > 20 || p.getFirstName().length() > 20 || p.getLastName().length() > 20) {
                            //Simple anti-Martin check :-)
                            throw new IllegalArgumentException("Input contains to many characters");
                        }
                        response = new Gson().toJson(p);
                    } catch (IllegalArgumentException iae) {
                        status = 400;
                        response = iae.getMessage();
                    } catch (IOException e) {
                        status = 500;
                        response = "Internal Server Problem";
                    }
                    break;
                //Used to put role on existing person.     
                case "PUT":
                    try {

                        InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");

                        BufferedReader br = new BufferedReader(isr);
                        String jsonText = br.readLine();

                        String path = he.getRequestURI().getPath();
                        int lastIndex = path.lastIndexOf("/");

                        if (lastIndex > 0) {  //person/id

                            Long id = Long.parseLong(path.substring(lastIndex + 1));
                            Roleschool pAddRole = facade.addRoleFromGson(jsonText, id);

                            response = new Gson().toJson(pAddRole);
                        }

                    } catch (IllegalArgumentException iae) {
                        status = 400;
                        response = iae.getMessage();
                    } catch (IOException e) {
                        status = 500;
                        response = "Internal Server Problem";
                    }
                    break;
                //Used to delete a person based on ID.     
                case "DELETE":
                    try {
                        String path = he.getRequestURI().getPath();
                        int lastIndex = path.lastIndexOf("/");
                        if (lastIndex > 0) {  //person/id
                            String idStr = path.substring(lastIndex + 1);
                            Long id = Long.valueOf(idStr);

                            Person pDeleted = facade.delete(id);

                            response = new Gson().toJson(pDeleted);
                        } else {
                            status = 400;
                            response = "<h1>Bad Request</h1>No id supplied with request";
                        }
                    } catch (NumberFormatException nfe) {
                        response = "Id is not a number";
                        status = 404;
                    }
                    break;
            }
            he.getResponseHeaders().add("Content-Type", "application/json");
            he.sendResponseHeaders(status, 0);
            try (OutputStream os = he.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }

}
