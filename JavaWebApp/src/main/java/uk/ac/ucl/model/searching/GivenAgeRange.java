package uk.ac.ucl.model.searching;

import uk.ac.ucl.model.ColumnTypes.Date;
import uk.ac.ucl.model.dataframes.DataFrame;
import uk.ac.ucl.model.dataframes.FrameException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GivenAgeRange {
    private ArrayList<Date> birthdates;
    private ArrayList<Date> deathdates;
    private ArrayList<Integer> ages;
    private DataFrame dataFrame;

    public GivenAgeRange(DataFrame frame) throws IOException, FrameException {
        dataFrame = frame;
        birthdates = dataFrame.getColumnByName("BIRTHDATE").getAlldata();
        deathdates = dataFrame.getColumnByName("DEATHDATE").getAlldata();
        getAges();
    }

    private void getAges(){
        ages = new ArrayList<>();
        for(int i=0;i<birthdates.size();i++){
            if(!birthdates.get(i).validity()){
                ages.add(100000);
            }else if(deathdates.get(i).validity()){
                ages.add(100000);
            }else{
                ages.add(2020-birthdates.get(i).getDataByLabel("year"));
            }
        }
    }

    public ArrayList<Integer> getGivenAgeIndexes(int lower, int upper){
        return (ArrayList<Integer>) IntStream.range(0,birthdates.size()).boxed()
                .filter(i -> ages.get(i)>= lower && ages.get(i)<=upper)
                .collect(Collectors.toList());
    }

    public ArrayList<Integer> getYoungest(){
        int youngest = 100000;
        for(int age : ages){
            if(age<youngest){
                youngest = age;
            }
        }
        int finalYoungest = youngest;
        return (ArrayList<Integer>) IntStream.range(0,ages.size()).boxed()
                .filter(i -> ages.get(i) == finalYoungest)
                .collect(Collectors.toList());
    }

    public double getAverage(){
        int number_of_valid_ages = 0;
        int sum = 0;
        for(int age: ages){
            if(age != 100000){
                sum += age;
                number_of_valid_ages++;
            }
        }
        return sum/number_of_valid_ages;
    }



}
