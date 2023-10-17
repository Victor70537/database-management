package edu.uga.cs4370;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.ManyToOne;

@Entity
@Table (name = "Review")
public class Review {

    @Id
    private Integer reviewID;

    @ManyToOne
    private Movie movie;

    @ManyToOne
    private User user;

    private Double rating;


}
