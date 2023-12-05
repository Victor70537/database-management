package edu.uga.cs4370;


// import javax.persistence.Entity;
// import javax.persistence.Id;
// import javax.persistence.Table;

// @Entity
// @Table(name = "Genre")
public class Genre {
    
    // @Id
    private int ID;
    private String name;

    public Genre (int ID, String name) {
        this.ID = ID;
        this.name = name;
    }
}
