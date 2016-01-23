package de.due.ldsa.db;

import java.util.Iterator;

/**
 * Created by  Romina
 */
public class IteratorMerger<T> implements Iterator<T> {
    public IteratorMerger(Iterator<T>[] sources) {
        this.iterators = sources;
        this.currentIter = 0;
    }

    Iterator<T>[] iterators;
    int currentIter;

    @Override
    public boolean hasNext() {
        throw new DbException("not yet implemented.");
    }

    @Override
    public T next() {
        throw new DbException("not yet implemented.");
    }
}
