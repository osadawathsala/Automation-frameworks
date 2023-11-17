package com.example.springboot.repository;

import com.example.springboot.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Optional<Employee> findByEmail(String email);
    //define custom query using JPQL with index params
    @Query("select e from Employee e where e.firstName = ?1 and e.lastName = ?2")
    Employee findByJPQL(String firstName,String lastName);

    //define custom query using JPQL with name params
    @Query("select e from Employee e where e.firstName = :firstName and e.lastName = :lastName")
    Employee findByJPQLWithNameParams(@Param("firstName") String firstName,@Param("lastName") String lastName);
    //define Native SQL query using index params
    @Query(value = "select * from employees e where e.first_name =?1 and e.last_name =?2",nativeQuery = true)
    Employee findByNativeSQL(String firstName,String lastName);

    //define Native SQL query using name params
    @Query(value = "select * from employees e where e.first_name =:firstName and e.last_name = :lastName",nativeQuery = true)
    Employee findByNativeSQLWithNameParams(@Param("firstName") String firstName,@Param("lastName") String lastName);

}