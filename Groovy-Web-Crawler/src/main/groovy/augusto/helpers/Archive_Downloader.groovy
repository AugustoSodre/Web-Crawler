package augusto.helpers

class Archive_Downloader {

    static void download(String outputFileName, String url){
        def outputFile = new File("downloads/${outputFileName}")
        def uri = new URI(url)
        def connection = uri.toURL().openConnection()

        outputFile << connection.inputStream


    }
}
