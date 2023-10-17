package edu.uga.cs4370;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Genre")
public class Genre {
    
    @Id
    private Integer ID;
    private String name;

    public String toString () {
        return "Genre{" 
            + "id: " + ID 
            + ", name: " + name
            + "}";
    }

}
