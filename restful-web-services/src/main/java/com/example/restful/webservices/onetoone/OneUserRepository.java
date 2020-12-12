package com.example.restful.webservices.onetoone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OneUserRepository extends JpaRepository<OneUser, Integer>{

}
