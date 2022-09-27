/**
 *  Implements an exception that should be thrown for bad transaction.
 */
public class BadTransactionException extends Exception {
  public int amount;  // The invalid amount.
  /**
   *  Creates an exception object for invalid amount "badAmount".
   **/
  public BadTransactionException(int badAmount) {
    super("Invalid account number: " + badAmount);

    amount = badAmount;
  }
}
