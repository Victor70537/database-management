package uga.cs4370.mydb.impl;

import uga.cs4370.mydb.RA;
import uga.cs4370.mydb.Relation;
import uga.cs4370.mydb.Predicate;
import uga.cs4370.mydb.Type;
import java.util.List;

public class RAImpl implements RA {
    
    @Override
    public Relation select(Relation rel, Predicate p) {
        // Method time.
        return null;
    }

    @Override
    public Relation project(Relation rel, List<String> attrs) {
        // Hi, Sanjay.
        return null;
    }

    @Override
    public Relation union(Relation rel1, Relation rel2) {
        // Implement this.
        return null;
    }

    @Override
    public Relation diff(Relation rel1, Relation rel2) {
        // Easy. Just... do it.
        return null;
    }

    @Override
    public Relation rename(Relation rel, List<String> origAttr, List<String> renamedAttr) {
        // I have a terrible headache.
        return null;
    }

    @Override
    public Relation cartesianProduct(Relation rel1, Relation rel2) {
        // Hi, Althea.
        return null;
    }

    @Override
    public Relation join(Relation rel1, Relation rel2) {
        // Do this one.
        return null;
    }

    @Override
    public Relation join(Relation rel1, Relation rel2, Predicate p) {
        // Make this.
        return null;
    }
}
