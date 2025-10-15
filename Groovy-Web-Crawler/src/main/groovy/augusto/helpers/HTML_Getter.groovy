package augusto.helpers

import groovyx.net.http.HttpBuilder
import org.jsoup.nodes.Document

class HTML_Getter {

    static Document getHTMLText(String url){
        def http = HttpBuilder.configure { request.uri = url }

        Document htmlText = http.get {
            request.contentType = 'text/HTML'

            request.headers['User-Agent'] =
                    'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 ' +
                            '(KHTML, like Gecko) Chrome/123.0 Safari/537.36'
        } as Document

        return htmlText
    }
}
