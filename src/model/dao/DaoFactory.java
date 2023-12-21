package model.dao;

import db.DB;
import model.dao.impl.SellerDaoJDBC;

import java.sql.SQLException;

public class DaoFactory {

    public static SellerDao createSellerDao() throws SQLException {
        return new SellerDaoJDBC(DB.getConnection());
    }
}
