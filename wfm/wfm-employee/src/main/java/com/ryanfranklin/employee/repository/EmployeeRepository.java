package com.ryanfranklin.employee.repository;

import com.ryanfranklin.employee.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

//@RepositoryRestResource(collectionResourceRel = "employee", path = "employee")
@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

	@Query("SELECT max(o.updated) FROM Employee o")
	long lastUpdate();

}
