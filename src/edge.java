
public class edge implements Comparable<edge>{
vertex v1;
vertex v2;
String rd;
double dist;
public edge(vertex v1, vertex v2){
	this.v1=v1;
	this.v2=v2;
}
public edge(vertex v1, vertex v2,double dist,String rd){
	this.v1=v1;
	this.v2=v2;
	this.dist=dist;
	this.rd=rd;
}
@Override
public String toString() {
	return "edge [v1=" + v1 + ", v2=" + v2 + "]";
}
@Override
public int compareTo(edge o) {
	// TODO Auto-generated method stub
	if(o.dist<dist){
		return 1;
	}
	else if(o.dist>dist){
		return -1;
	}else{
		return 0;
	}
}
}
