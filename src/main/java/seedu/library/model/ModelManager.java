package seedu.library.model;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.library.commons.core.GuiSettings;
import seedu.library.commons.core.LogsCenter;
import seedu.library.model.person.Person;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;
import static seedu.library.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents the in-memory model of the library book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final LibraryBook libraryBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyLibraryBook libraryBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(libraryBook, userPrefs);

        logger.fine("Initializing with library book: " + libraryBook + " and user prefs " + userPrefs);

        this.libraryBook = new LibraryBook(libraryBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.libraryBook.getPersonList());
    }

    public ModelManager() {
        this(new LibraryBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getLibraryBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path libraryBookFilePath) {
        requireNonNull(libraryBookFilePath);
        userPrefs.setLibraryBookFilePath(libraryBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setLibraryBook(ReadOnlyLibraryBook libraryBook) {
        this.libraryBook.resetData(libraryBook);
    }

    @Override
    public ReadOnlyLibraryBook getLibraryBook() {
        return libraryBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return libraryBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        libraryBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        libraryBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        libraryBook.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return libraryBook.equals(other.libraryBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
