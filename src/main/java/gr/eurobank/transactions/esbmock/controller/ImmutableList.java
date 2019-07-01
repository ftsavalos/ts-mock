package gr.eurobank.transactions.esbmock.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ImmutableList<T extends Object> {

    private static final Object[] EMPTY_ELEMENTDATA = {};

    private int size;
    private T[] elementData;

    public ImmutableList(T[] data) {
        this.elementData = data;
    }

    public ImmutableList(int capacity) {
        List<T> list = new ArrayList<>(capacity);
        this.elementData = (T[]) list.toArray();
    }

    public ImmutableList(T[] data, int capacity) {
        this(data);
        this.size = capacity;
    }

    public int size() {
        return size;
    }

    public ImmutableList<T> set(int index, T element) {
        T[] o = this.elementData;
        o[index] = element;
        return new ImmutableList<>(o);
    }

    public ImmutableList<T> add(T element) {
        List<T> list = new ArrayList<>(Arrays.asList(this.elementData));
        list.add(element);
        size++;
        return new ImmutableList<T>((T[]) list.toArray());
    }

    public void forEach(Consumer<T> c) {
        for (T t : this.elementData) {
            c.accept(t);
        }
    }

}

