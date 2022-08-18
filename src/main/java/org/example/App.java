package org.example;



import java.sql.*;

/**
 * Hello world!
 *
 */
public class App 
{
    static  final String jdbcURL = "jdbc:mysql://localhost:3306/mydb";
    static final String username = "root";
    static final String password = "1234";
    public static void main( String[] args )
    {
        Connection connection;

        try {
            connection = DriverManager.getConnection(jdbcURL, username, password);
            // connection.setAutoCommit(false);

            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO user (name, age) VALUES ('Mike', 12)");
            // statement.execute("UPDATE user SET name = 'Ivan' WHERE  idUser = 1");
            // statement.execute("DELETE FROM user WHERE idUser = 1");

            ResultSet rs = statement.executeQuery("SELECT * FROM user");
            while(rs.next()){

                System.out.println("ID: " + rs.getInt("idUser"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Name: " + rs.getString("name"));
            }
            rs.close();

            String updateName = "UPDATE user SET name = 'Ivan' WHERE  name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateName);
            preparedStatement.setString(1,"Mike");
            preparedStatement.executeUpdate();

            statement.addBatch("INSERT INTO user (name, age) VALUES ('Anna', 19)");
            statement.addBatch("INSERT INTO user (name, age) VALUES ('Lybov', 45)");
            statement.addBatch("INSERT INTO user (name, age) VALUES ('Ruslan', 36)");
            statement.executeBatch();

            ResultSet resultSet = statement.executeQuery("SELECT user.name, user.age FROM user WHERE age = (SELECT max(age) FROM user)");
            while(resultSet.next()){
                System.out.println("name: " + resultSet.getString("name"));
                System.out.println("age: " + resultSet.getInt("age"));
            }
            resultSet.close();



            if(!connection.isClosed()){
                System.out.println( "Connect to db" );
            }

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
