package seedu.library.storage;

import seedu.library.commons.exceptions.DataConversionException;
import seedu.library.model.ReadOnlyLibraryBook;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

/**
 * Represents a storage for {@link seedu.library.model.LibraryBook}.
 */
public interface LibraryBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getLibraryBookFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyLibraryBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyLibraryBook> readLibraryBook() throws DataConversionException, IOException;

    /**
     * @see #getLibraryBookFilePath()
     */
    Optional<ReadOnlyLibraryBook> readLibraryBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyLibraryBook} to the storage.
     * @param libraryBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveLibraryBook(ReadOnlyLibraryBook libraryBook) throws IOException;

    /**
     * @see #saveLibraryBook(ReadOnlyLibraryBook)
     */
    void saveLibraryBook(ReadOnlyLibraryBook libraryBook, Path filePath) throws IOException;

}
