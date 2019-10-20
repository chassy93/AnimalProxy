import java.net.*;
import javax.swing.*;
import java.util.*;

public class ImageProxyTestDrive {
	ImageComponent imageComponent;
	JFrame frame = new JFrame("Animals");
	JMenuBar menuBar;
	JMenu menu;
	Hashtable<String, String> animals = new Hashtable<String, String>();

	public static void main (String[] args) throws Exception {
		ImageProxyTestDrive testDrive = new ImageProxyTestDrive();
	}

	public ImageProxyTestDrive() throws Exception {
		animals.put("Tiger","https://st3.depositphotos.com/1030351/18143/i/1600/depositphotos_181432434-stock-photo-funny-tiger-face.jpg");
		animals.put("Horse","https://upload.wikimedia.org/wikipedia/commons/8/8b/Funny_Horse_Faces.jpg");
		animals.put("Wolf","http://3.bp.blogspot.com/-dh77-1WR8Kg/VLbVOPbbEYI/AAAAAAAAvxU/htuK_EZ7FK8/s1600/ScreenShot3109.jpg");
		animals.put("Eagle","https://images.earthtouchnews.com/media/385410/really-grumpy.jpg");
		animals.put("Gecko","http://animalroll.com/wp-content/uploads/2017/05/gecko-smiling-3-e1495451312685.jpg");
		animals.put("Fish","https://www.bestfunnies.com/wp-content/uploads/2012/08/Funny-Fish-09.jpg");

		URL initialURL = new URL((String)animals.get("Eagle"));
		menuBar = new JMenuBar();
		menu = new JMenu("Animals");
		menuBar.add(menu);
		frame.setJMenuBar(menuBar);

		for (Enumeration<String> e = animals.keys(); e.hasMoreElements();) {
			String name = (String)e.nextElement();
			JMenuItem menuItem = new JMenuItem(name);
			menu.add(menuItem); 
			menuItem.addActionListener(event -> {
				imageComponent.setIcon(new ImageProxy(getCDUrl(event.getActionCommand())));
				frame.repaint();
			});
		}

		// set up frame and menus

		Icon icon = new ImageProxy(initialURL);
		imageComponent = new ImageComponent(icon);
		frame.getContentPane().add(imageComponent);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,600);
		frame.setVisible(true);

	}

	URL getCDUrl(String name) {
		try {
			return new URL((String)animals.get(name));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}
}