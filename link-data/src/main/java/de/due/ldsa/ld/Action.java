package de.due.ldsa.ld;

/**Interface for actions of any type being carried out
 * on an instance of any type.
 * 
 * @author Jan Kowollik
 *
 * @param <T> the instance type
 */
public interface Action<T> {

	public void onAction(T t);
	
}
