
import static org.junit.Assert.assertEquals;

import bignumber.BigNumber;
import bignumber.BigNumberImpl;
import java.math.BigInteger;
import org.junit.Test;

public class BigNumberImplTest {

  @Test
  public void testLeftShift() {
    BigNumber num = new BigNumberImpl();
    BigNumber num2 = new BigNumberImpl();
//    System.out.println("In test case...." + num);
    num.shiftLeft(1);
    num.toString();
    assertEquals("0", num.toString());
    assertEquals(1, num.length());
    num.addDigit(1);
    assertEquals("01", num.toString());
    num.shiftLeft(10);
    assertEquals("010000000000", num.toString());
    num.addDigit(7);
    assertEquals("0100000000007", num.toString());
    assertEquals("7", num.getDigitAt(0));
    assertEquals("0", num.getDigitAt(1));
//    assertEquals("0", num.getDigitAt(13));
    assertEquals("1", num.getDigitAt(11));
    assertEquals(13, num.length());
    num.shiftRight(1);
    assertEquals("010000000000", num.toString());

    num.shiftRight(3);
    assertEquals("010000000", num.toString());

//    num.getDigitAt(5);
    assertEquals("0", num.getDigitAt(2));
    assertEquals(0, num.compareTo((BigNumberImpl) num));
    assertEquals(1, num.compareTo((BigNumberImpl) num2));
//    System.out.println(num);

//    num.shiftLeft(1);num.shiftLeft(1);num.shiftLeft(1);num.shiftLeft(1);num.shiftLeft(1);num.shiftLeft(1);num.shiftLeft(1);num.shiftLeft(1);num.shiftLeft(1);num.shiftLeft(1);num.shiftLeft(1);num.shiftLeft(1);num.shiftLeft(1);num.shiftLeft(1);
  }

  @Test
  public void testBigNuberConstructor() {
    BigNumber num = new BigNumberImpl("32411");
    System.out.println(num);
  }

  @Test
  public void testLeftShift1() {
    BigNumber num = new BigNumberImpl("32419");
    System.out.println(num);
//    System.out.println(num);

//    num.shiftLeft(1);num.shiftLeft(1);num.shiftLeft(1);num.shiftLeft(1);num.shiftLeft(1);num.shiftLeft(1);num.shiftLeft(1);num.shiftLeft(1);num.shiftLeft(1);num.shiftLeft(1);num.shiftLeft(1);num.shiftLeft(1);num.shiftLeft(1);num.shiftLeft(1);
  }

  @Test
  public void testaddDigit() {
    BigNumber num = new BigNumberImpl();
    num.shiftLeft(3);
    assertEquals("0", num.toString());
    num.addDigit(3);
    assertEquals("3", num.toString());
    num.shiftLeft(3);
    assertEquals("3000", num.toString());
    num.addDigit(3);
    assertEquals("3003", num.toString());
    num.addDigit(3);
    num.addDigit(3);
    num.addDigit(3);
    num.addDigit(3);
    num.addDigit(3);
    assertEquals(4, num.length());
    assertEquals("3018", num.toString());
  }

  @Test
  public void testCopy() {
    BigNumber num = new BigNumberImpl();
    BigNumber num2 = new BigNumberImpl();
//    System.out.println("In test case...." + num);
    num.shiftLeft(1);
    num.toString();
//    assertEquals("0", num.toString());
    assertEquals(1, num.length());
    num.addDigit(15);
//    assertEquals("1", num.toString());
    num.shiftLeft(10);
//    assertEquals("10000000000", num.toString());
    num.addDigit(7);
//    assertEquals("100000000007", num.toString());
//    assertEquals("7", num.getDigitAt(0));
//    assertEquals("0", num.getDigitAt(1));
//    assertEquals("0", num.getDigitAt(13));
//    assertEquals("1", num.getDigitAt(11));
//    assertEquals(13, num.length());
    num.shiftRight(1);
//    assertEquals("1000000000", num.toString());

    num.shiftRight(3);
//    assertEquals("10000000", num.toString());
//    assertEquals(num, num.copy());;
  }

  @Test
  public void testAddTwoBig() {
    BigNumber num = new BigNumberImpl(
        "08000000000200200000000350000809000100000030093000080000000600004200000000080000600800009000000400004000000000008000009040020000000010000000070010000050010000000009002000040000070000000099000000000750008500080000000570040020000000010000000070010000050010000000009002000040000070000000099000000000750008500080000000570040020000000010000000070010000050010000000009002000040000070000000099000000000750008500080000000570");
//    num.addDigit(3);
//    num.addDigit(8);
//    num.shiftLeft(9);
//    assertEquals("11000000000", num.toString());
//    num.shiftRight(8);
//    assertEquals("110", num.toString());
    num.shiftRight(354);
    assertEquals("08000000000200200000", num.toString());
//    num.shiftRight(1);
//    assertEquals("1", num.toString());
  }

