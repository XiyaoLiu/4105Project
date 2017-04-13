import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class OutRunner {

	
	public static void main(String[] args){
		int n = 16; // nodes number n
		
		
		boolean[][] graph = graphGenerator(n);
		
		
//		
//		System.out.println(graphToString(ToolBox.tranAlg(graph)));
//		
//		System.out.println(ToolBox.getDegree(graph).toString());
		System.out.println("System generating combinations...");
		List<LinkedList<Integer>> combinations = ToolBox.combination(graph.length-1);
		
		System.out.println("System sorting combinations...");
		ToolBox.sortLinkedList(combinations);
		int hope = naiveBruteForce(graph, combinations);
		
//		for(int i = 0; i<combinations.size(); i++){
//			System.out.println(combinations.get(i).toString());
//		}
		
		System.out.print("the naive brute force answer: ");
		System.out.println(combinations.get(hope).toString());
		
		System.out.println(graphToString(graph));
//		LinkedList<Integer> base = new LinkedList<Integer>();
//		base.add(1);
//		base.add(2);
//		base.add(3);
//		base.add(4);
//		LinkedList<Integer> copy = new LinkedList<Integer>(base);
//		System.out.println(copy.toString());
//		
//		base.add(5);
//		
//		System.out.println(copy.toString());
//		System.out.println(base.toString());
//		List<LinkedList<Integer>> lists = ToolBox.combination(5);
//		ToolBox.sortLinkedList(lists);
		
		
	}
	
	public static boolean[][] graphGenerator(int numNode){
		int n = numNode;
		boolean[][] graph = new boolean[n][n];
		int maxEdges = n*(n-1)/2;

		List<Integer> edges = new ArrayList<Integer>();
		for(int i=0; i<maxEdges; i++){
			edges.add(i);
		} 
		Collections.shuffle(edges, new Random());
		int edgesNum = edges.get((int) (Math.random()*maxEdges));	
		

		List<Integer[]> pos = new ArrayList<Integer[]>();
		
		for(int i=0;i<n;i++){
			for(int j=i+1; j<n; j++){
				Integer[] cur = {i,j};
				pos.add(cur);
			}
		}
		
		for(int i=0; i< edgesNum; i++){
		
			boolean flag = false;
			do{
				long seed = System.nanoTime();
				Collections.shuffle(pos, new Random(seed));
				Integer[] position = pos.get(0);
				if( !graph[position[0]][position[1]]){
					graph[position[0]][position[1]] = true;
					graph[position[1]][position[0]] = true;
					flag = true;
				}
			}
			while(!flag);
			pos.remove(0);
		}
		
		return graph;
	}
	
	public static String graphToString(boolean[][] graph){
		StringBuffer buffer = new StringBuffer();
		int n = graph.length;
		buffer.append("      ");
		for(int size = 0; size<graph.length; size++){
//			System.out.println(graph.length);
			buffer.append(size);
			for(int digit = 0; digit < 5-String.valueOf(size).length(); digit++){
				buffer.append(" ");
			}
			buffer.append(" ");
		}
		buffer.append("\n");
//		System.out.println(buffer);
		for(int i=0;i<n;i++){
			buffer.append(i);
			for(int digit = 0; digit < 5-String.valueOf(i).length(); digit++){
				buffer.append(" ");
			}
			buffer.append(" ");
			for(int j=0;j<n;j++){
				buffer.append( graph[i][j] );
				if(graph[i][j]){
					
					buffer.append( " " );
				}
				
				buffer.append(" ");
			}
			buffer.append("\n");
		}
		
		return buffer.toString();
	}
	
	public static int naiveBruteForce(boolean[][] graph, List<LinkedList<Integer>> combinations){
		
		for(int combinationCounter = 0; combinationCounter < combinations.size(); combinationCounter++){
			System.out.println("checked/totle combinations: " + combinationCounter + "/" + combinations.size());
			boolean edges = false; //assume there is no edges
			LinkedList<Integer> combination = combinations.get(combinationCounter);
			//double loop
			for(int i = 0; i < combination.size() && !edges; i++){
				for(int j = i+1; j < combination.size() && !edges; j++){
					edges = graph[combination.get(i)][combination.get(j)];
				}
			}
			if(!edges){
				return combinationCounter;
			}
			
		}
		
		
		return 0;
	}
}
