package hu.syscode.teszt_feladat.profile;

public class StudentNotFoundException extends RuntimeException {

	public StudentNotFoundException(String id) {
		super("Could not find student " + id);
	}
}