package seedu.library.model;

import javafx.collections.ObservableList;
import seedu.library.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyLibraryBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

}
