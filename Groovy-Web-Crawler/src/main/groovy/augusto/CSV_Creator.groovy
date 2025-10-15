package augusto

import com.opencsv.CSVWriter

class CSV_Creator {

    static void create(String filename, String[] header, String[] data){

        try(CSVWriter writer = new CSVWriter(new FileWriter("downloads/${filename}"))){

            // Clean header - chain the operations
            def cleanHeader = header.collect {
                it.replace("\"", "").trim()
            } as String[]

            // Clean data - chain the operations
            def cleanData = data.collect {
                it.replace("\"", "").replace("]", "").replace("[", "").trim()
            }

            writer.writeNext(cleanHeader)

            for(String s : cleanData){
                def row = s.split(",").collect{it.trim()} as String[]
                writer.writeNext(row)
            }

            println "Csv criado!"

        } catch (Exception e){
            e.printStackTrace()
        }

    }
}
