package uga.cs4370.mydb;

import uga.cs4370.mydb.impl.RelationBuilderImpl;
import java.util.Arrays;
import java.util.List;

public class Driver {
    public static void main(String[] args) {

        /** Welcome **/

        System.out.println("\n===================================");
        System.out.println("\nSAVA: Rudimentary Database System\n");
        System.out.println("===================================\n");



        /** First, we make a new RelationBuilder with which we'll make our database. **/

        RelationBuilder builder = new RelationBuilderImpl();

        // Part one of that is to create the column names (attributes)
        // and then denote their types (from our Type class).
        List<String> attributes = Arrays.asList("StudentID", "FirstName", "LastName");
        List<Type> types = Arrays.asList(Type.INTEGER, Type.STRING, Type.STRING);

        // The second part is to *combine* those attributes and types into the "system,"" using our builder.
        Relation students = builder.newRelation("Students", attributes, types);



        /** Second, let's add some actual data to our schema. **/

        // To do that, we need to make some data cells.
        Cell vicID = new Cell(0);
        Cell vicFName = new Cell("Victor");
        Cell vicLName = new Cell("Qiu");

        // There are two ways to add this data.
        // The first is to make a Cell List and add them that way:
        List<Cell> rowData = Arrays.asList(vicID, vicFName, vicLName);
        students.insert(rowData);
        
        // The second is with the varargs insert method, which to me seems way easier:
        students.insert(new Cell(1), new Cell("Sanjay"), new Cell("DK"));
        


    }
}
