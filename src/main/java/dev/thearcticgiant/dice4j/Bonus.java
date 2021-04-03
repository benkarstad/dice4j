package dev.thearcticgiant.dice4j;

/**
 * A static numerical bonus to a roll.
 * Once created, its value cannot be changed, and calls to <code>roll</code> have no effect.
 */
public class Bonus implements Rollable{
	public final int value;

	public Bonus(int value){
		this.value = value;
	}

	/**
	 * Return this instance of <code>Bonus</code>, has no effect.
	 * @return This instance.
	 */
	@Override
	public Bonus roll(){
		return this;
	}

	@Override
	public int read(){
		return value;
	}

	/**
	 * Has no effect.
	 */
	@Override
	public void lock(){}

	/**
	 * Always returns true.
	 * @return True
	 */
	@Override
	public boolean isLocked(){
		return true;
	}

	@Override
	public String getName(){
		return Integer.toString(value);
	}

	@Override
	public String getMarkdownName(){
		return getName();
	}

	@Override
	public String toString(){
		return Integer.toString(value);
	}

	@Override
	public String toMarkdownString(){
		return toString();
	}
}
