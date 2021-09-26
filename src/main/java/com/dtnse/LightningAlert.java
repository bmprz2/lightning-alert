package com.dtnse;

import com.dtnse.service.AssetService;
import com.dtnse.service.LineProcessor;
import com.dtnse.service.StrikeParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LightningAlert
{
    public static final String DEFAULT_FILENAME = "lightning.json";
    private final LineProcessor lineProcessor;

    public LightningAlert(LineProcessor lineProcessor)
    {
        this.lineProcessor = lineProcessor;
    }

    public static void main(String[] args)
    {
        LightningAlert app = new LightningAlert(new LineProcessor(new AssetService(), new StrikeParser()));
        app.run(args.length > 0 ? args[0] : null);
    }

    public void run(String arg)
    {
        String filename = arg != null ? arg : DEFAULT_FILENAME;
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("input/" + filename);
        if (stream != null)
        {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream)))
            {
                String line;
                while ((line = reader.readLine()) != null)
                {
                    lineProcessor.process(line);
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("[error] unable to open file");
        }
    }

}