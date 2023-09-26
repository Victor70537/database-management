package uga.cs4370.mydb.impl;

import uga.cs4370.mydb.RA;
import uga.cs4370.mydb.Relation;
import uga.cs4370.mydb.RelationBuilder;
import uga.cs4370.mydb.Predicate;
import uga.cs4370.mydb.Type;
import uga.cs4370.mydb.Cell;
import java.util.ArrayList;
import java.util.List;

public class RAImpl implements RA {
    
    @Override
    public Relation select(Relation rel, Predicate p) {
        // Create an empty relation with the same attributes and types as the input relation
        RelationImpl result = new RelationImpl(rel.getName(), rel.getAttrs(), rel.getTypes());

        for (List<Cell> row : rel.getRows()) {
            if (p.check(row)) { // Check the predicate on the row
                result.insert(row);
            }
        }

        return result;
    }


    @Override
public Relation project(Relation rel, List<String> attrs) {
    // Validate if the given attributes exist in the relation
    for (String attr : attrs) {
        if (!rel.hasAttr(attr)) {
            throw new IllegalArgumentException("Attribute not found in the relation: " + attr);
        }
    }

    // Determine the indices of the attributes in the original relation
    List<Integer> attrIndices = new ArrayList<>();
    for (String attr : attrs) {
        attrIndices.add(rel.getAttrIndex(attr));
    }

    // Create an empty relation with the selected attributes and corresponding types
    List<Type> projectedTypes = new ArrayList<>();
    for (int index : attrIndices) {
        projectedTypes.add(rel.getTypes().get(index));
    }
    RelationImpl result = new RelationImpl(rel.getName(), attrs, projectedTypes);

    // Insert the projected rows into the result
    for (List<Cell> row : rel.getRows()) {
        List<Cell> projectedRow = new ArrayList<>();
        for (int index : attrIndices) {
            projectedRow.add(row.get(index));
        }
        result.insert(projectedRow);
    }

    return result;
}

    @Override
    public Relation union(Relation rel1, Relation rel2) {
        // Ensure the relations have the same attributes and types
        if (!rel1.getAttrs().equals(rel2.getAttrs()) || !rel1.getTypes().equals(rel2.getTypes())) {
            throw new IllegalArgumentException("The two relations must have the same attributes and types for union.");
        }
        
        // Create the union relation
        RelationBuilder builder = new RelationBuilderImpl();
        Relation unionRelation = builder.newRelation("UnionResult", rel1.getAttrs(), rel1.getTypes());
        
        // Insert rows from rel1
        for (List<Cell> row : rel1.getRows()) {
            unionRelation.insert(row);
        }

        // Insert rows from rel2 (ignoring duplicates)
        for (List<Cell> row : rel2.getRows()) {
            if (!unionRelation.getRows().contains(row)) {
                unionRelation.insert(row);
            }
        }

        return unionRelation;
    }

    @Override
    public Relation diff(Relation rel1, Relation rel2) {
        // Ensure the relations have the same attributes and types
        if (!rel1.getAttrs().equals(rel2.getAttrs()) || !rel1.getTypes().equals(rel2.getTypes())) {
            throw new IllegalArgumentException("The two relations must have the same attributes and types for difference.");
        }

        // Create the difference relation
        RelationBuilder builder = new RelationBuilderImpl();
        Relation diffRelation = builder.newRelation("DiffResult", rel1.getAttrs(), rel1.getTypes());
        
        // Insert rows from rel1 that are not in rel2
        for (List<Cell> row : rel1.getRows()) {
            if (!rel2.getRows().contains(row)) {
                diffRelation.insert(row);
            }
        }

        return diffRelation;
    }

