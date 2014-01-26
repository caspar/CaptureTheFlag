import java.io.*;
import java.util.*;

 
public class StatsGui {
	
	public boolean isRacist = true; // guilty until proven innocent
	public int score = 0;
	public int numGuesses = 0;
	public int currentStreak = 0;
	public int longestStreak = 0;
	public double guessPercentage = 0.0;
	
	public void getPercentage() {
		guessPercentage = score / numGuesses
	}

//	public boolean racistCheck() {
	//	return (europeScore + americaScore > otherScore);
		// in another file, if this is true, the message is "I think you're a racist >:("
//	}

	public void saveStats() {
		// code that saves stats
	}


	public void writeStats() {
		// code that writes changes to stats.txt
	}

}
