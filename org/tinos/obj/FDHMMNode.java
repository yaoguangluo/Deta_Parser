package org.tinos.obj;
import java.util.List;
public class FDHMMNode extends HMMNode{
	public List<String> getNext() {
		return next;
	}
	public void setNext(List<String> next) {
		this.next = next;
	}
	public List<String> getPrev() {
		return prev;
	}
	public void setPrev(List<String> prev) {
		this.prev = prev;
	}
	private List<String> next;
	private List<String> prev;
}