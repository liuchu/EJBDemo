package com.chuliu.demo.remote.client.controller;

import com.chuliu.demo.remote.client.ejb.QuizManagedBean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by eiuhucl on 7/23/2018.
 */
@SpringBootApplication
@Controller
/*@RequestMapping(value = "/")*/
public class PageController {

    private QuizManagedBean quizManagedBean = QuizManagedBean.buildInstance();
    /*@Autowired
    private QuizManagedBean quizManagedBean;*/

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/quiz",method = RequestMethod.GET)
    public String quiz(Model model){
        quizManagedBean.start();
        model.addAttribute("question", quizManagedBean.getQuestion());
        model.addAttribute("quizzesNumber", quizManagedBean.getPlayedQuizzesNumber());
        return "quiz";
    }

    @RequestMapping(value = "/score",method = RequestMethod.GET)
    public String score(Model model){

        model.addAttribute("playerName", quizManagedBean.getPlayerName());
        model.addAttribute("score", quizManagedBean.getScore());
        model.addAttribute("quizzesNumber", quizManagedBean.getPlayedQuizzesNumber());
        return "score";
    }

}
