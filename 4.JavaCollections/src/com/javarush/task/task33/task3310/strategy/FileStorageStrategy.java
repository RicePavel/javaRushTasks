package com.javarush.task.task33.task3310.strategy;

import com.javarush.task.task33.task3310.Helper;

public class FileStorageStrategy implements StorageStrategy {

    static final int DEFAULT_INITIAL_CAPACITY = 16;

    static final long DEFAULT_BUCKET_SIZE_LIMIT = 10000;

    FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];

    int size;

    long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;

    long maxBucketSize;

    Entry getEntry(long key) {
        try {
            Entry entry = null;
            for (FileBucket f : table) {
                if (f != null) {
                    entry = f.getEntry();
                    while (entry != null) {
                        if (entry.key == key) {
                            return entry;
                        }
                        entry = entry.next;
                    }
                }
            }
            return entry;
        } catch (Exception e) {
            Helper.printMessage(e.getMessage());
        }
        return null;
    }

    void resize(int newCapacity) {
        FileBucket[] oldTable = table;
        int oldCapacity = oldTable.length;
        FileBucket[] newTable = new FileBucket[newCapacity];
        transfer(newTable);
        table = newTable;
    }

    void transfer(FileBucket[] newTable) {
        FileBucket[] src = table;
        int newCapacity = newTable.length;
        for (int j = 0; j < src.length; j++) {
            FileBucket f = src[j];
            if (f != null) {
                newTable[j] = f;
            }
        }
    }



    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
        Long key = getKey(value);
        return (key != null);
    }

    boolean addEntry(Long key, String value) throws Exception {
        boolean isRecord = false;
        for (int i = 0; i < table.length; i++) {
            FileBucket f = table[i];
            if (f != null && f.getFileSize() > maxBucketSize) {
                continue;
            }
            if (f == null) {
                table[i] = new FileBucket();
                f = table[i];
            }
            Entry newEntry = new Entry(1, key, value, null);
            if (f.getEntry() == null) {
                f.putEntry(newEntry);
                return true;
            } else {
                Entry oldEntry = f.getEntry();
                oldEntry.next = newEntry;
                f.putEntry(oldEntry);
                return true;
            }
        }
        return isRecord;
    }

    @Override
    public void put(Long key, String value) {
        try {
            Entry entry = getEntry(key);
            if (entry == null) {
                boolean result = addEntry(key, value);
                if (!result) {
                    resize(table.length * 2);
                    addEntry(key, value);
                }
            }
        } catch (Exception e) {
            Helper.printMessage(e.getMessage());
        }
    }

    @Override
    public Long getKey(String value) {
        try {
            for (FileBucket f : table) {
                if (f != null) {
                    Entry entry = f.getEntry();
                    if (entry != null && entry.value != null && entry.value.equals(value)) {
                        return entry.key;
                    }
                }
            }
        } catch (Exception e) {
            Helper.printMessage(e.getMessage());
        }
        return null;
    }

    @Override
    public String getValue(Long key) {
        try {
            Entry entry = getEntry(key);
            if (entry != null) {
                return entry.value;
            }
        } catch (Exception e) {
            Helper.printMessage(e.getMessage());
        }
        return null;
    }

    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }
}
