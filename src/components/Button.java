package components;

import java.awt.AlphaComposite;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

// Création d'une classe Button qui hérite des méthodes de JButton
public class Button extends JButton{
	private static final long serialVersionUID = 1L;
    private float alpha;

    public float getAlpha() {
        return alpha;
    }
    public void setAlpha(double d) {
        this.alpha = (float) d;
    }

    // Méthode qui permet de personnaliser le composant customisé
    public Button(String txt) {
    	setText(txt);
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentAreaFilled(false);
        // curseur se transforme en main lorsqu'il survole le bouton
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        // Ecouteur de la souris
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
            	// ici on précise le degré de transparence du bouton
            	// lorsque le curseur de la souris le survole
                setAlpha(0.5f); 
            }
        });
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent me) {
            	// la transparence se réinitilialise à zéro
            	// lorsque la souris ne survole plus le bouton
                setAlpha(0f); 
            }
        });

    }
    
    // Méthodes override de la class JButton qui permettent de dessiner le composant bouton
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        int width = getWidth() - 1;
        int height = getHeight() - 1;
        g2.fill(new RoundRectangle2D.Double(0, 0, width, height, 20, 20));
        g2.dispose();
        super.paintComponent(grphcs);
    }
    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha * 0.8f));
        g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        g2.dispose();
    }

}
