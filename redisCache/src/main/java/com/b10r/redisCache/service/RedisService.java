package com.b10r.redisCache.service;

import com.b10r.redisCache.Dto.ResponseDto;
import com.b10r.redisCache.Dto.UserDetailsDto;
import com.b10r.redisCache.entity.UserDetails;

public interface RedisService {
	
	ResponseDto createUser(UserDetailsDto userDetailsDto);
	
	ResponseDto updateUser(UserDetailsDto userDetailsDto);
	void deleteuser(String userId);
	
	ResponseDto getUser(String userId);
	
	UserDetailsDto convertUserDetailsToDto(UserDetails userDetails);
	
	

}
