package KruskalsAlgorithm;

import java.util.Comparator;

public class DistComparator implements Comparator<Edge> {

	@Override
	public int compare(Edge edge1, Edge edge2) {
		if(edge1.getDistance()> edge2.getDistance()){
			return 1;
		} else if(edge1.getDistance() < edge2.getDistance()) {
			return -1;
		}
		return 0;
	}

}