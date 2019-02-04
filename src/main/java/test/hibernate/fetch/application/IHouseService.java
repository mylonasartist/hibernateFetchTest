package test.hibernate.fetch.application;

import test.hibernate.fetch.domain.House;

public interface IHouseService
{
   void create(House house);
   House get(String name);
}
