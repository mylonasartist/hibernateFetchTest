package test.hibernate.fetch.infrastructure.repository;

import org.hibernate.annotations.Cascade;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Set;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"house_recid", "indexInHouse"}))
public class Floor
{
   @Id
   @GeneratedValue
   private Long recid;

   @Column(nullable = false)
   private int indexInHouse;

   @OneToMany(cascade = CascadeType.ALL, mappedBy = "floor")
   @Cascade(value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
   private Set<Room> rooms;

   @ManyToOne
   private House house;

   public Floor()
   {
   }

   public Floor(House house, int indexInHouse)
   {
      this.house = house;
      this.indexInHouse = indexInHouse;
   }

   public Set<Room> getRooms()
   {
      return rooms;
   }

   public void setRooms(Set<Room> rooms)
   {
      this.rooms = rooms;
   }
}
