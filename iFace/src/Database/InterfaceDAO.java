package Database;

import java.util.ArrayList;

public interface InterfaceDAO <T> {
	
	public void add(T object);
	public void remove(T object);
	public void set(T object);
	public void listObject(ArrayList<T> object);
	
}
