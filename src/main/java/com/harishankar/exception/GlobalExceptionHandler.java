package com.harishankar.exception;

import java.io.FileNotFoundException;
import java.nio.file.AccessDeniedException;
import java.util.LinkedHashMap;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.harishankar.util.CommonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception e) {
		log.info("GlobalExceptionHandler : handleException() : {}",e.getMessage());
		return CommonUtil.errorMessage(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(SuccessException.class)
	public ResponseEntity<?> handleSuccessException(Exception e) {
		log.info("GlobalExceptionHandler : handleSuccessException() : {}",e.getMessage());
		return CommonUtil.successMessage(e.getMessage(), HttpStatus.OK);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> handleIllegalArgumentException(Exception e) {
		log.info("GlobalExceptionHandler : handleIllegalArgumentException() : {}",e.getMessage());

		return CommonUtil.errorMessage(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<?> handleNullPointerException(Exception e) {
		log.info("GlobalExceptionHandler : handleNullPointerException() : {}",e.getMessage());

		return CommonUtil.errorMessage(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ResourcesNotFoundException.class)
	public ResponseEntity<?> handleResourcesNotFound(Exception e) {
		log.info("GlobalExceptionHandler : handleResourcesNotFound() : {}",e.getMessage());

		return CommonUtil.errorMessage(e.getMessage(), HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.info("GlobalExceptionHandler : handleMethodArgumentNotValidException() : {}",e.getMessage());
		List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
		Map<String, Object> error = new LinkedHashMap<>();
		allErrors.stream().forEach(er -> {
			String msg = er.getDefaultMessage();
			String field = ((FieldError) (er)).getField();
			error.put(field, msg);
		});

		return CommonUtil.error(error, HttpStatus.BAD_REQUEST);
	}



	@ExceptionHandler(ExistDataException.class)
	public ResponseEntity<?> handleExistDataException(ExistDataException e) {
		log.info("GlobalExceptionHandler : handleExistDataException() : {}",e.getMessage());
		return CommonUtil.errorMessage(e.getMessage(), HttpStatus.CONFLICT);

	}

	@ExceptionHandler(FileNotFoundException.class)
	public ResponseEntity<?> handleFileNotFoundException(FileNotFoundException e) {
		log.info("GlobalExceptionHandler : handleFileNotFoundException() : {}",e.getMessage());
		return CommonUtil.errorMessage(e.getMessage(), HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		log.info("GlobalExceptionHandler : handleHttpMessageNotReadableException() : {}",e.getMessage());
		return CommonUtil.errorMessage(e.getMessage(), HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException e) {
		log.info("GlobalExceptionHandler : handleAccessDeniedException() : {}",e.getMessage());
		return CommonUtil.errorMessage(e.getMessage(), HttpStatus.FORBIDDEN);

	}


}
