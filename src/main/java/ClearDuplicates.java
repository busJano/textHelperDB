import Hibernate.HibernateUtil;
import org.hibernate.Session;

import static Handler.CleanDuplicatesDB.cleanDuplicates;

public class ClearDuplicates {

    public static void main(String[] args){
        Session session = HibernateUtil.getSessionFactory().openSession();
        for (int i = 0; i < 50; ++i) System.out.println();

        cleanDuplicates(session);

        HibernateUtil.shutdown();
    }

}
