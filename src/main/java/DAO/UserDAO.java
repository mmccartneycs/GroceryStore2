package DAO;

import Model.User;
import Util.ConnectionSingleton;

import java.sql.*;
public class UserDAO{

    public User addUser(User user){
        Connection connection = ConnectionSingleton.getConnection();
        try {
            String sql = "insert into users (email, password) values (?,?); " ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getUserEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                int generated_user_id = pkeyResultSet.getInt("account_id");
                return new User(generated_user_id, user.getUserEmail(), user.getPassword());
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public User validateUser(User user){
        Connection connection = ConnectionSingleton.getConnection();
        try {
            String sql = "select * from users where email = ? and password = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, user.getUserEmail());
            preparedStatement.setString(2, user.getPassword());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User existingUser = new User(rs.getInt("account_id"),
                        rs.getString("email"),
                        rs.getString("password"));
                return existingUser;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    public User getCredentials(int cart_id){

        return null;
    }
}