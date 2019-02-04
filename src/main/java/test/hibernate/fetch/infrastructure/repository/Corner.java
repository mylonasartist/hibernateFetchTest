package test.hibernate.fetch.infrastructure.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"room_recid", "name"}))
public class Corner
{
   @Id
   @GeneratedValue
   private Long recid;

   private String name;

   @ManyToOne
   private Room room;

   public Corner()
   {
   }

   public Corner(Room room)
   {
      this.room = room;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }


}
