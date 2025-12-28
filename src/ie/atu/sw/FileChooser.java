package ie.atu.sw;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FileChooser {
	
	private static JFrame createParent() {
		JFrame frame = new JFrame();
		frame.setUndecorated(true);
		frame.setSize(0, 0);
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);
		return frame;
	}

	public static File chooseFile() {
		final File[] result = new File[1];

		Runnable task = () -> {
			JFrame parent = createParent(); 
			JFileChooser chooser = new JFileChooser(new File(System.getProperty("user.home")));
			chooser.setDialogTitle("Select a .txt file");
    		chooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
			chooser.requestFocusInWindow();
			if (chooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) result[0] = chooser.getSelectedFile();
			parent.dispose();
		};

		try {
			if (SwingUtilities.isEventDispatchThread()) task.run();
			else SwingUtilities.invokeAndWait(task);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result[0];
	}

	public static File chooseSaveFile() {
		final File[] result = new File[1];

		Runnable task = () -> {
			JFileChooser chooser = new JFileChooser(new File(System.getProperty("user.home")));
			chooser.setDialogTitle("Save output file");
			chooser.setSelectedFile(new File("output.txt"));
			int choice = chooser.showSaveDialog(null);
			if (choice == JFileChooser.APPROVE_OPTION) result[0] = chooser.getSelectedFile();
		};

		try {
			if (SwingUtilities.isEventDispatchThread()) task.run();
			else SwingUtilities.invokeAndWait(task);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result[0];
	}

}
