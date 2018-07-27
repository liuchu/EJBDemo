package com.chuliu.demo.remote.client.ejb;

/**
 * Created by eiuhucl on 7/23/2018.
 */


import com.chuliu.demo.ejb3.server.api.IRemotePlayedQuizzesCounter;
import com.chuliu.demo.ejb3.server.api.IRemoteQuiz;
import com.chuliu.demo.jndi.lookup.ejb3.LookerUp;

public class QuizManagedBean {

    private String playerName;
    private int score = 0;
    private String question = "";
    private int answer;

    private static QuizManagedBean quizManagedBean;

    // I'm not DI'ing it!
    //@EJB
    private IRemoteQuiz  quizProxy;

    private IRemotePlayedQuizzesCounter playedQuizzesCounterProxy;

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

        //####
        String ejbsServerAddress = "127.0.0.1";
        int ejbsServerPort = 8080;
        String earName = "ejb3-server-client-ear";
        String moduleName = "ejb3-server-war";
        String deploymentDistinctName = "";
        String beanName = "QuizBean";
        String interfaceQualifiedName = IRemoteQuiz.class.getName();

        // No password required <= Component deployed in the same container
        LookerUp wildf9Lookerup = new LookerUp(ejbsServerAddress, ejbsServerPort);

        // We could instead use the following method by giving the exact JNDI name :
//		 quizProxy = (IRemoteQuiz) wildf9Lookerup.findSessionBean("ejb:ejb3-server-client-ear/ejb3-server-war//QuizBean!com.letsprog.learning.ejb3.server.api.IRemoteQuiz?stateful");
        quizProxy = (IRemoteQuiz) wildf9Lookerup.findRemoteSessionBean(LookerUp.SessionBeanType.STATEFUL, earName, moduleName, deploymentDistinctName,
                beanName, interfaceQualifiedName);

        //playedQuizzesCounterProxy
        String beanCounterName = "PlayedQuizzesCounterBean";
        String counterInterfaceQualifiedName = IRemotePlayedQuizzesCounter.class.getName();
        playedQuizzesCounterProxy = (IRemotePlayedQuizzesCounter) wildf9Lookerup.findRemoteSessionBean(LookerUp.SessionBeanType.SINGLETON, earName, moduleName, deploymentDistinctName,
                beanCounterName, counterInterfaceQualifiedName);

        quizProxy.begin(playerName);
    }

    public void start() {

        setQuestion(quizProxy.generateQuestionAndAnswer());

    }

    public boolean verifyAnswer(int answer){

        playedQuizzesCounterProxy.increment();

        boolean correct = quizProxy.verifyAnswerAndReward(answer);

        int gotScore = quizProxy.getScore();

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
