package uk.ac.ucl.model.searching;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class PartitionByLetter {
    private HashMap<Character,ArrayList<String>> partition_results;
    private ArrayList<String> data;

    public PartitionByLetter(ArrayList<String> data){
        this.data = data;
        partition_results = new HashMap<>();
        for(char x='A';x<='Z';x++){
            if(partition(x).size()!=0){
                partition_results.put(x,partition(x));
            }
        }
    }

    private ArrayList<String> partition(Character start){
        return (ArrayList<String>)
                data.stream().filter(p -> p.charAt(0) == start)
                .collect(Collectors.toList());
    }

    public HashMap<Character, ArrayList<String>> getResult(){
        return partition_results;
    }


}
