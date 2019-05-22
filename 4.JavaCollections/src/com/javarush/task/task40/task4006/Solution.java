package com.javarush.task.task40.task4006;

import java.io.*;

import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

/* 
Отправка GET-запроса через сокет
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        getSite(new URL("http://javarush.ru/social.html"));
    }

    public static void getSite(URL url) {
        try {
            Socket s = new Socket(url.getHost(), 80);
            PrintWriter pw = new PrintWriter(s.getOutputStream());
            pw.println("GET " + url.getFile() + " HTTP/1.1");
            pw.println("Host: " + url.getHost());
            pw.println("User-Agent: Mozilla/5.0");
            pw.println("Accept: text/html");
            pw.println("Connection: close");
            pw.println();
            pw.flush();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String responseLine;

            while ((responseLine = bufferedReader.readLine()) != null) {
                System.out.println(responseLine);
            }
            bufferedReader.close();
            pw.close();
        } catch (IOException e) {

        }
    }
}