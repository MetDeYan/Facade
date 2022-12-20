package com.intexsoft.apifacadeservice.repository;

import com.intexsoft.apifacadeservice.entity.roles.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * The interface role permissions repository.
 */
@Repository
public interface RolesRepository extends JpaRepository<Roles, String> {

    /**
     * Finds all user permissions based on their role.
     *
     * @param userName employee.
     * @return all user permissions based on their role.
     */
    @Query(nativeQuery = true, value = "select r.permissions from roles r " +
            "join employees e on r.id = e.role_id where e.username = :userName")
    String findPermissionsByUserName(@Param("userName") String userName);
}
