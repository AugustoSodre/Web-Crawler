# Web Crawler ANS - TISS Data Scraper

Um Web Crawler desenvolvido em **Groovy** para coletar dados públicos do site da **Agência Nacional de Saúde Suplementar (ANS)** relacionados ao padrão **TISS** (Troca de Informações na Saúde Suplementar).

## Sobre o Projeto

Este projeto implementa um bot/scraper automatizado que coleta documentos e informações públicas disponíveis no portal da ANS, especificamente relacionados ao padrão TISS utilizado no setor de saúde suplementar brasileiro.

### Objetivos

O crawler realiza três tarefas principais:

1. **Task 01**: Download dos arquivos da documentação do padrão TISS (versão mais recente)
   - Foco no componente de comunicação do "Espaço do Prestador de Serviços de Saúde"
   - Opção de download de todos os componentes organizados em pastas

2. **Task 02**: Coleta de histórico das versões do padrão TISS
   - Extração de dados da tabela de competência, publicação e início de vigência
   - Período: a partir de janeiro/2016
   - Exportação dos dados para formato CSV

3. **Task 03**: Download da "Tabela de erros no envio para a ANS"
   - Obtida da seção "Tabelas relacionadas"
   - Formato XLSX

## Tecnologias Utilizadas

- **[Groovy 3.0.19](https://groovy-lang.org/)** - Linguagem de programação
- **[HTTPBuilder-NG 1.0.4](https://http-builder-ng.github.io/http-builder-ng/)** - Cliente HTTP para requisições web
- **[Jsoup 1.17.2](https://jsoup.org/)** - Parser HTML e web scraping
- **[OpenCSV 5.9](http://opencsv.sourceforge.net/)** - Geração de arquivos CSV
- **[Gradle](https://gradle.org/)** - Gerenciamento de dependências e build

## 📁 Estrutura do Projeto

```
Groovy-Web-Crawler/
├── src/
│   └── main/
│       └── groovy/
│           └── augusto/
│               ├── Main.groovy                    # Ponto de entrada da aplicação
│               ├── downloads/                     # Pasta onde os arquivos são salvos
│               ├── helpers/
│               │   ├── HTML_Getter.groovy         # Utilitário para obtenção de HTML
│               │   ├── Archive_Downloader.groovy  # Gerenciador de downloads
│               │   └── CSV_Creator.groovy         # Criador de arquivos CSV
│               └── tasks/
│                   ├── Task01.groovy              # Download dos componentes TISS
│                   ├── Task02.groovy              # Coleta histórico de versões
│                   └── Task03.groovy              # Download tabela de erros
├── build.gradle                                   # Configuração do Gradle
└── lib/                                           # Bibliotecas Groovy
```

## 🚀 Como Executar

### Pré-requisitos

- **Java JDK 8+** instalado
- **Gradle** (incluído via Gradle Wrapper)

### Passos para execução

1. **Clone o repositório**
   ```bash
   git clone https://github.com/AugustoSodre/Web-Crawler.git
   cd Web-Crawler/Groovy-Web-Crawler
   ```

2. **Execute o projeto**
   ```bash
   # No Linux/Mac
   ./gradlew run

   # No Windows
   gradlew.bat run
   ```

3. **Ou compile e execute manualmente**
   ```bash
   ./gradlew build
   ./gradlew execute
   ```

### 📦 Arquivos Gerados

Todos os arquivos coletados são salvos em:
```
src/main/groovy/augusto/downloads/
```

- **Componentes TISS** (formato `.zip`)
- **Histórico de versões** (`historico_componentes_padrao_TISS.csv`)
- **Tabela de erros** (formato `.xlsx`)

## Detalhes das Tasks

### Task 01 - Download de Componentes
```groovy
task01.crawl("Componente de Segurança e Privacidade")
```
- Acessa a página do padrão TISS (versão mais recente)
- Localiza o link do componente especificado
- Baixa o arquivo ZIP

### Task 02 - Histórico de Versões
```groovy
task02.crawl()
```
- Acessa a página de histórico do padrão TISS
- Extrai dados da tabela (competência, publicação, vigência)
- Filtra registros desde janeiro/2016
- Gera arquivo CSV com os dados

### Task 03 - Tabela de Erros
```groovy
task03.crawl("Tabela de erros no envio para a ANS")
```
- Acessa a página de tabelas relacionadas
- Localiza e baixa a tabela de erros em formato XLSX

## Arquitetura

O projeto utiliza um padrão modular com separação de responsabilidades:

- **Main.groovy**: Orquestra a execução das tasks
- **Tasks**: Implementam a lógica específica de cada coleta
- **Helpers**: Fornecem funcionalidades reutilizáveis (HTTP requests, download, CSV)

### Helpers

- **HTML_Getter**: Simplifica requisições HTTP e parsing HTML
- **Archive_Downloader**: Gerencia downloads de arquivos
- **CSV_Creator**: Cria e popula arquivos CSV

## 📊 Exemplo de Saída

### Histórico de Versões (CSV)
```csv
competência,publicação,início de vigência
set/2025,30/08/2025,01/10/2025
jun/2025,31/05/2025,01/07/2025
mar/2025,28/02/2025,01/04/2025
...
```

## ⚠️ Observações Importantes

- Este crawler acessa **apenas dados públicos** disponibilizados pela ANS
- Respeita os limites e políticas de uso do site governamental
- Utiliza User-Agent apropriado para requisições HTTP
- Implementa tratamento de erros e exceções

## Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

## Autor

**Augusto Sodré**

- GitHub: [@AugustoSodre](https://github.com/AugustoSodre)

## Links Úteis

- [ANS - Agência Nacional de Saúde Suplementar](https://www.gov.br/ans)
- [Padrão TISS](https://www.gov.br/ans/pt-br/assuntos/prestadores/padrao-para-troca-de-informacao-de-saude-suplementar-2013-tiss)
- [Documentação Groovy](https://groovy-lang.org/documentation.html)
- [HTTPBuilder-NG Docs](https://http-builder-ng.github.io/http-builder-ng/)
- [Jsoup Documentation](https://jsoup.org/cookbook/)

---

