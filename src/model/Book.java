/**
 * The Book class represents a book entity with attributes such as ID, category, name, author, price,
 * quantity, and description.
 * It follows Java coding conventions for readability and maintainability.
 * 
 * @author [Telmen]
 * @version 1.0
 * @since [2023-11-20]
 */

package model;

public class Book {
    private int ID;
    private String category;
    private String name;
    private String author;
    private int price;
    private int number;
    private String desc;

    // Default constructor
    public Book() {
        // Constructor may be used for future enhancements
    }

    /**
     * Get the ID of the book.
     *
     * @return The book ID.
     */
    public int getID() {
        return ID;
    }

    /**
     * Set the ID of the book.
     *
     * @param ID The book ID to set.
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Get the category of the book.
     *
     * @return The book category.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Set the category of the book.
     *
     * @param category The book category to set.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Get the name of the book.
     *
     * @return The book name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the book.
     *
     * @param name The book name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the author of the book.
     *
     * @return The book author.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Set the author of the book.
     *
     * @param author The book author to set.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Get the price of the book.
     *
     * @return The book price.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Set the price of the book.
     *
     * @param price The book price to set.
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Get the quantity of the book.
     *
     * @return The book quantity.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Set the quantity of the book.
     *
     * @param number The book quantity to set.
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Get the description of the book.
     *
     * @return The book description.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Set the description of the book.
     *
     * @param desc The book description to set.
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }
}
