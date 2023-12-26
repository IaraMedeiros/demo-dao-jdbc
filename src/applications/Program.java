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
        Department dep = new Department(2, null);

        System.out.println("=== TESTE 1 : seller FindById");
        Seller s1 = sellerDao.findById(2);
        System.out.println(s1);

        System.out.println("\n=== TESTE 2 : seller FindByDepartment");
        List<Seller> list = sellerDao.findByDepartment(dep);
        for (Seller s: list) {
            System.out.println(s);
        }

        System.out.println("\n=== TESTE 3 : seller findAll");
        list = sellerDao.findAll();
        for (Seller s: list) {
            System.out.println(s);
        }

        System.out.println("\n=== TESTE 4 : seller insertion");
        Seller s = new Seller(null, "Greg Magenta","greg@gmail.com", new Date(), 2440, dep );
        sellerDao.insert(s);
        System.out.println("Inserted! New Id = " + s.getId());
        System.out.println(sellerDao.findById(s.getId()));
    }
}
