package com.b10r.redisCache.serviceImpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.b10r.redisCache.Dto.ResponseDto;
import com.b10r.redisCache.Dto.UserDetailsDto;
import com.b10r.redisCache.Repository.UserDetailsRepo;
import com.b10r.redisCache.constant.ResponseStatus;
import com.b10r.redisCache.entity.UserDetails;
import com.b10r.redisCache.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {

	private static final Logger log = org.slf4j.LoggerFactory.getLogger(RedisServiceImpl.class);

	@Autowired
	UserDetailsRepo userDetailsRepo;

	@Override
	public ResponseDto createUser(UserDetailsDto userDetailsDto) {
		log.info("INTO createUser");
		ResponseDto responseDto = new ResponseDto();
		try {
			if (userDetailsDto != null) {
				UserDetails userDetails = new UserDetails();
				userDetails.setAddress(!userDetailsDto.getAddress().isEmpty() ? userDetailsDto.getAddress() : null);
				userDetails.setAge(!userDetailsDto.getAge().isEmpty() ? userDetailsDto.getAge() : null);
				userDetails.setEmail(!userDetailsDto.getEmail().isEmpty() ? userDetailsDto.getEmail() : null);
				userDetails.setPhNumber(!userDetailsDto.getPhNumber().isEmpty() ? userDetailsDto.getPhNumber() : null);
				userDetails.setUserName(!userDetailsDto.getUserName().isEmpty() ? userDetailsDto.getUserName() : null);
				UserDetails userDetails1 = userDetailsRepo.save(userDetails);
				if (userDetails != null) {
					responseDto.setResponseStatus(ResponseStatus.SUCCESS.getValue());
					responseDto.setObj1(userDetails1);
				}
			} else {
				responseDto.setResponseStatus(ResponseStatus.FAILED.getValue());

			}

		} catch (Exception e) {
			log.error("EXCEPTION IN createUserImpl", e);
		}
		return responseDto;
	}

	@CachePut(value = "UserDetails", key = "#userDetailsDto.getId")
	@Override
	public UserDetails updateUser(UserDetailsDto userDetailsDto) {
		log.info("INTO updateUser");
		try {
			if (!userDetailsDto.getId().isEmpty()) {
				Optional<UserDetails> userDetails = userDetailsRepo.findById(Long.valueOf(userDetailsDto.getId()));
				if (userDetails.isPresent()) {
					UserDetails userDetails1 = userDetails.get();
					userDetails1.setAddress(!userDetailsDto.getAddress().isEmpty() ? userDetailsDto.getAddress()
							: userDetails1.getAddress());
					userDetails1.setAge(
							!userDetailsDto.getAge().isEmpty() ? userDetailsDto.getAge() : userDetails1.getAge());
					userDetails1.setEmail(
							!userDetailsDto.getEmail().isEmpty() ? userDetailsDto.getEmail() : userDetails1.getEmail());
					userDetails1.setPhNumber(!userDetailsDto.getPhNumber().isEmpty() ? userDetailsDto.getPhNumber()
							: userDetails1.getPhNumber());
					userDetails1.setUserName(!userDetailsDto.getUserName().isEmpty() ? userDetailsDto.getUserName()
							: userDetails1.getUserName());
					UserDetails Details = userDetailsRepo.save(userDetails1);
					return Details;
				} else {
					return null;
				}
			}
		} catch (Exception e) {
			log.error("EXCEPTION IN updateUser", e);
		}
		return null;
	}

	@Override
	@CacheEvict(value = "UserDetails", allEntries = true)
	public void deleteuser(String userId) throws IllegalArgumentException, OptimisticLockingFailureException {
		log.info("INTO deleteuser");
		try {
			if (!userId.isEmpty()) {
				userDetailsRepo.deleteById(Long.valueOf(userId));
			}
		}
		catch (Exception e) {
			log.error("EXCEPTION IN deleteuser", e);
		}
	}

	@Override
	@Cacheable(value = "UserDetails", key = "#userId")
	public UserDetails getUser(String userId) {
		log.info("INTO getUser by iD ");
		try {
			if (!userId.isEmpty()) {
				Optional<UserDetails> userDetails = userDetailsRepo.findById(Long.valueOf(userId));
				if (userDetails.isPresent()) {
					return userDetails.get();
				} else {
					return null;
				}

			} else {
				return null;
			}

		} catch (Exception e) {
			log.error("EXCEPTION IN getUser", e);
		}
		return null;
	}

}
