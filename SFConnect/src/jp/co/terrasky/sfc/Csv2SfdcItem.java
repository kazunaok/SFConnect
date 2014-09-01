package jp.co.terrasky.sfc;

import java.io.IOException;
import java.util.HashMap;

import com.sforce.soap.enterprise.UpsertResult;
import com.sforce.soap.enterprise.sobject.Item__c;

public class Csv2SfdcItem extends FileRead {
	static final String NAME = "NAME";
	static final String AMOUNT = "AMOUNT__C";
	static final String COUNT = "COUNT__C";
	static final String ITEMID = "ITEMID__C";

	private SfdcSession sfs = null;

	public void setFileName(String arg) {
		super.setFileName(arg);
	}

	public void setSfdcSession(SfdcSession sfs) {
		this.sfs = sfs;
	}

	public void fileRead() throws IOException {
		super.read();
	}

	public void upsert() throws Exception {
		Item__c[] items = new Item__c[super.getCsvList().size()];
		for (int i = 0; i < super.getCsvList().size(); i++) {
			Item__c item = new Item__c();
			HashMap map = (HashMap) super.getCsvList().get(i);
			items[i] = item;
			items[i].setName((String)map.get(NAME));
			items[i].setId((String)map.get(ITEMID));
			items[i].setAmount__c(Double.valueOf(map.get(AMOUNT).toString()));
			items[i].setCount__c(Double.valueOf(map.get(COUNT).toString()));
		}
		UpsertResult[] upsertResults = sfs.binding.upsert(ITEMID, items);
	}
}
