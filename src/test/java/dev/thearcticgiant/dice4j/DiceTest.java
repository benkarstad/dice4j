package dev.thearcticgiant.dice4j;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class DiceTest{

	@Test
	void roll(){
		String[] testExpressions = new String[]{
				"",
				"0",
				"+0",
				"-0",
				"5",
				"+5",
				"-5",
				"0d20",
				"0d20+0",
				"0d20-0",
				"0d20+5",
				"0d20-5",
				"d20",
				"d20+0",
				"d20-0",
				"d20+5",
				"d20-5",
				"3d6",
				"3d6+0",
				"3d6-0",
				"3d6+5",
				"3d6-5"
		},
		expectedNames = new String[]{
				"0",
				"0",
				"0",
				"0",
				"5",
				"5",
				"-5",
				"0",
				"0",
				"0",
				"5",
				"-5",
				"1d20",
				"1d20",
				"1d20",
				"1d20+5",
				"1d20-5",
				"3d6",
				"3d6",
				"3d6",
				"3d6+5",
				"3d6-5"
		};

		Iterator<String>
				testExpressionsIterator = Arrays.stream(testExpressions).iterator(),
				expectedNamesIterator = Arrays.stream(expectedNames).iterator();

		while(testExpressionsIterator.hasNext()){
			assertEquals(expectedNamesIterator.next(), Dice.roll(testExpressionsIterator.next()).getName());
		}
	}
}