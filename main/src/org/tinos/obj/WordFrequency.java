package org.tinos.view.obj;

public class WordFrequency {
	public String getPOS() {
		return POS;
	}

	public void setPOS(String POS) {
		this.POS = POS;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public WordFrequency getLeft() {
		return left;
	}

	public void setLeft(WordFrequency left) {
		this.left = left;
	}

	public WordFrequency getRight() {
		return right;
	}

	public void setRight(WordFrequency right) {
		this.right = right;
	}

	private String word;
	private String POS;
	private int frequency;
	private WordFrequency left;
	private WordFrequency right;
}