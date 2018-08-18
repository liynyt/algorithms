package Algorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class Graph {
	private int V;					//number of node
	private int V_origin;
	private int E_origin;
	private int[] nodes;			//set of nodes
	private int E;					//number of edge
	private Bag<Edge>[] adj;
	private Bag<Edge>[] in;
	private Bag<Link>[] adj_link;
//	private Map<Link, Edge> map;
	private double[][] weightValue;

	//create graph with weight input
	public Graph(File file) {
		BufferedReader infile;		
		try{
			infile = new BufferedReader(new FileReader(file));	
			V_origin =(Integer.parseInt(infile.readLine()));
			V= V_origin;					
			nodes  = new int[V];
			for(int v=0; v<V; v++) {
				nodes[v]= v;
			}
			int e = (Integer.parseInt(infile.readLine()));
			E_origin = e;
			adj = (Bag<Edge>[]) new Bag[40*V];
			in = (Bag<Edge>[]) new Bag[40*V];
			adj_link= (Bag<Link>[]) new Bag[V];
			weightValue = new double[V][V];
//			map = new HashMap<>();
			for(int v=0; v<V; v++) {
				adj[v]= new Bag<Edge>();
				in[v]= new Bag<Edge>();
				adj_link[v]= new Bag<Link>();
			}
			for(int i=0; i<e; i++) {
				int src = (Integer.parseInt(infile.readLine()));
				int dst = (Integer.parseInt(infile.readLine()));
				double weight = (Double.parseDouble(infile.readLine()));
				Link link = new Link(src, dst, weight);
				weightValue[src][dst] = weight;
				Edge edge = new Edge(src, dst, weight);
				addEdge(edge);
				addLink(link);
//				map.put(link, edge);
			}
			infile.close();	
		}
		catch (IOException e){
			System.out.print("error");
			e.printStackTrace();
		}
	}
	
	//create graph with weight set by links' slots
	public Graph(File file,File file1) {
		BufferedReader infile;
		BufferedReader linkfile;
		try{
			infile = new BufferedReader(new FileReader(file));	
			linkfile = new BufferedReader(new FileReader(file1));	
			V_origin =(Integer.parseInt(infile.readLine()));
			V= V_origin;					
			nodes  = new int[V];
			for(int v=0; v<V; v++) {
				nodes[v]= v;
			}
			int e = (Integer.parseInt(infile.readLine()));
			E_origin = e;
			adj = (Bag<Edge>[]) new Bag[40*V];
			in = (Bag<Edge>[]) new Bag[40*V];
			adj_link= (Bag<Link>[]) new Bag[V];
			weightValue = new double[V][V];
//			map = new HashMap<>();
			for(int v=0; v<V; v++) {
				adj[v]= new Bag<Edge>();
				in[v]= new Bag<Edge>();
				adj_link[v]= new Bag<Link>();
			}
			for(int i=0; i<e; i++) {
				int src = (Integer.parseInt(linkfile.readLine()));
				int dst = (Integer.parseInt(linkfile.readLine()));
				double value = (Double.parseDouble(linkfile.readLine())); 
				double weight = 1.0/value;
				weightValue[src][dst] = weight;
				Link link = new Link(src, dst, (int)value, weight);
				Edge edge = new Edge(src, dst, weight);
				addEdge(edge);
				addLink(link);
//				map.put(link, edge);
			}
			infile.close();	
		}
		catch (IOException e){
			System.out.print("error");
			e.printStackTrace();
		}
	}
	
	//initial create graph with weight set by input and calculated by links' slots
	public Graph(File file,boolean flag_slots) {
		BufferedReader infile;		
		try{
			infile = new BufferedReader(new FileReader(file));	
			V_origin =(Integer.parseInt(infile.readLine()));
			V= V_origin;
			int e = (Integer.parseInt(infile.readLine()));
			E_origin = e;
			nodes  = new int[V];
			adj = (Bag<Edge>[]) new Bag[40*V];
			in = (Bag<Edge>[]) new Bag[40*V];
			adj_link= (Bag<Link>[]) new Bag[V];
			weightValue = new double[V][V];
			for(int v=0; v<V; v++) {
				nodes[v]= v;
				adj[v]= new Bag<Edge>();
				in[v]= new Bag<Edge>();
				adj_link[v]= new Bag<Link>();
			}
			for(int i=0; i<e; i++) {
				int src = (Integer.parseInt(infile.readLine()));
				int dst = (Integer.parseInt(infile.readLine()));
				double weight = (Double.parseDouble(infile.readLine()));
				Link link = new Link(src, dst, weight);
				weight = 1.0/link.get_slotNumber();
				weightValue[src][dst] = 1.0/link.get_slotNumber();
				Edge edge = new Edge(src, dst, weight);
				addEdge(edge);
				addLink(link);
//				map.put(link, edge);
			}
			infile.close();	
		}
		catch (IOException e){
			System.out.print("open file error");
			e.printStackTrace();
		}
	}

	//again create graph with graph before
	public Graph(Graph graph) {
		double data[][] = graph.get_weightValue();
		E_origin = graph.get_E_origin();
		V_origin = graph.get_V_origin();
		V = graph.get_V_origin();
		nodes  = new int[V];
		adj = (Bag<Edge>[]) new Bag[40*V];
		in = (Bag<Edge>[]) new Bag[40*V];
		adj_link= (Bag<Link>[]) new Bag[V];
		weightValue = data;
		for(int v=0; v<V; v++) {
			nodes[v]= v;
			adj[v]= new Bag<Edge>();
			in[v]= new Bag<Edge>();
			adj_link[v]= new Bag<Link>();
		}
		for(Link l:graph.links()) {
			Link link = new Link(l);
			addLink(link);
			int src = link.get_src();
			int dst = link.get_dest();
			double weight = 1.0/link.get_slotUnused();
			Edge edge = new Edge(src, dst, weight);
			addEdge(edge);
//			map.put(link, edge);
		}
	}

	//initial create graph 
	public Graph(Graph graph, Bag<Link> links) {
		E_origin = graph.get_E_origin();
		V_origin = graph.get_V_origin();
		V = graph.get_V_origin();
		nodes  = new int[V];
		adj = (Bag<Edge>[]) new Bag[10*V];
		in = (Bag<Edge>[]) new Bag[10*V];
		adj_link= (Bag<Link>[]) new Bag[V];
		weightValue = new double[V][V];
		for(int v=0; v<V; v++) {
			nodes[v]= v;
			adj[v]= new Bag<Edge>();
			in[v]= new Bag<Edge>();
			adj_link[v]= new Bag<Link>();
		}
		for(Link l:links) {
			Link link = new Link(l);
			link.reset_slot_mask();
			link.set_slotUnused(link.get_slotNumber());
			int src = link.get_src();
			int dst = link.get_dest();
			double weight = 1.0/link.get_slotNumber();
			weightValue[src][dst] = weight;
			Edge edge = new Edge(src, dst, weight);
			addEdge(edge);
			addLink(link);
//			map.put(link, edge);
		}
	}
	
	public int get_V() {
		return V;
	}
	public int get_V_origin() {
		return V_origin;
	}
	public int get_E() {
		return this.E;
	}
	public int get_E_origin() {
		return this.E_origin;
	}

	public int get_outdegree(int v) {
        return adj[v].size();
    }
	public int get_indegree(int v) {
        return in[v].size();
    }
	
	public double[][] get_weightValue(){
		return weightValue;
	}
	public void set_weightValue(int i, int j, double value){
		weightValue[i][j] = value;
	}
	public void set_weightValue(double[][] weight){
		weightValue = weight;
	}

	public void addEdge(Edge e) {
        int v = e.from();
        int w = e.to();
        adj[v].add(e);
        in[w].add(e);
        this.E++;
    }
	public void addLink(Link l) {
		int v = l.get_src();
        adj_link[v].add(l);
	}
	
	public Iterable<Link> adj_link(int v){
		return adj_link[v];
	}
	public Iterable<Edge> adj(int v){
		return adj[v];
	}
	public Iterable<Edge> in(int v){
		return in[v];
	}
	//get edges
	public Iterable<Edge> edges() {
        Bag<Edge> list = new Bag<Edge>();
        for (int v = 0; v < V; v++) {
            for (Edge e : adj(v)) {
                list.add(e);
            }
        }
        return list;
    }
	
	public Iterable<Link> links() {
        Bag<Link> list = new Bag<Link>();
        for (int v = 0; v < V_origin; v++) {
            for (Link e : adj_link(v)) {
                list.add(e);
            }
        }
        return list;
    }
	public int[] get_nodes() {
		return nodes;
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append(V + " vertex," + E + " edges\n");
		for(int v=0; v<V; v++) {
			string.append(v + ": ");
			for(Edge e:this.adj[v]) {
				string.append(e + " ");
			}
			string.append("\n");
			string.append("in");
			for(Edge e:this.in[v]) {
				string.append(e + " ");
			}
			string.append("\n");			
		}
		return string.toString();
	}
	
	//all nodes of ni's next create links with node[V]
	public int addNode(int ni, int preni) {	
		Iterator<Edge> iterator = this.in(ni).iterator();
		adj[V] = new Bag<Edge>();
		in[V] = new Bag<Edge>();
		while( iterator.hasNext()) {
			Edge edge = iterator.next();
			int id = edge.from();
			if(id != preni) {	
				addEdge(new Edge(id, V, edge.get_weight()));			
			}
		}
    	return V++;
    }

	public static void main(String[] args) {
		File file = new File("D:\\eclipse\\workspace\\Simulation\\graph.txt");
		Graph graph = new Graph(file);
		System.out.println(graph);
		graph.set_weightValue(0, 8, 2);
//		Graph graph2 = new Graph(graph.get_weightValue());
//		System.out.println(graph2);
		
	}
}
