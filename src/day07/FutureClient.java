package day07;

public class FutureClient {
	
	public FutureData ruquest(final String queryStr){
		final FutureData futureData = new FutureData();
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				RealData realData = new RealData(queryStr);
				futureData.setRealData(realData);
			}
			
		}).start();;
		return futureData;
	}
}
