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
import javax.swing.ImageIcon;

public class MainVideoPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7724289890191948590L;
	private JTextField txtFile;
	private JTextField txtWidth;
	private JTextField txtHeight;
	private JTextField txtPosX;
	private JTextField txtPosY;
	private JTextField txtLogoFile;
	private EditEntryDialog parent;

	/**
	 * Create the panel.
	 */
	public MainVideoPanel(EditEntryDialog parent) {
		this.parent = parent;
		setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Main Video", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new MigLayout("", "[][grow][][grow][]", "[][][][]"));
		
		JLabel lblFile = new JLabel("File:");
		add(lblFile, "cell 0 0,alignx trailing");
		
		txtFile = new JTextField();
		add(txtFile, "cell 1 0 2 1,growx");
		txtFile.setColumns(10);
		
		JButton btnSelectVideoFile = new JButton("Select");
		btnSelectVideoFile.setIcon(new ImageIcon(MainVideoPanel.class.getResource("/javax/swing/plaf/metal/icons/ocean/file.gif")));
		btnSelectVideoFile.addActionListener(this);
		btnSelectVideoFile.setActionCommand("openVideoFile");
		add(btnSelectVideoFile, "cell 3 0");
		
		JLabel lblWidth = new JLabel("Width:");
		add(lblWidth, "cell 0 1,alignx trailing");
		
		txtWidth = new JTextField();
		add(txtWidth, "cell 1 1,growx");
		txtWidth.setColumns(10);
		
		JLabel lblHeight = new JLabel("Height:");
		add(lblHeight, "cell 2 1,alignx trailing");
		
		txtHeight = new JTextField();
		add(txtHeight, "cell 3 1,growx");
		txtHeight.setColumns(10);
		
		JLabel lblPosX = new JLabel("Pos X:");
		add(lblPosX, "cell 0 2,alignx trailing");
		
		txtPosX = new JTextField();
		add(txtPosX, "cell 1 2,growx");
		txtPosX.setColumns(10);
		
		JLabel lblPosY = new JLabel("Pos Y:");
		add(lblPosY, "cell 2 2,alignx trailing");
		
		txtPosY = new JTextField();
		add(txtPosY, "cell 3 2,growx");
		txtPosY.setColumns(10);
		
		JLabel lblLogo = new JLabel("Logo:");
		add(lblLogo, "cell 0 3,alignx trailing");
		
		txtLogoFile = new JTextField();
		add(txtLogoFile, "cell 1 3,growx");
		txtLogoFile.setColumns(10);
		
		JButton btnSelectLogoFile = new JButton("Select");
		btnSelectLogoFile.setIcon(new ImageIcon(MainVideoPanel.class.getResource("/javax/swing/plaf/metal/icons/ocean/file.gif")));
		btnSelectLogoFile.setActionCommand("openLogoFile");
		btnSelectLogoFile.addActionListener(this);
		add(btnSelectLogoFile, "cell 2 3");
		
		JButton btnAddOverlay = new JButton("Add Overlay");
		btnAddOverlay.setActionCommand("addOverlay");
		btnAddOverlay.addActionListener(this);
		add(btnAddOverlay, "cell 3 3");

	}
	
	public void setVideoFile(String path) {
		txtFile.setText(path);
	}
	
	public String getVideoFile() {
		return txtFile.getText();
	}
	
	public void setVideoWidth(int width) {
		txtWidth.setText(String.valueOf(width));
	}
	
	public int getVideoWidth() {
		return Integer.parseInt(txtWidth.getText());
	}
	
	public void setVideoHeight(int height) {
		txtHeight.setText(String.valueOf(height));
	}
	
	public int getVideoHeight() {
		return Integer.parseInt(txtHeight.getText());
	}
	
	public void setPosX(int posx) {
		txtPosX.setText(String.valueOf(posx));
	}
	
	public int getPosX() {
		return Integer.parseInt(txtPosX.getText());
	}
	
	public void setPosY(int posy) {
		txtPosY.setText(String.valueOf(posy));
	}
	
	public int getPosY() {
		return Integer.parseInt(txtPosY.getText());
	}
	
	public void setLogoFile(String path) {
		txtLogoFile.setText(path);
	}
	
	public String getLogoFile() {
		return txtLogoFile.getText();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "openVideoFile":
			JFileChooser fco = new JFileChooser();
			fco.setFileFilter(new FileNameExtensionFilter("Video (mp4)", "mp4"));
			int ret = fco.showOpenDialog(this);
			if (ret == JFileChooser.APPROVE_OPTION) {
				txtFile.setText(fco.getSelectedFile().getName());
			}
			break;
		
		case "openLogoFile":
			JFileChooser fc = new JFileChooser();
			fc.setFileFilter(new FileNameExtensionFilter("Logo (png)", "png"));
			int reto = fc.showOpenDialog(this);
			if (reto == JFileChooser.APPROVE_OPTION) {
				txtLogoFile.setText(fc.getSelectedFile().getName());
			}
			break;
			
		case "addOverlay":
			this.parent.addNewOverlay();
			break;

		default:
			break;
		}
		
	}

}
