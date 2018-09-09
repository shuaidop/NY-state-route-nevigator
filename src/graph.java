import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class graph {
boolean type;
static HashMap <vertex, LinkedList<vertex> > adjl= new HashMap();
static HashMap <String, vertex> vlst=new HashMap();
static ArrayList<edge> eg=new ArrayList();
static ArrayList<vertex> checkv=new ArrayList();
PriorityQueue<vertex> Q=new PriorityQueue();

static int c=0;
public graph(boolean k){
	this.type=k;
}
public void insert(edge k){
	//System.out.println("in the method");
	if(connected(k)){
		//System.out.println("the edge is already exist");
	}
	else{
		if(type==true){
			//System.out.println("undrt graph ; type ");
			if(adjl.containsKey(k.v1)){
				if(adjl.get(k.v2)==null){
					//System.out.println("v2 null; add undrt");
					  adjl.get(k.v1).add(k.v2);
					  LinkedList<vertex> lst = new LinkedList<vertex>();
					    adjl.put(k.v2,lst);
						lst.add(k.v1);
					  
				}else{
					//System.out.println("undrt ;v1 and v2 add");
				    adjl.get(k.v1).add(k.v2);
				    adjl.get(k.v2).add(k.v1);
				    
				    }
		                        }
			else if(adjl.containsKey(k.v2)){
				//System.out.println("v1 null; add undrt");
				  adjl.get(k.v2).add(k.v1);
				  LinkedList<vertex> lst = new LinkedList<vertex>();
				    adjl.put(k.v1,lst);
					lst.add(k.v2);
			}
			else{
				 LinkedList<vertex> lst1 = new LinkedList<vertex>();
				 LinkedList<vertex> lst2 = new LinkedList<vertex>();
				    adjl.put(k.v1,lst1);
					lst1.add(k.v2);
					adjl.put(k.v2,lst2);
					lst2.add(k.v1);
			}
		}
		else{
			//System.out.println("drt graph ; type ");
		    if(adjl.containsKey(k.v1)){
		    	//System.out.println("drt; v1 add");
	    adjl.get(k.v1).add(k.v2);
		}
		    else{
		    	//System.out.println("drt:v1 null add");
			LinkedList<vertex> lst = new LinkedList<vertex>();
		    adjl.put(k.v1,lst);
			lst.add(k.v2);
		        }
		}
	}
}
static double minW=Double.POSITIVE_INFINITY;
static double maxW=Double.NEGATIVE_INFINITY;
static double minH=Double.POSITIVE_INFINITY;
static double maxH=Double.NEGATIVE_INFINITY;
static double difH;
static double difW;
public static void compare(){

	for(String v:vlst.keySet()){
			if(vlst.get(v).i1<minW){
				minW=vlst.get(v).i1;
		}else if(vlst.get(v).i1>maxW){
			maxW=vlst.get(v).i1;
		}	
		
	}	
	for(String v:vlst.keySet()){
		if(vlst.get(v).i2<minH){
			minH=vlst.get(v).i2;
	}else if(vlst.get(v).i2>maxH){
		maxH=vlst.get(v).i2;
	}
}
	
	difW=maxW-minW;
	difH=maxH-minH;
		
}

public boolean connected(edge k){
	
	
		
    //undirected graph
	if(type==true){
		if(adjl.get(k.v1)!=null&&adjl.get(k.v2)!=null){
	
	if (adjl.get(k.v1).contains(k.v2)||adjl.get(k.v2).contains(k.v1)){
		return true;
	
	}else{
		return false;
	}
	}
	else{
		return false;
	}
	}
	//directed graph
	else{
		if(adjl.get(k.v1)!=null||adjl.get(k.v2)!=null){
		if(adjl.get(k.v1).contains(k.v2)){
			return true;
		}
		else{
			return false;
		}
		}
		else{
			return false;
		}
	}
}
public String toString(){
	for(vertex v: adjl.keySet()){
		System.out.print(v.intersect.toString()+" : ");
		for(vertex w: adjl.get(v)){
			System.out.print(w.intersect.toString()+ " ");
			
		}System.out.println();
	}
	return "";
}
public static graph read(String name){
	graph g=new graph(true);
	try{
		Scanner in=new Scanner(new File(name));
		while(in.hasNextLine()){
			//String line=in.next();
			//Scanner s=new Scanner(line);
			String type=in.next();
			String id=in.next();
			try{
			if(type.equals("i")){
			  double w=in.nextDouble();
			  double j=in.nextDouble();
			  c++;
			  vlst.put(id, new vertex(id ,w,j,c));
			  
			  
			}
			else if(type.equals("r")){
				
			  String in1=in.next();
			  String in2=in.next();
			  double dist=distFrom(vlst.get(in1),vlst.get(in2));
			  edge k= new edge(vlst.get(in1),vlst.get(in2),dist,id);
			  eg.add(k);
			  g.insert(k);
			}	
			
			}catch(Error e){
				System.out.println("input type is wrong");
			}
		}
	}catch(FileNotFoundException e){
		System.out.println("Can not open the file");
	}
	return g;
}
//double w1, double j1, double j2, double w2
public static double distFrom(vertex a,vertex b) {
    double earthRadius = 3959; //miles
    double dw = Math.toRadians(b.i1-a.i1);
    double dj = Math.toRadians(b.i2-a.i2);
    double x = Math.sin(dw/2) * Math.sin(dw/2) +
               Math.cos(Math.toRadians(a.i1)) * Math.cos(Math.toRadians(b.i1)) *
               Math.sin(dj/2) * Math.sin(dj/2);
    double c = 2 * Math.atan2(Math.sqrt(x), Math.sqrt(1-x));
    float d = (float) (earthRadius * c);

    return d;
    }
// ****************************************************\\

public void djk( String s ){

	for(vertex v:adjl.keySet()){
		if(v.intersect.equals(s)){
			v.distance_all=0;
			Q.add(v);
		}
	}

	while(!Q.isEmpty()){
		vertex k=Q.remove();
		k.reach=true;
		for(vertex w:adjl.get(k)){
			if(!w.reach){
				double ckw = distFrom(k,w);
				if(k.distance_all+ckw<w.distance_all){
					w.distance_all=ckw+k.distance_all;
					w.parent=k;
					Q.add(w);
				}
			}
		}
	}
}
static ArrayList<vertex> parentArray=new ArrayList();
public void find(String k){
	vertex p=null;
	vertex q=null;
	for(vertex v:adjl.keySet()){
		if(v.intersect.equals(k)){
			p=q=v;
		}
	} 
	parentArray.add(p);
	while(p.parent!=null){
	      System.out.println(p.parent.intersect+" ");
	      p=p.parent;
	      parentArray.add(p);
	}
	
	System.out.println();
	System.out.println();
	System.out.println("total distance is "+q.distance_all);
}
static ArrayList<edge> mst=new ArrayList();
static double totalmst;
public ArrayList<edge> kru( )
{
	ds ds = new ds( c );
	
	PriorityQueue<edge> pq = new PriorityQueue<>( );
	
	for(edge a : eg){
		pq.add(a);
	}
	while( mst.size( ) != c - 1 &&!pq.isEmpty())
	{
		
		edge e = pq.remove( ); // Edge e = (u, v)
		int uset = ds.find( e.v1.index);
		int vset = ds.find( e.v2.index );
		if( uset != vset )
		{
			// Accept the edge
			mst.add( e );
			ds.union( uset, vset );
		}
	}
	for(edge e: graph.mst){
		totalmst=totalmst+e.dist;
        System.out.println(e.rd);
        }
	
	System.out.println();
	System.out.println();
	System.out.println("total distance for MST "+totalmst);
	return mst;
}

}
