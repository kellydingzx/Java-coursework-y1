package ucl.ac.uk;

import uk.ac.ucl.*;

import org.junit.Before;
import org.junit.Test;
import uk.ac.ucl.MySetException;

import java.io.IOException;
import java.util.Iterator;

import static org.junit.Assert.*;

public class MapTest {
    private MySet<Integer> empty;
    private MySet<Integer> one;
    private MySet<Integer> several;

    private MySetFactory factory = MySetFactory.getInstance();

    @Before
    public void setUp() throws MySetException {
        factory.setClassName("MapMySet");
        empty = new MapMySet<>();
        one = new MapMySet<>();
        one.add(0);
        several = new MapMySet<>();
        several.add(0);
        several.add(1);
        several.add(2);
    }

    @Test
    public void testContainsEmptyList()
    {
        assertFalse(empty.contains(0)) ;
    }

    @Test
    public void testContainsOne()
    {
        assertTrue(one.contains(0));
    }

    @Test
    public void testContainsSeveral()
    {
        assertTrue(several.contains(0));
        assertTrue(several.contains(1));
        assertTrue(several.contains(2));
    }

    @Test
    public void testIsEmptyEmptyList()
    {
        assertTrue(empty.isEmpty()) ;
    }

    @Test
    public void testIsEmptyOne()
    {
        assertFalse(one.isEmpty());
    }

    @Test
    public void testIsEmptySeveral()
    {
        assertFalse(several.isEmpty());
    }

    @Test
    public void testSizeEmpty(){
        assertEquals(java.util.Optional.of(Integer.valueOf(0)), java.util.Optional.of(empty.size())) ;
    }

    @Test
    public void testSizeOne(){
        assertEquals(java.util.Optional.of(Integer.valueOf(1)), java.util.Optional.of(one.size())) ;
    }

    @Test
    public void testSizeSeveral(){
        assertEquals(java.util.Optional.of(Integer.valueOf(3)), java.util.Optional.of(several.size())) ;
    }

    @Test
    public void testOneBecomesEmpty(){
        assertFalse(one.isEmpty());
        one.remove(0);
        assertTrue(one.isEmpty());
    }

    @Test
    public void testSeveralBecomesEmpty(){
        assertFalse(one.isEmpty());
        several.remove(0);
        several.remove(1);
        several.remove(2);
        assertTrue(several.isEmpty());
    }

    @Test
    public void testEmptyIteratorHasNext(){
        assertFalse(empty.iterator().hasNext());
    }

    @Test
    public void testOneIteratorHasNext(){
        Iterator<Integer> iterator = one.iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testSeveralIteratorHasNext(){
        Iterator<Integer> iterator = several.iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        iterator.next();
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testPowerSetEmpty() throws MySetException {
        MySet<MySet<Integer>> powerSets = empty.powerSet();
        assertEquals(1,powerSets.size());
    }

    @Test
    public void testPowerSetOne() throws MySetException {
        MySet<MySet<Integer>> powerSets = one.powerSet();
        assertEquals(2,powerSets.size());
    }

    @Test
    public void testPowerSetSeveral() throws MySetException {
        MySet<MySet<Integer>> powerSets = several.powerSet();
        assertEquals(8,powerSets.size());
    }

    @Test
    public void testEquals() throws MySetException {
        assertFalse(one.equals(empty));
    }

    @Test
    public void testReadFromFile() throws MySetException, IOException {
        MySet<Integer> four = factory.getMySet();
        four.add(1);
        four.add(2);
        four.add(3);
        four.add(4);
        MySet<Integer> read = factory.getMySet();
        read.ReadFromFile("test-in-1.txt");
        assertTrue(four.equals(read));
    }

    @Test
    public void testReadAndWrite1() throws MySetException, IOException {
        several.WriteToFile("test-out-1.txt");
        MySet<Integer> read = factory.getMySet();
        read.ReadFromFile("test-out-1.txt");
        assertTrue(several.equals(read));
    }

    @Test
    public void testReadAndWrite2() throws MySetException, IOException {
        MySet<String> four = factory.getMySet();
        four.add("a");
        four.add("b");
        four.add("c");
        four.add("d");
        four.WriteToFile("test-out-3.txt");
        MySet<String> read = factory.getMySet();
        read.ReadFromFile("test-out-3.txt");
        assertTrue(four.equals(read));
    }

    @Test
    public void testReadAndWrite3() throws MySetException, IOException {
        MySet<Double> four = factory.getMySet();
        four.add(1.2);
        four.add(1.3);
        four.add(1.4);
        four.add(1.5);
        four.WriteToFile("test-out-2.txt");
        MySet<Double> read = factory.getMySet();
        read.ReadFromFile("test-out-2.txt");
        assertTrue(four.equals(read));
    }

}
