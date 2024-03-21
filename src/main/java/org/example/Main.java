package org.example;
import org.example.entity.TypeProduct;
import org.example.service.ContentHtmlParser;
import org.example.service.ContentSaver;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String mouseUrl = "https://www.electromarket.by/mouse";
        String toasterUrl = "https://www.electromarket.by/toaster";
        String elecGrillUrl = "https://www.electromarket.by/electricgrill";
        String fileName = "src/main/resources/Content.xlsx";

        //parse html page
        ContentHtmlParser contentHtmlParser = new ContentHtmlParser();
        contentHtmlParser.parseMouseSection(TypeProduct.MOUSE, mouseUrl);
        contentHtmlParser.parseMouseSection(TypeProduct.TOASTER,toasterUrl);
        contentHtmlParser.parseMouseSection(TypeProduct.ELECTRIC_GRILL,elecGrillUrl);

        //save content
        ContentSaver contentSaver = new ContentSaver();
        contentSaver.saveProducts(contentHtmlParser.getProducts(), fileName);
    }
}