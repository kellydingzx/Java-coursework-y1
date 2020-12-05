package uk.ac.ucl;

import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
// reference: https://codepumpkin.com/hashset-internal-implementation/
// Accessed on: 20 Feb 2020

public class MapMySet<T extends Comparable<T>> extends AbstractMySet<T> {
    private HashMap<T,Object> map;
    private static final Object PRESENT = new Object();

    public MapMySet(){
        this.map = new HashMap<T,Object>();
    }

    @Override
    public void add(T value) throws MySetException {
        if(map.put(value,PRESENT) != null){
            throw new MySetException("Element already exist.");
        }
    }

    @Override
    public boolean contains(T value) {
        return map.containsKey(value) ;
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void remove(T value) {
        map.remove(value) ;
    }

    @Override
    public Iterator<T> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int compareTo(MySet<T> o) {
        throw new UnsupportedOperationException();
    }
}
