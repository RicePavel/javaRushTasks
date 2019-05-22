package com.javarush.task.task39.task3911;

import java.util.*;

public class Software {
    int currentVersion;

    private Map<Integer, String> versionHistoryMap = new LinkedHashMap<>();

    public void addNewVersion(int version, String description) {
        if (version > currentVersion) {
            versionHistoryMap.put(version, description);
            currentVersion = version;
        }
    }

    public int getCurrentVersion() {
        return currentVersion;
    }

    public Map<Integer, String> getVersionHistoryMap() {
        return Collections.unmodifiableMap(versionHistoryMap);
    }

    public boolean rollback(int rollbackVersion) {
        boolean found = false;
        for (Integer version: versionHistoryMap.keySet()) {
            if (version == rollbackVersion) {
                found = true;
                break;
            }
        }
        if (found) {
            currentVersion = rollbackVersion;
            Set<Integer> keys = new HashSet(versionHistoryMap.keySet());
            for (Integer version: keys) {
                if (version > currentVersion) {
                    versionHistoryMap.remove(version);
                }
            }
            return true;
        }
        return false;
    }
}
