package augusto

import augusto.tasks.Task01
import augusto.tasks.Task02

static void main(String[] args) {


    Task01 task01 = new Task01()
    Task02 task02 = new Task02()

    task01.crawl("Componente de Segurança e Privacidade")
    task02.crawl()

}