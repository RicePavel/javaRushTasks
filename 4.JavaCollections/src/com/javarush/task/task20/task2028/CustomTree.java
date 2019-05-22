package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collection;
import java.util.List;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Serializable, Cloneable {

    Entry<String> root;

    public CustomTree() {
        root = new Entry<>("");
    }


    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    public String getParent(String s) {
        Entry<String> entry = getEntry(s, root);
        if (entry != null) {
            return entry.parent.elementName;
        }
        return null;
    }

    public boolean remove(Object o) {
        if (o instanceof String) {
            String name = (String) o;
            Entry<String> entry = getEntry(name, root);
            if (entry != null) {
                Entry<String> parent = entry.parent;
                if (parent.leftChild == entry) {
                    parent.leftChild = null;
                }
                if (parent.rightChild == entry) {
                    parent.rightChild = null;
                }
                entry.parent = null;
                return true;
            }
            return false;
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public boolean add(String s) {
        if (getEntry(s, root) == null) {
            return addEntry(s);
        }
        return false;
    }

    private boolean addEntry(String s) {
        int maxDepth = getMaxDepth(1, root);
        if (maxDepth == 1) {
            return addEntry(1, s, root, 1);
        } else {
            boolean result = addEntry(maxDepth - 1, s, root, 1);
            if (!result) {
                return addEntry(maxDepth, s, root, 1);
            }
        }
        return false;
    }

    private boolean addEntry(int requiredDepth, String name, Entry<String> to, int currentDepth) {
        if (requiredDepth == currentDepth) {
            if (to.leftChild == null) {
                Entry<String> added = new Entry<>(name);
                to.leftChild = added;
                added.parent = to;
                return true;
            }
            if (to.rightChild == null) {
                Entry<String> added = new Entry<>(name);
                to.rightChild = added;
                added.parent = to;
                return true;
            }
            return false;
        }
        if (requiredDepth > currentDepth) {
            if (to.leftChild != null) {
                boolean result = addEntry(requiredDepth, name, to.leftChild, currentDepth + 1);
                if (result) {
                    return true;
                }
            }
            if (to.rightChild != null) {
                boolean result = addEntry(requiredDepth, name, to.rightChild, currentDepth + 1);
                if (result) {
                    return true;
                }
            }
        }
        return false;
    }

    private int getMaxDepth(int level, Entry<String> entry) {
        int maxDepth = level;
        if (entry.leftChild != null) {
            int deep = getMaxDepth(level + 1, entry.leftChild);
            if (deep > maxDepth) {
                maxDepth = deep;
            }
        }
        if (entry.rightChild != null) {
            int deep = getMaxDepth(level + 1, entry.rightChild);
            if (deep > maxDepth) {
                maxDepth = deep;
            }
        }
        return maxDepth;
    }

    private Entry<String> getEntry(String name, Entry<String> entry) {
        if (entry.leftChild != null && entry.leftChild.elementName.equals(name)) {
            return entry.leftChild;
        }
        if (entry.rightChild != null && entry.rightChild.elementName.equals(name)) {
            return entry.rightChild;
        }
        if (entry.leftChild != null) {
            Entry<String> s = getEntry(name, entry.leftChild);
            if (s != null) {
                return s;
            }
        }
        if (entry.rightChild != null) {
            Entry<String> s = getEntry(name, entry.rightChild);
            if (s != null) {
                return s;
            }
        }
        return null;
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return getSize(root) - 1;
    }



    private int getSize(Entry<String> entry) {
        if (entry == null) {
            return 0;
        } else {
            int size = 1;
            size += getSize(entry.leftChild);
            size += getSize(entry.rightChild);
            return size;
        }
    }

    static class Entry<T> implements Serializable {
        String elementName;
        boolean availableToAddLeftChildren;
        boolean availableToAddRightChildren;
        Entry<T> parent;
        Entry<T> leftChild;
        Entry<T> rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }


    }

}
