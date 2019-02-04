package test.hibernate.fetch.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.hibernate.fetch.domain.House;
import test.hibernate.fetch.domain.IHouseRepository;

@Service("houseService2")
@Transactional
public class HouseService2 implements IHouseService
{
   @Autowired
   @Qualifier("houseRepository2")
   private IHouseRepository repository;

   public void create(House house) {
      repository.create(house);
   }

   public House get(String name)
   {
      return repository.get(name);
   }
}
