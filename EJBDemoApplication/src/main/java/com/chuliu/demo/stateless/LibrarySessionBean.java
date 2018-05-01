package com.chuliu.demo.stateless;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chuliu on 2018/5/1.
 */
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
}
