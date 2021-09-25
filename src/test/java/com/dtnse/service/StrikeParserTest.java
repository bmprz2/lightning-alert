package com.dtnse.service;

import com.dtnse.exception.InvalidInputException;
import com.dtnse.model.FlashType;
import com.dtnse.model.Strike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.dtnse.exception.ErrorMessage.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StrikeParserTest
{

    private StrikeParser parser;

    @BeforeEach
    void setUp()
    {
        parser = new StrikeParser();
    }

    @Test
    void givenValidJson_whenParse_thenReturnStrike() throws InvalidInputException
    {
        String line = "{\"flashType\":1,\"strikeTime\":1446760902510,\"latitude\":8.7020156,\"longitude\":-12.2736188,\"peakAmps\":3034,\"reserved\":\"000\",\"icHeight\":11829,\"receivedTime\":1446760915181,\"numberOfSensors\":6,\"multiplicity\":1}";

        final Strike strike = parser.parse(line);

        assertEquals(FlashType.CLOUD_TO_CLOUD, strike.getFlashType());
        assertEquals(1446760902510L, strike.getStrikeTime());
        assertEquals(8.7020156D, strike.getLatitude());
        assertEquals(-12.2736188D, strike.getLongitude());
        assertEquals(3034, strike.getPeakAmps());
        assertEquals("000",  strike.getReserved());
        assertEquals(11829, strike.getIcHeight());
        assertEquals(1446760915181L, strike.getReceivedTime());
        assertEquals(1, strike.getMultiplicity());
    }

    @Test
    void givenJsonWithInvalidFlashType_whenParse_thenThrowInvalidInputException()
    {
        String line = "{\"flashType\":2,\"strikeTime\":1446760902510,\"latitude\":8.7020156,\"longitude\":-12.2736188,\"peakAmps\":3034,\"reserved\":\"000\",\"icHeight\":11829,\"receivedTime\":1446760915181,\"numberOfSensors\":6,\"multiplicity\":1}";

        Exception exception = assertThrows(InvalidInputException.class, () -> parser.parse(line));

        assertEquals(InvalidInputException.formatMessage(MISSING_OR_INVALID_FLASH_TYPE, line), exception.getMessage());
    }

    @Test
    void givenJsonWithMissingFlashType_whenParse_thenThrowInvalidInputException()
    {
        String line = "{\"strikeTime\":1446760902380,\"latitude\":10.5799716,\"longitude\":-14.0589797,\"peakAmps\":3117,\"reserved\":\"000\",\"icHeight\":18831,\"receivedTime\":1446760915182,\"numberOfSensors\":8,\"multiplicity\":1}";

        Exception exception = assertThrows(InvalidInputException.class, () -> parser.parse(line));

        assertEquals(InvalidInputException.formatMessage(MISSING_OR_INVALID_FLASH_TYPE, line), exception.getMessage());
    }

    @Test
    void givenJsonWithMissingLatitude_whenParse_thenThrowInvalidInputException()
    {
        String line = "{\"flashType\":1,\"strikeTime\":1446760902523,longitude\":-12.2895479,\"peakAmps\":3501,\"reserved\":\"000\",\"icHeight\":15392,\"receivedTime\":1446760915182,\"numberOfSensors\":7,\"multiplicity\":4}";

        Exception exception = assertThrows(InvalidInputException.class, () -> parser.parse(line));

        assertEquals(InvalidInputException.formatMessage(MISSING_LATITUDE, line), exception.getMessage());
    }

    @Test
    void givenJsonWithMissingLongitude_whenParse_thenThrowInvalidInputException()
    {
        String line = "{\"flashType\":1,\"strikeTime\":1446760902510,\"latitude\":8.7020156,\"peakAmps\":3034,\"reserved\":\"000\",\"icHeight\":11829,\"receivedTime\":1446760915181,\"numberOfSensors\":6,\"multiplicity\":1}";

        Exception exception = assertThrows(InvalidInputException.class, () -> parser.parse(line));

        assertEquals(InvalidInputException.formatMessage(MISSING_LONGITUDE, line), exception.getMessage());
    }


}