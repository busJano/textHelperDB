package Handler;

import Entities.Word;
import Hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CleanDuplicatesDB {

    public static void cleanDuplicates(Session session) {
        double count = 0;
        boolean keepGoing = true;

        int loadingCapacity = 40000000;
        int offset = 50000;

        session.beginTransaction();

        Query countQuery = session.createQuery("select count (*) from Word");
        Long totalRowsCount = (Long)countQuery.uniqueResult();

        System.out.println(totalRowsCount);

        Query test = session.createQuery("select id from Word ")
                .setFirstResult(offset)
                .setMaxResults(loadingCapacity);

        List list = test.list();

        System.out.println(list.get(0));

//        while (keepGoing) {
//
//            Query getWordQuery = session.createQuery("from Word where id = :id")
//                    .setParameter("id", count);
//
//            try {
//                Word word = (Word) getWordQuery.uniqueResult();
//
//                Query getDuplicatesQuery = session.createQuery("from Word where mainWord =: mainWord and wordsBefore =:wordsBefore and wordsAfter =:wordsAfter");
//                List wordList = getDuplicatesQuery.getResultList();
//                if (!wordList.isEmpty()) {
//                    word.setCount(wordList.size());
//                    session.save(word);
//                }
//
//                System.out.println();
//
//            } catch (Exception ex) {
//                keepGoing = false;
//                System.out.println(ex);
//            }
//        }
        session.clear();
        session.close();
    }

}
