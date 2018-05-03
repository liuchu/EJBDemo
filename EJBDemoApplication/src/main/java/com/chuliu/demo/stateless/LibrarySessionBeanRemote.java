package com.chuliu.demo.stateless;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by chuliu on 2018/5/1.
 */

@Remote
public interface LibrarySessionBeanRemote {

    void addBook(String bookName);

    List getBooks();

    String getSingleBook(String bookName);
}
