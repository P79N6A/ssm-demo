package com.exams;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * java实现爬虫
 *
 * @author fangcong on 2018/8/10.
 */
public class Robot {

    private static final String REGEX = "(http://img[0-9])(\\S)*(.jpg)";

    private static final String DUOWAN_PIC = "(http[s]?:)(\\S)*(.jpg)";

    private static final String BAIDU_PIC = "(https://ss0\\.bdstatic\\.com/.*\\.jpg)";

    /**
     * 定义URL匹配规则
     */
    private static final Pattern URL_PATTERN = Pattern.compile(BAIDU_PIC);

    public static void main(String[] args) {
        // String urlStr = "http://tu.duowan.com/tu";
        String urlStr = "https://image.baidu.com/search/index?ct=201326592&z=&tn=baiduimage&ipn=r&word=%E5%A3%81%E7%BA%B8%20%E4"
            + "%B8%8D%E5%90%8C%E9%A3%8E%E6%A0%BC%20%E7%BE%8E%E5%A5%B3&pn=0&istype=2&ie=utf-8&oe=utf-8&cl=2&lm=-1"
            + "&st=-1&fr=&fmq=1526269427171_R&ic=0&se=&sme=&width=&height=&face=0";
        URL url;
        URLConnection urlConnection;
        BufferedReader bufferedReader = null;
        PrintWriter printWriter = null;
        try {
            HttpGet httpGet = new HttpGet(urlStr);
            httpGet.setHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 "
                    + "Safari/537.36");
            CloseableHttpClient clent = HttpClients.createDefault();
            CloseableHttpResponse response = clent.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                PrintWriter printWriter2 = new PrintWriter(new FileWriter("F:/test/html.txt"), true);
                printWriter = new PrintWriter(new FileWriter("F:/test/robot.txt"), true);
                bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String buf;
                while ((buf = bufferedReader.readLine()) != null) {
                    printWriter2.println(buf);
                    Matcher matcher = URL_PATTERN.matcher(buf);
                    if (matcher.find()) {
                        printWriter.println(matcher.group(1));
                    }
                }
            }

            /*url = new URL(urlStr);
            urlConnection = url.openConnection();
            */
            System.out.println("爬取成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
}
