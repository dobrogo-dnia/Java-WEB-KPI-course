package org.example.javawebkpicourse.service.exception;

public class CatTravellerNotFoundException extends RuntimeException {

    private static final String TRAVELLER_NOT_FOUND_MESSAGE = "Cat traveller with id %s not found :(";

    public CatTravellerNotFoundException(Long travellerId) {
        super(String.format(TRAVELLER_NOT_FOUND_MESSAGE, travellerId));
    }

}
