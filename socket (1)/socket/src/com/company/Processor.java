package com.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Processor of HTTP request.
 */
public class Processor extends Thread{
    private final Socket socket;
    private final HttpRequest request;

    public Processor(Socket socket, HttpRequest request) {
        this.socket = socket;
        this.request = request;
    }

    public void process() throws IOException {
        System.out.println("Got request:");
        System.out.println(request.toString());
        System.out.flush();

        PrintWriter output = new PrintWriter(socket.getOutputStream());
        System.out.println(request.getRequestLine());


        if(request.getRequestLine().equals("GET /create/itemid HTTP/1.1")){
            // We are returning a simple web page now.
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>Hello</title></head>");
            output.println("<body><p>Hello, this is creation page</p></body>");
            output.println("</html>");
            output.flush();
        }
        else if(request.getRequestLine().equals("GET /delete/itemid HTTP/1.1")){
            // We are returning a simple web page now.
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>Hello</title></head>");
            output.println("<body><p>Hello, this is deletion page</p></body>");
            output.println("</html>");
            output.flush();
        }

        else if(request.getRequestLine().equals("GET /exec/params HTTP/1.1")){
            // We are returning a simple web page now.
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>Hello</title></head>");
            output.println("<body><p>Hello, finding prime numbers till 10000: </p>");
            for(int n = 0; n < 100000; n++){
                int i , m=0, flag=0;
                m = n/2;
                if(n==0 || n == 1){
                    output.print(n+" is not prime number ");
                }else{
                    for(i = 2; i <= m; i++){
                        if(n%i == 0){
                            output.print(n+" is not prime number ");
                            flag = 1;
                            break;
                        }
                    }
                    if(flag == 0)  { output.print(n+" is prime number"); }
                }
            }
            output.println("</body>");
            output.println("</html>");
            output.flush();
        }

        else {
            // We are returning a simple web page now.
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>Hello</title></head>");
            output.println("<body><p>Hello, world!</p></body>");
            output.println("</html>");
            output.flush();

        }
        socket.close();
    }
}
