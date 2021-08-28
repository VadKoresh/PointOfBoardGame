package main.exception;

public class BoardGameAlreadyExistEx extends Exception{
    public BoardGameAlreadyExistEx(String message) {
        super(message);
    }
}
