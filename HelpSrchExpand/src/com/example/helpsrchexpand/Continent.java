package com.example.helpsrchexpand;

import java.util.ArrayList;

public class Continent {

	private String name;

	private ArrayList<BeanHelp> countryList = new ArrayList<BeanHelp>();

	public Continent(String name, ArrayList<BeanHelp> countryList) {
		super();
		this.name = name;
		this.countryList = countryList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<BeanHelp> getCountryList() {
		return countryList;
	}

	public void setCountryList(ArrayList<BeanHelp> countryList) {
		this.countryList = countryList;
	};

}
