package de.due.ldsa.db;

import java.util.Iterator;
import java.util.NoSuchElementException;

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
        if (!iterators[currentIter].hasNext()) {
            if (iterators.length == currentIter) {
                //last iterator reached.
                return false;
            }
            //current iterator reached end, check next.
            int checkIter = currentIter;
            while (true) {
                checkIter++;
                if (checkIter == iterators.length) return false;
                if (iterators[checkIter].hasNext()) return true;
                if (checkIter == iterators.length) return false;
            }
        }
        return true;
    }

    @Override
    public T next() {
        if (!iterators[currentIter].hasNext()) {
            if (iterators.length == currentIter) {
                //last iterator reached.
                throw new NoSuchElementException();
            }
            //current iterator reached end, get next one.
            currentIter++;
            return next();
        }
        return iterators[currentIter].next();
    }
}
