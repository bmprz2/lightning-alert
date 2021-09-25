package com.dtnse;

import com.dtnse.util.TileUtil;
import org.junit.jupiter.api.Test;

class TileUtilTest
{

    @Test
    void latLongToQuadKey()
    {
        final String quadKey = TileUtil.latLonToQuadKey(8.7020156, -12.2736188);
        System.out.println(quadKey);
    }
}