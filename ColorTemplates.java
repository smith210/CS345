import java.awt.*;
import java.lang.*;
import javax.swing.*;
import javax.accessibility.*;
import java.util.*;

public class ColorTemplates{

	private LinkedList<Colors> palettes;
// these color palettes are inspired by Anastasia Beverly Hills eyeshadow palettes

	ColorTemplates(){
		palettes = new LinkedList<Colors>();
		createTemplates();
	}

	public Colors get(int ID){return palettes.get(ID); }

	private void createTemplates(){
		Colors one = new Colors();//Subculture
		one.setPrimary(new Color(239, 171, 0));//mustard orange
		one.setSecondary(new Color(239, 163, 129));//corally pink
		one.setSupport1(new Color(138, 62, 42)); // brick Red
		one.setSupport2(new Color(83, 108, 76)); // muted forest green
		one.createIcon("images/tanzima.jpg");

		Colors two = new Colors();// Prism
		two.setPrimary(new Color(0, 0, 70));// deep navy blue
		two.setSecondary(new Color(204, 216, 234));// baby blue
		two.setSupport1(new Color(254, 255, 174)); // light yellow
		two.setSupport2(new Color(15, 77, 62)); // deep green
		two.createIcon("images/deneke.jpg");

		Colors three = new Colors();//Norvina <3
		three.setPrimary(new Color(142, 92, 40));// meduim redish brown
		three.setSecondary(new Color(229, 217, 194));// an off white
		three.setSupport1(new Color(199, 125, 193)); // orchid purple
		three.setSupport2(new Color(237, 208, 202)); // baby pink
		three.createIcon("images/jagodzinski.jpg");

		palettes.add(one);
		palettes.add(two);
		palettes.add(three);

	}

/*
  public static class abhSubculture{


private Image dollars = Toolkit.getDefaultToolkit().createImage("images/deneke.jpg");

  }

  public static class abhModernRenaissance{

  }

  public static class abhNorvina{


  }
*/

}
