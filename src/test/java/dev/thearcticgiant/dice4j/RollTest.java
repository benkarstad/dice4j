package dev.thearcticgiant.dice4j;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RollTest{

	@Test
	void constructor(){
		assertThrows(RuntimeException.class, ()->new Roll(1, 0, 0));
		assertThrows(RuntimeException.class, ()->new Roll(-1, 1));
	}

	@Test
	void total(){
		Roll roll, negativeBonus, noBonus, onlyBonus, empty;
		roll = new Roll(3, 6, 5);
		negativeBonus = new Roll(3, 6, -7);
		noBonus = new Roll(3, 6);
		onlyBonus = new Roll(0, 0, 1);
		empty = new Roll();

		//fudge all the rolls;
		for(Die die : roll.dice) die.fudge(6);
		for(Die die : negativeBonus.dice) die.fudge(6);
		for(Die die : noBonus.dice) die.fudge(6);

		assertEquals(23, roll.read());
		assertEquals(11, negativeBonus.read());
		assertEquals(18, noBonus.read());
		assertEquals(1, onlyBonus.read());
		assertEquals(0, empty.read());
	}

	@Test
	void getName(){
		Roll roll, oneDie, negativeBonus, noBonus, onlyBonus, onlyNegativeBonus, empty;
		roll = new Roll(3, 6, 5);
		oneDie = new Roll(1, 20);
		negativeBonus = new Roll(3, 6, -7);
		noBonus = new Roll(3, 6);
		onlyBonus = new Roll(0, 0, 1);
		onlyNegativeBonus = new Roll(0, 0, -1);
		empty = new Roll();

		assertEquals("3d6+5", roll.getName());
		assertEquals("1d20", oneDie.getName());
		assertEquals("3d6-7", negativeBonus.getName());
		assertEquals("3d6", noBonus.getName());
		assertEquals("1", onlyBonus.getName());
		assertEquals("-1", onlyNegativeBonus.getName());
		assertEquals("0", empty.getName());
	}

	@Test
	void testToString(){
		Roll roll, negativeBonus, noBonus, onlyBonus, empty;
		roll = new Roll(3, 6, 5);
		negativeBonus = new Roll(3, 6, -7);
		noBonus = new Roll(3, 6);
		onlyBonus = new Roll(0, 0, 1);
		empty = new Roll();

		//fudge all the rolls;
		for(Die die : roll.dice) die.fudge(6);
		for(Die die : negativeBonus.dice) die.fudge(6);
		for(Die die : noBonus.dice) die.fudge(6);

		assertEquals("[6, 6, 6]+5 = 23", roll.toString());
		assertEquals("[6, 6, 6]-7 = 11", negativeBonus.toString());
		assertEquals("[6, 6, 6] = 18", noBonus.toString());
		assertEquals("[]+1 = 1", onlyBonus.toString());
		assertEquals("[] = 0", empty.toString());
	}
}