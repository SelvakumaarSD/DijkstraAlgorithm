
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoundTripPlanner {
	// user inputs for the source and destination
	private int startCityIndex;
	private int endCityIndex;

	// Graph created using the following vertices and edges
	private WeightedGraph<String> flightNetwork;

	// array of vertices
	private String[] cities;
	// array of weighted edges [source][dest][weight]
	private int[][] connections;

	// forward and return route cities lists and cost of trip
	private List<String> forwardRoute;
	private double forwardRouteCost;
	private List<String> returnRoute;
	private double returnRouteCost;

	public WeightedGraph<String>.ShortestPathTree forwardPathTree;
	public WeightedGraph<String>.ShortestPathTree returnPathTree;

	/*
	 * Constructor:
	 * - Assigns class variables
	 * - Invokes generateRoundTrip() method
	 */
	public RoundTripPlanner(String[] cities, int[][] connections, int startCityIndex, int endCityIndex) {
		// TO DO
		this.cities = cities;
		this.connections = connections;
		this.startCityIndex = startCityIndex;
		this.endCityIndex = endCityIndex;

		this.flightNetwork = new WeightedGraph<String>(cities, connections);
		this.generateRoundTrip();
	}


	/*
	 * Round trip generator:
	 * - Creates flight network graph
	 * - Updates forward trip path variable and forward trip cost
	 * - Performs necessary actions for return trip planning
	 * - Updates return trip path variable and return trip cost
	 */
	public void generateRoundTrip() {
		// TO DO
		
		forwardPathTree = flightNetwork.getShortestPath(startCityIndex);
		forwardRoute = forwardPathTree.getPath(endCityIndex);
		forwardRouteCost = forwardPathTree.getCost(endCityIndex);
		List<Integer> forwardRouteIndex = new ArrayList<>();

		for (int i=0; i<forwardRoute.size(); i++){
			forwardRouteIndex.add(flightNetwork.getIndex(forwardRoute.get(i)));
		}
		
		for(int i=0; i<flightNetwork.neighbors.size(); i++){
			for(Edge edge: flightNetwork.neighbors.get(i)){
				if(forwardRouteIndex.contains(edge.u) && forwardRouteIndex.contains(edge.v)){
					((WeightedEdge) edge).weight = Double.POSITIVE_INFINITY;
				}
			}
		}
		
		returnPathTree = flightNetwork.getShortestPath(endCityIndex);
		returnRoute = returnPathTree.getPath(startCityIndex);
		returnRouteCost = returnPathTree.getCost(startCityIndex);
	}


	/*
	 * Trip viewer:
	 * - prints forward trip in the format:
	 * "Forward trip from A to B: A �> P �> Q �> R �> B"
	 * - prints return trip in the same format:
	 * "Return trip from B to A: B �> S �> T �> U �> A"
	 * - prints the costs for the forward trip, return trip, and total trip in the format:
	 *  "Forward route cost: 200.0"
	 *  "Return route cost: 300.0"
	 *  "Total trip cost: 500.0"
	 */
	public void printRoundTrip() {
		// TO DO
		System.out.print("Forward trip from " + flightNetwork.getVertex(startCityIndex) + " to " + flightNetwork.getVertex(endCityIndex) + ": ");
		for (int i=0; i<forwardRoute.size(); i++){
			System.out.print(forwardRoute.get(i));
			if(i != forwardRoute.size()-1){
				System.out.print(" -> ");
			}
		}
		System.out.println();
		System.out.println("Forward Route Cost: " + forwardRouteCost);

		System.out.print("Return trip from " + flightNetwork.getVertex(endCityIndex) + " to " + flightNetwork.getVertex(startCityIndex) + ": ");
		for (int i=0; i<returnRoute.size(); i++){
			System.out.print(returnRoute.get(i));
			if(i != returnRoute.size()-1){
				System.out.print(" -> ");
			}
		}
		System.out.println();
		System.out.println("Return Route Cost: " + returnRouteCost);

		System.out.println("Total trip cost: " + (forwardRouteCost + returnRouteCost));
	}

	// Returns the forwardRoute class variable
	public List<String> getForwardRoute() {
		return forwardRoute;
	}

	// Returns the returnRoute class variable
	public List<String> getReturnRoute() {
		return returnRoute;
	}

	// Returns the forwardRouteCost class variable
	public double getForwardRouteCost() {
		return forwardRouteCost;
	}

	// Returns the returnRouteCost class variable
	public double getReturnRouteCost() {
		return returnRouteCost;
	}



}
