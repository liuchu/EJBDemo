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

    // Same WAR
    public LookerUp(){
        prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
    }

    public Object findLocalSessionBean(String moduleName, String beanName, String interfaceFullQualifiedName) {

        Context context = null;
        try {
            context = new InitialContext(prop);
        } catch (NamingException e) {
            System.out.println("Failed to init naming context");
            e.printStackTrace();
        }
        String jndiUri = "java:global/"+moduleName+"/"+beanName+"!"+interfaceFullQualifiedName;
        System.out.println("JNDI URI: "+jndiUri);


        Object object = null;
        try {
            object = context.lookup(jndiUri);
            context.close();
        } catch (NamingException e) {
            System.out.println("Failed to look up "+beanName);
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

}
