package test.hibernate.fetch.domain;

public interface IHouseRepository
{
   void create(House house);
   House get(String id);
}
