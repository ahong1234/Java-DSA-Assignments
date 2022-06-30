/* Name: Alexander Hong
 * Description: Utility class containing two overloaded compareTo methods.
 * Determine whether the list object input is in ascending order
 */
import java.util.List;


public class OrderedList {
	public static <T extends Comparable<? super T>> boolean checkSorted(List<T> list) {
		boolean isSorted = true;
		// iterate through list from last index
		for(int i = list.size()-1; i > 0 ; i--) {
			T current = list.get(i);
			if (!checkSorted(list, current)) {
				isSorted = false;
			}
		}
		return isSorted;
	}

	private static <T extends Comparable<? super T>> boolean checkSorted(List<T> list, T current) {
		T currentValue = list.get(list.indexOf(current));
		T nextValue = list.get(list.indexOf(current) - 1);
			if (nextValue != null) {	
				return currentValue.compareTo(nextValue) >= 0; 
			}
		return true;
		}
}