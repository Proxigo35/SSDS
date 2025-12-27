package ie.atu.sw;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import java.io.File;

public class FileChooser {

	public static File chooseFile() {
		final File[] result = new File[1];

		Runnable task = () -> {
			JFileChooser chooser = new JFileChooser(new File(System.getProperty("user.home")));
			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) result[0] = chooser.getSelectedFile();
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
			JFileChooser chooser = new JFileChooser(
				new File(System.getProperty("user.home"))
			);

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
