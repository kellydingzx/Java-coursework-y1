package uk.ac.ucl;

import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * Example code illustrating the basic use of MySet objects.
 * Modify and extend this as you work on the questions.
 * Keep this class organised and well-written!
 */
public class Main
{
  private MySetFactory factory = MySetFactory.getInstance();

  // This print method gets you started with the ability to display a set contents.
  // It should be replaced by using the overridden toString method, once you have that working.
  public <T extends Comparable<T>> void printSet(MySet<T> set)
  {
    boolean first = true;
    System.out.print("{");
    for (T value : set) {
      if (!first) { System.out.print(" , "); }
      first = false;
      System.out.print(value);
    }
    System.out.println("}");
  }

  // There are better ways of creating a comma separated list, here is another example.
  public <T extends Comparable<T>> void printSet2(MySet<T> set)
  {
    StringJoiner joiner = new StringJoiner(", ");
    for (T value : set) {
      joiner.add(value.toString());
    }
    System.out.println(joiner);
  }

  // Or you can use streams.
  public <T extends Comparable<T>> void printSet3(MySet<T> set)
  {
    System.out.println(set.toList().stream().map(Object::toString).collect(Collectors.joining(", ")));
  }

  public <T extends Comparable<T>> void print(MySet<T> set)
  {
    printSet3(set);
    // The statement below will work when toString is overridden.
    // System.out.println(set);
  }

  public  <T extends Comparable<T>> void checkOperations(MySet<T> set1, MySet<T> set2) throws MySetException
  {
    System.out.print("Set1: ");
    print(set1);
    System.out.print("Set2: ");
    print(set2);
    System.out.print("Union: ");
    print(set1.union(set2));
    System.out.print("Intersection: ");
    print(set1.intersection(set2));
    System.out.print("Difference: ");
    print(set1.difference(set2));
  }

  public void checkIntSets()
  {
    MySet<Integer> set1;
    MySet<Integer> set2;
    try
    {
      set1 = factory.getMySet();
      set1.add(1);
      set1.add(2);
      set1.add(3);
      set2 = factory.getMySet();
      set2.add(2);
      set2.add(3);
      set2.add(4);
      checkOperations(set1, set2);
    }
    catch (MySetException e)
    {
      System.out.println("====> MySet Exception thrown...");
    }
  }

  public void checkStringSets()
  {
    MySet<String> set1;
    MySet<String> set2;
    try
    {
      set1 = factory.getMySet();
      set1.add("One");
      set1.add("Two");
      set1.add("Three");
      set2 = factory.getMySet();
      set2.add("Two");
      set2.add("Three");
      set2.add("Four");
      checkOperations(set1, set2);
    }
    catch (MySetException e)
    {
      System.out.println("====> MySet Exception thrown...");
    }
  }


  public void go()
  {
    factory.setClassName("ArrayMySet");

    checkIntSets();
    checkStringSets();
  }

  public static void main(String[] args)
  {
    new Main().go();
  }
}
