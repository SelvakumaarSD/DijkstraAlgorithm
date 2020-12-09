# DijkstraAlgorithm
In this case we are applying a weighted graph to develop a solution for a travel agent’s trip planner.

The travel agent performs the following actions for above flight connection graph:
- Asks the client for their starting city and destination city
- The client then plans a round trip, which begins from the given start city and ends in the same city, with transit cities, if there are no direct connections available.
- The round trip is planned as follows:
o The planned forward trip finds the cheapest path from the starting city to the destination city, with transit cities in the forward trip, 
if there are no direct connections available.
o Next, the planned return trip finds the cheapest path from the destination city to the starting city, with transit cities in the return trip, 
where none of the transit cities in the forward trip (if any), are common with the transit cities on the return trip.
o If the start and destination cities are directly connected, the return trip will not be on the same directly connected path.
o Example:
- Start at A, End at B
- Forward trip: A – P – Q – R – B
- Return trip: B – S – T – U – A
- The travel agent also provides the cost for the forward trip, the return trip, and the total cost of the round trip for the client.

Based on the above facts, a weighted graph using Dijkstra's algorithm has been implemented to plan the roundtrip of salesman 
such that he does not travel through the same cities during forward and return trip
