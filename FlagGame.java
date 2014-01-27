import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.concurrent.TimeUnit;


public class FlagGame {
    private static FlagGame fg;
    private static ArrayList<String> correct = new ArrayList<String>();
    private static ArrayList<String> incorrect = new ArrayList<String>();
    private static int longestStreak = 0;
    private static MenuGui mg;
    private static GameGui gg;
    private static StatsGui sg;

    public static void main(String[] args) {
	fg = new FlagGame();
	fg.createMenuGui();
    }

    public void createMenuGui() {
	mg = new MenuGui(fg);
    }

    public void createGameGui(int type, int mod) {
	gg = new GameGui(fg, type, mod);
    }

    public void createStatsGui() {
	sg = new StatsGui(fg);
    }

    public void addCorrect(ArrayList<String> correct) {
	this.correct.addAll(correct);
    }

    public void addIncorrect(ArrayList<String> incorrect) {
	this.incorrect.addAll(incorrect);
    }

    public ArrayList<String> getCorrect() {
	return correct;
    }

    public ArrayList<String> getIncorrect() {
	return incorrect;
    }

    public int getLongestStreak() {
	return longestStreak;
    }

    public void setLongestStreak(int lS) {
	if (lS > longestStreak)
	    longestStreak = lS;
    }
}
