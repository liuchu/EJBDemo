package com.chuliu.demo.client;

import com.chuliu.demo.stateless.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.*;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by chuliu on 2018/5/1.
 */
public class EJBTester {

    BufferedReader brConsoleReader = null;

    private static final String HTTP = "http";

    static Logger logger = Logger.getLogger("EJBTester");

    private void testCalculator() throws NamingException {

        final RemoteCalculator statelessRemoteCalculator = EJBTester.lookupRemoteStatelessCalculator();

        logger.info("Obtained a remote stateless calculator for invocation");
        // invoke on the remote calculator
        int a = 204;
        int b = 340;
        logger.info("Adding " + a + " and " + b + " via the remote stateless calculator deployed on the server");
        int sum = statelessRemoteCalculator.add(a, b);
        logger.info("Remote calculator returned sum = " + sum);
        if (sum != a + b) {
            throw new RuntimeException("Remote stateless calculator returned an incorrect sum " + sum + " ,expected sum was " + (a + b));
        }
        // try one more invocation, this time for subtraction
        int num1 = 3434;
        int num2 = 2332;
        logger.info("Subtracting " + num2 + " from " + num1 + " via the remote stateless calculator deployed on the server");
        int difference = statelessRemoteCalculator.subtract(num1, num2);
        logger.info("Remote calculator returned difference = " + difference);
        if (difference != num1 - num2) {
            throw new RuntimeException("Remote stateless calculator returned an incorrect difference " + difference + " ,expected difference was " + (num1 - num2));
        }


    };

    private void testCounter() throws NamingException{

        final RemoteCounter statefulRemoteCounter = lookupRemoteStatefulCounter();
        logger.info("Obtained a remote stateful counter for invocation");
        // invoke on the remote counter bean
        final int NUM_TIMES = 20;
        logger.info("Counter will now be incremented " + NUM_TIMES + " times");
        for (int i = 0; i < NUM_TIMES; i++) {
            logger.info("Incrementing counter");
            statefulRemoteCounter.increment();
            logger.info("Count after increment is " + statefulRemoteCounter.getCount());
        }
        // now decrementing
        System.out.println("Counter will now be decremented " + NUM_TIMES + " times");
        for (int i = NUM_TIMES; i > 0; i--) {
            logger.info("Decrementing counter");
            statefulRemoteCounter.decrement();
            logger.info("Count after decrement is " + statefulRemoteCounter.getCount());
        }
    }

    /**
     * Looks up and returns the proxy to remote stateless calculator bean
     *
     * @return
     * @throws NamingException
     */
    private static RemoteCalculator lookupRemoteStatelessCalculator() throws NamingException {

        logger.info("*** Looping up RemoteStatelessCalculator ***");

        final Hashtable jndiProperties = new Hashtable();
        //jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        if(Boolean.getBoolean(HTTP)) {
            //use HTTP based invocation. Each invocation will be a HTTP request
            jndiProperties.put(Context.PROVIDER_URL,"http://localhost:8080/wildfly-services");
        } else {
            //use HTTP upgrade, an initial upgrade requests is sent to upgrade to the remoting protocol
            jndiProperties.put(Context.PROVIDER_URL,"remote+http://localhost:8080");
        }

        //jndiProperties.put(Context.URL_PKG_PREFIXES, "org.wildfly.naming.client");
        final Context context = new InitialContext(jndiProperties);
        // The app name is the application name of the deployed EJBs. This is typically the ear name
        // without the .ear suffix. However, the application name could be overridden in the application.xml of the
        // EJB deployment on the server.
        // Since we haven't deployed the application as a .ear, the app name for us will be an empty string
        final String appName = "";
        // This is the module name of the deployed EJBs on the server. This is typically the jar name of the
        // EJB deployment, without the .jar suffix, but can be overridden via the ejb-jar.xml
        // In this example, we have deployed the EJBs in a jboss-as-ejb-remote-app.jar, so the module name is
        // jboss-as-ejb-remote-app
        final String moduleName = "ejb3";
        // AS7 allows each deployment to have an (optional) distinct name. We haven't specified a distinct name for
        // our EJB deployment, so this is an empty string
        final String distinctName = "";
        // The EJB name which by default is the simple class name of the bean implementation class
        final String beanName = CalculatorBean.class.getSimpleName();
        // the remote view fully qualified class name
        final String viewClassName = RemoteCalculator.class.getName();
        // let's do the lookup
        final String JNDIUri =
                "".equals(distinctName)?"ejb:" + appName + "/" + moduleName + "/" + beanName + "!" + viewClassName:
                "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName;
        logger.info("The URI is: "+JNDIUri);
        return (RemoteCalculator) context.lookup(JNDIUri);
    }

    /**
     * Looks up and returns the proxy to remote stateful counter bean
     *
     * @return
     * @throws NamingException
     */
    private static RemoteCounter lookupRemoteStatefulCounter() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        if(Boolean.getBoolean(HTTP)) {
            //use HTTP based invocation. Each invocation will be a HTTP request
            jndiProperties.put(Context.PROVIDER_URL,"http://localhost:8080/wildfly-services");
        } else {
            //use HTTP upgrade, an initial upgrade requests is sent to upgrade to the remoting protocol
            jndiProperties.put(Context.PROVIDER_URL,"remote+http://localhost:8080");
        }
        final Context context = new InitialContext(jndiProperties);
        // The app name is the application name of the deployed EJBs. This is typically the ear name
        // without the .ear suffix. However, the application name could be overridden in the application.xml of the
        // EJB deployment on the server.
        // Since we haven't deployed the application as a .ear, the app name for us will be an empty string
        final String appName = "";
        // This is the module name of the deployed EJBs on the server. This is typically the jar name of the
        // EJB deployment, without the .jar suffix, but can be overridden via the ejb-jar.xml
        // In this example, we have deployed the EJBs in a jboss-as-ejb-remote-app.jar, so the module name is
        // jboss-as-ejb-remote-app
        final String moduleName = "ejb3";
        // AS7 allows each deployment to have an (optional) distinct name. We haven't specified a distinct name for
        // our EJB deployment, so this is an empty string
        final String distinctName = "";
        // The EJB name which by default is the simple class name of the bean implementation class
        final String beanName = CounterBean.class.getSimpleName();
        // the remote view fully qualified class name
        final String viewClassName = RemoteCounter.class.getName();
        // let's do the lookup (notice the ?stateful string as the last part of the jndi name for stateful bean lookup)
        return (RemoteCounter) context.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName + "?stateful");
    }

    public static void main(String[] args) {

        EJBTester ejbTester = new EJBTester();

        try {
            ejbTester.testCalculator();
            ejbTester.testCounter();
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }
}
