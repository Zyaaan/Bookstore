/**
 * The BookDao interface defines methods for accessing book-related data from a data source.
 * Implementations of this interface should provide functionality to retrieve book information.
 * It follows Java coding conventions for readability and maintainability.
 * 
 * @author [Telmen]
 * @version 1.0
 * @since [2023-11-22]
 */

package model;

import java.sql.SQLException;

public interface BookDao {

    /**
     * Retrieves book data from the data source.
     *
     * @throws SQLException if there is an issue with the data source.
     */
    public void getDataBooks() throws SQLException;

}
