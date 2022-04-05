package com.example.rest.repositories;

import com.example.rest.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
@Repository
public interface  RoleRepositories  extends JpaRepository<Role,Long> {

    @Modifying
    @Query(value = "select distinct role from Role role where  role.name  in :roles")
    Set<Role> getSetRoles(@Param("roles") Set<String>roles);


}
