package uga.cs4370.mydb.impl;

import uga.cs4370.mydb.Cell;
import uga.cs4370.mydb.Relation;
import uga.cs4370.mydb.Type;
import java.util.List;
import java.util.ArrayList;

public class RelationImpl implements Relation {
    
    private String name;
    private List<List<Cell>> rows;
    private List<Type> types;
    private List<String> attrs;

    public RelationImpl(String name, List<String> attrs, List<Type> types) {
        this.name = name;
        this.attrs = new ArrayList<>(attrs);
        this.types = new ArrayList<>(types);
        this.rows = new ArrayList<>();
    }    

    @Override
    public String getName() {
        // Done
        return name;
    }

    @Override
    public int getSize() {
        // Done
        return rows.size();
    }

    @Override
    public List<List<Cell>> getRows() {
        // Done


        // Create a new list to hold deep copies of rows
        List<List<Cell>> deepCopies = new ArrayList<>();

        // Iterate through the original rows and create deep copies
        for (List<Cell> originalRow : rows) {
            List<Cell> deepCopyRow = new ArrayList<>();

            // Iterate through the cells in the original row and create deep copies
            for (Cell originalCell : originalRow) {
                // Assuming Cell has a copy constructor or clone method for deep copy
                Cell deepCopyCell = originalCell; // Use your deep copy mechanism here
                deepCopyRow.add(deepCopyCell);
            }

            // Add the deep copy of the row to the list
            deepCopies.add(deepCopyRow);
        }

        return deepCopies;
    }


    @Override
    public List<Type> getTypes() {
        // Done
        return types;
    }

    @Override
    public List<String> getAttrs() {
        // Done
        return attrs;
    }

    @Override
    public boolean hasAttr(String attr) {
        // Done
        return attrs.contains(attr);
    }

    @Override
    public int getAttrIndex(String attr) {
        // Done
        if (!attrs.contains(attr)){
            throw new IllegalArgumentException("Illegal Arguments");       
        } 
        else {
            return attrs.indexOf(attr);
        }
    }

    @Override
    public void insert(Cell... cells) {
        // Check if the number of provided cells matches the number of attributes
        if (cells.length != attrs.size()) {
            throw new IllegalArgumentException("Number of cells does not match the number of attributes.");
        }
    
        List<Cell> newRow = new ArrayList<>(); // iterates through the cells and add them to the new row
        for (int i = 0; i < cells.length; i++) {
            // Check if the cell type matches the attribute type
            if (cells[i].getType() != types.get(i)) {
                throw new IllegalArgumentException("Cell type does not match the attribute type at index: " + i);
            }
            newRow.add(cells[i]);
        }
    
        // Add the new row
        rows.add(newRow);
    }    

    @Override
    public void insert(List<Cell> cells) {
        // Check if the number of provided cells matches the number of attributes
        if (cells.size() != attrs.size()) {
            throw new IllegalArgumentException("Number of cells does not match the number of attributes.");
        }
    
        List<Cell> newRow = new ArrayList<>();
        for (int i = 0; i < cells.size(); i++) {
            Cell cell = cells.get(i);
    
            // Check if the cell type matches the attribute type
            if (cell.getType() != types.get(i)) {
                throw new IllegalArgumentException("Cell type does not match the attribute type at index: " + i);
            }
    
            newRow.add(cell);
        }
    
        // Add the new row
        rows.add(newRow);
    }
    
    @Override
    public void print() {
        // Done
        
        for (int i = 0; i < rows.size(); i++) {
            for (int j = 0; j < rows.get(0).size(); j++) {
                System.out.print(rows.get(i).get(j).toString());
                System.out.print(" | ");
            }
            System.out.println("");
        }
    }
}
