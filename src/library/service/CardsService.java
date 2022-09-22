package library.service;

import library.dao.CardsDAO;
import library.domain.Cards;

import java.util.List;

public class CardsService {
    private CardsDAO cardsDAO = new CardsDAO();

    public boolean deleteCard() {
        int update = cardsDAO.update("delete * from card");
        return update > 0;
    }

    public boolean IsIdExist(String Id) {
        Cards card = cardsDAO.querySingle("select * from card where Id = ?", Cards.class, Id);
        return card != null;
    }

    public List<Cards> getCardIdByName(String Name) {
        return cardsDAO.queryMulti("select Id from card where Name = ?", Cards.class, Name);
    }

    public List<Cards> getCardIdByDept(String Dept) {
        return cardsDAO.queryMulti("select Id from card where Department = ?", Cards.class, Dept);
    }

    public List<Cards> getCardIdByType(String Type) {
        return cardsDAO.queryMulti("select Id from card where Type = ?", Cards.class, Type);
    }

    public List<Cards> getCardId() {
        return cardsDAO.queryMulti("select Id from card", Cards.class);
    }

    public boolean deleteCardById(String Id) {
        int update = cardsDAO.update("delete from card where Id = ?", Id);
        return update > 0;
    }

    public boolean deleteCardByName(String Name) {
        int update = cardsDAO.update("delete from card where Name = ?", Name);
        return update > 0;
    }

    public boolean deleteCardByDept(String Dept) {
        int update = cardsDAO.update("delete from card where Department = ?", Dept);
        return update > 0;
    }

    public boolean deleteCardByType(String Type) {
        int update = cardsDAO.update("delete from card where Type = ?", Type);
        return update > 0;
    }

    public boolean insertCard(String Id, String Name, String Department, String Type) {
        int update = cardsDAO.update("insert into card values(?, ?, ?, ?)", Id, Name, Department, Type);
        return update > 0;
    }
}
