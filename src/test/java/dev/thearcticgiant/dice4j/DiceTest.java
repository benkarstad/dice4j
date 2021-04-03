package dev.thearcticgiant.dice4j;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiceTest{

	@Test
	void constructor(){
		assertThrows(RuntimeException.class, ()->new Dice(1, 0, 0));
		assertThrows(RuntimeException.class, ()->new Dice(-1, 1));
	}

	@Test
	void getName(){
		Dice
			oneDie = new Dice(1, 20),
			noBonus = new Dice(3, 6);

		assertEquals("1d20", oneDie.getName());
		assertEquals("3d6", noBonus.getName());
	}
}