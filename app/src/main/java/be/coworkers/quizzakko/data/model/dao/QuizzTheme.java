package be.coworkers.quizzakko.data.model.dao;

import java.io.Serializable;

/**
 *
 * Created by sebastienk on 14-Feb-16.
 */
public class QuizzTheme implements Serializable {

    private static final long serialVersionUID = 1L;

    private long themeId;
    private String title;

    public QuizzTheme() {

    }

    public QuizzTheme(String title) {
        setTitle(title);
    }

    public long getThemeId() {
        return themeId;
    }

    public void setThemeId(long id) {
        this.themeId = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}