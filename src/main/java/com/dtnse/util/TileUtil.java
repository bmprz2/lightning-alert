package com.dtnse.util;

public class TileUtil
{
    private static final int LEVEL_OF_DETAIL = 12;
    private static final double MIN_LATITUDE = -85.05112878;
    private static final double MAX_LATITUDE = 85.05112878;
    private static final double MIN_LONGITUDE = -180;
    private static final double MAX_LONGITUDE = 180;

    public static String latLonToQuadKey(double lat, double lon) {
        double latitude = clip(lat, MIN_LATITUDE, MAX_LATITUDE);
        double longitude = clip(lon, MIN_LONGITUDE, MAX_LONGITUDE);

        double x = (longitude + 180) / 360;
        double sinLatitude = Math.sin(latitude * Math.PI / 180);
        double y = 0.5 - Math.log((1 + sinLatitude) / (1 - sinLatitude)) / (4 * Math.PI);

        long mapSize = mapSize(LEVEL_OF_DETAIL);
        int tileX = ((int) clip(x * mapSize + 0.5, 0, mapSize - 1)) / 256;
        int tileY = ((int) clip(y * mapSize + 0.5, 0, mapSize - 1)) / 256;

        StringBuilder quadKey = new StringBuilder();
        for (int i = LEVEL_OF_DETAIL; i > 0; i--)
        {
            char digit = '0';
            int mask = 1 << (i - 1);
            if ((tileX & mask) != 0)
            {
                digit++;
            }
            if ((tileY & mask) != 0)
            {
                digit++;
                digit++;
            }
            quadKey.append(digit);
        }
        return quadKey.toString();
    }

    private static double clip(double n, double minValue, double maxValue)
    {
        return Math.min(Math.max(n, minValue), maxValue);
    }

    public static int mapSize(int levelOfDetail)
    {
        return  256 << levelOfDetail;
    }
}
