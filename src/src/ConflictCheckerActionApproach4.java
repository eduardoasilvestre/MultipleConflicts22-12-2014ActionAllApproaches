package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import util.SetUtil;

public class ConflictCheckerActionApproach4 {

	public List<List<Norm>> verifyConflicts(List<Norm> norms) {
		List<List<Norm>> conflicts = new ArrayList<List<Norm>>();

		List<List<Norm>> normsNtoN = Combinations.generateAllCombinations(norms, 2);

		for (List<Norm> normsTemp : normsNtoN) {
			boolean conflictCheckerReturn = this.verifyConflictsCase4(normsTemp);
			if (conflictCheckerReturn) {
				conflicts.add(normsTemp);
			}
		}
		return conflicts;
	}

	private boolean verifyConflictsCase4(List<Norm> norms) {
		Norm norm1 = norms.get(0);
		Norm norm2 = norms.get(1);
		
		if (!this.deonticConceptChecker(norm1, norm2)) {
			return false;
		}
		
		Map<String, Set<String>> mapNorm1 = norm1.getBehavior().getMap();
		Map<String, Set<String>> mapNorm2 = norm2.getBehavior().getMap();

		for (Map.Entry<String, Set<String>> entry : mapNorm1.entrySet()) {
			String key = entry.getKey();
			
			if (!mapNorm2.containsKey(key)) {
				continue;
			}
			Set<String> set1 = mapNorm1.get(key);
			Set<String> set2 = mapNorm2.get(key);
			if (SetUtil.intersection(set1, set2).size() == 1)  {
				return true;
			}
		}
		return false;
	}

	private boolean deonticConceptChecker(Norm norm1, Norm norm2) {
		if ((norm1.getDeonticConcept().equals(DeonticConcept.PROHIBITION) && 
			norm2.getDeonticConcept().equals(DeonticConcept.OBLIGATION))
			|| (norm1.getDeonticConcept().equals(DeonticConcept.PROHIBITION) && 
			norm2.getDeonticConcept().equals(DeonticConcept.PERMISSION))) {
			return true;
		}
		if ((norm2.getDeonticConcept().equals(DeonticConcept.PROHIBITION) && 
			norm1.getDeonticConcept().equals(DeonticConcept.OBLIGATION))
			|| (norm2.getDeonticConcept().equals(DeonticConcept.PROHIBITION) && 
			norm1.getDeonticConcept().equals(DeonticConcept.PERMISSION))) {
			return true;
		}

		return false;
	}
}
