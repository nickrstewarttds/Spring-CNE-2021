package com.qa.cne.service.exceptions;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Coffee does not exist in db!")
public class CoffeeNotFoundException extends EntityNotFoundException {

}
