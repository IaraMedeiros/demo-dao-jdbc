package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

public class DepartmentDaoJDBC implements DepartmentDao {
    private Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("INSERT INTO department" +
                    "(Name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);

            st.setString(1,obj.getName());

            int rowsAffected = st.executeUpdate();
            if(rowsAffected > 0){
                rs = st.getGeneratedKeys();
                if(rs.next()){
                    obj.setId(rs.getInt(1));
                }
                DB.closeResultSet(rs);
            }  else {
            throw new DbException("Erro inesperado, nenhuma linha afetada");
        }

        }
        catch (SQLException e) {
        throw new DbException(e.getMessage());
    }
        finally {
        DB.closeStatement(st);
    }
}



    @Override
    public void update(Department obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE department " +
                            "SET Name = ? " +
                            "WHERE Id = ?");

            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());

            st.executeUpdate();
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("DELETE FROM department WHERE id = ?");
            st.setInt(1, id);
            st.executeUpdate();

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }

    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT department.* FROM department WHERE department.Id = ?");
            st.setInt(1,id);
            rs = st.executeQuery();

            if(rs.next()){
                return instantiateDepartment(rs);
            } else {
                return null;
            }


        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }


    }

    private Department instantiateDepartment(ResultSet rs){
        Department dep = new Department();
        try {
            dep.setName(rs.getString("Name"));
            dep.setId(rs.getInt("Id"));
            return dep;

        }catch (SQLException e){
            throw new DbException( e.getMessage());
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT department.* FROM department order by Id");
            rs = st.executeQuery();
            List<Department> list = new ArrayList<>();

            while(rs.next()){
                list.add(instantiateDepartment(rs));
            }
            return list;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }


    }
}
