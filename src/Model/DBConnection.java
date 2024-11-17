package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection(){

        Connection connection;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/museum_db",
                    "root", "r.masucci");

            if(connection != null){

                System.out.println("*---------- Connection Successfully ----------*\n");

            }

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }


        return connection;

    }
}
