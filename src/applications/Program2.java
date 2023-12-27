package applications;

import db.DB;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.impl.DepartmentDaoJDBC;
import model.entities.Department;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Program2 {
    public static void main(String[] args) throws SQLException {
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
        System.out.println("=====TESTE 1 - findById ======");
        Department d = departmentDao.findById(2);
        System.out.println(d);

        System.out.println("=====TESTE 2 - findAll ======");
        List<Department> listDep = departmentDao.findAll();
        for (Department dep: listDep) {
            System.out.println(dep);
        }

        System.out.println("=====TESTE 3 - Update ======");
        Department dep = departmentDao.findById(6);
        dep.setName("Toys");
        departmentDao.update(dep);
        System.out.println(departmentDao.findById(dep.getId()));

       /* System.out.println("=====TESTE 4 - Insert ======");
        Department department = new Department(null,"Food");
        departmentDao.insert(department);
        System.out.println(departmentDao.findById(department.getId()));
        */

        System.out.println("=====TESTE 5 - Delete ======");
        departmentDao.deleteById(8);

    }
}
