package uk.ac.ucl;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * This class implements methods common to all concrete set implementations but does not
 * represent a complete set implementation.<br />
 *
 * New set objects are created using a MySetFactory.
 */

public abstract class AbstractMySet<T extends Comparable<T>> implements MySet<T>
{
  @Override
  public boolean equals(MySet<T> aSet) throws MySetException {
    return difference(aSet).size() == 0;
  }

  @Override
  public int hashCode()
  {
    return toList().hashCode();
  }

  public List<T> toList()
  {
    List<T> result = new ArrayList<>();
    // TODO write the code to return a List of the set contents.
    for(T val: this){
      result.add(val);
    }
    return result;
  }

  public MySet<T> union(MySet<T> mySet) throws MySetException
  {
    MySet<T> result = MySetFactory.getInstance().getMySet();
    for (T val:this) {
      result.add(val);
    }
    for (T val:mySet) {
      if(!result.contains(val)){
        result.add(val);
      }
    }
    //TODO write the statements needed to find the union

    return result;
  }

  public MySet<T> intersection(MySet<T> mySet) throws MySetException
  {
    MySet<T> result = MySetFactory.getInstance().getMySet();
    for(T val:this){
      if(mySet.contains(val)){
        result.add(val);
      }
    }
    // TODO write the statements needed to find the intersection.

    return result;
  }

  public MySet<T> difference(MySet<T> mySet) throws MySetException
  {
    MySet<T> result = MySetFactory.getInstance().getMySet();
    for(T val:this){
      if(!mySet.contains(val)){
        result.add(val);
      }
    }
    // TODO write the statements needed to find the difference.
    return result;
  }

  protected void checkSize(int maximumSize)
    throws MySetException
  {
     // TODO throw an exception if the set exceeds its maximum size.
    if(this.size()>maximumSize){
      throw  new MySetException("Exceed maximum size.");
    }
  }

  // A helper method that might be useful.
  private void addAll(MySet<T> source, MySet<T> destination)
    throws MySetException
  {
    for (T value : source)
    {
      destination.add(value);
    }
  }

  public String toString(){
    String result = "{";
    if(this.isEmpty()){
      return result + "}";
    }
    for(T val:this) result = result.concat(val.toString() + ",");
    return result.substring(0, result.length()-1)+"}";
  }

  public void WriteToFile(String FileName) throws IOException {
    FileWriter fw = new FileWriter(FileName);
    String string = this.toString();
    for (int i = 0; i < string.length(); i++) {
      fw.write(string.charAt(i));
    }
    fw.close();
  }

  public void ReadFromFile(String FileName) throws IOException, MySetException {

    String input = new String(Files.readAllBytes(Paths.get(FileName)));
    ArrayList<String> substrings = new ArrayList<>(Arrays.asList(input.substring
            (1,input.length()-1).split(",", -1)));
    if (substrings.get(0).matches("(0|[1-9][0-9]*)")) {
      MySet<Integer> integers = MySetFactory.getInstance().getMySet();
      for (String s : substrings) {
        integers.add(Integer.valueOf(s));
      }
      addAll((MySet<T>) integers,this);
    } else if (substrings.get(0).matches("\\d+(\\.\\d+)")) {
      MySet<Double> doubles = MySetFactory.getInstance().getMySet();
      for (String s : substrings) {
        doubles.add(Double.valueOf(s));
      }
      addAll((MySet<T>) doubles, this);
    } else {
      MySet<String> strings = MySetFactory.getInstance().getMySet();
      for(String s : substrings){
        strings.add(s);
      }
      addAll((MySet<T>)strings,this);
    }
  }
  private static String intToBinary(int decimal, int digits){
    String temp = Integer.toBinaryString(decimal);
    if(digits==0){
      return temp;
    }
    int digits_exist = temp.length();
    String result = temp;
    result = "0".repeat(digits - digits_exist) + result;
    return result;
  }

  // Reference: http://jvalentino.blogspot.com/2007/02/shortcut-to-calculating-power-set-using.html
  // Accessed on: 20 Feb 2020
  public MySet<MySet<T>> powerSet() throws MySetException {
    MySet<MySet<T>> result = new MapMySet<>();
    int num_elements = this.size();
    int num_powersets = (int) Math.pow(2, num_elements);
    for (int i = 0; i < num_powersets; i++) {
      String binary = intToBinary(i, num_elements);
      MySet<T> subset = new MapMySet<>();
      for (int j = 0; j < binary.length(); j++) {
        if (binary.charAt(j) == '1') {
          subset.add(this.toList().get(j));
        }
      }
      result.add(subset);
    }
    return result;
  }
}
