/**
 * 
 */
package com.syndicate.master.user;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author User
 *
 */
@ControllerAdvice
public class UserExceptions extends ResponseEntityExceptionHandler {

//	@ExceptionHandler({ AuthenticationException.class })
//	public ResponseEntity<ErrorDetails> handleBadCredentialException(BadCredentialsException ex, WebRequest request) {
//		System.out.println("Bad credentials");
//		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),"Bad credentials.!");
//		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
//	}
}
