package util;

import java.util.Random;

public class RowGenerator {
	public static int[] getRow(){
		int[] temp=new int[9];
		int[] row=new int[9];
		
		for(int i=0;i<9;i++){
			temp[i]=i+1;
		}
		
		for(int i=0;i<9;i++){
			int random=new Random().nextInt(9);
			
			if(temp[random]!=-1){
				row[i]=temp[random];
				temp[random]=-1;
			}else{
				i--;
			}
		}
		
		return row;
	}
}
