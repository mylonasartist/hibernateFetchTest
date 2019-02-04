package test.hibernate.fetch.application;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import test.hibernate.fetch.domain.Corner;
import test.hibernate.fetch.domain.Floor;
import test.hibernate.fetch.domain.House;
import test.hibernate.fetch.domain.Room;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-ptl.xml")
public class HouseServiceTest
{
   @Autowired
   @Qualifier("houseService1")
   private IHouseService service1;

   @Autowired
   @Qualifier("houseService2")
   private IHouseService service2;

   @Test
   public void getLazy()
   {
      House house = createHouse("myHouse1");
      service1.create(house);
      House fromDatasource = service1.get(house.getName());
      assertHousesEqual(house, fromDatasource);
   }

   @Test
   public void getEager()
   {
      House house = createHouse("myHouse2");
      service2.create(house);
      House fromDatasource = service2.get(house.getName());
      assertHousesEqual(house, fromDatasource);
   }

   private void assertHousesEqual(House house, House fromDatasource)
   {
      Assert.assertEquals(house.getName(), fromDatasource.getName());
      Assert.assertEquals(house.getFloors().size(), fromDatasource.getFloors().size());
      for (int i = 0; i < house.getFloors().size(); i++)
      {
         Assert.assertEquals(house.getFloors().get(i).getRooms().size(),
               fromDatasource.getFloors().get(i).getRooms().size());
         for (Room room : house.getFloors().get(i).getRooms())
         {
            Assert.assertTrue(fromDatasource.getFloors().get(i).getRooms().stream().anyMatch(r ->
                  r.getName().equals(room.getName())));
         }
      }
   }

   private House createHouse(String name)
   {
      House house = new House(name);
      house.setAddressLine1("Some Country, Some County, Some City");
      house.setAddressLine2("Some Street and Number");
      house.setFloors(createFloors());
      return house;
   }

   private List<Floor> createFloors()
   {
      List<Floor> floors = new ArrayList<>();
      for (int i = 0; i < 3; i++)
      {
         Floor floor = new Floor();
         floor.setRooms(createRooms(i));
         floors.add(floor);
      }
      return floors;
   }

   private Set<Room> createRooms(int floorInex)
   {
      Set<Room> rooms = new HashSet<>();
      for (int i = 0; i < 5; i++)
      {
         Room room = new Room();
         String roomName = "Room " + i + " on Floor " + floorInex;
         room.setName(roomName);
         room.setCorners(createCorners(roomName));
         rooms.add(room);
      }
      return rooms;
   }

   private Set<Corner> createCorners(String roomName)
   {
      Set<Corner> corners = new HashSet<>();
      for (int i = 0; i < 5; i++)
      {
         Corner corner = new Corner();
         corner.setName("Corner " + i + " in Room " + roomName);
         corners.add(corner);
      }
      return corners;
   }
}
