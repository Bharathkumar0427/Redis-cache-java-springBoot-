package com.b10r.redisCache.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.b10r.redisCache.entity.UserDetails;

@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetails, Long> {

}