  @Test
  public void numberCreation1() {
    BigNumber num = new BigNumberImpl();
    assertEquals("0", num.toString());
    num.shiftLeft(1);
    assertEquals("0", num.toString());
    num.addDigit(3);
    assertEquals("3", num.toString());
    num.shiftLeft(1);
    assertEquals("30", num.toString());
    num.addDigit(2);
    assertEquals("32", num.toString());
    num.shiftLeft(1);
    num.addDigit(4);
    num.shiftLeft(1);
    num.addDigit(1);
    num.shiftLeft(1);
    num.addDigit(1);
    assertEquals("32411", num.toString());
    num.shiftRight(1);
    assertEquals("3241", num.toString());
    num.shiftRight(6);
    assertEquals("0", num.toString());
    num.addDigit(1);
    assertEquals("1", num.toString());
  }

  @Test
  public void testgetDigitAt1() {
    BigNumber num = new BigNumberImpl("126374996938587");
    assertEquals(0, num.getDigitAt(15));
    assertEquals(7, num.getDigitAt(0));
    assertEquals(1, num.getDigitAt(14));
//    assertEquals(1, num.getDigitAt(-1));
    assertEquals(15, num.length());
  }

  @Test
  public void testShifts() {
    BigNumber num = new BigNumberImpl("126374996938587");
    String str = "126374996938587";
    for (int i = 0; i < 1000; i++) {
      num.shiftLeft(i);
      for (int j = 0; j < i; j++) {
        str = str + "0";
      }

    }
    System.out.println(str.length());
    System.out.println(num.length());
    assertEquals(str, num.toString());
    num.addDigit(9);
    num.addDigit(9);
    num.addDigit(8);

    for (int i = 0; i < 1000; i++) {
      num.shiftRight(i);
//      for(int j=0; j<i; j++) {
//        str = str + "0";
//      }
    }
    assertEquals("126374996938587", num.toString());
    num.shiftRight(10000);
    assertEquals("0", num.toString());
  }

  @Test
  public void numbersStartingWithZero() {
    BigNumber num = new BigNumberImpl("0002");
    assertEquals(1, num.length());
    assertEquals("2", num.toString());
  }

  @Test
  public void testEquals() {
    BigNumber A = new BigNumberImpl("1234");
    BigNumber B = new BigNumberImpl("1234");
    assertEquals(true, A.equals(B));
  }

  @Test
  public void testCompareTo() {
    BigNumber A = new BigNumberImpl("1234");
    BigNumber B = new BigNumberImpl("1234");
    assertEquals(0, A.compareTo(B));

    BigNumber A1 = new BigNumberImpl("123");
    BigNumber B1 = new BigNumberImpl("1234");
    assertEquals(-1, A1.compareTo(B1));

    BigNumber A2 = new BigNumberImpl("12334");
    BigNumber B2 = new BigNumberImpl("1234");
    assertEquals(1, A2.compareTo(B2));

    BigNumber A3 = new BigNumberImpl("1235");
    BigNumber B3 = new BigNumberImpl("1234");
    assertEquals(1, A3.compareTo(B3));
  }

  @Test
  public void testHashCode() {
    BigNumber A = new BigNumberImpl("1234");
    BigNumber B = new BigNumberImpl("1234");
    BigNumber C = new BigNumberImpl("12345");
    assertEquals(true, A.hashCode() == B.hashCode());
    assertEquals(false, A.hashCode() == C.hashCode());
  }

  @Test
  public void testShiftRight() {
    BigNumber A = new BigNumberImpl();
    A.shiftLeft(1);
    A.addDigit(6);
    assertEquals("6", A.toString());
    A.shiftLeft(1);
    A.addDigit(6);
    assertEquals("66", A.toString());
    A.shiftLeft(1);
    assertEquals("660", A.toString());
    A.shiftRight(1);
    assertEquals("6", A.toString());
//    BigNumber B = new BigNumberImpl("1234");
//    BigNumber C = new BigNumberImpl("12345");
//    assertEquals(true, A.hashCode() == B.hashCode());
//    assertEquals(false, A.hashCode() == C.hashCode());
  }

}
