package atze.plsedit;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JSeparator;

public class MainWindow implements ActionListener {

	private JFrame frame;
	private JPanel panel;
	private JButton btnLoad;
	private JButton btnSave;
	private JButton btnAdd;
	private JButton btnRemove;
	private JButton btnEdit;
	private JSeparator separator;
	private JList<PlaylistEntry> lstPlaylist;
	private Playlist pl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		DB.createNew();
		
//		System.out.println(pl.getNext().getVideoFile());
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setTitle("untitled - PlaylistEditor");
		frame.setBounds(100, 100, 650, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		btnLoad = new JButton("Load");
		btnLoad.setActionCommand("openPl");
		btnLoad.addActionListener(this);
		panel.add(btnLoad);
		
		btnSave = new JButton("Save");
		btnSave.setActionCommand("savePl");
		btnSave.addActionListener(this);
		panel.add(btnSave);
		
		separator = new JSeparator();
		panel.add(separator);
		
		btnAdd = new JButton("Add");
		btnAdd.setActionCommand("addEntry");
		btnAdd.addActionListener(this);
		panel.add(btnAdd);
		
		btnRemove = new JButton("Remove");
		btnRemove.setActionCommand("removeEntry");
		btnRemove.addActionListener(this);
		panel.add(btnRemove);
		
		btnEdit = new JButton("Edit");
		btnEdit.setActionCommand("editEntry");
		btnEdit.addActionListener(this);
		panel.add(btnEdit);
		
		pl = new Playlist();
//		model = new DefaultListModel<>();
		lstPlaylist = new JList<>(pl.getModel());
		lstPlaylist.setCellRenderer(new EntryCellRenderer());
		frame.getContentPane().add(new JScrollPane(lstPlaylist), BorderLayout.CENTER);
		
//		Iterator<PlaylistEntry> it = pl.getIter();
//		while (it.hasNext()) {
//			model.addElement(it.next());
//		}
	}

	@Override
	public void actionPerformed(ActionEvent action) {
		switch (action.getActionCommand()) {
		case "openPl":
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(frame);
			File file = fc.getSelectedFile();
			if (file == null) {
				return;
			}
			frame.setTitle(file.getName() + " - PlaylistEditor");
			pl.removeAll();
			pl.load(file.getPath());
			break;
		case "savePl":
			JFileChooser fcs = new JFileChooser();
			int ret = fcs.showSaveDialog(frame);
			if (ret == JFileChooser.APPROVE_OPTION) {
				File files = fcs.getSelectedFile();
				pl.saveToFile(files);
				frame.setTitle(files.getName() + " - PlaylistEditor");
			}
			break;
		case "removeEntry":
			pl.remove(lstPlaylist.getSelectedValue());
//			model.removeElement(lstPlaylist.getSelectedValue());
			break;
		case "addEntry":
			PlaylistEntry entry = new PlaylistEntry();
			pl.add(entry);
			break;
		case "editEntry":
			PlaylistEntry selectedEntry = lstPlaylist.getSelectedValue();
			int selectedIndex = lstPlaylist.getSelectedIndex();
			EditEntryDialog dialog = new EditEntryDialog(frame);
			selectedEntry = dialog.showDialog(selectedEntry);
			pl.update(selectedEntry, selectedIndex);
			System.out.println("DIALOG CLOSED");
			
			break;
		default:
			break;
		}
		
	}
}
