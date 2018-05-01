package com.chuliu.demo.client;

import com.chuliu.demo.stateless.LibrarySessionBeanRemote;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.*;
import java.util.List;
import java.util.Properties;

/**
 * Created by chuliu on 2018/5/1.
 */
public class EJBTester {

    BufferedReader brConsoleReader = null;
    Properties props;
    InitialContext ctx;

    {
        props = new Properties();
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream input = loader.getResourceAsStream("jndi.properties");

            if (input == null) {
                System.exit(-1);
            }

            props.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            ctx = new InitialContext(props);
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        brConsoleReader =
                new BufferedReader(new InputStreamReader(System.in));
    }


    private void showGUI(){
        System.out.println("**********************");
        System.out.println("Welcome to Book Store");
        System.out.println("**********************");
        System.out.print("Options \n1. Add Book\n2. Exit \nEnter Choice: ");
    }

    private void testStatelessEjb(){

        try {
            int choice = 1;
            LibrarySessionBeanRemote libraryBean =
                    (LibrarySessionBeanRemote)ctx.lookup("LibrarySessionBean/remote");
            while (choice != 2) {
                String bookName;
                showGUI();
                String strChoice = brConsoleReader.readLine();
                choice = Integer.parseInt(strChoice);
                if (choice == 1) {
                    System.out.print("Enter book name: ");
                    bookName = brConsoleReader.readLine();
                    libraryBean.addBook(bookName);
                }else if (choice == 2) {
                    break;
                }
            }

            List<String> booksList = libraryBean.getBooks();
            System.out.println("Book(s) entered so far: " + booksList.size());

            for (int i = 0; i < booksList.size(); ++i) {
                System.out.println((i+1)+". " + booksList.get(i));
            }

            LibrarySessionBeanRemote libraryBean1 =
                    (LibrarySessionBeanRemote)ctx.lookup("LibrarySessionBean/remote");
            List<String> booksList1 = libraryBean1.getBooks();

            System.out.println(
                    "***Using second lookup to get library stateless object***");
            System.out.println(
                    "Book(s) entered so far: " + booksList1.size());

            for (int i = 0; i < booksList1.size(); ++i) {
                System.out.println((i+1)+". " + booksList1.get(i));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                if(brConsoleReader !=null){
                    brConsoleReader.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {

        EJBTester ejbTester = new EJBTester();

        ejbTester.testStatelessEjb();
    }
}
