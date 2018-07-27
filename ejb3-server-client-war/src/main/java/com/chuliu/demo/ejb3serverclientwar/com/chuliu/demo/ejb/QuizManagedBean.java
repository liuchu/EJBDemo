package com.chuliu.demo.ejb3serverclientwar.com.chuliu.demo.ejb;

/**
 * Created by eiuhucl on 7/23/2018.
 */

import com.chuliu.demo.ejb3.server.api.ILocalPlayedQuizzesCounter;
import com.chuliu.demo.ejb3.server.api.ILocalQuiz;
import com.chuliu.demo.ejb3.server.api.IPlayedQuizzesCounter;
import com.chuliu.demo.jndi.lookup.ejb3.LookerUp;

public class QuizManagedBean {

    private String playerName;
    private int score = 0;
    private String question = "";
    private int answer;

    private static QuizManagedBean quizManagedBean;

    // I'm not DI'ing it!
    //@EJB
    private ILocalQuiz quizProxy;

    private ILocalPlayedQuizzesCounter playedQuizzesCounterProxy;

    public static QuizManagedBean buildInstance(){

        synchronized (QuizManagedBean.class){
            if (quizManagedBean == null) {
                synchronized (QuizManagedBean.class) {
                    quizManagedBean = new QuizManagedBean();
                }
            }
        }

        return quizManagedBean;
    }

    public void setup(){
        System.out.println("Started quiz game.");
        String moduleName = "ejb3-server-client-war"; // WAR name OR ejb-jar.xml module-name
        String beanName = "QuizBean";
        String interfaceQualifiedName = ILocalQuiz.class.getName();

        // No password required <= Component deployed in the same container
        LookerUp wildf9Lookerup = new LookerUp();

        // We could instead the following method by giving the exact JNDI name :
        // wildf9Lookerup.findSessionBean("java:global/ejb3-server-client-war/QuizBean!com.letsprog.learning.ejb3.server.api.ILocalQuiz")
        quizProxy = (ILocalQuiz) wildf9Lookerup.findLocalSessionBean(moduleName,beanName,interfaceQualifiedName); // Good

        String moduleName2 = "ejb3-server-client-war"; // WAR name OR ejb-jar.xml module-name
        String beanName2 = "PlayedQuizzesCounterBean";
        String interfaceQualifiedName2 = ILocalPlayedQuizzesCounter.class.getName();
        playedQuizzesCounterProxy = (ILocalPlayedQuizzesCounter) wildf9Lookerup.findLocalSessionBean(moduleName2,beanName2,interfaceQualifiedName2);

        quizProxy.begin(playerName);
    }

    public void start() {

        setQuestion(quizProxy.generateQuestionAndAnswer());

    }

    public boolean verifyAnswer(int answer){

        playedQuizzesCounterProxy.increment();

        if(quizProxy == null ){
            System.out.println("quizProxy is null");
            return false;
        }

        boolean correct = quizProxy.verifyAnswerAndReward(answer);

        int gotScore = quizProxy.getScore();
        System.out.println("Score in QuziManagedBean is: "+gotScore);
        setScore(gotScore);

        if(!correct){
            quizProxy.end();
        }

        System.out.println("RESULT: answer yes/no: "+correct);

        return correct;
    }

    public long getPlayedQuizzesNumber(){
        return playedQuizzesCounterProxy.getNumber();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
