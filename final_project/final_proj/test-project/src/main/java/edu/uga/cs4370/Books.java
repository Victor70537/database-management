package edu.uga.cs4370;

import java.time.Year;

// import javax.persistence.Entity;
// import javax.persistence.Id;
// import javax.persistence.Table;

public class Books {
    
    // @Id
    private String ISBN;
    private String Book_Title;
    private String Book_Author;
    private int Year_Of_Publication;
    private String Publisher;
    private String Image_URL_S;
    private String Image_URL_M;
    private String Image_URL_L;

    public Books (String ISBN, String Book_Title, String Book_Author) {
        this.ISBN = ISBN;
        this.Book_Title = Book_Title;
        this.Book_Author = Book_Author;
    }

    public Books (String ISBN, String Book_Title, String Book_Author, int Year_Of_Publication, 
                String Publisher, String Image_URL_S, String Image_URL_M, String Image_URL_L) {
        this.ISBN = ISBN;
        this.Book_Title = Book_Title;
        this.Book_Author = Book_Author;
        this.Year_Of_Publication = Year_Of_Publication;
        this.Publisher = Publisher;
        this.Image_URL_S = Image_URL_S;
        this.Image_URL_M = Image_URL_M;
        this.Image_URL_L = Image_URL_L;
    }
}

