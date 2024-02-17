package GlobalExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.b10r.redisCache.Dto.ResponseDto;
import com.b10r.redisCache.constant.ResponseStatus;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(Exception.class)
	ResponseEntity<ResponseDto> parentException(Exception e){
		ResponseDto responseDto=new ResponseDto();
		  responseDto.setResponseStatus(ResponseStatus.FAILED.getValue());
	  return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.CONFLICT);
	
	}
}
