package hashset;

import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;
import java.util.WeakHashMap;

public class PruebaSet {

	public static void main(String[] args) {
		Animal a1 = new Animal("gato", 1);
		Animal a2 = new Animal("gato", 2);
		Animal a3 = new Animal("perro", 1);
		Animal a4 = new Animal("perro", 3);
		Animal a5 = new Animal("perro", 3);

		System.out.println("\nComparaciÃ³n de objetos:");
		System.out.println("a1 es igual a a2:" + a1.equals(a2));
		System.out.println("a1 es igual a a3:" + a1.equals(a3));
		System.out.println("a2 es igual a a3:" + a2.equals(a3));
		System.out.println("a4 es igual a a5:" + a4.equals(a5));
		System.out.println("a4 es igual a a4:" + a4.equals(a4));

		/*
		 * el set considera que dos objetos son iguales cuando se cumple a la vez que:
		 * -El metodo equals devuelve true -El metodo hashCode devuelve el mismo entero
		 */
		System.out.println("\nValor de los cÃ³digos hash:");
		System.out.println("CÃ³digo hash de a1:" + a1.hashCode());
		System.out.println("CÃ³digo hash de a2:" + a2.hashCode());
		System.out.println("CÃ³digo hash de a3:" + a3.hashCode());
		System.out.println("CÃ³digo hash de a4:" + a4.hashCode());
		System.out.println("CÃ³digo hash de a5:" + a5.hashCode());

		// Creamos un hashSet
		HashSet<Animal> animales = new HashSet<>();
		animales.add(a1);
		animales.add(a2);
		animales.add(a3);
		animales.add(a4);
		animales.add(a5);

		// imprimo el contenido del conjunto
		System.out.println("\nContenido del HashSet (SIN ORDEN):");
		for (Animal animal : animales) {
			System.out.println(animal + " HahsCode:" + animal.hashCode());
		}

		// Creamos un LinkedHashSet
		LinkedHashSet<Animal> animalesPorOrdenInsercion = new LinkedHashSet<>();
		animalesPorOrdenInsercion.add(a1);
		animalesPorOrdenInsercion.add(a2);
		animalesPorOrdenInsercion.add(a3);
		animalesPorOrdenInsercion.add(a4);
		animalesPorOrdenInsercion.add(a5);

		// imprimo el contenido del conjunto
		System.out.println("\nContenido del LinkedHashSet " + "(POR ORDEN DE INSERCION):");
		for (Animal animal : animalesPorOrdenInsercion) {
			System.out.println(animal + " HahsCode:" + animal.hashCode());
		}

		// Creamos un TreeSet
		TreeSet<Animal> animalesPorOrdenEspecie = new TreeSet<>();
		animalesPorOrdenEspecie.add(a1);
		animalesPorOrdenEspecie.add(a2);
		animalesPorOrdenEspecie.add(a3);
		animalesPorOrdenEspecie.add(a4);
		animalesPorOrdenEspecie.add(a5);

		// imprimo el contenido del conjunto
		System.out.println("\nContenido del TreeSet " + "(POR ORDEN DE ESPECIE):");
		for (Animal animal : animalesPorOrdenEspecie) {
			System.out.println(animal + " HahsCode:" + animal.hashCode());
		}
		/*
		 * A la hora de insertar objetos en un TreeSet, aunque equals == true y HashCode
		 * tenga el mismo Hash, prevalecerÃ¡ el resultado de la funciÃ³n compareTo() (en
		 * realidad en hash no se tiene en cuenta porque ahora no se usa, no es un
		 * HashSet).
		 */

		/*
		 * En realidad las variantes de Set se usan poco (lo mas normal, de manera
		 * auxiliar para recorrer un HashMap con keySet y entrySet). En realidad,
		 * tambien los Mapas del tipo que sean se usan poco (ya que normalmente hay un
		 * índice consecutivo).
		 */

		/*
		 * Si queremos una coleccion de elementos no repetidos podemos usar un ArrayList
		 * con una comprobacion con equals() antes de insertar. Si partimos ya de una
		 * lista con elementos repetidos podemos transformarlo a LinkedHashSet para que
		 * respete el orden y elimine los duplicados y pasar el resultado a la lista
		 * nuevamente. Si ademÃ¡s queremos ordenarla por valores, usaremos TreeSet
		 * (HashSet no es recomendable porque me devuelve la lista sin el mismo orden
		 * que tenia)
		 */
		ArrayList<Float> decimalesRepetidos = new ArrayList<>();
		decimalesRepetidos.add(134f);
		decimalesRepetidos.add(6.8f);
		decimalesRepetidos.add(5.4f);
		decimalesRepetidos.add(-300f);
		decimalesRepetidos.add(6.8f);
		decimalesRepetidos.add(5.4f);
		decimalesRepetidos.add(-3.56f);
		System.out.println("\n\nLista con decimales Repetidos:");
		System.out.println(decimalesRepetidos);

		Set<Float> decimalesNoRepetidosSinOrdenar = new HashSet<>(decimalesRepetidos);
		System.out.println("\n\nDecimales No Repetidos sin ordenar (HashSet):");
		System.out.println(decimalesNoRepetidosSinOrdenar);

		Set<Float> decimalesNoRepetidosOrdenadosPorInsercion = new LinkedHashSet<>(decimalesRepetidos);
		System.out.println("\n\nDecimales No Repetidos ordenados igual que la lista (LinkedHashSet):");
		System.out.println(decimalesNoRepetidosOrdenadosPorInsercion);

		Set<Float> decimalesNoRepetidosOrdenadosPorValor = new TreeSet<>(decimalesRepetidos);
		System.out.println("\n\nDecimales No Repetidos ordenados por valor (TreeSet):");
		System.out.println(decimalesNoRepetidosOrdenadosPorValor);

		// despues de crear el Set se transforma de vuelta a lista para seguir usandola
		System.out.println("\n\nLista con decimales NO repetidos:");
		decimalesRepetidos = new ArrayList<>(decimalesNoRepetidosOrdenadosPorInsercion);

		/*
		 * Ademas de Comparable, se puede usar también Comparator para tener varios
		 * comparadores, por diferentes atributos, orden inverso... ya que Comparable
		 * solo nos permite un tipo de comparación dentro de la misma clase.
		 */
		TreeSet<Animal> ordenadoPorCodigo = 
				new TreeSet<>(new ComparadorDeAnimalesPorCodigo());
		ordenadoPorCodigo.add(a1);
		ordenadoPorCodigo.add(a2);
		ordenadoPorCodigo.add(a3);
		ordenadoPorCodigo.add(a4);
		ordenadoPorCodigo.add(a5);
		System.out.println("\nContenido del TreeSet " + "(POR ORDEN DE CODIGO):");
		System.out.println(ordenadoPorCodigo);

		TreeSet<Animal> ordenadoPorCodigoDesc = 
				new TreeSet<Animal>(new ComparadorDeAnimalesPorCodigoDescendente());
		ordenadoPorCodigoDesc.add(a1);
		ordenadoPorCodigoDesc.add(a2);
		ordenadoPorCodigoDesc.add(a3);
		ordenadoPorCodigoDesc.add(a4);
		ordenadoPorCodigoDesc.add(a5);
		System.out.println("\nContenido del TreeSet " + "(POR ORDEN DE CODIGO DESCENDENTE):");
		System.out.println(ordenadoPorCodigoDesc);
		
		/*Otra manera de usar un Comparator es crearlo directamente
		 * en el constructor de la coleccion (en lugar de estar
		 * declarado en otra clase) caso en que será anónimo
		 * y se usará directamente el tipo de su padre (la interfaz)*/
		TreeSet<Animal> ordenadoPorEspecieDesc = 
				new TreeSet<Animal>( new Comparator<Animal>() {
					@Override
					public int compare(Animal a1, Animal a2) {		
						return a2.getEspecie().compareTo(a1.getEspecie());	
					}					
				} );		
		ordenadoPorEspecieDesc.add(a1);
		ordenadoPorEspecieDesc.add(a2);
		ordenadoPorEspecieDesc.add(a3);
		ordenadoPorEspecieDesc.add(a4);
		ordenadoPorEspecieDesc.add(a5);
		System.out.println("\nContenido del TreeSet " + "(POR ORDEN DE ESPECIE DESCENDENTE):");
		System.out.println(ordenadoPorEspecieDesc);
		
		/*En realidad el método anterior es enrevesado, pero es un 
		 * paso intermedio para llegar a usar una expresion lambda
		 * al crear el Comparator, ahorrandonos mucho código*/
		TreeSet<Animal> ordenadoPorEspecieDescLambda = 
				new TreeSet<Animal>( 
					// (parametros funcion) -> {cuerpo funcion}
					(anim1,anim2) ->  anim2.getEspecie().compareTo(anim1.getEspecie())				
				);		
		ordenadoPorEspecieDescLambda.add(a1);
		ordenadoPorEspecieDescLambda.add(a2);
		ordenadoPorEspecieDescLambda.add(a3);
		ordenadoPorEspecieDescLambda.add(a4);
		ordenadoPorEspecieDescLambda.add(a5);
		System.out.println("\nContenido del TreeSet " + 
				"(POR ORDEN DE ESPECIE DESCENDENTE CON EXPRESION LAMBDA):");
		System.out.println(ordenadoPorEspecieDescLambda);
		
		/*
		 * El uso de colecciones ordenadas por diferentes campos es donde mas se usa los
		 * Comparable y Comparator, por ejemplo, ordenar listas por diferentes campos
		 * Usos principales de Comparator:
				-Cuando queremos ordenar un arrayList 
					con su metodo sort.
				-Cuando queremos crear un TreeMap pasando 
					el comparador a su constructor. 
					Se podría usar para crear un TreeSet 
					también, pero acabamos de ver que se 
					puede usar en su lugar una lista y 
					ordenarla con sort.					
		 */
		ArrayList<Animal> miLista = new ArrayList<>();
		miLista.add(a1);
		miLista.add(a2);
		miLista.add(a3);
		miLista.add(a4);
		miLista.add(a5);
		System.out.println("\n\nContenido de la lista antes de ordenar: ");
		System.out.println(miLista);
		miLista.sort(new ComparadorDeAnimalesPorCodigo() );
		System.out.println("\nContenido de la lista despues de ordenar " + "(POR  CODIGO):");
		System.out.println(miLista);
		
		
	}

}
