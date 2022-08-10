import ex.api.AnalysisService;

module client {
	exports serviceloader.client;

	requires java.desktop;
	requires api;
	requires opencsv;
	uses AnalysisService;
}