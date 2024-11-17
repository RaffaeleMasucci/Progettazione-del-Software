package Model.DAOFactoryMethod;

import java.sql.SQLException;
import java.util.List;

public interface CRUD {


    void insert(Object object) throws SQLException;

    void delete(Object object) throws SQLException;

    void update(Object object) throws SQLException;

    Object search(Object object) throws SQLException;

    List<?> list() throws SQLException;

}
