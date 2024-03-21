package org.example.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/***
 * is never used
 */
public class HttpController {
    private final static String urlMouseSection = "https://www.electromarket.by/mouse";
    private final static String urlToasterSection = "https://www.electromarket.by/toaster";
    private final static String urlElecGrillSection = "https://www.electromarket.by/electricgrill";
    public String getMouseSection(){
        try {
            URL url = new URL(urlMouseSection);
            HttpURLConnection con = null;
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer htmlContent = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                htmlContent.append(inputLine);
            }
            in.close();
            return htmlContent.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
