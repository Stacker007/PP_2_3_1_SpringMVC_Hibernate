package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO<User> {
    @PersistenceContext
    private EntityManager em;

    public List<User> getUsers() {
        return em.createQuery("from User", User.class).getResultList();
    }

    public User getUser(int id) {
        return em.find(User.class, id);
    }

    public void save(User user) {
        em.persist(user);
    }

    public void update(int id, User updatedUser) {
        User user = em.find(User.class, id);
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        em.merge(user);
    }

    public void delete(int id) {
        em.remove(em.find(User.class, id));
    }
}