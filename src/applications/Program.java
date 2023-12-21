package applications;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;

public class Program {
    public static void main(String[] args) {
        Department d1 = new Department(1, "Books");
        Seller s1 = new Seller(1, "Bob Brown", "bobbrown@gmail.com", new Date(), 3000);

        SellerDao sellerDao = DaoFactory.createSellerDao();
        System.out.println(d1);
        System.out.println(s1);
    }
}