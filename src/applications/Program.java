package applications;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Program {
    public static void main(String[] args) throws SQLException {
        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=== TESTE 1 : seller FindById");
        Seller s1 = sellerDao.findById(2);
        System.out.println(s1);

        System.out.println("\n=== TESTE 2 : seller FindByDepartment");
        List<Seller> sellerByDep = sellerDao.findByDepartment(new Department(2, null));
        for (Seller s: sellerByDep) {
            System.out.println(s);
            System.out.println("a");
        }
    }
}
