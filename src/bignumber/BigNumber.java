package bignumber;

/**
 * BigNumber interface stores the BigNumbers in list. It stores individual digits (0-9) in a single
 * node of such a list. It performs various operations like shiftLeft, shiftRight, length,
 * getDigitAt, copy and compare. Mathematical operations such as addition is supported by this
 * interface.
 */
public interface BigNumber extends Comparable<BigNumber> {

  /**
   * Return the length of the given big number which is represented as list.
   *
   * @return length of the list.
   */
  int length();

  /**
   * Shifts the list left by given number of shifts. Left-shifting by one position is equivalent to
   * multiplying the number by 10.
   *
   * @param shifts number of shifts that are to be performed.
   */
  void shiftLeft(long shifts);

  /**
   * Shifts the list right by given number of shifts. Right-shifting by one position is equivalent
   * to dividing the number by 10.
   *
   * @param shifts number of shifts that are to be performed.
   */
  void shiftRight(long shifts);

  /**
   * Takes a single digit as an argument and adds it to this number. This method throws an
   * IllegalArgumentException if the digit passed to it is not a single non-negative digit.
   *
   * @param digit single non-negative digit.
   */
  void addDigit(int digit);

  /**
   * Takes a position as an argument and returns the digit at that position. Positions start at 0
   * (rightmost digit).
   *
   * @param index position.
   * @return digit at the given position.
   */
  int getDigitAt(int index);

  /**
   * Compares two BigNumbers and returns a boolean.
   *
   * @return if the objects are equal or not.
   */
  int compareTo(BigNumber that);

  /**
   * Creates a copy of the given BigNumber.
   *
   * @return an identical and independent copy of this number.
   */
  BigNumber copy();

  /**
   * Takes another BigNumber and returns the sum of these two numbers.
   *
   * @return result stores as a BigNumber.
   */
  BigNumber add(BigNumber other);
}