    @Override
    public Relation rename(Relation rel, List<String> origAttr, List<String> renamedAttr) {
        // Ensure origAttr and renamedAttr are of the same size
        if (origAttr.size() != renamedAttr.size()) {
            throw new IllegalArgumentException("Original and renamed attribute lists must be of the same size.");
        }
        
        // Create the new attribute list for the renamed relation
        List<String> newAttrs = new ArrayList<>(rel.getAttrs());
        for (int i = 0; i < origAttr.size(); i++) {
            int index = newAttrs.indexOf(origAttr.get(i));
            if (index != -1) {
                newAttrs.set(index, renamedAttr.get(i));
            } else {
                throw new IllegalArgumentException("Original attribute " + origAttr.get(i) + " not found in the relation.");
            }
        }
    
        // Create the renamed relation
        RelationBuilder builder = new RelationBuilderImpl();
        Relation renamedRelation = builder.newRelation("RenamedResult", newAttrs, rel.getTypes());
    
        // Copy rows
        for (List<Cell> row : rel.getRows()) {
            renamedRelation.insert(row);
        }
    
        return renamedRelation;
    }
    
    @Override
    public Relation cartesianProduct(Relation rel1, Relation rel2) {
        // Create attribute and type lists for the new relation
        List<String> combinedAttrs = new ArrayList<>(rel1.getAttrs());
        combinedAttrs.addAll(rel2.getAttrs());
    
        List<Type> combinedTypes = new ArrayList<>(rel1.getTypes());
        combinedTypes.addAll(rel2.getTypes());
    
        // Create the relation for cartesian product
        RelationBuilder builder = new RelationBuilderImpl();
        Relation productRelation = builder.newRelation("ProductResult", combinedAttrs, combinedTypes);
    
        // Populate the relation with the cartesian product
        for (List<Cell> row1 : rel1.getRows()) {
            for (List<Cell> row2 : rel2.getRows()) {
                List<Cell> combinedRow = new ArrayList<>(row1);
                combinedRow.addAll(row2);
                productRelation.insert(combinedRow);
            }
        }
    
        return productRelation;
    }
    
    @Override
    public Relation join(Relation rel1, Relation rel2) {
    // Create an empty result relation
    RelationBuilder builder = new RelationBuilderImpl();
    List<String> combinedAttrs = new ArrayList<>(rel1.getAttrs());
    combinedAttrs.addAll(rel2.getAttrs());
    List<Type> combinedTypes = new ArrayList<>(rel1.getTypes());
    combinedTypes.addAll(rel2.getTypes());
    Relation result = builder.newRelation("JoinResult", combinedAttrs, combinedTypes);

    // Perform the natural join
    for (List<Cell> row1 : rel1.getRows()) {
        for (List<Cell> row2 : rel2.getRows()) {
            boolean canJoin = true;
            for (String attr : rel1.getAttrs()) {
                if (rel2.hasAttr(attr) && !row1.get(rel1.getAttrIndex(attr)).equals(row2.get(rel2.getAttrIndex(attr)))) {
                    canJoin = false;
                    break;
                }
            }
            if (canJoin) {
                List<Cell> combinedRow = new ArrayList<>(row1);
                combinedRow.addAll(row2);
                result.insert(combinedRow);
            }
        }
    }

    return result;
    }

    @Override
    public Relation join(Relation rel1, Relation rel2, Predicate p) {
        List<Cell> combinedRow;
        List<List<Cell>> combinedRows = new ArrayList<>();
    
        for (List<Cell> row1 : rel1.getRows()) {
            for (List<Cell> row2 : rel2.getRows()) {
                combinedRow = new ArrayList<>(row1);
                combinedRow.addAll(row2);
                
                if (p.check(combinedRow)) {
                    combinedRows.add(combinedRow);
                }
            }
        }
    
        List<String> combinedAttributes = new ArrayList<>(rel1.getAttrs());
        combinedAttributes.addAll(rel2.getAttrs());
        
        List<Type> combinedTypes = new ArrayList<>(rel1.getTypes());
        combinedTypes.addAll(rel2.getTypes());
    
        return new RelationImpl("JoinedRelation", combinedAttributes, combinedTypes, combinedRows);
        
    }
    

}
