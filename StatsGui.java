import java.io.*;
import java.util.*;

 
public class StatsGui {
	
	public boolean isRacist = true; // guilty until proven innocent
	public int score = 0;
	public int ameurocaScore = 0;
	public int ameurocaGuesses = 0;
	public int oceafricasiaScore = 0;
	public int oceafricasiaGuesses = 0;
	public int numGuesses = 0;
	public int currentStreak = 0;
	public int longestStreak = 0;
	public double overallGuessPercentage = 0.0;
	public double ameurocaPercentage = 0.0;
	public double oceafricasiaPercentage = 0.0;
	
	public void updateBasicStats(boolean input) {
		numGuesses += 1;
		if (input = true) {
			score += 1;
			currentStreak += 1;
			if (currentStreak >= longestStreak) {
				longestStreak += 1;
			}
		}
	}

	public void updateSpecificStats(boolean input, String continent) {
		if (continent == America || continent == Europe) {
			ameurocaGuesses += 1;
			if (input == true) {
				ameurocaScore += 1;
			}
		}
		else {
			oceafricasiaGuesses += 1;
			if (input == true) {
				oceafricasiaScore += 1;
			}
		}
	}
	
	public void getOverallPercentage() {
		overallGuessPercentage = score / numGuesses;
	}
	
	public void getAmeurocaPercentage() {
		ameurocaPercentage = ameurocaScore / ameurocaGuesses;
	}
	
	public void getOceafricasiaPercentage() {
		oceafricasiaPercentage = oceafricasiaScore / oceafricasiaGuesses;
	}

	public boolean racistCheck() {
		return ((ameurocaPercentage - oceafricasiaPercentage >= 15.0));
	}

	public void saveStats() {
		// code that saves stats
		// do we really need this?
	}


	public void writeStats() {
		// code that writes changes to stats.txt
		// this also seems unnecessary
	}

}
