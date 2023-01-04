package model.fetcher;

import java.util.ArrayList;

public class DateErrorException extends Throwable {
		//constructor that allows years to be passed as an argument 
		public DateErrorException(ArrayList<String> years) {
			super(years + " has null values");
		}
	}


