package com.test.exceptions;

import org.openqa.selenium.NotFoundException;

public class NotSuchVacancy extends NotFoundException {
    public NotSuchVacancy(String message) {
        super(message);
    }
}
