package test.hibernate.fetch.infrastructure.repository;

import org.hibernate.annotations.Cascade;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Set;



@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class House
{
   @Id
   @GeneratedValue
   private Long recid;

   @Column(nullable = false)
   private String name;

   private String addressLine1;
   private String addressLine2;

   @OneToMany(cascade = CascadeType.ALL, mappedBy = "house")
   @OrderBy("indexInHouse asc")
   @Cascade(value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
   private Set<Floor> floors;

   public House()
   {
   }

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

   public Set<Floor> getFloors()
   {
      return floors;
   }

   public void setFloors(Set<Floor> floors)
   {
      this.floors = floors;
   }
}
