package Entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(indexes = { @Index(name = "IDX_MYIDX1", columnList = "mainWord,wordsBefore,wordsAfter") })
public class Word {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @GenericGenerator(name = "", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator")
    private int id;

    private String mainWord;
    private String wordsBefore;
    private String wordsAfter;

    private int count;

    public Word() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMainWord() {
        return mainWord;
    }

    public void setMainWord(String mainWord) {
        this.mainWord = mainWord;
    }

    public String getWordsBefore() {
        return wordsBefore;
    }

    public void setWordsBefore(String wordsBefore) {
        this.wordsBefore = wordsBefore;
    }

    public String getWordsAfter() {
        return wordsAfter;
    }

    public void setWordsAfter(String wordsAfter) {
        this.wordsAfter = wordsAfter;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
