package test.hibernate.fetch.domain;

import java.util.List;

public class House
{
   private String name;
   private String addressLine1;
   private String addressLine2;
   private List<Floor> floors;

   public House(String name)
   {
      this.name = name;
   }

   public String getName()
   {
      return name;
   }

   public String getAddressLine1()
   {
      return addressLine1;
   }

   public void setAddressLine1(String addressLine1)
   {
      this.addressLine1 = addressLine1;
   }

   public String getAddressLine2()
   {
      return addressLine2;
   }

   public void setAddressLine2(String addressLine2)
   {
      this.addressLine2 = addressLine2;
   }

   public List<Floor> getFloors()
   {
      return floors;
   }

   public void setFloors(List<Floor> floors)
   {
      this.floors = floors;
   }
}
