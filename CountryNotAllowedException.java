package model.fetcher;

public class CountryNotAllowedException extends Throwable {
	//constructor that allows a string to be passed as an argument 
	public CountryNotAllowedException(String err) {
		super(err);
	}
}
