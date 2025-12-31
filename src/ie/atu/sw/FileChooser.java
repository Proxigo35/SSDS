package ie.atu.sw;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

/**
* Allows the user to select a file using the operating systems native file navigator.
*/
public class FileChooser {

	/**
	* Opens a native file chooser dialog to allow the user to select a text file.
	*
	* @return the selected {@link File}, or {@code null} if no file was chosen
	*/
	public static File chooseFile() {

		// selected must be a final variable to be able to be used within lamda expression.
		// So they array is final, but the first element can be changed safely.
        final File[] selected = new File[1];

		try {
            SwingUtilities.invokeAndWait(() -> {
                JFileChooser chooser = new JFileChooser();
				
				// Accept only text files.
                chooser.setFileFilter(new FileNameExtensionFilter("Text files (*.txt)", "txt"));
                chooser.setAcceptAllFileFilterUsed(false);

				// If a file was chosen, update selected with a reference to the file.
                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) selected[0] = chooser.getSelectedFile();
            });
		} catch (Exception e) {
			System.out.println("Something went wrong when trying to open file choose window.");
		}

        return selected[0];
    }
}
