package edu.cmu.lti.f14.hw3_chongshm.casconsumers;

import java.util.Comparator;
import java.util.List;

public class Storage {
	private int rel;
	private double cosine;
	private String text;
	private int rank;
	private int qid;
	
	public Storage(double a, int b, int c,int  d, String t){
		cosine = a;
		rel = d;
		rank=b;
		qid = c;
		text=t;
		
	}
	public void setRel(int Rel){
		rel = Rel;
	}
	public void setCosine(double Cosine){
		cosine = Cosine;
	}
	public void setRank(int Rank){
		rank = Rank;
	}
	public void setQid(int Qid){
		qid = Qid;
	}

	public void setText(String Text){
		text = Text;
	}
	public double getCosine(){
		return cosine;
	}
	public int getRel(){
		return rel;
	}
	public String getText(){
		return text;
	}
	public int getRank(){
		return rank;
	}
	public int getQid(){
		return qid;
	}
	
		     
		
}
