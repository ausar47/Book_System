package library.dao;

import library.domain.Books;
import library.utils.JDBCUtilsByDruid;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

    public class BooksDAO extends BasicDAO<Books> {
        public int insertByBatches(String fileName) {
            Connection connection = null;
            try {
                connection = JDBCUtilsByDruid.getConnection();
                File file = new File(fileName);
                BufferedReader reader = null;
                String sql = "insert into library.book values(?,?,?,?,?,?,?,?,?)";
                int line = 0;
                try {
                    reader = new BufferedReader(new FileReader(file));
                    String tempString = null;
                    while (reader.readLine() != null)
                        line++; // count the line
                    Object[][] params = new Object[line][]; // a 2-D array to store the params of each sql queries
                    reader = new BufferedReader(new FileReader(file));
                    line = 0;
                    while ((tempString = reader.readLine()) != null) {
                        // read file and get the params
                        char[] BookNo = new char[32], BookName = new char[32], Type = new char[32], Publisher = new char[32], Date = new char[32], Author = new char[32], Price = new char[32], Num = new char[32];
                        char[] temp = tempString.toCharArray();
                        int i = 0;
                        while (temp[i] == ' ' || temp[i] == '(')
                            i++;
                        int j = 0;
                        while (temp[i] != ',') {
                            BookNo[j] = temp[i];
                            i++;    j++;
                        }
                        i++;    j = 0;
                        String bookno = new String(BookNo);
                        bookno = bookno.trim();
                        while (temp[i] == ' ')
                            i++;
                        while (temp[i] != ',') {
                            Type[j] = temp[i];
                            i++;    j++;
                        }
                        i++;    j = 0;
                        String type = new String(Type);
                        type = type.trim();
                        while (temp[i] == ' ')
                            i++;
                        while (temp[i] != ',') {
                            BookName[j] = temp[i];
                            i++;    j++;
                        }
                        i++;    j = 0;
                        String bookname = new String(BookName);
                        bookname = bookname.trim();
                        while (temp[i] == ' ')
                            i++;
                        while (temp[i] != ',') {
                            Publisher[j] = temp[i];
                            i++;    j++;
                        }
                        i++;    j = 0;
                        String publisher = new String(Publisher);
                        publisher = publisher.trim();
                        while (temp[i] == ' ')
                            i++;
                        while (temp[i] != ',') {
                            Date[j] = temp[i];
                            i++;    j++;
                        }
                        i++;    j = 0;
                        String date = new String(Date);
                        int date_ = Integer.parseInt(date.trim());
                        while (temp[i] == ' ')
                            i++;
                        while (temp[i] != ',') {
                            Author[j] = temp[i];
                            i++;    j++;
                        }
                        i++;    j = 0;
                        String author = new String(Author);
                        author = author.trim();
                        while (temp[i] == ' ')
                            i++;
                        while (temp[i] != ',') {
                            Price[j] = temp[i];
                            i++;    j++;
                        }
                        i++;    j = 0;
                        String price = new String(Price);
                        double price_ = Double.parseDouble(price.trim());
                        while (temp[i] == ' ')
                            i++;
                        while (temp[i] != ',' && temp[i] != ')') {
                            Num[j] = temp[i];
                            i++;    j++;
                        }
                        String num = new String(Num);
                        int num_ = Integer.parseInt(num.trim());
                        // store the current params into the array
                        params[line++] = new Object[]{bookno, bookname, type, publisher, date_, author, price_, num_, num_};
                    }
                    try {
                        qr.batch(connection, sql, params); // now batch all the sql instructions
                    } catch (SQLException e) {
                        System.out.println("===============文件格式错误或书籍号重复===============");
                        return 0;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                        }
                    }
                }
                return 1;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                JDBCUtilsByDruid.close(null, null, connection);
            }
        }
    }
