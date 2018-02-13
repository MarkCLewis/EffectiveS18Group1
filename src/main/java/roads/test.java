package roads;

import javax.swing.JFrame;

public class test {
	public static void main(String[] args) {
		
		JFrame f = new JFrame("Title");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DrawLine d = new DrawLine();
		f.add(d);
		f.setSize(400,250);
		f.setVisible(true);
	}
}
