package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAO {
    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return em.createQuery("from User").getResultList();

    }

    @Transactional(readOnly = true)
    public User getUser(int id) {
        return em.find(User.class, id);
    }

    @Transactional
    public void save(User user) {
        em.persist(user);
    }

    @Transactional
    public void update(int id, User updatedUser) {
        User user = em.find(User.class, id);
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        em.merge(user);
    }

    @Transactional
    public void delete(int id) {
        em.remove(em.find(User.class, id));

    }
}
