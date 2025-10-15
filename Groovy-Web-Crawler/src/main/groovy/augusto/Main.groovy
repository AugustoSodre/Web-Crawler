package augusto

import augusto.tasks.Task01
import augusto.tasks.Task02
import augusto.tasks.Task03

static void main(String[] args) {

    Task01 task01 = new Task01()
    Task02 task02 = new Task02()
    Task03 task03 = new Task03()

    task01.crawl("Componente de Segurança e Privacidade")
    task02.crawl()
    task03.crawl("Tabela de erros no envio para a ANS")

}