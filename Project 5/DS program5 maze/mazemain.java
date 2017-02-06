
import java.util.Random;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

public class mazemain {
	
	int rows;
	int coloumns;
	int arrSize;
	DisjointSets ds;
	Random r = new Random();
   HashMap<Integer,ArrayList<Integer>> pairs = new HashMap<>();
   
   
	public mazemain(int r,int c){
		rows = r;
		coloumns = c;
		arrSize = rows * coloumns;
		ds = new DisjointSets(arrSize);
		createMaze();
      for(int j = 0;j < c;j++){
			System.out.print(" _");
		}
		System.out.println();
		for(int i = 0;i < r;i++){
			System.out.print("|");
			for(int j = 0;j < c;j++){
				int n = 1;
				int b = 1;
				ArrayList<Integer> tmp = pairs.get(i*coloumns+j);
				for(int k : tmp){
					int diff = k - (i*coloumns+j);
					if(diff == 1){
						n = 0;
					}
					if(diff == coloumns){
						b = 0;
					}
				}
				if(b == 1){
					System.out.print("_");
				}else{
					System.out.print(" ");
				}
				if(n == 1){
					System.out.print("|");
				}else{
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
	
	public int selectBox(int cent){
		int sel = -1;
		while(sel < 0 || sel >= arrSize){
			int num = r.nextInt(4);
			switch(num){
			case 0:
				sel = cent - coloumns;
				break;
			case 1:
				if(cent % coloumns == 0){
					sel = -1;
				}else{
					sel = cent - 1;
				}
				break;
			case 2:
				if(cent % coloumns == coloumns-1){
					sel = -1;
				}else{
					sel = cent+1;
				}
				break;
			case 3:
				sel = cent + coloumns;
				break;
			}
		}
		
		return sel;
	}
	public void createMaze(){
		int f1;
		int f2;
		int box;
		for(int i = 0;i < arrSize;i++){			
			box = selectBox(i);
			f1 = ds.find(i);	
			f2 = ds.find(box);
			if(f1 != f2){
				ds.union(f1, f2);
				if(pairs.containsKey(i)){
					pairs.get(i).add(box);
				}else{
					ArrayList<Integer> tmp = new ArrayList<>();
					tmp.add(box);
					pairs.put(i, tmp);
				}
				if(pairs.containsKey(box)){
					pairs.get(box).add(i);
				}else{
					ArrayList<Integer> tmp = new ArrayList<>();
					tmp.add(i);
					pairs.put(box, tmp);
				}
			}
		}
		for(int i = 0;i < arrSize;i++){
			for(int j = 0;j < 4;j++){
				box = selectBox(i,j);
				if(box >= 0 && box < arrSize){
					f1 = ds.find(i);	
					f2 = ds.find(box);
					if(f1 != f2){
						ds.union(f1, f2);
						if(pairs.containsKey(i)){
							pairs.get(i).add(box);
						}else{
							ArrayList<Integer> tmp = new ArrayList<>();
							tmp.add(box);
							pairs.put(i, tmp);
						}
						if(pairs.containsKey(box)){
							pairs.get(box).add(i);
						}else{
							ArrayList<Integer> tmp = new ArrayList<>();
							tmp.add(i);
							pairs.put(box, tmp);
						}
					}
				}
		    }
		}
	}
	
	
	
	public int selectBox(int cent,int num){
		int sel = -1;
		switch(num){
		case 0:
			sel = cent - coloumns;
			break;
		case 1:
			if(cent % coloumns == 0){
				sel = -1;
			}else{
				sel = cent - 1;
			}
			break;
		case 2:
			if(cent % coloumns == coloumns-1){
				sel = -1;
			}else{
				sel = cent+1;
			}
			break;
		case 3:
			sel = cent + coloumns;
			break;
		}
		return sel;
	}
	
	
	
	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		System.out.println("coloumns = ");
		int c = sc.nextInt();
		System.out.println("rows = ");
		int r = sc.nextInt();
		mazemain m = new mazemain(r,c);
		sc.close();
	}
}
