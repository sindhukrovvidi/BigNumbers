package bignumber;

public interface BigNumber extends Comparable<BigNumber> {

  int length();

  void shiftLeft(long shifts);

  void shiftRight(long shifts);

  void addDigit(int digit);

  int getDigitAt(int index);

  int compareTo(BigNumber that);

  BigNumber copy();

  BigNumber add(BigNumber other);
}
