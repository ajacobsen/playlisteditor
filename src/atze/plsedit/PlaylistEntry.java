package atze.plsedit;

import java.util.ArrayList;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;

public class PlaylistEntry {
	
	LinkedTreeMap<String, Object> video;
	String logo;
	ArrayList<LinkedTreeMap<String, Object>> overlays;
	
	public PlaylistEntry() {
		video = new LinkedTreeMap<String, Object>();
		video.put("file", "");
		video.put("size", new LinkedTreeMap<String, Object>());
		video.put("position", new LinkedTreeMap<String, Object>());
		logo = "";
		overlays = new ArrayList<LinkedTreeMap<String, Object>>();
	}
	
	@SuppressWarnings("unchecked")
	public PlaylistEntry(LinkedTreeMap<String, Object> entry) {
		video = (LinkedTreeMap<String, Object>) entry.get("video");
		logo = (String) entry.get("logo");
		overlays = (ArrayList<LinkedTreeMap<String, Object>>) entry.get("overlays");
	}
	
	public String getVideoFile() {
		return (String) video.getOrDefault("file", "");
	}
	
	public void setVideoFile(String p) {
		video.put("file", p);
	}
	
	@SuppressWarnings("unchecked")
	public Integer getVideoHeight() {
		Integer ret = 0;
		try {
			ret = ((Double) ((Map<String, Object>) video.get("size")).getOrDefault("y", 720d)).intValue();
		} catch (ClassCastException e) {
			ret = (Integer) ((Map<String, Object>) video.get("size")).getOrDefault("y", 720);
		}
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	public void setVideoHeight(int height) {
		((Map<String, Object>) video.get("size")).put("y", height);
	}
	
	@SuppressWarnings("unchecked")
	public Integer getVideoWidth() {
		Integer ret = 0;
		try {
			ret = ((Double) ((Map<String, Object>) video.get("size")).getOrDefault("x", 1280d)).intValue();
		} catch (ClassCastException e) {
			ret = (Integer) ((Map<String, Object>) video.get("size")).getOrDefault("x", 1280);
		}
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	public void setVideoWidth(int width) {
		((Map<String, Object>) video.get("size")).put("x", width);
	}

	@SuppressWarnings("unchecked")
	public Integer getPosX() {
		Integer ret = 0;
		try {
			ret = ((Double) ((Map<String, Object>) video.get("position")).getOrDefault("x", 0d)).intValue();
		} catch (ClassCastException e) {
			ret = (Integer) ((Map<String, Object>) video.get("position")).getOrDefault("x", 0d);
		}
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	public void setPosX(int posx) {
		((Map<String, Object>) video.get("position")).put("x", posx);
	}
	
	@SuppressWarnings("unchecked")
	public Integer getPosY() {
		Integer ret = 0;
		try {
			ret = ((Double) ((Map<String, Object>) video.get("position")).getOrDefault("y", 0d)).intValue();
		} catch (ClassCastException e) {
			ret = (Integer) ((Map<String, Object>) video.get("position")).getOrDefault("y", 0d);
		}
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	public void setPosY(int posy) {
		((Map<String, Object>) video.get("position")).put("y", posy);
	}
	
	public String getLogo() {
		return logo;
	}
	
	public void setLogo(String path) {
		logo = path;
	}
	
	public void addOverlay(LinkedTreeMap<String, Object> overlay) {
		overlays.add(overlay);
	}
	
	public void removeOverlay(LinkedTreeMap<String, Object> overlay) {
		overlays.remove(overlay);
	}
	
	public void removeOverlay(int index) {
		overlays.remove(index);
	}
	
	public void removeAllOverlays() {
		overlays.clear();
	}
	
	public void updateOverlay(int index, LinkedTreeMap<String, Object> overlay) {
		overlays.remove(index);
		overlays.add(index, overlay);
	}
	public ArrayList<LinkedTreeMap<String, Object>> getOverlays() {
		return overlays;
	}
	
	public String toJson() {
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		StringBuilder sb = new StringBuilder();
		sb.append("{ \"video\" : ");
		sb.append(prettyGson.toJson(video));
		sb.append(",");
		sb.append(" \"logo\" : \"");
		sb.append(getLogo());
		sb.append("\",");
		sb.append("\"overlays\" : ");
		sb.append(prettyGson.toJson(overlays));
		sb.append("}");
		return sb.toString();
	}
}
