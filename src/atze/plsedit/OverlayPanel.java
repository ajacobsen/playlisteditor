package atze.plsedit;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class OverlayPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2490611917596354022L;
	private JTextField txtFile;
	private JTextField txtStartTime;
	private JButton btnRemove;
	private EditEntryDialog parent;
	private JButton btnSelect;
	
	/**
	 * Create the panel.
	 */
	public OverlayPanel(EditEntryDialog parent) {
		this.parent = parent;
		setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Overlay #", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new MigLayout("", "[][grow][]", "[][][]"));
		
		JLabel lblFile = new JLabel("File:");
		add(lblFile, "cell 0 0,alignx trailing");
		
		txtFile = new JTextField();
		add(txtFile, "cell 1 0,growx");
		txtFile.setColumns(10);
		
		btnSelect = new JButton("Select");
		btnSelect.setActionCommand("openFile");
		btnSelect.addActionListener(this);
		add(btnSelect, "cell 2 0");
		
		JLabel lblStartTime = new JLabel("Start Time:");
		add(lblStartTime, "cell 0 1,alignx trailing");
		
		txtStartTime = new JTextField();
		add(txtStartTime, "cell 1 1,growx");
		txtStartTime.setColumns(10);
		
		btnRemove = new JButton("Remove");
		btnRemove.setActionCommand("removeOverlay");
		btnRemove.addActionListener(this);
		add(btnRemove, "cell 1 2");

	}
	
	public void setFile(String path) {
		txtFile.setText(path);
	}
	
	public String getFile() {
		return txtFile.getText();
	}
	
	public void setStartTime(int time) {
		txtStartTime.setText(String.valueOf(time));
	}
	
	public int getStartTime() {
		return Integer.parseInt(txtStartTime.getText());
	}

	public void setTitle(String num) {
		setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Overlay " + num, TitledBorder.LEADING, TitledBorder.TOP, null, null));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "removeOverlay":
			this.parent.removeOverlay(this);
			break;

		case "openFile":
			JFileChooser fc = new JFileChooser();
			fc.setFileFilter(new FileNameExtensionFilter("Overlay video (.mov)", "mov"));
			int ret = fc.showOpenDialog(this);
			if (ret == JFileChooser.APPROVE_OPTION) {
				txtFile.setText(fc.getSelectedFile().getName());
			}
			break;
			
		default:
			break;
		}
		
	}
}
