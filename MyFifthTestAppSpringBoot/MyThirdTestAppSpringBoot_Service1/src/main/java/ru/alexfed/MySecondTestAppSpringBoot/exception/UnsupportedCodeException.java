package ru.alexfed.MySecondTestAppSpringBoot.exception;
public class UnsupportedCodeException extends Exception{
    public UnsupportedCodeException() {
        super("uid = 123");
    }
}
