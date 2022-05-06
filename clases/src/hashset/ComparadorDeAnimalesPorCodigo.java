package hashset;

import java.util.Comparator;

public class ComparadorDeAnimalesPorCodigo implements Comparator<Animal>{

	@Override
	public int compare(Animal a1, Animal a2) {
		return a1.getCodigo() - a2.getCodigo();	
	}
}
