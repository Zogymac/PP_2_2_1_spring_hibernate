package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user, Car car) {
      sessionFactory.getCurrentSession().save(user);
      sessionFactory.getCurrentSession().save(car);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession()
              .createQuery("select u from User u join u.car");
      return query.getResultList();
   }

   @Override
   @Transactional
   public User getUserByCar(String model, int series) {
      String hql = ("select u from User u join u.car where u.car.model = :model" +
                           " and u.car.series = :series");
      Query query = sessionFactory.getCurrentSession().createQuery(hql);

      query.setParameter("model", model);
      query.setParameter("series", series);

      return (User) query.getResultList().get(0);
   }



}
