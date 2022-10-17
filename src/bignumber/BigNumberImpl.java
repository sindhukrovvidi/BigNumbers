package bignumber;

public class BigNumberImpl implements BigNumber {

  private int number;

  private int length;

  private BigNumberImpl next;

  private BigNumberImpl tail;

  private BigNumberImpl prev;

  public BigNumberImpl(String n) {
    String tempStr = n.replaceFirst("^0+(?!$)", "");
    BigNumberImpl head = new BigNumberImpl();
    BigNumberImpl temp = head;
    BigNumberImpl prevTemp = new BigNumberImpl();
    int index = 0;
    while (index < tempStr.length()) {
      if (!Character.isDigit(tempStr.charAt(index))) {
        throw new IllegalArgumentException("Invalid String, it should contain only numbers.");
      }
      temp.number = Integer.parseInt(String.valueOf(tempStr.charAt(index)));
      if (index < tempStr.length() - 1) {
        temp.next = new BigNumberImpl();
        this.tail = temp.next;
      } else if (tempStr.length() == 1) {
        this.tail = head;
      }
      prevTemp.next = temp;
      temp.prev = prevTemp;
      prevTemp = prevTemp.next;
      temp = temp.next;
      index++;
    }
    head.prev = null;
    this.number = head.number;
    this.next = head.next;
    this.length = tempStr.length();
    this.prev = null;
  }

  public BigNumberImpl() {
    this.number = 0;
    this.next = null;
    this.length = 1;
    this.tail = this;
    this.prev = null;
  }


  @Override
  public String toString() {
    String str = "";
    BigNumberImpl temp = this;
    while (temp != null) {
      str = str + temp.number;
      temp = temp.next;
    }
    return str;
  }

  @Override
  public int length() {
    if (this.next == null) {
      return 1;
    }
    return 1 + this.next.length();
  }

  @Override
  public void shiftLeft(long shifts) {
    if ((this.next == null && this.number == 0)) {

    } else if (shifts < 0) {
      this.shiftRight(Math.abs(shifts));
    } else {
      long count = shifts;
      while (count > 0) {
        BigNumberImpl temp = new BigNumberImpl();
        if (this.length == 1) {
          this.next = temp;
          temp.prev = this;
        }
        this.tail.next = temp;
        temp.prev = tail;
        this.tail = tail.next;
        count--;
        length++;
      }
    }

  }

  @Override
  public void shiftRight(long shifts) {
    if ((this.next == null && this.number == 0)) {

    } else if (shifts < 0) {
      this.shiftLeft(Math.abs(shifts));
    } else {

      int currentLength = length;
      if (currentLength <= shifts) {
        number = 0;
        next = null;
        length = 1;
        prev = null;
        tail = this;
      } else {
        long count = shifts;
        BigNumberImpl temp = this;
        while (count > 0) {
          // should update next also - breaking if 2 digits
//          this.tail.next = null;
          this.tail = this.tail.prev;
          count--;
        }
        this.tail.next = null;
//        if (shifts < this.length / 2) {
//          while (count > 0) {
//            this.tail = this.tail.prev;
//            count--;
//          }
//          this.tail.next = null;
//        } else {
//          while ((length - count) > 1) {
//            temp = temp.next;
//            count++;
//          }
//          temp.next = null;
//        }
        this.length -= shifts;
      }

    }
  }

  @Override
  public void addDigit(int digit) {
    if (digit > 9 || digit < 0) {
      throw new IllegalArgumentException("The digit should be a single non negative integer.");
    }

    BigNumberImpl temp = addTwoNumbers(this, new BigNumberImpl(String.valueOf(digit)));
    this.number = temp.number;
    this.next = temp.next;
    this.tail = temp.tail;
    this.prev = temp.prev;
    this.length = temp.length;
  }

  @Override
  public int getDigitAt(int index) {
    if (index < 0) {
      throw new IllegalArgumentException("The position must be non negative value.");
    }
    if (index > this.length()) {
      return 0;
    } else {
      int count = 0;
      BigNumberImpl tempTail = this.tail;
      while (tempTail != null) {
        if (count == index) {
          return tempTail.number;
        }
        count++;
        tempTail = tempTail.prev;
      }
    }
    return 0;
  }

  @Override
  public int compareTo(BigNumber newNumber) {
    if (this.length() > newNumber.length()) {
      return 1;
    } else if (this.length() < newNumber.length()) {
      return -1;
    } else {
      if (this.equals(newNumber)) {
        return 0;
      } else {
        int index = length - 1;
        while (index >= 0) {
          int digit1 = this.getDigitAt(index);
          int digit2 = newNumber.getDigitAt(index);
          if (digit1 < digit2) {
            return -1;
          } else if (digit1 > digit2) {
            return 1;
          }
          index--;
        }
        return 0;
      }
    }
  }

  // change the implementation of this. traverse the linked list instead of fetching the digit.
  @Override
  public boolean equals(Object newObject) {
    if (this == newObject) {
      return true;
    }

    if (!(newObject instanceof BigNumber)) {
      return false;
    }

    BigNumber that = (BigNumber) newObject;

    int index = length - 1;
    while (index >= 0) {
      int digit1 = this.getDigitAt(index);
      int digit2 = ((BigNumber) newObject).getDigitAt(index);
      if (!(digit1 == digit2)) {
        return false;
      }
      index--;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int hashcode = 0;
    BigNumberImpl temp = this;
    while (temp != null) {
      hashcode += temp.number * (10) ^ 2;
      temp = temp.next;
    }
    return hashcode;
  }

  @Override
  public BigNumber copy() {
    BigNumber newCopy = new BigNumberImpl(this.toString());
    return newCopy;
  }

  @Override
  public BigNumber add(BigNumber other) {
    BigNumber test = addTwoNumbers(this, other);
    return test;
  }

  private BigNumberImpl addTwoNumbers(BigNumberImpl l1, BigNumber list2) {
    BigNumberImpl l2 = (BigNumberImpl) list2;
    BigNumberImpl add = new BigNumberImpl();
    if (l1 == null) {
      return l2;
    }
    if (l2 == null) {
      return l1;
    }
    BigNumberImpl tail1 = l1.tail;
    BigNumberImpl tail2 = l2.tail;
    int carry = 0;
    int len = 0;
    BigNumberImpl addTail = add;
    while (tail1 != null || tail2 != null || carry != 0) {
      int x = (tail1 != null) ? tail1.number : 0;
      int y = (tail2 != null) ? tail2.number : 0;
      int sum = x + y + carry;
      carry = sum / 10;
      add.number = sum % 10;
      if (tail1 != null) {
        tail1 = tail1.prev;
      }
      if (tail2 != null) {
        tail2 = tail2.prev;
      }
      BigNumberImpl temp = new BigNumberImpl();
      temp.next = add;
      add.prev = temp;
      add = temp;
      len++;
    }
    add = add.next;
    add.prev = null;
    add.length = len;
    add.tail = addTail;
    return add;
  }

}


