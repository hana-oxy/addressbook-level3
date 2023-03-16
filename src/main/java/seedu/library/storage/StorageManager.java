package seedu.library.storage;

import seedu.library.commons.core.LogsCenter;
import seedu.library.commons.exceptions.DataConversionException;
import seedu.library.model.ReadOnlyLibraryBook;
import seedu.library.model.ReadOnlyUserPrefs;
import seedu.library.model.UserPrefs;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private LibraryBookStorage libraryBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code LibraryBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(LibraryBookStorage libraryBookStorage, UserPrefsStorage userPrefsStorage) {
        this.libraryBookStorage = libraryBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ LibraryBook methods ==============================

    @Override
    public Path getLibraryBookFilePath() {
        return libraryBookStorage.getLibraryBookFilePath();
    }

    @Override
    public Optional<ReadOnlyLibraryBook> readLibraryBook() throws DataConversionException, IOException {
        return readLibraryBook(libraryBookStorage.getLibraryBookFilePath());
    }

    @Override
    public Optional<ReadOnlyLibraryBook> readLibraryBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return libraryBookStorage.readLibraryBook(filePath);
    }

    @Override
    public void saveLibraryBook(ReadOnlyLibraryBook libraryBook) throws IOException {
        saveLibraryBook(libraryBook, libraryBookStorage.getLibraryBookFilePath());
    }

    @Override
    public void saveLibraryBook(ReadOnlyLibraryBook libraryBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        libraryBookStorage.saveLibraryBook(libraryBook, filePath);
    }

}
