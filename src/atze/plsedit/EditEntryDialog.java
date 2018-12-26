package atze.plsedit;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import com.google.gson.internal.LinkedTreeMap;

import javax.swing.BoxLayout;

public class EditEntryDialog extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5380604126518632493L;
	private final JPanel contentPanel = new JPanel();
	private PlaylistEntry entry;
	private MainVideoPanel mainVideoPanel;
	private ArrayList<OverlayPanel> overlayPanels;
	private JScrollPane sp;

	public EditEntryDialog(JFrame frame) {
		super(frame, true);
		overlayPanels = new ArrayList<>();
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		sp = new JScrollPane(contentPanel);
		getContentPane().add(sp, BorderLayout.CENTER);
//		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		{
			mainVideoPanel = new MainVideoPanel(this);
			contentPanel.add(mainVideoPanel);
			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						entry.setVideoFile(mainVideoPanel.getVideoFile());
						entry.setLogo(mainVideoPanel.getLogoFile());
						entry.setVideoWidth(mainVideoPanel.getVideoWidth());
						entry.setVideoHeight(mainVideoPanel.getVideoHeight());
						entry.setPosX(mainVideoPanel.getPosX());
						entry.setPosY(mainVideoPanel.getPosY());
						entry.removeAllOverlays();
						for (Iterator<OverlayPanel> iterator = overlayPanels.iterator(); iterator.hasNext();) {
							System.out.println("OVERLAYPANEL");
							OverlayPanel overlayPanel = (OverlayPanel) iterator.next();
							LinkedTreeMap<String, Object> overlay = new LinkedTreeMap<>();
							overlay.put("file", overlayPanel.getFile());
							overlay.put("offset", overlayPanel.getStartTime());
							entry.addOverlay(overlay);
						}
						setVisible(false);
						
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public PlaylistEntry showDialog(PlaylistEntry _entry) {
		entry = _entry;
		mainVideoPanel.setVideoFile(entry.getVideoFile());
		mainVideoPanel.setPosX(entry.getPosX());
		mainVideoPanel.setPosY(entry.getPosY());
		mainVideoPanel.setVideoWidth(entry.getVideoWidth());
		mainVideoPanel.setVideoHeight(entry.getVideoHeight());
		mainVideoPanel.setLogoFile(entry.getLogo());
		int i = 1;
		for (Iterator<LinkedTreeMap<String, Object>> iterator = entry.getOverlays().iterator(); iterator.hasNext();) {
			OverlayPanel overlayPanel = new OverlayPanel(this);
			LinkedTreeMap<String, Object> overlay = iterator.next();
			overlayPanel.setFile(overlay.getOrDefault("file", "").toString());
			try {
				overlayPanel.setStartTime(((Double) overlay.getOrDefault("offset", 0)).intValue());
			} catch (ClassCastException e) {
				overlayPanel.setStartTime((Integer) overlay.getOrDefault("offset", 0));
			}
			overlayPanel.setTitle(String.valueOf(i));
			contentPanel.add(overlayPanel);
			overlayPanels.add(overlayPanel);
			i++;
		}
		setVisible(true);
		return entry;
	}
	
	public void removeOverlay(OverlayPanel panel) {
		this.contentPanel.remove(panel);
		this.overlayPanels.remove(panel);
		this.validate();
		this.repaint();
	}

	public void addNewOverlay() {
		OverlayPanel op = new OverlayPanel(this);
		op.setFile("");
		op.setStartTime(0);
		contentPanel.add(op);
		overlayPanels.add(op);
		this.validate();
		this.repaint();
	}
}
