package com.flashvocabulary.dto;


public class WordSentenceView implements BaseEntry {
	
	private int id;
	private int wid;
	private int sid;
	private String word;
	private String sentence;
	private String translation;
	public WordSentenceView(){}
	
	public WordSentenceView(int wid,int sid,String word,String sentence,String translation)
	{
		this.wid = wid;
		this.sid = sid;
		this.word = word;
		this.sentence = sentence;
		this.translation = translation;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWid() {
		return wid;
	}

	public void setWid(int wid) {
		this.wid = wid;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return "id";
	}

	@Override
	public Boolean isPK_Increment() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "tb_WordSentenceView";
	}

}
