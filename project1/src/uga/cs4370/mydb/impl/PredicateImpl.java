package uga.cs4370.mydb.impl;

import uga.cs4370.mydb.Predicate;
import uga.cs4370.mydb.Cell;
import java.util.List;

public class PredicateImpl implements Predicate {

    @Override
    public boolean check(List<Cell> row) {
        // Done

        if (row.size() > 0) {
            return true;
        }
        else {
            return false;
        }
    }
}

// Test