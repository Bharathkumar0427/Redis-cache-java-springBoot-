package com.b10r.redisCache.service;

import com.b10r.redisCache.Dto.ResponseDto;
import com.b10r.redisCache.Dto.UserDetailsDto;
import com.b10r.redisCache.entity.UserDetails;

public interface RedisService {
	
	ResponseDto createUser(UserDetailsDto userDetailsDto);
	
	UserDetails updateUser(UserDetailsDto userDetailsDto);
	void deleteuser(String userId);
	
	UserDetails getUser(String userId);
	
	

}
