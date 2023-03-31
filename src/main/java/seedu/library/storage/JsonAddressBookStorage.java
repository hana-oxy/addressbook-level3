package seedu.library.storage;

import seedu.library.commons.core.LogsCenter;
import seedu.library.commons.exceptions.DataConversionException;
import seedu.library.commons.exceptions.IllegalValueException;
import seedu.library.commons.util.FileUtil;
import seedu.library.commons.util.JsonUtil;
import seedu.library.model.ReadOnlyLibraryBook;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonLibraryBookStorage implements LibraryBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonLibraryBookStorage.class);

    private Path filePath;

    public JsonLibraryBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getLibraryBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyLibraryBook> readLibraryBook() throws DataConversionException {
        return readLibraryBook(filePath);
    }

    /**
     * Similar to {@link #readLibraryBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyLibraryBook> readLibraryBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableAddressBook> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableAddressBook.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveLibraryBook(ReadOnlyLibraryBook libraryBook) throws IOException {
        saveLibraryBook(libraryBook, filePath);
    }

    /**
     * Similar to {@link #saveLibraryBook(ReadOnlyLibraryBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAddressBook(ReadOnlyLibraryBook libraryBook, Path filePath) throws IOException {
        requireNonNull(libraryBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableLibraryBook(libraryBook), filePath);
    }

}
