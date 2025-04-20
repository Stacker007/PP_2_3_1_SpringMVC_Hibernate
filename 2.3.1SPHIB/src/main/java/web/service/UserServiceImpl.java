package web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDAO;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO<User> userDAO;

    public UserServiceImpl(UserDAO<User> userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDAO.getUsers();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(int id) {
        return userDAO.getUser(id);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userDAO.save(user);
    }

    @Override
    @Transactional
    public void updateUser(int id, User updatedUser) {
        userDAO.update(id, updatedUser);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userDAO.delete(id);
    }
}