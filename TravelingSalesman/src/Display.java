import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Display extends JPanel {

	public static void main(String[] args){ 
		JFrame window = new JFrame();
		
		Display display = new Display();
		
		
		
		window.add(display);
		window.setSize(1000, 1000);
		window.setVisible(true);
    } 
	
	
	private List<City> cities;
	private List<Trail> trails;
	private List<VisitorMark> marks;
	private JPanel panel;
	
	public Display() {
		panel = new JPanel();
		panel.add(new JLabel("Test"));
		add(panel);
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		trails.forEach(t -> t.draw(g));
		cities.forEach(c -> c.draw(g));
		marks.forEach(m -> m.draw(g));
	}
	
	
}
