package JUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MathUtilsTest 
{
	MathUtils mathUtils;
	
	@BeforeAll  //This will give error because JUnit does not have a class to run that method.that is why it have to be static
	static void beforeAllInit()
	{
		System.out.println("This needs to run before all....");
	}
	
	@BeforeEach
	void init()
	{
	   mathUtils= new MathUtils();
	}
	
	@AfterEach 
	void clean()
	{
		System.out.println("Cleaning up.......");
	}

	@Test
	void test() 
	{
		int value=2;
		assumeTrue(value==2);//basically telling jvm that when you run this i am assuming that this value should be true.If its not correct please do not run this test
		int expected=2;
		int actual= mathUtils.add(1, 1);
		assertEquals(expected, actual, "The add method should add two numbers");
	}
	
	@Test
	@Disabled //help in skipping methods
	void  testDivide()
	{
		assertThrows(ArithmeticException.class, () ->mathUtils.divide(1, 0), "Divide by zero should throw") ;
		//assertThrows(NullPointerException.class, () ->mathUtils.divide(1, 0), "Divide by zero should throw") ; 
	}
	
	@Nested
	@DisplayName("add method")
	class AddTest
	{
		@Test
		@DisplayName("When adding two positive numbers")
		void testAddPositive()
		{
			assertEquals(2, mathUtils.add(1, 1), "should return the right sum ");
		}
		
		@Test
		@DisplayName("When adding two positive numbers")
		void testAddNegative()
		{
			assertEquals(-2, mathUtils.add(-1, -1), "should return the right sum ");
		}
	}
	
	@Test
	@DisplayName("multiply method")
	void testMultiply()
	{
		assertAll(
				() -> assertEquals(4, mathUtils.multiply(2, 2)),
				() -> assertEquals(0, mathUtils.multiply(2, 0)),
				() -> assertEquals(-2, mathUtils.multiply(2, -1))	
				);
	}
	
	//@RepeatedTest instead of @Test
	@RepeatedTest(3)   //run this test 3 times
	void testMultiply1(RepetitionInfo repetitionInfo)
	{
		 assertEquals(4, mathUtils.multiply(2, 2));
	}
	

}
