package applications;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.SQLException;
import java.util.Date;

public class Program {
    public static void main(String[] args) throws SQLException {

        SellerDao sellerDao = DaoFactory.createSellerDao();
        Seller s1 = sellerDao.findById(3);

        System.out.println(s1);
    }
}
