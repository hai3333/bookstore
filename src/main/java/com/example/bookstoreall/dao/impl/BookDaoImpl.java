package com.example.bookstoreall.dao.impl;

import com.example.bookstoreall.dao.BookDao;
import com.example.bookstoreall.poje.Book;

import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql = " insert into t_book(name, author, price,sales,stock,img_path ) values(?,?,?,?,?,?)";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock()
        ,book.getImg_path());
    }

    @Override
    public int deleteBook(Integer id) {
        String sql="delete from t_book where id=?";

        return update(sql,id);
    }

    @Override
    public int updateBook(Book book) {
        String sql="update t_book set name=?, author=?, price=?,sales=?,stock=?,img_path=? where id=?";

        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock()
                ,book.getImg_path(),book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
      String sql="select id, name, author, price,sales,stock,img_path imgPath from t_book where id=?";
    return queryForOne(Book.class,sql,id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql="select id,name, author, price,sales,stock,img_path imgPath from t_book ";
        return queryForList(Book.class,sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
    String sql="select count(*) from t_book";
        Number a= (Number) queryForSingleValue(sql);
        return  a.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql="select id,name, author, price,sales,stock,img_path imgPath from t_book limit ?,?";
        return queryForList(Book.class,sql,begin,pageSize);
    }

    @Override
    public Integer queryForPageTotalCountByPrice(int min, int max) {
        String sql="select count(*) from t_book where price between ? and ?";
        Number a= (Number) queryForSingleValue(sql,min,max);
        return  a.intValue();
    }



    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql="select id,name, author, price,sales,stock,img_path imgPath from t_book  where price between ? and ? order by price limit ?,? ";
        return queryForList(Book.class,sql,min,max,begin,pageSize);
    }
}
