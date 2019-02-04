package test.hibernate.fetch.domain;

import java.util.Set;

public class Floor
{
   private Set<Room> rooms;

   public Set<Room> getRooms()
   {
      return rooms;
   }

   public void setRooms(Set<Room> rooms)
   {
      this.rooms = rooms;
   }
}
