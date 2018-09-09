import java.util.ArrayList;

public class ds {
	int [] s;
	public ds(int e){
		s=new int [e+1];
		for( int i = 0; i < s.length; i++ )
			s[ i ] = -1;
	}
	public void union( int root1, int root2 )
	{
		if( s[ root2 ] < s[ root1 ] )
		{
			s[ root1 ] = root2; 
		}
		else
		{
			if( s[ root1 ] == s[ root2 ] )
				s[ root1 ]--; // Update height if same
			s[ root2 ] = root1; // Make root1 new root
		}
	}
	public int find( int x ){
		if( s[ x ] < 0 ){
			return x;
		}
		else{
			return s[ x ] = find( s[ x ] );
		}
	}
}