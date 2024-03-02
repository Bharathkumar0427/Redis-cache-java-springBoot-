package com.b10r.redisCache.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.b10r.redisCache.Dto.ResponseDto;
import com.b10r.redisCache.Dto.UserDetailsDto;
import com.b10r.redisCache.constant.ResponseStatus;
import com.b10r.redisCache.entity.UserDetails;
import com.b10r.redisCache.service.RedisService;
import com.b10r.redisCache.serviceImpl.RedisServiceImpl;

@RestController
@RequestMapping("/redis")
public class RedisController {

	private static final Logger log = org.slf4j.LoggerFactory.getLogger(RedisServiceImpl.class);

	@Autowired
	RedisService redisService;

	@PostMapping("/saveUserDetails")
	ResponseEntity<ResponseDto> saveUserDetails(@RequestBody UserDetailsDto userDetailsDto) {

		ResponseDto responseDto = null;
		try {
			responseDto = redisService.createUser(userDetailsDto);
		} catch (Exception e) {
			log.error("EXCEPTION IN saveUserDetails", e);
		}

		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.CREATED);
	}

	@PutMapping("/updateUserDetails")
	ResponseEntity<ResponseDto> updateUserDetails(@RequestBody UserDetailsDto userDetailsDto) throws Exception {

		ResponseDto responseDto = null;
		try {
			responseDto = redisService.updateUser(userDetailsDto);

		} catch (Exception e) {
			log.error("EXCEPTION IN updateUserDetails", e);

		}

		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
	}

	@DeleteMapping("/deleteUserDetailById")
	ResponseEntity<ResponseDto> deleteUserDetails(@RequestParam(name = "id") String id) {

		ResponseDto responseDto = null;
		try {
			redisService.deleteuser(id);
		} catch (Exception e) {
			log.error("EXCEPTION IN deleteUserDetails", e);
		}

		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
	}

	@GetMapping("/getUserById/{id}")
	ResponseEntity<ResponseDto> getUserByid(@PathVariable(name = "id") String id) {

		ResponseDto responseDto = null;
		try {
			responseDto = redisService.getUser(id);
			
		} catch (Exception e) {
			log.error("EXCEPTION IN getUserByid", e);
		}

		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
	}

}
