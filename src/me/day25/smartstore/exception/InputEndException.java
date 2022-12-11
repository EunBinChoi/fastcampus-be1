package me.day25.smartstore.exception;

import me.day25.smartstore.util.Message;

public class InputEndException extends Exception {
    public InputEndException() {
        super(Message.ERR_MSG_INPUT_END);
    }

    public InputEndException(String message) {
        super(message);
    }
}
