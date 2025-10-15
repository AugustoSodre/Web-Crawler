package augusto.tasks

import augusto.helpers.CSV_Creator
import groovyx.net.http.HttpBuilder
import org.jsoup.nodes.Document

import java.text.SimpleDateFormat

class Task02 {
    //Objetivo: Resgatar o Histórico das versões dos Componentes do Padrão TISS da ANSS desde Jan/2016 e salvar em arquivo CSV

     void crawl() {
        //Initialization
        def http = HttpBuilder.configure { request.uri = 'https://www.gov.br/ans/pt-br/assuntos/prestadores/padrao-para-troca-de-informacao-de-saude-suplementar-2013-tiss/padrao-tiss-historico-das-versoes-dos-componentes-do-padrao-tiss' }

        Document htmlText = http.get {
            request.contentType = 'text/HTML'

            request.headers['User-Agent'] =
                    'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 ' +
                            '(KHTML, like Gecko) Chrome/123.0 Safari/537.36'
        } as Document

        //Table Head
        def table_head = getTableHeadText(htmlText)

        println(table_head)

        //Table Data
        def table_data = getTableDataText(htmlText, "01/2016")

        for (List row : table_data ) {
            println(row)
        }

         CSV_Creator.create("historico_componentes_padrao_TISS.csv", table_head as String[], table_data as String[])

    }

    List getTableHeadText(Document htmlText){
        def raw_table_head = htmlText.getElementsByTag("th")

        return [raw_table_head[0].text().toLowerCase(), raw_table_head[1].text().toLowerCase(), raw_table_head[2].text().toLowerCase()]
    }

    List getTableDataText(Document htmlText, String deadlineStr){
        def raw_table_data = htmlText.getElementsByTag("td")

        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");

        Date deadline = sdf.parse(deadlineStr)

        def dateMap = [
                "jan" : "01",
                "fev" : "02",
                "mar" : "03",
                "abr" : "04",
                "mai" : "05",
                "jun" : "06",
                "jul" : "07",
                "ago" : "08",
                "set" : "09",
                "out" : "10",
                "nov" : "11",
                "dez" : "12"
        ]

        def table_data = []
        for (int i = 0; i < raw_table_data.size(); i += 9) {
            try {
                String dateStr = raw_table_data[i].text().trim().toLowerCase()

                String month = dateStr.split("/")[0]
                String year = dateStr.split("/")[1]

                month = dateMap[month]

                def currentDate = sdf.parse("${month}/${year}")

                if (currentDate < deadline) {
                    break
                }

                def tempList = []
                tempList.add(raw_table_data[i].text().toLowerCase())
                tempList.add(raw_table_data[i + 1].text().toLowerCase())
                tempList.add(raw_table_data[i + 2].text().toLowerCase())

                table_data.add(tempList)
            } catch (Exception e){
                e.printStackTrace()
            }
        }

        return table_data
    }

}
