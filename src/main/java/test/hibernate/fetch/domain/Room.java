package test.hibernate.fetch.domain;

import java.util.Set;

public class Room
{
   private String name;
   private Set<Corner> corners;

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public Set<Corner> getCorners()
   {
      return corners;
   }

   public void setCorners(Set<Corner> corners)
   {
      this.corners = corners;
   }
}
