package com.dtnse.service;

import com.dtnse.model.FlashType;
import com.dtnse.model.Strike;
import com.dtnse.exception.InvalidInputException;
import com.dtnse.util.AlertPrinter;
import com.dtnse.util.TileUtil;

import java.util.Optional;

public class LineProcessor
{
    private final AssetService assetService;
    private final StrikeParser jsonParser;

    public LineProcessor(AssetService assetService, StrikeParser jsonParser)
    {
        this.assetService = assetService;
        this.jsonParser = jsonParser;
    }

    public void process(String line)
    {
        try
        {
            final Strike strike = jsonParser.parse(line);
            if (isNotHeartbeat(strike))
            {
                final String quadKey = TileUtil.latLonToQuadKey(strike.getLatitude(), strike.getLongitude());
                Optional.ofNullable(assetService.get(quadKey))
                        .ifPresent(AlertPrinter::print);
            }
        }
        catch (InvalidInputException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private boolean isNotHeartbeat(Strike strike)
    {
        return !FlashType.HEARTBEAT.equals(strike.getFlashType());
    }
}