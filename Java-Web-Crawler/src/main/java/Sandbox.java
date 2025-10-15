import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Sandbox {

    public static void main(String[] args) {
        Document document = getDocument("https://wikipedia.org/");

        System.out.println(document.getElementsByTag("p"));
    }

    public static Document getDocument(String url) {
        try{
            Connection connection = Jsoup.connect(url);

            if(connection.response().statusCode() == 200){
                return Jsoup.connect(url).get();
            }

            return null;

        } catch (IOException e) {
            return null;
        }
    }
}
