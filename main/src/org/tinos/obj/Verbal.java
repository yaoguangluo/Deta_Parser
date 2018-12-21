package org.tinos.view.obj;
public class Verbal{
	public String getChinese() {
		return chinese;
	}
	public void setChinese(String chinese) {
		this.chinese = chinese;
	}
	public String getEnglish() {
		return english;
	}
	public void setEnglish(String english) {
		this.english = english;
	}
	public String getPartOfSpeech() {
		return partOfSpeech;
	}
	public void setPartOfSpeech(String partOfSpeech) {
		this.partOfSpeech = partOfSpeech;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	public Verbal getNext() {
		return next;
	}
	public void setNext(Verbal next) {
		this.next = next;
	}
	public Verbal getPrev() {
		return prev;
	}
	public void setPrev(Verbal prev) {
		this.prev = prev;
	}
	private String chinese;
	private String english;
	private String partOfSpeech;
	private String explain;
	private Verbal next;
	private Verbal prev;
}