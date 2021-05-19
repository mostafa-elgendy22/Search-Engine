

//testing crawler as a whole, Seeds + Rank, multiThreaded
// I didn't run this :)

//Seeds
class MainTests0 {
	static final int one_hour = 1000 * 3600;
	public static void main(String[] args) throws Exception {

		DataBaseConnection seedsConnection = new DataBaseConnection();
		seedsConnection.connect();

		Thread t0 = new Thread(new Crawler.Crawler_Seeds(seedsConnection));
		Thread t1 = new Thread(new Crawler.Crawler_Seeds(seedsConnection));
		Thread t2 = new Thread(new Crawler.Crawler_Seeds(seedsConnection));

		t0.start();
		t1.start();
		t2.start();

		//wait for some time
		Thread.currentThread().sleep(2 * one_hour);

		// interrupt all

		// not sure this works ?
		//Thread.currentThread().getThreadGroup().interrupt();

		t0.interrupt();
		t1.interrupt();
		t2.interrupt();

		// disable connection and end
		seedsConnection.disconnect();
	}

}

//Rank
class MainTest00{
	static final int one_hour = 1000 * 3600;
	public static void main(String[] args) throws Exception{

		DataBaseConnection seedsConnection = new DataBaseConnection();
		seedsConnection.connect();

		Thread t0 = new Thread(new Crawler.Crawler_Rank(seedsConnection, 1));
		Thread t1 = new Thread(new Crawler.Crawler_Rank(seedsConnection, 1));
		Thread t2 = new Thread(new Crawler.Crawler_Rank(seedsConnection, 1));

		//preparing tmp_Rank1
		seedsConnection.fillTmpRank(1);

		//START
		t0.start();
		t1.start();
		t2.start();

		//wait until they finish
		t0.join();
		t1.join();
		t2.join();

		// disable connection and end
		seedsConnection.disconnect();
	}

}

// test fetch to file
class MainTests1 {
	public static void main(String[] args) {
		Fetcher.fetchToFile("https://en.wikipedia.org/wiki/Sinc_function#Relationship_to_the_Dirac_delta_distribution",
				"./retrievedPages/");
		Fetcher.fetchToFile("https://en.wikipedia.org/wiki/Sinc_function#Relationship_to_the_Dirac_delta_distribution",
				"./temp/");
	}
}

// test is changed
class MainTests2 {
	public static void main(String[] args) {
		String url = "https://en.wikipedia.org/wiki/Sinc_function#Relationship_to_the_Dirac_delta_distribution";
		System.out.println(Fetcher.isChanged(url));
	}
}

// test database connection
class MainTests3 {
	public static void main(String[] args) {
		DataBaseConnection seedsConnection = new DataBaseConnection();
		seedsConnection.connect();
		// seedsConnection.pushUrlToSeeds("https://stackoverflow.com", 15);

		// seedsConnection.popUrlFromSeeds();
		// System.out.println("Url: " + seedsConnection.url + "\npriority: " +
		// seedsConnection.priority);

		// seedsConnection.updatePriortyInSeeds("https://stackoverflow.com", 50);
		seedsConnection.disconnect();
	}
}

// test scheduler
class MainTests4 {
	public static void main(String[] args) {
		DataBaseConnection seedsConnection = new DataBaseConnection();
		seedsConnection.connect();

		seedsConnection.disconnect();
	}

}

// test is Crawled
class MainTests5 {
	public static void main(String[] args) {
		String url = "https://en.wikipedia.org/wiki/Sinc_function#Relationship_to_the_Dirac_delta_distribution";
		System.out.println(Fetcher.isCrawled(url));
	}
}

//test retrieved urls
class MainTests6 {
	public static void main(String[] args) {
		String url = "https://en.wikipedia.org/wiki/Sinc_function";
		String site = Fetcher.fetchToString(url);
		String[] result = PatternMatcher.ExtractUrlsFromString(site);
		System.out.println(result.length);
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
	}
}