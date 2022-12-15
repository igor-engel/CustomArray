package org.example;

import java.util.Collection;

public class CustomArrayImpl<T> implements CustomArray {
    private T[] data;
    private int dataSize;

    public CustomArrayImpl() {
        this.data = (T[]) new Object[10];
        this.dataSize = 0;
    }

    @Override
    public int size() {
        return dataSize;
    }

    @Override
    public boolean isEmpty() {
        System.out.println(size());
        return dataSize == 0;
    }

    @Override
    public boolean add(Object value) {

        ensureCapacity(1);

        data[dataSize] = (T) value;
        dataSize = dataSize + 1;
        return true;
    }


    @Override
    public boolean addAll(Object[] items) {
        if (items == null) {
            return false;
        }

        for (Object item : items) {
            add(item);
        }

        return true;
    }

    @Override
    public boolean addAll(Collection items) {
        if (items == null) {
            return false;
        }

        for (Object item : items) {
            add(item);
        }

        return true;
    }

    @Override
    public boolean addAll(int index, Object[] items) {
        if (items == null) {
            return false;
        }

        if (index > dataSize || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Индекс не существует в массиве.");
        }

        ensureCapacity(items.length);

        if (index == dataSize) {
            addAll(items);
            return true;
        }

        int newDataSize = dataSize + items.length;

        T[] buffer = (T[]) new Object[newDataSize];

        for (int i = 0; i < index; i++) {
            buffer[i] = data[i];
        }

        for (int i = 0; i < items.length; i++) {
            buffer[i + index] = (T) items[i];
        }

        for (int i = index; i < dataSize; i++) {
            buffer[i + items.length] = data[i];
        }

        dataSize = newDataSize;
        data = buffer;

        return true;
    }

    @Override
    public Object get(int index) {
        if (index >= dataSize || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Индекс не существует в массиве.");
        }

        return data[index];
    }

    @Override
    public Object set(int index, Object item) {
        if (index >= dataSize || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Индекс не существует в массиве.");
        }

        data[index] = (T) item;

        return true;
    }

    @Override
    public void remove(int index) {
        if (index >= dataSize || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Индекс не существует в массиве.");
        }

        for (int i = index; i < dataSize; i++) {
            if (i == dataSize - 1) {
                dataSize--;
                return;
            }

            data[i] = data[i + 1];
        }

        dataSize--;
    }

    @Override
    public boolean remove(Object item) {
        for (int i = 0; i < dataSize; i++) {
            if (data[i].equals(item)) {
                for (int j = i; j < dataSize; j++) {
                    if (j == dataSize - 1) {
                        dataSize--;
                        return true;
                    }

                    data[j] = data[j + 1];
                }
            }
        }

        return false;
    }

    @Override
    public boolean contains(Object item) {
        for (int i = 0; i < dataSize; i++) {
            if (data[i].equals(item)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int indexOf(Object item) {
        for (int i = 0; i < dataSize; i++) {
            if (data[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void ensureCapacity(int newElementsCount) {
        int totalElements = dataSize + newElementsCount;

        if (totalElements <= data.length) {
            return;
        }

        int newCapacity = 2 * data.length;

        if (totalElements > newCapacity) {
            newCapacity = totalElements;
        }

        T[] newData = (T[]) new Object[newCapacity];
        for (int i = 0; i < dataSize; ++i) {
            newData[i] = data[i];
        }
        data = newData;
    }

    @Override
    public int getCapacity() {
        return data.length;
    }

    @Override
    public void reverse() {
        Object[] newData = new Object[size()];

        for (int i = 0; i < newData.length; i++) {
            newData[i] = data[i];
        }

        data = (T[]) newData;

        int left = 0;
        int right = size() - 1;

        while (left < right) {
            Object temp = data[left];
            data[left] = data[right];
            data[right] = (T) temp;

            left++;
            right--;
        }
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[dataSize];

        for (int i = 0; i < dataSize; i++) {
            result[i] = data[i];
        }

        return result;
    }

    @Override
    public String toString() {

        StringBuilder cb = new StringBuilder();

        cb.append("[ ");
        for (int i = 0; i < dataSize; ++i) {
            T value = data[i];
            cb.append(value);
            cb.append(" ");
        }

        cb.append("]");
        return cb.toString();
    }
}
