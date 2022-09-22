package library.service;

import library.dao.BooksDAO;
import library.domain.Books;

import java.util.List;

public class BooksService {
    private BooksDAO booksDAO = new BooksDAO();

    public boolean bookEnter(String bookNo, String bookName, String type, String publisher, int date, String author, double price, int num) {
        int update = booksDAO.update("insert into book values(?,?,?,?,?,?,?,?,?)", bookNo, bookName, type, publisher, date, author, price, num, num);
        return update > 0;
    }

    public boolean stockMinusOne(String BookNo) {
        int update = booksDAO.update("update book set stock = stock - 1 where BookNo = ? and stock > 0", BookNo);
        return update > 0;
    }

    public boolean stockPlusOne(String BookNo) {
        int update = booksDAO.update("update book set stock = stock + 1 where BookNo = ?", BookNo);
        return update > 0;
    }

    public boolean bookEnterByBatches(String fileName) {
        int update = booksDAO.insertByBatches(fileName);
        return update > 0;
    }

    public List<Books> bookSearchByType(String type) {
        return booksDAO.queryMulti("select * from book where Type >= ? order by Type asc", Books.class, type);
    }

    public List<Books> bookSearchByName(String bookname) {
        return booksDAO.queryMulti("select * from book where BookName >= ? order by BookName asc", Books.class, bookname);
    }

    public List<Books> bookSearchByPublisher(String publisher) {
        return booksDAO.queryMulti("select * from book where Publisher >= ? order by Publisher asc", Books.class, publisher);
    }

    public List<Books> bookSearchByDate(int date) {
        return booksDAO.queryMulti("select * from book where Date >= ? order by Date asc", Books.class, date);
    }

    public List<Books> bookSearchByAuthor(String author) {
        return booksDAO.queryMulti("select * from book where Author >= ? order by Author asc", Books.class, author);
    }

    public List<Books> bookSearchByPrice(double price) {
        return booksDAO.queryMulti("select * from book where Price >= ? order by Price asc", Books.class, price);
    }
}
