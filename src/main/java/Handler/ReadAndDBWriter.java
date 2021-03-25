package Handler;

import Entities.Word;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadAndDBWriter {

    public static void writeDB(Session session) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        File input = new File("C:/Users/janob/Projektchen/TextHelperLearner/src/main/resources/texts/database/test.txt");
        double count = 0;

        session.beginTransaction();

        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line;
            while ((line = br.readLine()) != null) {

                Word word = getWordFromLine(line);

                if (word.getMainWord() == null || word.getMainWord().isEmpty()) {
                    continue;
                }


                Word resultWord = null;


//                if (resultWord == null) {
//                    session.save(word);
//                } else {
//                    resultWord.setCount(resultWord.getCount() + 1);
//                    session.save(resultWord);
//                }

                session.save(word);
                try {
                    session.flush();
                    session.clear();
                } catch (Exception exception) {
                    System.out.println("Found duplicate");

                    Query query = session.createQuery("from Word where wordsBefore =:wordBefore and mainWord =:mainWord and wordsAfter = :wordsAfter")
                            .setParameter("wordBefore", word.getWordsBefore())
                            .setParameter("wordsAfter", word.getWordsAfter())
                            .setParameter("mainWord", word.getMainWord());

                    try {
                        resultWord = (Word) query.uniqueResult();
                    } catch (Exception ex) {
                        System.out.println(ex);
                        System.out.println("MainWord: " + word.getMainWord() + ", wordsAfter: " + word.getWordsAfter() + ", wordsBefore: " + word.getWordsBefore());
                    }

                    if (resultWord != null) {
                        resultWord.setCount(resultWord.getCount() + 1);
                        session.save(resultWord);
                        session.flush();
                        session.clear();
                    }
                }

                count++;

                if (count % 50 == 0) {
                    System.out.println("Checked lines: " + count);
//                    session.flush();
//                    session.clear();
                }

                if (count % 4987 == 0) {
                    System.out.println("Checked lines: " + count);
                }
            }

            session.flush();
            session.clear();


            System.out.println("Checked lines: " + count);
            System.out.println("=================== FINISHED ===================");
        }
    }

    private static Word getWordFromLine(String line) {
        int indexRightBracket = line.indexOf("[");
        int indexLeftBracket = line.indexOf("]");

        String beforeMain = line.substring(0, indexRightBracket - 1).toLowerCase();
        String afterMain = line.substring(indexLeftBracket + 1).toLowerCase();
        String mainWord = line.substring(indexRightBracket + 1, indexLeftBracket).toLowerCase();

        Word word = new Word();
        word.setMainWord(mainWord);
        word.setWordsBefore(beforeMain);
        word.setWordsAfter(afterMain);

        word.setCount(0);

        return word;
    }

    @Transactional
    private static void save(List<Word> words, Session session) {
//        session.getTransaction().commit();

        for (int i = 0; i < words.size(); i++) {
            session.persist(words.get(i));
        }

//        session.getTransaction().commit();
    }

}
