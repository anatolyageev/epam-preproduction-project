package com.epam.anatolii.ageev.exeptions;

public class NoSuchUserException extends RuntimeException{
public NoSuchUserException(){
    super("User with such login does not exist.");
}
}
