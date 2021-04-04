package dev.thearcticgiant.dice4j;

import java.util.Comparator;

public class KeepHighest extends Keep{
	public KeepHighest(int count){
		super(count, new Comparator<Rollable>(){
			@Override
			public int compare(Rollable r1, Rollable r2){
				return r1.read()-r2.read();
			}
		});
	}
}
