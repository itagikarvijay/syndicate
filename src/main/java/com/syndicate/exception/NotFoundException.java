/**
 * 
 */
package com.syndicate.exception;

/**
 * @author User
 *
 */

//@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Party Not found.!")
public class NotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NotFoundException(String message) {
		super(message);
	}


}
