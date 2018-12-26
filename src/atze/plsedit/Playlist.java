package atze.plsedit;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import javax.swing.DefaultListModel;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.stream.JsonReader;

public class Playlist {

//	private ArrayList<PlaylistEntry> entries = new ArrayList<>();
	private DefaultListModel<PlaylistEntry> model;
	
	public Playlist() {
		model = new DefaultListModel<>();
	}
	
	public DefaultListModel<PlaylistEntry> getModel() {
		return model;
	}
	
	@SuppressWarnings("unchecked")
	public void load(String filePath) {
		
		JsonParser parser = new JsonParser();
		FileReader freader = null;
		try {
			freader = new FileReader(filePath);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		JsonReader reader = new JsonReader(freader);
		
		JsonElement e = parser.parse(reader);
		JsonArray a = e.getAsJsonArray();
		for (JsonElement jsonElement : a) {
			LinkedTreeMap<String, Object> map = (LinkedTreeMap<String, Object>) new Gson().fromJson(jsonElement, Map.class);
//			entries.add(new PlaylistEntry(map));
			model.addElement(new PlaylistEntry(map));
		}
	}
	
	public void add(PlaylistEntry newEntry) {
//		entries.add(newEntry);
		model.addElement(newEntry);
	}
	
	public void update(PlaylistEntry entry, int index) {
		model.removeElementAt(index);
		model.add(index, entry);
	}
	
	public void remove(PlaylistEntry entry) {
//		entries.remove(entry);
		model.removeElement(entry);
	}
	
	public void removeAll() {
		model.removeAllElements();
	}
	
	public Enumeration<PlaylistEntry> getIter() {
//		return entries.iterator();
		return model.elements();
	}
	
	public boolean saveToFile(File file) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		
		Enumeration<PlaylistEntry> en = model.elements();
		while (en.hasMoreElements()) {
			PlaylistEntry entry = (PlaylistEntry) en.nextElement();
			sb.append(entry.toJson());
			if (en.hasMoreElements()) {
				sb.append(",");
			}
		}
		sb.append("]");
		System.out.println(sb.toString());
		try {
			FileWriter fw = new FileWriter(file);
			fw.write(sb.toString());
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
