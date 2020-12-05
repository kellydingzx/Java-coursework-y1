package uk.ac.ucl.model.searching;

import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;
import uk.ac.ucl.model.dataframes.FrameException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ReformSearchResults {
    private ArrayList<SearchResult> results;
    private HashMap<String,String> reformed_result;
    private Model model;

    public ReformSearchResults(ArrayList<SearchResult> results) throws FrameException, IOException {
        model = ModelFactory.getModel();
        reformed_result = new HashMap<>();
        this.results = results;
        reform();
    }

    private void reform() throws FrameException {
        HashMap<String,ArrayList<String>> partially_reformed = new HashMap<>();
        for(SearchResult result: results){
            ArrayList<Integer> indexes = result.getIndexes();
            String category = result.getColName();
            for(Integer index:indexes){
                String id = model.getIDbyIndex(index);
                if(partially_reformed.containsKey(id)){
                    ArrayList<String> categories_old = partially_reformed.get(id);
                    categories_old.add(category);
                    partially_reformed.replace(id,categories_old);
                }else{
                    partially_reformed.put(id, new ArrayList<>(Arrays.asList(category)));
                }
            }
        }
        for(Map.Entry entry: partially_reformed.entrySet()){
            ArrayList<String> categories = (ArrayList<String>) entry.getValue();
            String result = "Found at Column(s): " + categories.stream().collect(Collectors.joining(","));
            reformed_result.put((String) entry.getKey(), result);
        }
    }

    public HashMap<String,String> getReformed_result(){
        return reformed_result;
    }
}
