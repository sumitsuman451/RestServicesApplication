package com.example.restful.webservices.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubPostRepository extends JpaRepository<SubPost, Long>{

}
