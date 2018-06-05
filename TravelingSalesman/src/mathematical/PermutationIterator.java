package mathematical;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PermutationIterator<O extends Object> {
	List<O> list;
	int[] count;
	public PermutationIterator(List<O> list) {
		this.list = list;
		count = new int[list.size()];
		for (int i = 0; i < count.length; i++) count[i] = 0;
	}
	
	public List<O> next() {
		if (!hasNext()) return null;
		List<O> permutation = new LinkedList<>();
		List<O> elements = new ArrayList<>(list);
		for (int i = 0; i < count.length; i++) {
			permutation.add(elements.get(count[i]));
			elements.remove(count[i]);
		}
		boolean unchanged = true;
		for (int i = 0; unchanged && i < count.length; i++) {
			if (count[count.length - (i+1)] < i) {
				count[count.length - (i+1)] += 1;
				for (int j = count.length - (i); j < count.length; j++) count[j] = 0;
				unchanged = false;
			}
		}
		if (unchanged) count[0] = -1;
		return permutation;
	}
	
	public boolean hasNext() {
		return count[0] != -1;
	}
}
