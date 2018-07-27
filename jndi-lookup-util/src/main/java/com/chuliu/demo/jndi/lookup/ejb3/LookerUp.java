package com.chuliu.demo.jndi.lookup.ejb3;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

/**
 * Created by eiuhucl on 7/23/2018.
 */
public class LookerUp {

    private Properties prop = new Properties();
    private String jndiPrefix;

    //In Same WAR
    public LookerUp(){
        prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
    }

    //In Same EAR
    public LookerUp(String address, int httpPort){

        String protocol;
        protocol = "http-remoting";
        prop.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        prop.put(Context.PROVIDER_URL, protocol+"://"+address+":"+httpPort+"/");
        prop.put("jboss.naming.client.ejb.context", true);
        prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        jndiPrefix="ejb";

    }

    //Find local session bean
    public Object findLocalSessionBean(String moduleName, String beanName, String interfaceFullQualifiedName) {

        Context context = null;
        try {
            context = new InitialContext(prop);
        } catch (NamingException e) {
            System.out.println("Failed to init naming context");
            e.printStackTrace();
        }
        String jndiUri = "java:global/"+moduleName+"/"+beanName+"!"+interfaceFullQualifiedName;

        Object object = null;
        try {
            object = context.lookup(jndiUri);
            context.close();
        } catch (NamingException e) {
            System.out.println("Failed to look up "+beanName+". The JNDI URI is:"+jndiUri);
            e.printStackTrace();
        }

        return object;
    }

    //Find remote session bean
    public Object findRemoteSessionBean(SessionBeanType beanType, String earName, String moduleName, String deploymentDistinctName, String beanName, String interfaceFullQualifiedName){

        String suffix = "";
        if(beanType.equals(SessionBeanType.STATEFUL)){
            suffix = "?stateful";
        }

        Context context = null;

        try {
            context = new InitialContext(prop);
        }catch (NamingException e) {
            System.out.println("Failed to init naming context");
            e.printStackTrace();
        }


        Object object = null;
        try {
            object = context.lookup(jndiPrefix+":"+earName+"/"+moduleName+"/"+deploymentDistinctName+"/"+beanName+"!"+interfaceFullQualifiedName+suffix);
            context.close();
        } catch (NamingException e) {
            System.out.println();
            e.printStackTrace();
        }

        return object;
    }

    public Object findSessionBean(String jndiName){
        Object object = null;
        try {
            final Context context = new InitialContext(prop);
            object = context.lookup(jndiName);
            context.close();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        return object;
    }

    public enum SessionBeanType {
        STATEFUL,STATELESS,SINGLETON
    }

}
