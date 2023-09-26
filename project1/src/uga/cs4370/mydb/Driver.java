package uga.cs4370.mydb;

import uga.cs4370.mydb.impl.RAImpl;
import uga.cs4370.mydb.impl.RelationBuilderImpl;
import java.util.Arrays;
import java.util.List;

public class Driver {
    public static void main(String[] args) {

        /** Welcome **/

        System.out.println("\n\n============================================");
        System.out.println("\nWelcome to SAVA: Rudimentary Database System\n");
        System.out.println("============================================\n\n");

        /** First, we make a new RelationBuilder with which we'll make our database. **/

        RelationBuilder builder = new RelationBuilderImpl();

        // Part one of that is to create the column names (attributes)
        // and then denote their types (from our Type class).
        List<String> attributes = Arrays.asList("StudentID", "FirstName", "LastName");
        List<Type> types = Arrays.asList(Type.INTEGER, Type.STRING, Type.STRING);

        // The second part is to *combine* those attributes and types into the
        // "system,"" using our builder.
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

        // Let's add a couple more, then print everything we have!
        students.insert(new Cell(2), new Cell("Alex"), new Cell("Fritz"));
        students.insert(new Cell(3), new Cell("Althea"), new Cell("Pamintawan"));
        System.out.println("\nFirst student table:\n");
        students.print();

        /** Now, let's do some relational algebra! **/

        // First, we need a relational algebra object:
        RA ra = new RAImpl();

        // Next, we'll make a predicate to do our operation with.
        // I think I want to select rows whose IDs are greater than 1, so let's make
        // that:
        Predicate predicate = row -> {

            Cell idCell = row.get(0); // ID is the first element in the row, so 0th position
            return idCell.getAsInt() > 1; // Check if ID > 1

        };

        // We want our result to go into a new Relation (new table), so we'll need to
        // make one.
        // This is where we put all of our relational algebra pieces together!
        // The following will select rows from students whose IDs are greater than 1.
        Relation result = ra.select(students, predicate);

        // Let's print it -- just to make sure!
        System.out.println("\nStudents whose IDs are greater than 1:\n");
        result.print();

        /** Additional Testing **/

        // The rest of this is just going to be a playground for other RA methods.
        // I won't be as verbose with comments here.

        // Let's project only the first names. The "FirstName" attribute comes from
        // above (line 25)

        Relation projected = ra.project(students, Arrays.asList("FirstName"));
        System.out.println("\nProjected relation with only First Names:\n");
        projected.print();

        // Let's make a new table.

        Relation students2 = builder.newRelation("Students2",
                Arrays.asList("StudentID", "FirstName", "LastName"),
                Arrays.asList(Type.INTEGER, Type.STRING, Type.STRING));

        students2.insert(new Cell(10), new Cell("Jaxon"), new Cell("Smith"));
        students2.insert(new Cell(11), new Cell("Ally"), new Cell("Johnson"));
        students2.insert(new Cell(12), new Cell("Trevon"), new Cell("Stewert"));
        students2.insert(new Cell(1), new Cell("Sanjay"), new Cell("DK"));
        System.out.println("\nSecond student table:\n");
        students2.print();

        // Now, let's union the two tables. (Notice how it removes duplicates!

        Relation union = ra.union(students, students2);
        System.out.println("\nStudents1 union Students2\n");
        union.print();

        // Diff the two tables. It only keeps originals from the first one.
        // Aka, it removes anything from table1 that's in table2.

        Relation diff = ra.diff(students, students2);
        System.out.println("\nStudents1 diff Students2\n");
        diff.print();

        // Cartesian product time.

        Relation cartesian = ra.cartesianProduct(students, students2);
        System.out.println("\nStudents1 cartesian Students2\n");
        cartesian.print();

        // Let's rename our attributes for students2.

        System.out.println("\nOriginal Students2:");
        System.out.println(students2.getAttrs().toString());

        Relation renamedStudents2 = ra.rename(students2,
                Arrays.asList("StudentID", "FirstName", "LastName"),
                Arrays.asList("ID", "FName", "LName"));

        System.out.println("\nRenamed Students2:");
        System.out.println(renamedStudents2.getAttrs().toString());

        // Testing natural join:

        Relation naturalJoin = ra.join(students, students2);

        System.out.println("\nNatural Join Result:\n");
        naturalJoin.print();

        // Now for a conditional join; this will be a bit more complicated.

        Relation conditionalJoin = ra.join(students, renamedStudents2, combinedRow -> {
            Cell studentFirstName = combinedRow.get(students.getAttrIndex("FirstName"));
            Cell renamedStudentFirstName = combinedRow.get(students.getAttrs().size() + renamedStudents2.getAttrIndex("FName"));
        
            return studentFirstName.equals(renamedStudentFirstName);
        });

        System.out.println("\nConditional Join Result (Based on First Name):\n");
        conditionalJoin.print();
    
        /** That's all, folks! **/
        System.out.println("\nLooks like our project was a success! Woo!\n\n\nExiting SAVA...\n\n");
        System.exit(0);

    }
}
