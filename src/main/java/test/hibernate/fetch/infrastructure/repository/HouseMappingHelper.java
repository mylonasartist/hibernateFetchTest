package test.hibernate.fetch.infrastructure.repository;

import test.hibernate.fetch.domain.Floor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class HouseMappingHelper
{
   static House toPersistent(test.hibernate.fetch.domain.House domain)
   {
      House persistent = new House(domain.getName());
      persistent.setAddressLine1(domain.getAddressLine1());
      persistent.setAddressLine2(domain.getAddressLine2());
      persistent.setFloors(toPersistent(domain.getFloors(), persistent));
      return persistent;
   }

   private static Set<test.hibernate.fetch.infrastructure.repository.Floor> toPersistent(
         List<Floor> domain,
         House persistentHouse)
   {
      Set<test.hibernate.fetch.infrastructure.repository.Floor> persistentFloors =
            new HashSet<>(domain.size());
      for (int i = 0; i < domain.size(); i++)
      {
         test.hibernate.fetch.infrastructure.repository.Floor currentFloor =
               new test.hibernate.fetch.infrastructure.repository.Floor(persistentHouse, i);
         currentFloor.setRooms(domain.get(i).getRooms().stream().map(r ->
         {
            Room persistentRoom = new Room(currentFloor);
            persistentRoom.setName(r.getName());
            persistentRoom.setCorners(r.getCorners().stream().map(c ->
            {
               Corner persistentCorner = new Corner(persistentRoom);
               persistentCorner.setName(c.getName());
               return persistentCorner;
            }).collect(Collectors.toSet()));
            return persistentRoom;
         }).collect(Collectors.toSet()));
         persistentFloors.add(currentFloor);
      }
      return persistentFloors;
   }

   public static test.hibernate.fetch.domain.House toDomain(House persistent)
   {
      test.hibernate.fetch.domain.House domain =
            new test.hibernate.fetch.domain.House(persistent.getName());
      domain.setAddressLine1(persistent.getAddressLine1());
      domain.setAddressLine2(persistent.getAddressLine2());
      domain.setFloors(persistent.getFloors().stream().map(f ->
      {
         Floor domainFloor = new Floor();
         domainFloor.setRooms(f.getRooms().stream().map(r ->
         {
            test.hibernate.fetch.domain.Room domainRoom =
                  new test.hibernate.fetch.domain.Room();
            domainRoom.setName(r.getName());
            domainRoom.setCorners(r.getCorners().stream().map(c ->
            {
               test.hibernate.fetch.domain.Corner domainCorner =
                     new test.hibernate.fetch.domain.Corner();
               domainCorner.setName(c.getName());
               return domainCorner;
            }).collect(Collectors.toSet()));
            return domainRoom;
         }).collect(Collectors.toSet()));
         return domainFloor;
      }).collect(Collectors.toList()));
      return domain;
   }
}
