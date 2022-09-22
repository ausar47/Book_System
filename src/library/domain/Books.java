package library.domain;

public class Books {
    private String BookNo;
    private String BookName;
    private String Type;
    private String Publisher;
    private Integer Date;
    private String Author;
    private Double Price;
    private Integer Storage;
    private Integer Stock;

    public Books() {}

    @Override
    public String toString() {
        return "( " +
                BookNo +
                ", " + Type +
                ", " + BookName +
                ", " + Publisher +
                ", " + Date +
                ", " + Author +
                ", " + Price +
                ", " + Storage +
                ", " + Stock +
                " )";
    }

    public Books(String bookNo, String bookName, String type, String publisher, Integer date, String author, Double price, Integer storage, Integer stock) {
        BookNo = bookNo;
        BookName = bookName;
        Type = type;
        Publisher = publisher;
        Date = date;
        Author = author;
        Price = price;
        Storage = storage;
        Stock = stock;
    }

    public String getBookNo() {
        return BookNo;
    }

    public void setBookNo(String bookNo) {
        BookNo = bookNo;
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
}
