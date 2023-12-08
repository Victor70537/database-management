package edu.uga.cs4370;

public class Books {
    private String isbn;
    private String bookTitle;
    private String bookAuthor;
    private String yearOfPublication;
    private String publisher;
    private String imageURLS;
    private String imageURLM;
    private String imageURIL;

    // Constructors
    public Books() {
    }

    public Books(String isbn, String bookTitle, String bookAuthor, String yearOfPublication, String publisher, String imageURLS, String imageURLM, String imageURIL) {
        this.isbn = isbn;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.yearOfPublication = yearOfPublication;
        this.publisher = publisher;
        this.imageURLS = imageURLS;
        this.imageURLM = imageURLM;
        this.imageURIL = imageURIL;
    }

    // Getters and setters
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(String yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getImageURLS() {
        return imageURLS;
    }

    public void setImageURLS(String imageURLS) {
        this.imageURLS = imageURLS;
    }

    public String getImageURLM() {
        return imageURLM;
    }

    public void setImageURLM(String imageURLM) {
        this.imageURLM = imageURLM;
    }

    public String getImageURIL() {
        return imageURIL;
    }

    public void setImageURIL(String imageURIL) {
        this.imageURIL = imageURIL;
    }

    // toString method
    @Override
    public String toString() {
        return "Books{" +
               "isbn='" + isbn + '\'' +
               ", bookTitle='" + bookTitle + '\'' +
               ", bookAuthor='" + bookAuthor + '\'' +
               ", yearOfPublication='" + yearOfPublication + '\'' +
               ", publisher='" + publisher + '\'' +
               ", imageURLS='" + imageURLS + '\'' +
               ", imageURLM='" + imageURLM + '\'' +
               ", imageURIL='" + imageURIL + '\'' +
               '}';
    }
}
