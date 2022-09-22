package library.domain;

import java.util.Date;

public class Records {
    private String BookNo;
    private String Id;
    private Date BorrowTime;
    private Date ReturnTime;
    private String AdminID;
    private String BookName;
    private String Type;
    private String Publisher;
    private Integer Date;
    private String Author;
    private Double Price;
    private Integer Storage;
    private Integer Stock;

    public Records() {

    }

    @Override
    public String toString() {
        return "（ " +
                 BookNo +
                ", " + Type +
                ", " + BookName +
                ", " + Publisher +
                ", " + Date +
                ", " + Author +
                ", " + Price +
                ", " + Storage +
                ", " + Stock +
                " ）";
    }

    public String getBookNo() {
        return BookNo;
    }

    public void setBookNo(String bookNo) {
        BookNo = bookNo;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public java.util.Date getBorrowTime() {
        return BorrowTime;
    }

    public void setBorrowTime(java.util.Date borrowTime) {
        BorrowTime = borrowTime;
    }

    public java.util.Date getReturnTime() {
        return ReturnTime;
    }

    public void setReturnTime(java.util.Date returnTime) {
        ReturnTime = returnTime;
    }

    public String getAdminID() {
        return AdminID;
    }

    public void setAdminID(String adminID) {
        AdminID = adminID;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public Integer getDate() {
        return Date;
    }

    public void setDate(Integer date) {
        Date = date;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public Integer getStorage() {
        return Storage;
    }

    public void setStorage(Integer storage) {
        Storage = storage;
    }

    public Integer getStock() {
        return Stock;
    }

    public void setStock(Integer stock) {
        Stock = stock;
    }

    public Records(String bookNo, String id, java.util.Date borrowTime, java.util.Date returnTime, String adminID, String bookName, String type, String publisher, Integer date, String author, Double price, Integer storage, Integer stock) {
        BookNo = bookNo;
        Id = id;
        BorrowTime = borrowTime;
        ReturnTime = returnTime;
        AdminID = adminID;
        BookName = bookName;
        Type = type;
        Publisher = publisher;
        Date = date;
        Author = author;
        Price = price;
        Storage = storage;
        Stock = stock;
    }
}
