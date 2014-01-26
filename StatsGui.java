import java.io.*;
import java.util.*;

 
public class StatsGui {
	
	public boolean isRacist = true; // guilty until proven innocent
	public int score = 0;
	public int ameurocaScore = 0;
	public int ameurocaGuesses = 0;
	public int africasiaScore = 0;
	public int africasiaGuesses = 0;
	public int numGuesses = 0;
	public int currentStreak = 0;
	public int longestStreak = 0;
	public double overallGuessPercentage = 0.0;
	public double ameurocaPercentage = 0.0;
	public double africasiaPercentage = 0.0;
	
	public void updateBasicStats(boolean input) {
		numGuesses += 1
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
			africasiaGuesses += 1;
			if (input == true) {
				africasiaScore += 1;
			}
		}
	}
	
	public void getOverallPercentage() {
		overallGuessPercentage = score / numGuesses;
	}
	
	public void getAmeurocaPercentage() {
		ameurocaPercentage = ameurocaScore / ameurocaGuesses;
	}
	
	public void getAfricasiaPercentage() {
		africasiaPercentage = africasiaScore / africasiaGuesses;
	}

	public boolean racistCheck() {
		return ((ameurocaPercentage - africasiaPercentage >= 15.0));
	}

	public void saveStats() {
		// code that saves stats
	}


	public void writeStats() {
		// code that writes changes to stats.txt
	}

}
