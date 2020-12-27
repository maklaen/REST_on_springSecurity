package com.example.rest.repository;


import com.example.rest.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @EntityGraph(value = "graph.Profile.roles", type = EntityGraph.EntityGraphType.LOAD)
    Optional<User> findByUsername(String username);

    int countByUsername(String username);

}
