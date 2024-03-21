package org.example.service;

import org.example.entity.Product;
import org.example.entity.TypeProduct;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContentHtmlParser {
    private String mainUrl = "https://www.electromarket.by";
    private Map<TypeProduct, List<Product>> products = new HashMap<>();

    public Map<TypeProduct, List<Product>> getProducts() {
        return products;
    }

    public void parseMouseSection(TypeProduct typeProduct, String url) throws IOException {
        List<Product> productList = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();

        // get links with web pages
        Elements pagesEl = doc.getElementsByClass("w100 clearfix");
        Elements linkToPages = pagesEl.get(0).getElementsByTag("a");
//        TODO: Учесть, что страниц может быть больше 10 и они не все отображаются
        // remove
        linkToPages.remove(linkToPages.size() - 1);
        for (Element link : linkToPages) {
                String linkHref = link.attr("href");
            // get certain page with product
            doc = Jsoup.connect(url+ linkHref).get();
            Elements elementsByClass = doc.getElementsByAttributeValueStarting("class", "product stock");
            for (Element elementByClass: elementsByClass) {
                Product product = new Product();

                // parse link
                Elements span = elementByClass.getElementsByClass("info-level4");
                Elements links = span.get(0).getElementsByTag("a");
                product.setName(links.get(0).text());
                product.setLink(mainUrl + links.get(0).attr("href"));

                // parse price
                Elements elementsPrice = elementByClass.getElementsByClass("price");
                Element elementPrice = elementsPrice.get(0);

                // pattern for find price
                Pattern pattern = Pattern.compile("[0-9]+[.,]?[0-9]+");
                Matcher matcher = pattern.matcher(elementPrice.toString());
                while (matcher.find()) {
                    String priceString = elementPrice.toString().substring(matcher.start(), matcher.end());
                    product.setPrice(Double.parseDouble(priceString.replace(",", ".")));
                }
                //filling products
                productList.add(product);
            }
        }
        products.put(typeProduct, productList);
    }
}
