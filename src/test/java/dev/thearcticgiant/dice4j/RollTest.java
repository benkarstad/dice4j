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
}