import java.util.*;

class Edge {
    int dest;
    int weight;

    public Edge(int dest, int weight) {
        this.dest = dest;
        this.weight = weight;
    }
}




class Graph {
    private int V;
    private LinkedList<Edge>[] adj;


    public Graph(int V) {
        this.V = V;
        adj = new LinkedList[V];
        for (int i = 0; i < V; ++i)
            adj[i] = new LinkedList<>();


    }


    void addEdge(int src, int dest, int weight) {
        Edge edge = new Edge(dest, weight);
        adj[src].add(edge);
    }

    void printShortestPath(int src) {
        int[] dist = new int[V];
        boolean[] sptSet = new boolean[V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;

            for (Edge edge : adj[u]) {
                int v = edge.dest;
                int weight = edge.weight;

                if (!sptSet[v] && dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v])
                    dist[v] = dist[u] + weight;
            }
        }

        System.out.println("Vertex \t Distance from Source \t char");
        for (int i = 0; i < V; i++)
            System.out.println(i + " \t\t " + dist[i]);
    }

    int minDistance(int dist[], boolean sptSet[]) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }
}

public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph(5);

        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 5);
        graph.addEdge(1, 3, 1);
        graph.addEdge(1, 2, 2);
        graph.addEdge(2, 1, 3);
        graph.addEdge(2, 3, 9);
        graph.addEdge(2, 4, 2);
        graph.addEdge(3, 4, 4);
        graph.addEdge(4, 3, 6);
        graph.addEdge(4, 0, 7);

        graph.printShortestPath(0);
    }
}