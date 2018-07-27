package com.chuliu.demo.ejb3serverclientwar.com.chuliu.demo.controller;

import com.chuliu.demo.ejb3serverclientwar.com.chuliu.demo.ejb.QuizManagedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.NamingException;

/**
 * Created by eiuhucl on 7/23/2018.
 */
@SpringBootApplication
@Controller
@RequestMapping(value = "/req")
public class IQuizController {

    private QuizManagedBean quizManagedBean = QuizManagedBean.buildInstance();
    /*@Autowired
    private QuizManagedBean quizManagedBean;*/

    /*@RequestMapping(value = "/question",method = RequestMethod.GET)
    public String getQuestion(HttpRequest request, Model model){

    }*/

    @RequestMapping(value = "/answer",method = RequestMethod.POST)
    public String answer(String answer, Model model){

        boolean result = quizManagedBean.verifyAnswer(Integer.valueOf(answer));

        if (result) {
            return "redirect:/quiz";
        } else {
            return "redirect:/score";
        }

    }

    @RequestMapping(value = "/play",method = RequestMethod.GET)
    public String play(String playerName){
        quizManagedBean.setup();
        quizManagedBean.setPlayerName(playerName);

        return "redirect:/quiz";
    }


}
