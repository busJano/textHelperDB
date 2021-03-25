import Entities.Word;
import Handler.ReadAndDBWriter;
import Hibernate.HibernateUtil;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.hibernate.Session;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class GenerateTextHelperDB {

    public static void main(String[] args) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        for (int i = 0; i < 50; ++i) System.out.println();

        disableLogging();

        ReadAndDBWriter.writeDB(session);

        session.disconnect();

        HibernateUtil.shutdown();
    }

    private static void disableLogging() {
        LogManager logManager = LogManager.getLogManager();
        Logger logger = logManager.getLogger("");
        logger.setLevel(Level.OFF); //could be Level.OFF
    }
}
