package com.jawbr.dnd5e.exptracker.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@AllArgsConstructor
@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserAccountDeactivatedException extends RuntimeException {
    private String message;
}
