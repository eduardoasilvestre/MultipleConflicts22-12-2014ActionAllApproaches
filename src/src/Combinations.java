package src;

import java.util.ArrayList;
import java.util.List;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

public class Combinations {
	public static List<List<Norm>> generateAllCombinations(List<Norm> norms, int i) {
		//System.out.println("An�lise ser� feita com  o seguinte n�mero de normas: " + i);
		ICombinatoricsVector<Norm> initialVector = Factory.createVector(norms);
		   
		Generator<Norm> gen = Factory.createSimpleCombinationGenerator(initialVector, i);
		
		List<List<Norm>> r = new ArrayList<List<Norm>>();
		
		for (ICombinatoricsVector<Norm> combination : gen) {
			r.add(combination.getVector());
		}
		return r;
	}
}
