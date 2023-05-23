import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;



/* Darian Torres
*/


public class Railroad{

	int numT;
	String fileName;
	LinkedList<Edge> edges[];
	int counter;	
	
	//Makes class edge so we can utilize for railrodes
	class Edge implements Comparable<Edge>{
		String src;
		String dest;
		int weight;
		
		public Edge(String src, String dest, int weight){
			this.src = src;
			this.dest = dest;
			this.weight = weight;
			}
		
		@Override
		public int compareTo(Edge e) {
			return this.weight - e.weight;
		}
		
	}
	
	class disjoint{
		Edge edgeData;
	    String name;
		disjoint parent;
		
		//disjoint constructors
	    public disjoint(String name) {
			this.name = name;
			this.parent = null;
	    }	    
	    
	    public disjoint(Edge edgeData) {
			this.edgeData = edgeData;
			this.parent = null;
	    }	    
	  
	    public disjoint() {
			this.parent = null;
	    }	    
	    
	    //finds the representative of the disjoint set
	    public disjoint find(){
	    	if (this.parent == null)
	    		return this;
	    	else
	    		return this.parent.find();
	    }
	    
	    void union(disjoint i) {
	        // Find the representatives
	        disjoint irep = i.find();
	        // And do the same for the set that includes j
	        disjoint jrep = this.find();
	     
	        // Elements are in same set, no need to unite anything.
	        if (irep == jrep)
	            return;
	        
	        else
	        	jrep.parent = irep;
	        
	    }
	   
	    
	}
	
	public Railroad(int numT, String fileName){
		//counts the total number of edges
		counter = 0;
		
		//number of lines inside of file!
		this.numT = numT;
		//taking in file name to get for making Railroads
		this.fileName = fileName;
	}
	
	public void addEdge(String src, String dest, int weight){
		Edge edge = new Edge(src, dest, weight);
		edges[counter].addFirst(edge);
		counter++;		
	}

	
	//function that reads in the file and puts them into the linked lists
	public String buildRailroad() throws FileNotFoundException{
		//Have a list that contains all of the unique vertices
		//
		
		
		//scans in Railroads file
		File Railroads= new File(this.fileName);
		Scanner R = new Scanner(Railroads);
		
		
		//array of edges and vertices
		Edge[] edges = new Edge[this.numT];
        ArrayList<String> Vertices1 = new ArrayList<String>();
        ArrayList<disjoint> disjointV = new ArrayList<disjoint>();

		for(int i = 0; i < this.numT; i++) {
			edges[i] = new Edge(R.next(), R.next(), R.nextInt());		
			if(!Vertices1.contains(edges[i].src))
					Vertices1.add(edges[i].src);
			if(!Vertices1.contains(edges[i].dest))
					Vertices1.add(edges[i].dest);
		}
		
		//sort
		Arrays.sort(edges);
		
		//making disjoint set of vertices
		for(int j = 0; j < Vertices1.size() ; j++) {
			disjoint disjoint1 = new disjoint(Vertices1.get(j));
			disjointV.add(disjoint1);
		}	
		
		//A=empty set
        disjoint A = new disjoint();
        for(int z = 0; z<this.numT; z++) {
        	disjoint disjointA = null;
        	disjoint disjointB = null;
        	for (int y = 0; y< Vertices1.size(); y++) {
        		if(disjointV.get(y).name.equals(edges[z].src))
        			disjointA = disjointV.get(y);
        		if(disjointV.get(y).name.equals(edges[z].dest))
        			disjointB = disjointV.get(y);
        	}
    		if(disjointB.find() != disjointA.find()) {
            	disjoint disjointE = new disjoint(edges[z]);
    			disjointA.union(disjointB);
    			A.union(disjointE);
    		}
    		
        }
		
        //printing
        int total = 0;
        String End = "";
        String one;
        String two;
        while(A != null) {
        	if(A.edgeData == null) {
        		A = A.parent;
        		continue;
        	}
        	total = total + A.edgeData.weight;
        	one = A.edgeData.src;
        	two = A.edgeData.dest;
        		if(one.compareTo(two) < 0)
        			End = End + (A.edgeData.src +"---"+ A.edgeData.dest + "\t$" + A.edgeData.weight + "\n");
        		else
        			End = End + (A.edgeData.dest +"---"+ A.edgeData.src + "\t$" + A.edgeData.weight + "\n");
        	A = A.parent;
        }
        
        End = End + ("The cost of the railroad is $" + total + ".");
		//closes file
		R.close();		
		return End;
	}
	 
	 
	
}
