package dev.thearcticgiant.dice4j;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DieTest{

	@Test
	void constructor(){
		assertThrows(RuntimeException.class, ()->new Die(0, 0));
		assertThrows(RuntimeException.class, ()->new Die(-1, 1));
	}

	@Test
	void fudge(){
		Die die = new Die(6);
		for(int i=0; i<6; i++){
			assertEquals(i, die.fudge(i));
			assertEquals(i, die.read());
		}
	}

	@Test
	void roll(){
		Die die = new Die(Integer.MAX_VALUE);
		for(int i=0; i<2<<5; i++){
			assertEquals(die.roll().read(), die.read());
			assertNotEquals(die.read(), die.roll().read());
		}
	}

	@Test
	void read(){
		Die d6 = new Die(6);
		assertEquals(d6.roll().read(), d6.read());
	}

	@Test
	void getName(){
		Die d6 = new Die(6);
		assertEquals("1d6", d6.getName());
	}

	@Test
	void testToString(){
		Die d6 = new Die(6);
		d6.fudge(6);
		assertEquals("1d6 (6)", d6.toString());
	}

	@Test
	void testSeededDice(){
		Random random = new Random();
		for(int i = 0; i<2<<3; i++){
			Die d1, d2;
			long seed = random.nextLong();
			d1 = new Die(Integer.MAX_VALUE, seed);
			d2 = new Die(Integer.MAX_VALUE, seed);
			for(int i1=0; i1<2<<5; i1++){
				assertEquals(d1.roll().read(), d2.roll().read());
			}
		}
	}
}