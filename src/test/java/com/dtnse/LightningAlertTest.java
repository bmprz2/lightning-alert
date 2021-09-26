package com.dtnse;

import com.dtnse.service.LineProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LightningAlertTest
{
    @InjectMocks
    private LightningAlert lightningAlert;

    @Mock
    private LineProcessor processor;

    @BeforeEach
    void setUp()
    {
        lenient().doNothing().when(processor).process(anyString());
    }

    @Test
    void givenValidFilename_whenRun_thenProcess()
    {
        lightningAlert.run("valid.json");

        verify(processor, times(2)).process(anyString());
    }

    @Test
    void givenNoFilename_whenRun_thenProcessUsingDefaultFilename()
    {
        lightningAlert.run(null);

        verify(processor, times(3)).process(anyString());
    }

    @Test
    void givenInvalidFilename_whenRun_thenWriteError()
    {
        lightningAlert.run("invalid.json");

        verifyNoInteractions(processor);
    }
}