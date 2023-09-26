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

    public RelationImpl(String name, List<String> attributes, List<Type> types, List<List<Cell>> rows) {
    this.name = name;
    this.attrs = attributes;
    this.types = types;
    this.rows = rows;

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

        List<Cell> newRow = new ArrayList<>();
        for (int i = 0; i < cells.length; i++) {
            // Check if the cell type matches the attribute type
            if (getCellType(cells[i]) != types.get(i)) {
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
            if (getCellType(cell) != types.get(i)) {
                throw new IllegalArgumentException("Cell type does not match the attribute type at index: " + i);
            }

            newRow.add(cell);
        }

        // Add the new row
        rows.add(newRow);
    }

    /*
     * This is super inefficient; not sure why we can't have a getType() method in Cell.java.
    */
    private Type getCellType(Cell cell) {
    try {
        cell.getAsInt();
        return Type.INTEGER;
    } catch (RuntimeException e) {
        // Ignore the exception
    }

    try {
        cell.getAsString();
        return Type.STRING;
    } catch (RuntimeException e) {
        // Ignore the exception
    }

    try {
        cell.getAsDouble();
        return Type.DOUBLE;
    } catch (RuntimeException e) {
        // This should never happen unless there's a new type added to Cell
        throw new IllegalStateException("Unknown cell type");
        
    }
}

    @Override
    public void print() {
        if (rows.isEmpty()) {
            return;
        }

        // I decided to make this uber complicated so it would look pretty in the terminal.

        // Step 1: Figure out the max width for each column
        List<Integer> maxWidths = new ArrayList<>();
        for (int j = 0; j < rows.get(0).size(); j++) {
            int maxColumnWidth = 0;
            for (int i = 0; i < rows.size(); i++) {
                int cellWidth = rows.get(i).get(j).toString().length();
                if (cellWidth > maxColumnWidth) {
                    maxColumnWidth = cellWidth;
                }
            }
            maxWidths.add(maxColumnWidth);
        }

        // Step 2: Use that maximum to figure out the space needed w/ String.format()
        for (int i = 0; i < rows.size(); i++) {
            for (int j = 0; j < rows.get(0).size(); j++) {
                String formattedValue = String.format("%-" + (maxWidths.get(j) + 2) + "s", rows.get(i).get(j)); // +2 for extra padding
                System.out.print(formattedValue);
                System.out.print("| ");
            }
            System.out.println();
        }
    }

}
