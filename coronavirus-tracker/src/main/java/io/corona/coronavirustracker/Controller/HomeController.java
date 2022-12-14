package io.corona.coronavirustracker.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.corona.coronavirustracker.CoronaVirusDataService;
import io.corona.coronavirustracker.models.LocationStats;

@Controller
public class HomeController {
	
	
	@Autowired
	CoronaVirusDataService coronaVirusDataService;
	
	@GetMapping("/")
	public String home(Model model)
	{
		List<LocationStats> allStats = coronaVirusDataService.getAllStats();
		int totalReportedCases=allStats.stream().mapToInt(stat->stat.getLatestTotalCases()).sum();
		model.addAttribute("locationStats", allStats);
		model.addAttribute("totalReportedCases", totalReportedCases);
		return "home";
	}
}
