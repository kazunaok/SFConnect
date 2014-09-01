package jp.co.terrasky.sfc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class FileRead {
	/** ファイル */
	private File file = null;

	/** ファイル名 */
	private String fileName = null;

	/* 読み込みリスト */
	private List<Map> csvList = null;

	public void setFileName(String arg) {
		this.fileName = arg;
	}

	public void read() throws IOException {
		String arg = null;
		file = new File(fileName);
		byte[] b = new byte[(int)file.length()];
		FileInputStream stream = new FileInputStream(file);
		stream.read(b);
		arg = new String(b);

		Vector row = new Vector();
		arg = arg.replace("\r", "");
		arg = arg.replace("\"", "");
		String[] cols = arg.split("\n");

		for (int i = 0; i < cols.length; i++) {
			row.add(cols[i].split(","));
		}
		rowsAdd(row);
	}

	private void rowsAdd(Vector row) {
		csvList = new ArrayList<Map>();

		String[] keys = (String[])row.elementAt(0);

		for (int i = 1; i < row.size(); i++) {
			String[] values = (String[])row.elementAt(i);
			HashMap<String, String> map = new HashMap<String, String>();
			for (int j = 0; j < keys.length; j++) {
				map.put(keys[j], values[j]);
			}
			csvList.add(map);
		}
	}

	public List<Map> getCsvList() {
		return csvList;
	}
}
