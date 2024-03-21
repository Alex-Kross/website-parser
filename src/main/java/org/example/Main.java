package org.example;
import org.example.service.ContentHtmlParser;
import org.example.service.ContentSaver;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String mouseUrl = "https://www.electromarket.by/mouse";
        String toasterUrl = "https://www.electromarket.by/toaster";
        String elecGrillUrl = "https://www.electromarket.by/electricgrill";

        //parse html page
        ContentHtmlParser contentHtmlParser = new ContentHtmlParser();
        contentHtmlParser.parseMouseSection(mouseUrl);
        contentHtmlParser.parseMouseSection(toasterUrl);
        contentHtmlParser.parseMouseSection(elecGrillUrl);

        //save content
        ContentSaver contentSaver = new ContentSaver();
        contentSaver.saveProduct(ContentHtmlParser.getProducts());
    }
}