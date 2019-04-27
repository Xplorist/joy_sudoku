package sudoku;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

import util.RandomGenerator;
import util.RowGenerator;

public class Sudoku {
	private int count=0;
	private int[][] rows=new int[9][9];
	private int[][] cols=new int[9][9];
	
	@SuppressWarnings("unchecked")
	private HashSet<Integer>[] zons=new HashSet[9];
	
	public Sudoku(){
		for(int i=0;i<9;i++){
			zons[i]=new HashSet<Integer>();
		}
		
		rows[0]=RowGenerator.getRow();
		generateCols();
		generateZons();
		foreachRows();
	}
	
	public void generateCols(){
		for(int i=0;i<rows.length;i++){
			for(int j=0;j<rows[i].length;j++){
				cols[j][i]=rows[i][j];
			}
		}
	}
	
	public void generateZons(){
		for(int i=0;i<rows.length;i++){
			for(int j=0;j<rows[i].length;j++){
				int zonsNo=j/3+i/3*3;
				zons[zonsNo].add(rows[i][j]);
			}
		}
	}
	
	//core algorithm
	public int generateNumber(int i,int j){
		int[] nowRow=rows[i];
		int[] nowCol=cols[j];
		int zonsNo=j/3+i/3*3;//use imagination
		HashSet<Integer> nowZon=zons[zonsNo];
		
		HashSet<Integer> total=new HashSet<Integer>();
		for(int x=0;x<10;x++){
			total.add(x);
		}
		HashSet<Integer> nowHaveSet=new HashSet<Integer>();
		for(int n:nowRow){
			nowHaveSet.add(n);
		}
		
		for(int n:nowCol){
			nowHaveSet.add(n);
		}
		
		for(Integer n:nowZon){
			nowHaveSet.add(n);
		}
		
		for(Integer n:nowHaveSet){
			total.remove(n);
		}
		
		int number=RandomGenerator.getRandomInHashSet(total);
		
		return number;
	}
	
	public void foreachRows(){
		for(int i=1;i<rows.length;i++){
			boolean flag=false;
			for(int j=0;j<rows[i].length;j++){
				rows[i][j]=generateNumber(i,j);
				if(rows[i][j]==0){
					flag=true;
					count++;
					System.out.println("rowNo=0:"+Arrays.toString(rows[0]));
					System.out.println("rowNo="+i+":"+Arrays.toString(rows[i]));
					//show();
					rows[i]=new int[9];
					System.out.println(count+"...");
					break;
				}
			}
			
			if(flag){
				i--;
				if(count>100){
					show();
					break;
				}
			}else{
				generateCols();
				generateZons();
			}
		}
	}
	
	public static void main(String[] args){
		int[][] result=start();
		result=digHole(result);
		printResult(result);
	}
	
	public static int[][] digHole(int[][] a){
		for(int i=0;i<a.length;i++){
			for(int j=0;j<4;j++){
				int random=new Random().nextInt(9);
				if(a[i][random]!=0){
					a[i][random]=0;
				}else{
					j--;
				}
			}
		}
		
		System.out.println("FinalResult:");
		for(int i=0;i<a.length;i++){
			System.out.println(Arrays.toString(a[i]));
		}
		
		return a;
	}
	
	public static void printResult(int[][] a){
		System.out.println("Sudoku:");
		for(int j=0;j<22;j++){
			System.out.print("-");
		}
		System.out.println();
		for(int i=0;i<a.length;i++){
			for(int j=0;j<a[i].length;j++){
				if(j==0){
					System.out.print("|");
				}
				System.out.print(a[i][j]+" ");
				if((j+1)%3==0){
					System.out.print("|");
				}
			}
			if((i+1)%3==0){
				System.out.println();
				for(int j=0;j<22;j++){
					System.out.print("-");
				}
			}
			System.out.println();
		}
	}
	
	public static int[][] start(){
		Sudoku sudoku=new Sudoku();
		int[][] tempRows=sudoku.rows;
		
		System.out.println("Rows:");
		boolean flag=false;
		for(int i=0;i<tempRows.length;i++){
			System.out.println(Arrays.toString(tempRows[i]));
			for(int j=0;j<tempRows[i].length;j++){
				if(tempRows[i][j]==0){
					flag=true;
					break;
				}
			}
			
			if(flag){
				break;
			}
		}
		
		if(flag){
			return start();
		}
		
		return tempRows;
	}
	
	public static void start1(){
		Sudoku sudoku=new Sudoku();
		int[][] tempRows=sudoku.rows;
		int[][] tempCols=sudoku.cols;
		HashSet<Integer>[] tempZons=sudoku.zons;
		
		System.out.println("Rows:");
		for(int i=0;i<tempRows.length;i++){
			System.out.println(Arrays.toString(tempRows[i]));
		}
		
		System.out.println("\nCols:");
		for(int i=0;i<tempCols.length;i++){
			System.out.println(Arrays.toString(tempCols[i]));
		}
		
		System.out.println("\nZons:");
		for(int i=0;i<tempZons.length;i++){
			System.out.print("[");
			for(Integer in:tempZons[i]){
				System.out.print(in+",");
			}
			System.out.println("]");
		}
	}
	
	public void show(){
		System.out.println("Rows:");
		for(int i=0;i<rows.length;i++){
			System.out.println(Arrays.toString(rows[i]));
		}
	}
	
	public void show1(){
		System.out.println("Rows:");
		for(int i=0;i<rows.length;i++){
			System.out.println(Arrays.toString(rows[i]));
		}
		
		System.out.println("\nCols:");
		for(int i=0;i<cols.length;i++){
			System.out.println(Arrays.toString(cols[i]));
		}
		
		System.out.println("\nZons:");
		for(int i=0;i<zons.length;i++){
			System.out.print("[");
			for(Integer in:zons[i]){
				System.out.print(in+",");
			}
			System.out.println("]");
		}
	}
}
