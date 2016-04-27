package be.coworkers.quizzakko.data.model.dao;

/**
 *
 * Created by sebastienk on 22-Feb-16.
 */
public class DataHolder {

    public Quizz quizz = new Quizz();

    private static DataHolder instance = null;

    private DataHolder() {
        // Exists only to defeat instantiation.
    }
    public static DataHolder getInstance() {
        if(instance == null) {
            instance = new DataHolder();
        }
        return instance;
    }
}
