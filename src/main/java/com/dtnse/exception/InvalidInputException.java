package com.dtnse.exception;

import java.text.MessageFormat;

public class InvalidInputException extends Exception
{
    public InvalidInputException(String message, String line)
    {
        super(formatMessage(message, line));
    }

    public static String formatMessage(String message, String line)
    {
        return MessageFormat.format("[error] {0} {1}", message, line);
    }
}
