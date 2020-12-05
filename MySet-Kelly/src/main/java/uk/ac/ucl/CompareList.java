package uk.ac.ucl;

import java.util.Comparator;

public class CompareList implements Comparator<MySet> {

    @Override
    public int compare(MySet o1, MySet o2) {
        try {
            if(o1.difference(o2).size()==0 && o2.difference(o1).size() == 0){
                return 0;
            }
        } catch (MySetException e) {
            e.printStackTrace();
        }
        if(o1.size()>o2.size()){
            return 1;
        }
        return -1;
    }
}
