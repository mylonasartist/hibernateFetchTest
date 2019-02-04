package test.hibernate.fetch.infrastructure.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import test.hibernate.fetch.domain.House;
import test.hibernate.fetch.domain.IHouseRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository("houseRepository2")
public class HouseRepository2 implements IHouseRepository
{
   private static final String getById =
         "from House h left join fetch h.floors f left join fetch f.rooms r " +
               "left join fetch r.corners where h.name = :name";

   private EntityManager entityManager;

   @Autowired
   public void setEntityManager(EntityManager entityManager)
   {
      this.entityManager = entityManager;
   }

   public void create(House house)
   {
      test.hibernate.fetch.infrastructure.repository.House persistent =
            HouseMappingHelper.toPersistent(house);
      entityManager.persist(persistent);
   }

   public House get(String name)
   {
      Query query = entityManager.createQuery(getById);
      query.setParameter("name", name);
      test.hibernate.fetch.infrastructure.repository.House house =
            (test.hibernate.fetch.infrastructure.repository.House) query.getSingleResult();
      return HouseMappingHelper.toDomain(house);
   }
}
