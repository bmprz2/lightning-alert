package com.dtnse.service;

import com.dtnse.exception.InvalidInputException;
import com.dtnse.model.Asset;
import com.dtnse.util.AlertPrinter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.dtnse.model.FlashType.CLOUD_TO_GROUND;
import static com.dtnse.model.FlashType.HEARTBEAT;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LineProcessorTest
{

    @InjectMocks
    private LineProcessor processor;

    @Mock
    private AssetService service;

    @Spy
    private StrikeParser parser;

    @Test
    void givenValidJsonAndNotHeartbeatAndMatchingAsset_whenProcess_thenPrint() throws InvalidInputException
    {
        String line = createJsonString(CLOUD_TO_GROUND.code());
        String quadKey = "111";
        Asset asset = createAsset(quadKey);
        when(service.get(anyString())).thenReturn(asset);

        processor.process(line);

        assertTrue(AlertPrinter.isPrinted(quadKey));
        verify(parser).parse(line);
        verify(service).get(any());
    }

    @Test
    void givenValidJsonAndNotHeartbeatAndNoMatchingAsset_whenProcess_thenDoNotPrint() throws InvalidInputException
    {
        String line = createJsonString(CLOUD_TO_GROUND.code());
        String quadKey = "111";
        when(service.get(anyString())).thenReturn(null);

        processor.process(line);

        assertFalse(AlertPrinter.isPrinted(quadKey));
        verify(parser).parse(line);
        verify(service).get(any());
    }

    @Test
    void givenHeartbeat_whenProcess_thenDoNotPrint() throws InvalidInputException
    {
        String line = createJsonString(HEARTBEAT.code());

        processor.process(line);

        assertFalse(AlertPrinter.isPrinted("111"));
        verify(parser).parse(line);
        verifyNoInteractions(service);
    }

    private String createJsonString(int flashType)
    {
        return "{\"flashType\":" + flashType + ",\"strikeTime\":1446760902976,\"latitude\":32.9905308,\"longitude\":-98.34038,\"peakAmps\":1700,\"reserved\":\"000\",\"icHeight\":17229,\"receivedTime\":1446760915185,\"numberOfSensors\":14,\"multiplicity\":8}";
    }

    private Asset createAsset(String quadKey)
    {
        Asset asset = new Asset();
        asset.setQuadKey(quadKey);
        asset.setAssetName("North Park");
        asset.setAssetOwner("001");
        return asset;
    }
}