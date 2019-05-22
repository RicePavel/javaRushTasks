package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {

    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        ids.clear();
        long ms1 = new Date().getTime();
        for (String s: strings) {
            ids.add(shortener.getId(s));
        }
        long ms2 = new Date().getTime();
        return ms2 - ms1;
    }

    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        strings.clear();
        long ms1 = new Date().getTime();
        for (Long id: ids) {
            strings.add(shortener.getString(id));
        }
        long ms2 = new Date().getTime();
        return ms2 - ms1;
    }

    @Test
    public void testHashMapStorage() {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());
        Set<String> origStrings = new HashSet<>();
        for (int i = 1; i <= 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }
        Set<Long> ids = new HashSet<>();
        long ms1 = getTimeToGetIds(shortener1, origStrings, ids);
        long ms2 = getTimeToGetIds(shortener2, origStrings, ids);
        Assert.assertTrue((ms1 > ms2));
        ms1 = getTimeToGetStrings(shortener1, ids, origStrings);
        ms2 = getTimeToGetStrings(shortener2, ids, origStrings);
        Assert.assertEquals(ms1, ms2, 30);
    }

}
