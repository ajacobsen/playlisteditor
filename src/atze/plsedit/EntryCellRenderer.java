package atze.plsedit;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
//import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

import net.miginfocom.swing.MigLayout;

public class EntryCellRenderer extends JPanel implements ListCellRenderer<PlaylistEntry> {

	private static final long serialVersionUID = 2L;
	
	private JLabel lblVideoFile;
//	private JTextField txtVideoFile;
	public EntryCellRenderer() {
		setOpaque(true);
		setBackground(new Color(255,255,255));
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setLayout(new MigLayout());
		
		lblVideoFile = new JLabel("Video:");
		add(lblVideoFile, "align label");
		
//		txtVideoFile = new JTextField();
//		txtVideoFile.setOpaque(false);
//		add(txtVideoFile, "span, wrap");
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends PlaylistEntry> list, PlaylistEntry value, int index, boolean isSelected, boolean cellHasFocus) {
		lblVideoFile.setText(value.getVideoFile());
		if (isSelected) {
			setBackground(Color.BLUE);
			lblVideoFile.setForeground(Color.WHITE);
		} else {
			setBackground(Color.WHITE);
			lblVideoFile.setForeground(Color.BLACK);
		}
		
		return this;
	}

}
