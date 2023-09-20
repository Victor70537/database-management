package uga.cs4370.mydb.impl;

import uga.cs4370.mydb.Relation;
import uga.cs4370.mydb.RelationBuilder;
import uga.cs4370.mydb.Type;
import java.util.List;

public class RelationBuilderImpl implements RelationBuilder {

    @Override
    public Relation newRelation(String name, List<String> attrs, List<Type> types) {
        // Check for invalid arguments (mismatched attribute or type counts)
        if (attrs.size() != types.size()) {
            throw new IllegalArgumentException("The number of attributes and types must match.");
        }
        
        // This method helps us check for empty/invalid attribute names; it makes use of some ez regex.
        for (String attr : attrs) {
            if (attr == null || attr.trim().isEmpty() || !attr.matches("[a-zA-Z0-9]+")) {
                throw new IllegalArgumentException("Attribute names must be non-empty and alphanumeric.");
            }
        }
        
        // Down here we should make and return the new relation object. I just used null for now.
        return null;
    
    }
}
