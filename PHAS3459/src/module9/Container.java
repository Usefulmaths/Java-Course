package module9;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class Container extends JFrame implements MouseListener{
	public static int posX = 0;
	public static int posY = 0;
	
    public Container(final String title) {
        super(title);
        addMouseListener(this);
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		posX = e.getX();
		posY = e.getY();
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}