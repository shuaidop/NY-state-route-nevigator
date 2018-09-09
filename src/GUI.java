
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class GUI extends JComponent {
public GUI(){
	super();
}
 graph graph=new graph(true);

public void paintComponent(Graphics g){
	for(vertex v: graph.adjl.keySet()){
		for(vertex w: graph.adjl.get(v)){
			g.drawLine( (int)((w.i2-graph.minH)*getWidth()/graph.difH),(int) (getHeight()-(w.i1-graph.minW)*getHeight()/graph.difW),(int) ((v.i2-graph.minH)*getWidth()/graph.difH),(int) (getHeight()-(v.i1-graph.minW)*getHeight()/graph.difW));
			//System.out.println(" x1 :"+graph.difH+"   y1 "+blcH+"   x2 :"+ blcW+"   y2 :"+blcH);
		}
	}

	for(int i=0; i<graph.parentArray.size()-1;i++){
		g.setColor(Color.RED);
		g.drawLine( (int)((graph.parentArray.get(i).i2-graph.minH)*getWidth()/graph.difH),(int) (getHeight()-(graph.parentArray.get(i).i1-graph.minW)*getHeight()/graph.difW),(int) ((graph.parentArray.get(i+1).i2-graph.minH)*getWidth()/graph.difH),(int) (getHeight()-(graph.parentArray.get(i+1).i1-graph.minW)*getHeight()/graph.difW));
	}
	for(edge e: graph.mst){
		g.setColor(Color.pink);
		g.drawLine((int)((e.v1.i2-graph.minH)*getWidth()/graph.difH),
				(int) (getHeight()-(e.v1.i1-graph.minW)*getHeight()/graph.difW),
				(int) ((e.v2.i2-graph.minH)*getWidth()/graph.difH),
				(int) (getHeight()-(e.v2.i1-graph.minW)*getHeight()/graph.difW));

	}

}
	static boolean show;
	static boolean srt;
	static boolean mstt;
public static void main(String[] args) {
	// TODO Auto-generated method stub
	String filename=args[0];
	graph k=new graph(false);
	k.read(filename);
	if(args[1].equals("-show")||args[1].equals("[-show]")){
		show=true;
		
	}
	if(args.length>2){
		
		if(args[2].equals("-meridianmap")||args[2].equals("[-meridianmap]")){
			mstt=true;
			k.kru();
		}else if(args[2].equals("-directions")){
			srt=true;
			k.djk(args[3]);
		    k.find(args[4]);
		}
	}
	
    //k.read(filename);
   k.compare();	
    //k.djk("i48847");
	//k.find("i4855");
	
    //k.toString();
    JFrame frame = new JFrame("Map");
	GUI map = new GUI();
	frame.add(map);
	
	frame.setVisible(true);
	frame.setSize(500,500);

}
}
