package com.chuliu.demo.stateless;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chuliu on 2018/5/1.
 */
@Stateless
public class LibrarySessionBean implements LibrarySessionBeanRemote {

    List<String> bookShelf;

    public LibrarySessionBean(){
        bookShelf = new ArrayList<String>();
    }

    public void addBook(String bookName) {

    }


    public List getBooks() {
        return bookShelf;
    }

    @Override
    public String getSingleBook(String bookName) {
        return bookName;
    }
}
