package dg.invoice.app.commons;

import java.util.Optional;

public final class AppConstants {

    final public static String APP_TITLE = "InvoiceDG - Dawid Golaszewski";
    final public static String ADD_ITEM_TITLE = "Add item";

    final public static String ERROR_TEXT = "Error";
    public static final String INFO_TEXT = "Information";

    final public static String ERROR_DUPLICATE_WINDOW_HEADER = "Another window already open.";
    final public static String ERROR_DUPLICATE_WINDOW_MESSAGE = "Looks like another window has been already opened. In order to open new window please finish your work in the existing one.";

    final public static String ERROR_INVALID_INPUT_HEADER = "Invalid input.";
    final public static String ERROR_INVALID_INPUT_MESSAGE = "One of the inputs provided is invalid. Please correct the wrong input. %s";

    public static final String ERROR_EMPTY_FIELD_HEADER = "Invoice data incomplete";
    public static final String ERROR_EMPTY_FIELD_MESSAGE = "Data for field: %s was not provided. In order to validate and lock, please enter missing information.";

    public static final String ERROR_INVALID_DATE_HEADER = "Provided date is invalid";
    public static final String ERROR_INVALID_DATE_MESSAGE = "Entered date should be in right point in time. Please make sure you have entered the correct date to continue.";

    public static final String ERROR_SAVE_HEADER = "Cannot save the data";
    public static final String ERROR_SAVE_UNLOCKED_MESSAGE = "In order to save the data you first need to fill the form completely and validate the inputs using the button Validate and lock";
    public static final String ERROR_SAVE_NO_ITEMS_MESSAGE = "Saving data is not possible for empty item list. Please head over to the items tab and add items.";

    public static final String ERROR_LOAD_HEADER = "Cannot load the data";
    public static final String ERROR_LOAD_MESSAGE = "Data in the from is currently in locked state and it might be lost in the process. In order to load a file please unlock the data";

    public static final String PAYMENT_BANK = "Bank transfer";
    public static final String PAYMENT_CASH = "Cash";

    public static final String INFO_SUCCESS = "Requested action completed with success.";
    public static final String INFO_FAILURE = "Oops. Something went wrong.";

    public static final String ITEMS_TAG = "[ITEMS]";
    public static final String EXTENSION = "faktura";
}
