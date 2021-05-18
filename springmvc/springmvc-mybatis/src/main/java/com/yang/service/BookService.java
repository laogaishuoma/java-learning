package com.yang.service;

import com.yang.pojo.Book;

import java.util.List;

public interface BookService {
    /**
     * 增加一本书
     * @param book
     * @return
     */
    int addBook(Book book);

    /**
     * 删除一本书
     * @param id
     * @return
     */
    int deleteBook(int id);

    /**
     * 修改一本书
     * @param book
     * @return
     */
    int updateBook(Book book);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    Book queryBookById(int id);

    /**
     * 查询全部的书
     */
    List<Book> queryAllBook();
}
