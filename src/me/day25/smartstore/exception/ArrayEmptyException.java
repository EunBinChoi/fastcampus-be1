package me.day25.smartstore.exception;

import me.day25.smartstore.util.Message;

public class ArrayEmptyException extends Exception {

    public ArrayEmptyException() {
        super(Message.ERR_MSG_INVALID_ARR_EMPTY);
    }

    public ArrayEmptyException(String message) {
        super(message);
    }
}
