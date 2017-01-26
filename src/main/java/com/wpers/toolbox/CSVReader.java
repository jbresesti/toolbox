package com.wpers.toolbox;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * * Java Program to parse and read CSV file using traditional BufferedReader *
 * approach and by using more functional CSV parser from Apache Commons CSV *
 * library. Apache Commons CSV support different CSV format including default *
 * one, with or without header, reading EXCEL or XLS CSV file etc. * * @author
 */
public class CSVReader {
	private static class Country {
		private String name;
		private String capital;
		private String currency;

		public Country(String name, String capital, String currency) {
			this.name = name;
			this.capital = capital;
			this.currency = currency;
		}

		public String name() {
			return name;
		};

		public String capital() {
			return capital;
		}

		public String currency() {
			return currency;
		}

		@Override
		public String toString() {
			return "Country [name=" + name + ", capital=" + capital + ", currency=" + currency + "]";
		}
	}

	public static void main(String args[]) throws FileNotFoundException, IOException {
		System.out.println("Reading from CSV file using BufferedReader and String Split");
		List nations = readCSV();
		print(nations);
		System.out.println("Parsing CSV file using CSVParser of Apache commons CSV");
		parseCSV();
	}

	/*
	 * * Java program to read CVS file using BufferedReader and String split() *
	 * method
	 */ public static List readCSV() throws FileNotFoundException, IOException {
		List countries = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader("./src/main/resources/countries.csv"));
		String line = br.readLine(); // Reading header, Ignoring
		while ((line = br.readLine()) != null && !line.isEmpty()) {
			String[] fields = line.split(",");
			String name = fields[0];
			String capital = fields[1];
			String currency = fields[2];
			Country nation = new Country(name, capital, currency);
			countries.add(nation);
		}
		br.close();
		return countries;
	}

	/*
	 * * Method to read CSV file using CSVParser from Apache Commons CSV
	 */ public static void parseCSV() throws FileNotFoundException, IOException {
		CSVParser parser = new CSVParser(new FileReader("./src/main/resources/countries.csv"), CSVFormat.DEFAULT.withHeader());
		for (CSVRecord record : parser) {
			System.out.printf("%s\t%s\t%s\n", record.get("NAME"), record.get("CAPITAL"), record.get("CURRENCY"));
		}
		parser.close();
	}

	public static void print(List<Country> countries) {
		System.out.println("========================");
		for (Country country : countries) {
			System.out.println(country);
		}
		System.out.println("========================");
	}

}