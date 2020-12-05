package uk.ac.ucl.model;

import uk.ac.ucl.model.dataframes.FrameException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Patient {
    private ArrayList<String> details;
    private Model model = ModelFactory.getModel();

    public Patient(int rowNumber) throws IOException, FrameException {
        addDetailByRowNum(rowNumber);
    }

    public Patient(String colname, String firstname) throws IOException, FrameException {
       int row =  model.getRowIndexbyAspect(colname,firstname);
        addDetailByRowNum(row);
    }

    private void addDetailByRowNum(int rowNum) throws FrameException {
        details = new ArrayList<>();
        ArrayList<String> all_col_names = model.getAspects();
        for(String name:all_col_names){
            details.add(name + ": " + model.getPatientDetail(name,rowNum));
        }
    }


    public String toString(){
        return details.stream().collect(Collectors.joining("<br/>"));
    }

}
