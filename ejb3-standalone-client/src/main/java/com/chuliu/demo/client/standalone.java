package com.chuliu.demo.client;

import com.chuliu.demo.ejb3.server.api.IRemoteQuiz;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Hashtable;

/**
 * Created by eiuhucl on 7/27/2018.
 */
public class standalone {

    public static void main(String[] args) throws Exception{
        final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

        // Setting JNDI Context
        final Context context = new InitialContext(jndiProperties);

        // Using the JNDI name of the EJB following this pattern :
        // "ejb:{EarName}/{ModuleName}/{DeploymentName}/{EJBClassName}!{EJBInterfaceFullyQualifiedName}?{Stateful}"
        IRemoteQuiz remoteQuiz = (IRemoteQuiz) context.lookup("ejb:/ejb3-server-war//QuizBean!com.chuliu.demo.ejb3.server.api.IRemoteQuiz?stateful");
        remoteQuiz.begin("Lucky Man");

        Boolean answerIsCorrect = true;

        while (answerIsCorrect) {
            System.out.println("The answer is correct. Your score is : "+remoteQuiz.getScore()+". "
                    + "Very well done, "+remoteQuiz.getPlayerName());
            String question = remoteQuiz.generateQuestionAndAnswer();
            System.out.println("Question : "+question);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String answer = br.readLine();

            answerIsCorrect = remoteQuiz.verifyAnswerAndReward(Integer.parseInt(answer));
        }

        System.out.println("The answer is wrong. Your score is : "+remoteQuiz.getScore()+". "
                + "Have a better luck next time, "+remoteQuiz.getPlayerName());

        remoteQuiz.end();
    }
}
