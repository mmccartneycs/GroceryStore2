package Service;

import Model.User;
import DAO.UserDAO;

public class UserService{
    private UserDAO userDAO;
    public UserService(){
        userDAO = new UserDAO();
    }


    public User addUser(User user){
        return userDAO.addUser(user);
    }

    public User validateUser(User user){
        return userDAO.validateUser(user);
    }

}