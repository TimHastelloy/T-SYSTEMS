package com.tsystems.javaschool.tasks.subsequence;

import java.util.*;

public class Subsequence implements ISubsequence{

    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
    @SuppressWarnings("rawtypes")
    public boolean find(List x, List y) {
        // TODO: Implement the logic here
        if((x!=null)&&(y!=null))
        {
            y.retainAll(x);
            Set s = new LinkedHashSet(y);
            Object o[] = s.toArray();
            List l = Arrays.asList(o);
            return l.equals(x);
        }
        else throw new IllegalArgumentException();
    }
}
