package test.hibernate.fetch.infrastructure.repository;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Set;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"floor_recid", "name"}))
public class Room
{
   @Id
   @GeneratedValue
   private Long recid;

   private String name;

   @OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
   private Set<Corner> corners;

   @ManyToOne
   private Floor floor;

   public Room()
   {
   }

   public Room(Floor floor)
   {
      this.floor = floor;
   }

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
