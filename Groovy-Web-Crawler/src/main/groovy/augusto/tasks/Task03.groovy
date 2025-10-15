package augusto.tasks

import augusto.helpers.Archive_Downloader
import augusto.helpers.HTML_Getter
import groovyx.net.http.HttpBuilder
import org.jsoup.nodes.Document

class Task03 {

    void crawl(String componente){
        componente = componente.toLowerCase()

        def htmlText = HTML_Getter.getHTMLText("https://www.gov.br/ans/pt-br/assuntos/prestadores/padrao-para-troca-de-informacao-de-saude-suplementar-2013-tiss/padrao-tiss-tabelas-relacionadas")

        def link =  htmlText.select("a[href\$=.xlsx]:contains(${componente})")
        link = link.attr("href")

        println link
        Archive_Downloader.download("${componente.toLowerCase().replace(" ", "_")}_latest.xlsx", link)
    }


}
