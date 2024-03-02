package com.b10r.redisCache.serviceImpl;

import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
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
				boolean userdetailsCheck=ObjectUtils.isNotEmpty(userDetails1);
				if (userdetailsCheck) {
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

	@CachePut(value = "ResponseDto", key = "#userDetailsDto.getId")
	@Override
	public ResponseDto updateUser(UserDetailsDto userDetailsDto) {
		log.info("INTO updateUser");
		ResponseDto responseDto = new ResponseDto();
		try {
			if (ObjectUtils.isNotEmpty(userDetailsDto.getId())) {
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

					
					responseDto.setObj1(convertUserDetailsToDto(userDetailsRepo.save(userDetails1)));
					responseDto.setResponseStatus(ResponseStatus.SUCCESS.getValue());

					return responseDto;
				} else {
					responseDto.setResponseStatus(ResponseStatus.UNKNOWN_ID.getValue());
					return responseDto;
				}
			}
		} catch (Exception e) {
			log.error("EXCEPTION IN updateUser", e);
			responseDto.setResponseStatus(ResponseStatus.FAILED.getValue());
		}
		return responseDto;
	}

	@Override
	@CacheEvict(value = "UserDetails", key = "#userId")
	public void deleteuser(String userId) throws IllegalArgumentException, OptimisticLockingFailureException {
		log.info("INTO deleteuser");
		try {
			if (!userId.isEmpty()) {
				userDetailsRepo.deleteById(Long.valueOf(userId));
			}
		} catch (Exception e) {
			log.error("EXCEPTION IN deleteuser", e);
		}
	}

	@Override
	@Cacheable(value = "ResponseDto", key = "#userId")
	public ResponseDto getUser(String userId) {
		log.info("INTO getUser by iD ");
		ResponseDto responseDto = new ResponseDto();
		try {
			if (ObjectUtils.isNotEmpty(userId)) {
				Optional<UserDetails> userDetails = userDetailsRepo.findById(Long.valueOf(userId));
				if (userDetails.isPresent()) {

					responseDto.setObj1(userDetails.get());
				} else {
					responseDto.setResponseStatus(ResponseStatus.UNKNOWN_ID.getValue());
				}

			} else {
				responseDto.setResponseStatus(ResponseStatus.UNKNOWN_ID.getValue());
			}

		} catch (Exception e) {
			log.error("EXCEPTION IN getUser", e);
		}
		return responseDto;
	}

	@Override
	public UserDetailsDto convertUserDetailsToDto(UserDetails userDetails) {

		try {
			if (ObjectUtils.isNotEmpty(userDetails)) {
				UserDetailsDto userdto = new UserDetailsDto();
				userdto.setAddress(!userDetails.getAddress().isEmpty() ? userDetails.getAddress() : null);
				userdto.setAge(!userDetails.getAge().isEmpty() ? userDetails.getAge() : null);
				userdto.setEmail(!userDetails.getEmail().isEmpty() ? userDetails.getEmail() : null);
				userdto.setId(userDetails.getId() > 0 ? String.valueOf(userDetails.getId()) : null);
				userdto.setPhNumber(!userDetails.getPhNumber().isEmpty() ? userDetails.getPhNumber() : null);
				userdto.setUserName(!userDetails.getUserName().isEmpty() ? userDetails.getUserName() : null);
				return userdto;
			}

		} catch (Exception e) {
			log.error("EXCEPTION IN convertUserDetailsToDto", e);
		}
		return null;
	}

}
