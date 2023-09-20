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

    // Initialize attributes here, if needed

    @Override
    public String getName() {
        // Do this!
        return null;
    }

    @Override
    public int getSize() {
        // And this.
        return 0;
    }

    @Override
    public List<List<Cell>> getRows() {
        // Do this one
        // (Remember to return a *deep copy*)
        return null;
    }

    @Override
    public List<Type> getTypes() {
        // Implement this one.
        return null;
    }

    @Override
    public List<String> getAttrs() {
        // Need this, too.
        return null;
    }

    @Override
    public boolean hasAttr(String attr) {
        // This.
        return false;
    }

    @Override
    public int getAttrIndex(String attr) {
        // Implement this & throw an exception if the attribute doesn't exist
        return 0;
    }

    @Override
    public void insert(Cell... cells) {
        // Vic can ligma
    }

    @Override
    public void insert(List<Cell> cells) {
        // Make this method.
    }

    @Override
    public void print() {
        // Do this!
    }
}
