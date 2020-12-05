package uk.ac.ucl.model;

import uk.ac.ucl.model.ColumnTypes.*;
import uk.ac.ucl.model.dataframes.DataFrame;
import uk.ac.ucl.model.dataframes.FrameException;
import uk.ac.ucl.model.searching.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Model
{
  // The example code in this class should be replaced by your Model class code.
  // The data should be stored in a DataFrame.
  private DataFrame dataframe;
  private Search searching;
  private GivenAgeRange ageRange;

  public List<String> getPatientNames() throws FrameException {
    List<String> names = new ArrayList<>();
    MyDataColumn name_col = dataframe.getColumnByName("FIRST");
    for(int i=0;i<name_col.getSize();i++){
      names.add((String)name_col.getRowValue(i));
    }
    return names;
  }

  public void readFile(File file) throws IOException, FrameException {
    BufferedReader csvReader = new BufferedReader(new FileReader(file.getAbsolutePath()));
    String row;
    boolean firstRow = false;
    List<String> firstRowNames = new ArrayList<>();

    while((row =csvReader.readLine())!=null) {
      List<String> data = Arrays.asList(row.split(","));
      // Manipulate the first row
      if(!firstRow){
        firstRowNames = data;
        dataframe = new DataFrame();
        for(String str: data){
          dataframe.addColumn(MyColumnFactory.getInstance().getMyColumn(str.toUpperCase()));
        }
        firstRow = true;
      }else { // Manipulate the rest
        for(int i=0;i<firstRowNames.size();i++){
          try {
            dataframe.addValue(firstRowNames.get(i), data.get(i));
          }catch (ArrayIndexOutOfBoundsException | FrameException e){
            dataframe.addValue(firstRowNames.get(i), "");
          }
        }
      }

    }
    csvReader.close();
    ageRange = new GivenAgeRange(dataframe);
  }

  // This also returns dummy data. The real version should use the keyword parameter to search
  // the patient data and return a list of matching items.
  public HashMap<String, String> searchFor(String keyword) throws FrameException, IOException {
    searching = new Search(dataframe);
    ArrayList<SearchResult> results = searching.searchString(keyword);
    return new ReformSearchResults(results).getReformed_result();
  }

  public String getPatientDetail(String colName, int index) throws FrameException {
    return dataframe.getValue(colName,index);
  }

  public ArrayList<String> getAspects(){
    return dataframe.getColumnNames();
  }

  public HashMap<Character,ArrayList<String>> getPartitionResult(String label) throws FrameException {
    return new PartitionByLetter(dataframe.getColumnByName(label).getAlldata()).getResult();
  }

  public int getRowIndexbyAspect(String aspect, String data) throws FrameException {
    return dataframe.getColumnByName(aspect).getIndexbyData(data);
  }

  public String getIDbyIndex(int index) throws FrameException {
    return dataframe.getColumnByName("ID").getRowValue(index).toString();
  }

  public ArrayList<String> getGivenAgeRangePatients(int lower, int upper) throws FrameException, IOException {
    ArrayList<Integer> indexes = ageRange.getGivenAgeIndexes(lower,upper);
    ArrayList<String> ids = new ArrayList<>();
    for(Integer index: indexes){
      ids.add(getIDbyIndex(index));
    }
    return ids;
  }

  public HashMap<String,String> multipleFieldSearchResult(ArrayList<String> colNames, ArrayList<String> keywords) throws FrameException, IOException {
    ArrayList<SearchResult> results = new ArrayList<>();
    for(String colname:colNames) {
      for (String keyword : keywords) {
        results.add(dataframe.getColumnByName(colname).searchThroughCol(keyword));
      }
    }
    return new ReformSearchResults(results).getReformed_result();
  }

  public ArrayList<String> getOldest() throws FrameException {
    searching = new Search(dataframe);
    return indexesToIDs(searching.getOldestPerson());
  }

  public ArrayList<String> getYoungest(){
    return indexesToIDs(ageRange.getYoungest());
  }

  private ArrayList<String> indexesToIDs(ArrayList<Integer> indexes){
    return (ArrayList<String>) indexes.stream()
            .map(p -> {
              String return_str = null;
              try {
                return_str = getIDbyIndex(p);
              } catch (FrameException e) {
                e.printStackTrace();
              }
              return return_str;
            })
            .collect(Collectors.toList());
  }

  public double getAverageAge(){
    return ageRange.getAverage();
  }

  public ArrayList<String> getAllAgeRange() throws FrameException, IOException {
    ArrayList<String> results = new ArrayList<>();
    for(int i=0;i<=120;i+=10){
      results.add("Number of patients of age between " +i + " and " + (i+10) + ": " + getGivenAgeRangePatients(i,i+10).size());
    }
    return results;
  }






}
