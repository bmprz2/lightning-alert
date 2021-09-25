package com.dtnse.service;

import com.dtnse.model.Strike;
import com.dtnse.exception.InvalidInputException;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.NoSuchElementException;

import static com.dtnse.exception.ErrorMessage.*;

public class StrikeParser
{

    public StrikeParser()
    {
    }

    public Strike parse(String line) throws InvalidInputException
    {
        Strike strike;
        try
        {
            strike = new Gson().fromJson(line, Strike.class);
            validate(strike);
        }
        catch (JsonSyntaxException e)
        {
            throw new InvalidInputException(INVALID_JSON_FORMAT, line);
        }
        catch (NoSuchElementException e)
        {
            throw new InvalidInputException(e.getMessage(), line);
        }

        return strike;
    }

    private void validate(Strike strike)
    {
        if (strike.getFlashType() == null)
        {
            throw new NoSuchElementException(MISSING_OR_INVALID_FLASH_TYPE);
        }

        if (strike.getLatitude() == null)
        {
            throw new NoSuchElementException(MISSING_LATITUDE);
        }

        if (strike.getLongitude() == null)
        {
            throw new NoSuchElementException(MISSING_LONGITUDE);
        }
    }
}