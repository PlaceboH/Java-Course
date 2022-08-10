import ex.api.AnalysisService;
import serviceloader.impl.AnalysisMediana;
import serviceloader.impl.AnalysisSrednia;

module impl {
	requires api;
	exports serviceloader.impl;
	
	provides AnalysisService
		with AnalysisSrednia, AnalysisMediana;
}