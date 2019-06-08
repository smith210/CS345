import java.awt.*;
import java.lang.*;
import javax.swing.*;
import javax.accessibility.*;
import java.util.*;

public class Colors{
	private Color primary;
	private Color secondary;
	private Color supportOne;
	private Color supportTwo;
	private Image playerIcon;
	private LinkedList<Image> profileFaces;// index: 0 = not working, 1 = working extra, 2 = working celebrity

	public Colors(){
		primary = new Color(0,0,0);
		secondary = new Color(0,0,0);
		supportOne= new Color(0,0,0);
		supportTwo= new Color(0,0,0);
		profileFaces = new LinkedList<Image>();
	}

	public void setPrimary(Color primary){ this.primary = primary; }
	public void setSecondary(Color secondary){ this.secondary = secondary; }
	public void setSupport1(Color supportOne){ this.supportOne = supportOne; }
	public void setSupport2(Color supportTwo){ this.supportTwo = supportTwo; }

	public Image createImage(String name){ return Toolkit.getDefaultToolkit().createImage(name);}
	
	public void createIcon(String name){ playerIcon = createImage(name); }

	public void addProfile(Image face){ profileFaces.add(face);}

	public Color getPrimary(){ return primary; }
	public Color getSecondary(){ return secondary; }
	public Color getSupport1(){ return supportOne; }
	public Color getSupport2(){ return supportTwo; }
	public Image getIcon(){ return playerIcon; }
	public LinkedList<Image> getFaces(){ return profileFaces; }
	
}
