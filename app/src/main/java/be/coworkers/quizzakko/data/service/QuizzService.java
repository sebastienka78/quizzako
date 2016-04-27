package be.coworkers.quizzakko.data.service;

import org.androidannotations.annotations.rest.Accept;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.MediaType;
import org.androidannotations.api.rest.RestClientHeaders;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import be.coworkers.quizzakko.data.model.dao.Quizz;

/**
 * be.coworkers.quizzakko.data.service
 * <p/>
 * Created by sebastienk on 12-Mar-16.
 */
@Rest(rootUrl = "http://www.quizzako.com:8080/quizzakko", converters = { GsonHttpMessageConverter.class })
public interface QuizzService extends RestClientHeaders {

    @Get("/quizz")
    @Accept(MediaType.APPLICATION_JSON)
    public Quizz[] getQuizz();
}
