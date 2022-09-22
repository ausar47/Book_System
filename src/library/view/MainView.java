package library.view;

import library.domain.Admin;
import library.domain.Books;
import library.domain.Cards;
import library.domain.Records;
import library.service.AdminService;
import library.service.BooksService;
import library.service.CardsService;
import library.service.RecordsService;
import library.utils.Utility;

import java.util.List;

public class MainView {
    private boolean loop = true;    // 控制是否退出菜单
    private boolean loop2 = true;   // 控制是否退出管理员菜单
    private String key = "";
    private AdminService adminService = new AdminService();
    private BooksService booksService = new BooksService();
    private CardsService cardsService = new CardsService();
    private RecordsService recordsService = new RecordsService();
    private String adminId;

    public static void main(String[] args) {
        new MainView().mainMenu();
    }

    public void ReturnBook() {
        System.out.println("请输入借书证Id：");
        String Id = Utility.readString(50);
        if (!cardsService.IsIdExist(Id)) {
            System.out.println("===============借书证不存在===============");
            return;
        }
        List<Records> list = recordsService.borrowFindBooksLend(Id);
        for (Records records : list)
            System.out.println(records);
        // 还书
        System.out.println("请输入书号：");
        String BookNo = Utility.readString(50);
        if (!recordsService.IsBookInRecord(BookNo, Id)) {
            System.out.println("===============错误，无记录或已归还！===============");
        }
        else {
            recordsService.ReturnBook(BookNo, Id, adminId);
            booksService.stockPlusOne(BookNo);
            System.out.println("===============还书成功===============");
        }
    }

    public void LendBook() {
        // 查询
        System.out.println("请输入借书证Id：");
        String Id = Utility.readString(50);
        if (!cardsService.IsIdExist(Id)) {
            System.out.println("===============借书证不存在===============");
            return;
        }
        List<Records> list = recordsService.borrowFindBooksLend(Id);
        for (Records records : list)
            System.out.println(records);
        // 借书
        System.out.println("请输入书号：");
        String BookNo = Utility.readString(50);
        if (recordsService.IsBookInRecord(BookNo, Id)) {
            System.out.println("===============该书已借阅===============");
            return;
        }
        if (!booksService.stockMinusOne(BookNo)) {
            System.out.println("===============该书无库存===============");
            // 输出最近归还时间
            Object ReturnTime = recordsService.printLatestReturntime(BookNo);
            System.out.println("最近归还时间：" + ReturnTime);
        } else {
            recordsService.borrowSuccess(Id, BookNo, adminId);
            System.out.println("===============借书成功===============");
        }
    }

