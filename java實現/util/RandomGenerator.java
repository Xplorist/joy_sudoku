package util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class RandomGenerator {
	public static int getRandomInHashSet(HashSet<Integer> set){
		if(set.size()==0){
			return 0;
		}
		int randomNo=new Random().nextInt(set.size());
		ArrayList<Integer> list=new ArrayList<Integer>();
		for(Integer n:set){
			list.add(n);
		}
		
		int number=list.get(randomNo);
		
		return number;
	}
}
