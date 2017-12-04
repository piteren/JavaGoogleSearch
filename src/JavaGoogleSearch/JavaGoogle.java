/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaGoogleSearch;

/**
 *
 * @author ppp
 */

import java.net.*;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

public class JavaGoogle {

    public static void main(String[] args) throws Exception {

        String google = "http://www.google.com/search?q=";
        String search = "packshot";
        String charset = "UTF-8";
        String userAgent = "ExampleBot 1.0 (+http://example.com/bot)"; // Change this to your company's name and bot homepage!

        Document caly = Jsoup.connect(google + URLEncoder.encode(search, charset)).userAgent(userAgent).get();
        Elements links = caly.select(".g>.r>a");

        for (Element link : links) {
            String title = link.text();
            String url = link.absUrl("href"); // Google returns URLs in format "http://www.google.com/url?q=<url>&sa=U&ei=<someKey>".
            url = URLDecoder.decode(url.substring(url.indexOf('=') + 1, url.indexOf('&')), "UTF-8");
            
            if (!url.startsWith("http")) {
                continue; // Ads/news/etc.
            }

            System.out.println("Title: " + title);
            System.out.println("URL: " + url);
        }
    }
}