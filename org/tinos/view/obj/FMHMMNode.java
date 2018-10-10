package org.tinos.view.obj;
import java.util.Map;
public class FMHMMNode extends HMMNode{
	public Map<String, Integer> getNext(){
		return next;
	}
	public void setNext(Map<String, Integer> next){
		this.next = next;
	}
	public Map<String, Integer> getPrev(){
		return prev;
	}
	public void setPrev(Map<String, Integer> prev){
		this.prev = prev;
	}
	private Map<String, Integer> next;
	private Map<String, Integer> prev;
}