    public void updateCards() {
        while (true) {
            System.out.println("请选择要做的操作：");
            System.out.println("\t 1 增加借书证");
            System.out.println("\t 2 删除借书证");
            System.out.println("\t 3 返回");
            List<Cards> cards;
            boolean flag = true;
            key = Utility.readString(1);
            switch (key) {
                case "1":
                    System.out.println("请输入借书证Id：");
                    String Id = Utility.readString(50);
                    System.out.println("请输入借书证姓名：");
                    String Name = Utility.readString(50);
                    System.out.println("请输入借书证单位：");
                    String Dept = Utility.readString(50);
                    System.out.println("请输入借书证类别(Student/Teacher)：");
                    String Type = Utility.readString(50);
                    if (!cardsService.insertCard(Id, Name, Dept, Type))
                        System.out.println("===============发生错误（ID重复或字符过长）===============");
                    else
                        System.out.println("===============增加成功===============");
                    break;
                case "2":
                    System.out.println("请选择删除方式：");
                    System.out.println("\t 1 通过ID删除");
                    System.out.println("\t 2 通过姓名删除");
                    System.out.println("\t 3 通过单位删除");
                    System.out.println("\t 4 通过类别删除(Student/Teacher)");
                    System.out.println("\t 5 全部删除");
                    key = Utility.readString(1);
                    boolean update;
                    switch (key) {
                        case "1":
                            System.out.println("请输入借书证ID：");
                            String ID = Utility.readString(50);
                            if (recordsService.borrowFindBooksLend(ID) != null) {
                                System.out.println("===============删除失败（还存在未还书籍）===============");
                                break;
                            }
                            update = cardsService.deleteCardById(ID);
                            if (!update)
                                System.out.println("===============删除失败（ID不存在）===============");
                            else {
                                System.out.println("===============删除成功===============");
                            }
                            break;
                        case "2":
                            System.out.println("请输入借书证姓名：");
                            String NAME = Utility.readString(50);
                            cards = cardsService.getCardIdByName(NAME);
                            for (Cards card : cards) {
                                String ID_ = card.getId();
                                if (recordsService.borrowFindBooksLend(ID_) != null) {
                                    System.out.println("===============删除失败（还存在未还书籍）===============");
                                    flag = false;
                                    break;
                                }
                            }
                            if (!flag)
                                break;
                            update = cardsService.deleteCardByName(NAME);
                            if (!update)
                                System.out.println("===============删除失败（姓名不存在）===============");
                            else {
                                System.out.println("===============删除成功===============");
                            }
                            break;
                        case "3":
                            System.out.println("请输入借书证单位：");
                            String DEPT = Utility.readString(50);
                            cards = cardsService.getCardIdByDept(DEPT);
                            for (Cards card : cards) {
                                String ID_ = card.getId();
                                if (recordsService.borrowFindBooksLend(ID_) != null) {
                                    System.out.println("===============删除失败（还存在未还书籍）===============");
                                    flag = false;
                                    break;
                                }
                            }
                            if (!flag)
                                break;
                            update = cardsService.deleteCardByDept(DEPT);
                            if (!update)
                                System.out.println("===============删除失败（单位不存在）===============");
                            else {
                                System.out.println("===============删除成功===============");
                            }
                            break;
                        case "4":
                            System.out.println("请输入借书证类别：");
                            String TYPE = Utility.readString(50);
                            cards = cardsService.getCardIdByType(TYPE);
                            for (Cards card : cards) {
                                String ID_ = card.getId();
                                if (recordsService.borrowFindBooksLend(ID_) != null) {
                                    System.out.println("===============删除失败（还存在未还书籍）===============");
                                    flag = false;
                                    break;
                                }
                            }
                            if (!flag)
                                break;
                            update = cardsService.deleteCardByType(TYPE);
                            if (!update)
                                System.out.println("===============删除失败（类别不存在）===============");
                            else {
                                System.out.println("===============删除成功===============");
                            }
                            break;
                        case "5":
                            cards = cardsService.getCardId();
                            for (Cards card : cards) {
                                String ID_ = card.getId();
                                if (recordsService.borrowFindBooksLend(ID_) != null) {
                                    System.out.println("===============删除失败（还存在未还书籍）===============");
                                    flag = false;
                                    break;
                                }
                            }
                            if (!flag)
                                break;
                            update = cardsService.deleteCard();
                            if (!update)
                                System.out.println("===============删除失败（借书证不存在）===============");
                            else
                                System.out.println("===============删除成功===============");
                            break;
                        default:
                            System.out.println("你的输入有误，请重新输入：");
                            break;
                    }
                    break;
                case "3":
                    return;
                default:
                    System.out.println("你的输入有误，请重新输入：");
                    break;
            }
        }
    }

    public void searchBooks() {
        List<Books> list;
        while (true) {
            System.out.println("请选择查询方式：");
            System.out.println("\t 1 按类别查询");
            System.out.println("\t 2 按书名查询");
            System.out.println("\t 3 按出版社查询");
            System.out.println("\t 4 按年份查询");
            System.out.println("\t 5 按作者查询");
            System.out.println("\t 6 按价格查询");
            System.out.println("\t 7 返回");
            key = Utility.readString(1);
            switch (key) {
                case "1":
                    System.out.println("请输入类别：");
                    String Type = Utility.readString(50);
                    list = booksService.bookSearchByType(Type);
                    printBookList(list);
                    break;
                case "2":
                    System.out.println("请输入书名：");
                    String BookName = Utility.readString(50);
                    list = booksService.bookSearchByName(BookName);
                    printBookList(list);
                    break;
                case "3":
                    System.out.println("请输入出版社：");
                    String Publisher = Utility.readString(50);
                    list = booksService.bookSearchByPublisher(Publisher);
                    printBookList(list);
                    break;
                case "4":
                    System.out.println("请输入年份：");
                    int Date = Utility.readInt();
                    list = booksService.bookSearchByDate(Date);
                    printBookList(list);
                    break;
                case "5":
                    System.out.println("请输入作者：");
                    String Author = Utility.readString(50);
                    list = booksService.bookSearchByAuthor(Author);
                    printBookList(list);
                    break;
                case "6":
                    System.out.println("请输入价格：");
                    double Price = Utility.readDouble();
                    list = booksService.bookSearchByPrice(Price);
                    printBookList(list);
                    break;
                case "7":
                    return;
                default:
                    System.out.println("你的输入有误，请重新输入：");
                    break;
            }

        }
    }

