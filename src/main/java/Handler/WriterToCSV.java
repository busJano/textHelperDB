package Handler;

import Entities.Word;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class WriterToCSV {

    public static void writeToCSV(List<Word> wordList, int count) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        File file = new File("C:/Users/janob/Projektchen/textHelperDB/src/main/java/out/out" + count + ".csv");
        file.createNewFile();
        try (
                Writer writer = Files.newBufferedWriter(file.toPath(), StandardOpenOption.APPEND);
        ) {
            StatefulBeanToCsv<Word> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withSeparator(";".toCharArray()[0])
                    .withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER)
                    .build();

            beanToCsv.write(wordList);
        }
    }
}
