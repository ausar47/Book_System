package library.service;

import library.dao.RecordsDAO;
import library.domain.Records;

import java.util.Date;
import java.util.List;

    public class RecordsService {
        private RecordsDAO recordsDAO = new RecordsDAO();

        public List<Records> borrowFindBooksLend(String Id) {
            return recordsDAO.queryMulti("select book.* from book, records where records.Id = ? and records.BookNo = book.BookNo and records.ReturnTime is null", Records.class, Id);
        }

        public void borrowSuccess(String Id, String BookNo, String AdminID) {
            Date BorrowTime = new Date();
            recordsDAO.update("insert into records values (?,?,?,null,?)", BookNo, Id, BorrowTime, AdminID);
        }

        public Object printLatestReturntime(String BookNo) {
            return recordsDAO.queryScalar("select max(ReturnTime) from records where BookNo = ?", BookNo);
        }

        public boolean IsBookInRecord(String BookNo, String Id) {
            Records record = recordsDAO.querySingle("select * from records where BookNo = ? and ReturnTime is null and Id = ?", Records.class, BookNo, Id);
            return record != null;
        }

        public void ReturnBook(String BookNo, String Id, String AdminID) {
            Date ReturnTime = new Date();
            recordsDAO.update("update records set ReturnTime = ?, AdminId = ? where BookNo = ? and ReturnTime is null and Id = ?", ReturnTime, AdminID, BookNo, Id);
        }

        public boolean borrowLend(String BookNo) {
            return true;
        }

    }
