package web.dao;

import java.util.List;

public interface UserDAO<T> {
    List<T> getUsers();
    T getUser(int id);
    void save(T entity);
    void update(int id, T updatedEntity);
    void delete(int id);
}