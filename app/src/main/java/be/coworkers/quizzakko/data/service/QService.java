package be.coworkers.quizzakko.data.service;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import be.coworkers.quizzakko.data.model.dao.Quizz;

/**
 * be.coworkers.quizzakko.data.service
 * <p/>
 * Created by sebastienk on 13-Mar-16.
 */
public class QService {


    public QService() {
    }

    public  ResponseEntity<Quizz[]> getQuizz() {

        final String url = "http://rest-service.guides.spring.io/greeting";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        //Quizz[] quizzs = restTemplate.getForObject(url, Quizz[].class);
        ResponseEntity<Quizz[]> responseEntity = restTemplate.getForEntity(url, Quizz[].class);
        return responseEntity;
    }
}
