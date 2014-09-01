package jp.co.terrasky.sfc;


public class SfcMain {
	private static String SFDC_USER_ID = "kkikuchi@de1.terrasky.co.jp";
	private static String SFDC_USER_PASSWORD = "zaq12wsxM5AXmPNVtoN5WcOFgf10akehj";
	private static String SFDC_END_POINT = "https://login.salesforce.com";

	public static void main(String[] args) {
		try {
			SfdcSession sfs = SfdcSession.openSession(SFDC_USER_ID, SFDC_USER_PASSWORD, SFDC_END_POINT);
			Csv2SfdcItem item = new Csv2SfdcItem();
			item.setFileName("Item__c.csv");
			item.fileRead();
			item.setSfdcSession(sfs);
			item.upsert();
			sfs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

}
