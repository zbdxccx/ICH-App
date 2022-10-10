package com.contest.ichapp.util.searchUtil;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

@Slf4j
public class SearchUtil {
    public static String descriptionTag = "lemma-summary";
    public static String baseUrl = "https://baike.baidu.com";
    public static String secUrl = "https://baike.baidu.com/item/";
    public static String secKey = "href";
    public static String mainKey = "para";
    public static int timeOut = 30000;

    public static String searchFromBaidu(String keyWord) throws IOException {
        String url = secUrl + keyWord;
        Document parse = Jsoup.parse(new URL(url), timeOut);
        Elements para = parse.getElementsByClass(descriptionTag);
        if (para.isEmpty()) {
            para = parse.getElementsByClass(mainKey);
            url = Objects.requireNonNull(para.first()).getElementsByAttribute(secKey).attr(secKey);
            Document parse1 = Jsoup.parse(new URL(baseUrl + url), timeOut);
            log.info("search" + keyWord);
            return parse1.getElementsByClass(descriptionTag).text();
        } else {
            log.info("search" + keyWord);
            return parse.getElementsByClass(descriptionTag).text();
        }
    }
}
