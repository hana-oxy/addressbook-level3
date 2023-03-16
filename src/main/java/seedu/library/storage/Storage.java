package seedu.library.storage;

import seedu.library.commons.exceptions.DataConversionException;
import seedu.library.model.ReadOnlyLibraryBook;
import seedu.library.model.ReadOnlyUserPrefs;
import seedu.library.model.UserPrefs;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

/**
 * API of the Storage component
 */
public interface Storage extends LibraryBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getLibraryBookFilePath();

    @Override
    Optional<ReadOnlyLibraryBook> readLibraryBook() throws DataConversionException, IOException;

    @Override
    void saveLibraryBook(ReadOnlyLibraryBook libraryBook) throws IOException;

}