    public void printBookList(List<Books> list) {
        for (Books book : list)
            System.out.println(book);
    }

    public void enterBooks() {
        System.out.println("请选择入库方式（单本/批量文件导入）：");
        System.out.println("\t\t 1 单本入库");
        System.out.println("\t\t 2 批量文件导入");
        while (true) {
            key = Utility.readString(1);
            switch (key) {
                case "1" :
                    String BookNo;
                    String BookName;
                    String Type;
                    String Publisher;
                    int Date;
                    String Author;
                    double Price;
                    int Num;
                    System.out.println("请输入书籍编号：");
                    BookNo = Utility.readString(50);
                    System.out.println("请输入书名：");
                    BookName = Utility.readString(50);
                    System.out.println("请输入书籍类型：");
                    Type = Utility.readString(50);
                    System.out.println("请输入出版商：");
                    Publisher = Utility.readString(50);
                    System.out.println("请输入出版日期：");
                    Date = Utility.readInt();
                    System.out.println("请输入作者：");
                    Author = Utility.readString(50);
                    System.out.println("请输入价格：");
                    Price = Utility.readDouble();
                    System.out.println("请输入数量：");
                    Num = Utility.readInt();
                    if (!booksService.bookEnter(BookNo, BookName, Type, Publisher, Date, Author, Price, Num)) {
                        System.out.println("===============入库发生错误===============");
                        return;
                    }
                    System.out.println("===============入库成功===============");
                    return;
                case "2" :
                    System.out.println("请输入文件名：");
                    String fileName = Utility.readString(100);
                    if (!booksService.bookEnterByBatches(fileName)) {
                        System.out.println("===============入库发生错误===============");
                        return;
                    }
                    System.out.println("===============入库成功===============");
                    return;
                default:
                    System.out.println("你的输入有误，请重新输入：");
                    break;
            }
        }

    }

    public void mainMenu() {
        while (loop) {
            loop2 = true;
            System.out.println("===============图书管理系统===============");
            System.out.println("\t\t 1 管理员登录");
            System.out.println("\t\t 2 图书查询");
            System.out.println("\t\t 3 退出系统");
            System.out.println("请输入你的选择：");
            key = Utility.readString(1);
            switch (key) {
                case "1":
                    System.out.println("请输入管理员ID：");
                    adminId = Utility.readString(50);
                    System.out.println("请输入管理员密码：");
                    String pwd = Utility.readString(50);
                    Admin admin = adminService.getAdminByIdAndPwd(adminId, pwd);
                    // 之后改
                    if (admin != null) {
                        System.out.println("===============登录成功===============");
                        while (loop2) {
                            // 管理员菜单
                            System.out.println("===============管理员界面===============");
                            System.out.println("\t\t 1 图书入库");
                            System.out.println("\t\t 2 借书");
                            System.out.println("\t\t 3 还书");
                            System.out.println("\t\t 4 借书证管理");
                            System.out.println("\t\t 5 图书查询");
                            System.out.println("\t\t 9 退出登录");
                            System.out.println("请输入你的选择：");
                            key = Utility.readString(1);
                            switch (key) {
                                case "1":
                                    // 图书入库
                                    enterBooks();
                                    break;
                                case "2":
                                    // 借书
                                    LendBook();
                                    break;
                                case "3":
                                    // 还书
                                    ReturnBook();
                                    break;
                                case "4":
                                    // 借书证管理
                                    updateCards();
                                    break;
                                case "5":
                                    searchBooks();
                                    break;
                                case "9":
                                    loop2 = false;
                                    break;
                                default:
                                    System.out.println("你的输入有误，请重新输入：");
                                    break;
                            }
                        }
                    } else {
                        System.out.println("===============ID/密码错误===============");
                    }
                    break;
                case "2":
                    // 图书查询
                    searchBooks();
                    break;
                case "3":
                    loop = false;
                    break;
                default:
                    System.out.println("你输入有误，请重新输入：");
            }
        }
        System.out.println("已退出");
    }
}
