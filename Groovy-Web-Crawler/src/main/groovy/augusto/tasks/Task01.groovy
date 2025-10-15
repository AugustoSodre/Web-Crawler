package augusto.tasks

import augusto.helpers.Archive_Downloader
import augusto.helpers.HTML_Getter

class Task01 {
    //Objetivo: Baixar os componentes da documentação do padrão TISS (Troca de Informações na Saúde Suplementar), na versão mais recente

    void crawl(String componente){
        def htmlText = HTML_Getter.getHTMLText("https://www.gov.br/ans/pt-br/assuntos/prestadores/padrao-para-troca-de-informacao-de-saude-suplementar-2013-tiss/padrao-tiss-setembro-2025")

        def link =  htmlText.select("a[href\$=.zip]:has(span:contains(${componente}))")
        link = link.attr("href")

        Archive_Downloader.download("${componente.toLowerCase().replace(" ", "_")}_latest.zip", link)
    }
}
