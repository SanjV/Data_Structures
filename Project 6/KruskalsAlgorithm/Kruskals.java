package KruskalsAlgorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Kruskals {

	static Map<String, Integer> convertNameToIntMap = new HashMap<>();
	static Map<Integer, String> convertIntToNameMap = new HashMap<>();

	static int numVertices = 0;

	public static void main(String[] args) {

		// edges formed before sending the Kruskal
		ArrayList<Edge> edges = new ArrayList<Edge>();

		// edges formed after applying Kruskals ont he graph
		ArrayList<Edge> kruskalEdges = new ArrayList<Edge>();

		String file = "assn9_data.csv";
		// reads the entried in the csv file and forms the edges
		edges = readFromExcel(file);

		// Call Kruskals
		kruskalEdges = kruskal(edges, numVertices);

		// Print Kruskals edges
		int sumOfAllDistance = 0;
		for (int i = 0; i < kruskalEdges.size(); i++) {
			String source = convertIntToNameMap.get((kruskalEdges.get(i).getSource()));
			String destination = convertIntToNameMap.get((kruskalEdges.get(i).getDestn()));
			int distance = kruskalEdges.get(i).getDistance();
			sumOfAllDistance += distance;
			System.out.println(source + " , " + destination + " --> " + distance);
		}
		System.out.println("Sum of All Distance in the Tree : " + sumOfAllDistance);
	}

	/**
	 * Reads csv file and returns the arraylist of the complete edge.
	 * First read the csv file line by line add it to eges list
	 * each edge list added to the final completeEdgeList
	 * @param file
	 * @return
	 */
	private static ArrayList<Edge> readFromExcel(String file) {

		File link_data = new File(file);
		FileReader fileReader = null;
		BufferedReader br = null;
		ArrayList<Edge> completeEdgeList = new ArrayList<Edge>();
		try {
			fileReader = new FileReader(link_data);
			br = new BufferedReader(fileReader);
			String line = "";
			String lines = "";
			String lineArray[];

			
			int i = 1;

			while ((line = br.readLine()) != null) {
				lineArray = line.split(",");
				convertNameToIntMap.put(lineArray[0], i);
				convertIntToNameMap.put(i, lineArray[0]);
				lines += line + "\n";
				i++;
				numVertices++;

			}
			lineArray = lines.split("\n");

			ArrayList<Edge> singleLineEdge;

			for (i = 1; i < lineArray.length; i++) {
				singleLineEdge = new ArrayList<Edge>();
				singleLineEdge = addEdge(lineArray[i]);

				for (int j = 0; j < singleLineEdge.size(); j++) {
					completeEdgeList.add(singleLineEdge.get(j));
				}

			}
			br.close();

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return completeEdgeList;

	}

	/**
	 * Each line is read and added to a arraylist and returned
	 * @param line
	 * @return
	 */
	private static ArrayList<Edge> addEdge(String line) {
		String[] data = line.split(",");
		/* The first parameter in each line corresponds to city "u" */
		int source = convertNameToIntMap.get(data[0]);

		//Data read is in the formal source,destination,distance
		//REad accordingly and save to arraylist
		ArrayList<Edge> singleLineEdge = new ArrayList<Edge>();
		for (int j = 1; j < data.length; j = j + 2) {

			int destn = convertNameToIntMap.get(data[j]);
			int distance = Integer.parseInt(data[j + 1]);
			Edge e = new Edge();

			e.setSource(source);
			e.setDestn(destn);
			e.setDistance(distance);
			singleLineEdge.add(e);
		}

		return singleLineEdge;
	}
	
	
	/**
	 * Taken from the Book.
	 * This method 
	 * @param edges
	 * @param numVertices
	 * @return
	 */
	static ArrayList<Edge> kruskal(List<Edge> edges, int numVertices) {
		//Disjoint set of size as number of edges
		DisjSets ds = new DisjSets(edges.size());
		
		//Comparator compares the distance between the vertices
		Comparator<Edge> comparator = new DistComparator();
		
		//Priority queue with comparator is created to build it 
		//with giving distance between the two cities as the de queue criteria.
		PriorityQueue<Edge> pq = new PriorityQueue<>(edges.size(), comparator);
		ArrayList<Edge> mst = new ArrayList<>();

		//Add all the edges to the priority queue
		for (int i = 0; i < edges.size(); i++) {
			pq.add(edges.get(i));
		}

		while (mst.size() != numVertices - 1) {
			Edge e = pq.remove(); // Edge e = (u, v)
			int uset = ds.find(e.getSource());
			int vset = ds.find(e.getDestn());
			if (uset != vset) {
				// Accept the edge
				mst.add(e);
				ds.union(uset, vset);
			}
		}
		return mst;
	}

}
