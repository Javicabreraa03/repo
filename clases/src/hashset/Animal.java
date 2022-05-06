package hashset;

public class Animal implements Comparable<Animal> {
	private String especie;
	private int codigo;

	public Animal(String especie, int codigo) {
		this.especie = especie;
		this.codigo = codigo;
	}

	public String getEspecie() {
		return especie;
	}

	public int getCodigo() {
		return codigo;
	}
	
	@Override
	public String toString() {
		return especie+"("+codigo+")";
	}
	
	//considero que dos animales serán iguales si 
	//son iguales ambos atributos
	@Override
	public boolean equals(Object o) {
		
		//son iguales si sus especies son iguales
		boolean especieIgual =  this.especie.equals( ((Animal)o).especie);
		
		//son iguales si sus codigos son iguales  
		boolean codigoIgual =  this.codigo == ((Animal)o).codigo;
		
		//son iguales si ambos atributos son iguales
		boolean especieYcodigoIgual = 
				especieIgual && codigoIgual;
		
		return especieYcodigoIgual;								
	}

	//el hascode será diferente aunque dos objetos o1 y o2 
	//tengan el mismo contenido exactamente. 
	//Por ello, debemos redefinirlos a un valor que nos 
	//interese. En este caso tomamos la 1ª letra de especie 
	//como ejemplo (sencillo aunque no sea un caso real para usar)
	@Override
	public int hashCode() {
		return codigo;
		//return especie.charAt(0);
	}

	@Override
	public int compareTo(Animal otroAnimal) {
		return this.especie.compareTo(otroAnimal.especie);		
	}
}
