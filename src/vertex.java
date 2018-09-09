
public class vertex implements Comparable<vertex> {
String intersect;
double i1;
double i2;
double distance_all=Double.POSITIVE_INFINITY;
boolean reach = false;
vertex parent;
int index;
public vertex(String k, double i1, double i2){
	this.i1=i1;
	this.i2=i2;
	this.intersect=k;
}
public vertex(String k, double i1, double i2,int index){
	this.i1=i1;
	this.i2=i2;
	this.intersect=k;
    this.index=index; 
}
@Override
public String toString() {
	return "vertex [intersect=" + intersect + ", i1=" + i1 + ", i2=" + i2 + "]";
}
public int compareTo(vertex o) {
	// TODO Auto-generated method stub
    if(distance_all<o.distance_all){
    	return -1;
    }else if(distance_all==o.distance_all){
    	return 0;
    }
    else {
    	return 1;
    }
	
}

}
