package dev.thearcticgiant.dice4j;

public class Bonus implements Rollable{
	public final int value;

	public Bonus(int value){
		this.value = value;
	}
	@Override
	public Bonus roll(){
		return this;
	}

	@Override
	public int read(){
		return value;
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
