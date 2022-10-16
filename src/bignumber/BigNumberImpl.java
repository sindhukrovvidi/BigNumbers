package bignumber;

public class BigNumberImpl implements BigNumber {

  private int number;

  private int length;

  private BigNumberImpl next;

  private BigNumberImpl tail;

  private BigNumberImpl prev;

  public BigNumberImpl(String n) {
    BigNumberImpl head = new BigNumberImpl();
    BigNumberImpl temp = head;
    BigNumberImpl prevTemp = new BigNumberImpl();
    int index = 0;
    while (index < n.length()) {
      temp.number = Integer.parseInt(String.valueOf(n.charAt(index)));
      if (index < n.length() - 1) {
        temp.next = new BigNumberImpl();
        this.tail = temp.next;
      } else if (n.length() == 1) {
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
    this.length = n.length();
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
    return this.length;
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
        if (shifts < this.length / 2) {
          while (count > 0) {
            this.tail = this.tail.prev;
            count--;
          }
          this.tail.next = null;
        } else {
          while ((length - count) > 1) {
            temp = temp.next;
            count++;
          }
          temp.next = null;
        }
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

//  @Override
//  public int getDigitAt(int index) {
//    int currentLength = this.length();
//    if (index > currentLength) {
//      throw new IllegalArgumentException("The given index is not valid.");
//    }
//    int digit = 0;
//    BigNumberImpl current = this;
//    if(index < currentLength/2){
//      int count = 1;
//      while (current != null) {
//        if (count == index) {
//          return current.number;
//        }
//        count++;
//        current = current.next;
//      }
//    } else {
//      int count = 1;
//      BigNumberImpl tailTemp = this.tail;
//      while (tailTemp != null) {
//        if (count == currentLength - index + 1) {
//          return tailTemp.number;
//        }
//        count++;
//        current = current.prev;
//      }
//    }
//
//
//    int count = 1;
//    while (current != null) {
//      if (count == index) {
//        return current.number;
//      }
//      count++;
//      current = current.next;
//    }
//    return digit;
//  }

  @Override
  public int compareTo(BigNumber newNumber) {
    if (this.length() > newNumber.length()) {
      return 1;
    } else if (this.length() < newNumber.length()) {
      return -1;
    } else {
      return 0;
    }
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


