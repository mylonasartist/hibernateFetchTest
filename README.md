# hibernateFetchTest

Here we have a small hierarchy of entities associated by composition:
House has many Floors, Floor has many Rooms, Room has many Corners.

An embedded database HSQLDB is used for testing with HouseServiceTest.

A service and repository allow to create new House and get House by name.

There are 2 implementations of IHouseRepository: HouseRepository1 and HouseRepository2.
The only difference between HouseRepository1 and HouseRepository2 is in the getById query.
HouseRepository1 uses the default fetch strategy (defined on persistent entities mapping, i.e. JPA annotations on test.hibernate.fetch.infrastructure.repository.House, test.hibernate.fetch.infrastructure.repository.Floor, etc.) - lazy select fetching for collections, i.e. database query made when the association is accessed.
Sometimes it is better to override the default fetch strategy, e.g. when we know the extended object graph will be required.
HouseRepository2 overrides the default fetch strategy (select) with "left join fetch" which makes Hibernate to fetch associations eagerly using an outer join.

The generated queries can be seen when running test methods of HouseServiceTest.
HouseServiceTest.getLazy() uses HouseRepository1 - after a sequence of insert commands (House created) there are plenty of select queries that belong to a single call of reading a House by its name.
There is a query for the House, a query for its Floors, and for each of the Floors we read Rooms in a separate query (N+1) problem. Furthermore we read Corners for each of the Rooms in a separate query.
HouseServiceTest.getEager() uses HouseRepository2 - after a sequence of insert commands ((House created)) we have a single query that reads the whole graph using outer joins.

The project can be built with build.bat.

The getLazy() and getEager() test methods can be run separately with testLazy.bat and testEager.bat correspondingly.
These scripts produce testLazyOutput.txt and testEagerOutput.txt reports where the queries can be seen.

Some technical details:

- Order of Floors matters - in domain model House has Floors in a list.
In persistent entities House has java.util.Set of Floor items with @OrderBy annotation.
The Set type for the collection is used to avoid duplications if "left join" will be used.
The correct order is provided by Hibernate's implementation of the Set.

- Besides HQL, fetch strategy can also be overridden with Hibernate Criteria API.

- Eager fetch strategy can also be defined on entities mapping, but it is not always what we need, because sometimes we want the associations to be fetched in a lazy way.
E.g. for some reason (audit trails) we need to read object before deleting it.

