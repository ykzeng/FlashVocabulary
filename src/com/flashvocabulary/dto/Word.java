package com.flashvocabulary.dto;


public class Word implements BaseEntry {
	
	private int id;
	private String word;
	private String phonetics;
	private String antonym;
	private String synonym;
	private int lib_id;
	private String translation;
	
	
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getPhonetics() {
		return phonetics;
	}

	public void setPhonetics(String phonetics) {
		this.phonetics = phonetics;
	}

	public String getAntonym() {
		return antonym;
	}

	public void setAntonym(String antonym) {
		this.antonym = antonym;
	}

	public String getSynonym() {
		return synonym;
	}

	public void setSynonym(String synonym) {
		this.synonym = synonym;
	}

	public int getLib_id() {
		return lib_id;
	}

	public void setLib_id(int lib_id) {
		this.lib_id = lib_id;
	}

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "tb_word";
	}

}
