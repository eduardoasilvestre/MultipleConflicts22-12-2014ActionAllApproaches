package src;

import java.util.ArrayList;
import java.util.List;

public class ConflictCheckerActionApproach2 {

	public List<List<Norm>> verifyConflicts(List<Norm> norms) {
		List<List<Norm>> conflicts = new ArrayList<List<Norm>>();

		List<List<Norm>> normsNtoN = Combinations.generateAllCombinations(norms, 2);

		for (List<Norm> normsTemp : normsNtoN) {
			boolean conflictCheckerReturn = this.verifyConflictsCase2(normsTemp);
			if (conflictCheckerReturn) {
				conflicts.add(normsTemp);
			}
		}
		return conflicts;
	}

	private boolean verifyConflictsCase2(List<Norm> norms) {
		Norm norm1 = norms.get(0);
		Norm norm2 = norms.get(1);

		if (!this.deonticConceptChecker(norm1, norm2)) {
			return false;
		}

		String objectNorm1 = norm1.getBehavior().getObject(); 
		String objectNorm2 = norm2.getBehavior().getObject(); 
		if (objectNorm1.equals(objectNorm2)) {
			return true;
		}
		return false;
	}

	private boolean deonticConceptChecker(Norm norm1, Norm norm2) {
		if ((norm1.getDeonticConcept().equals(DeonticConcept.PROHIBITION) && norm2
				.getDeonticConcept().equals(DeonticConcept.OBLIGATION))
				|| (norm1.getDeonticConcept()
						.equals(DeonticConcept.PROHIBITION) && norm2
						.getDeonticConcept().equals(DeonticConcept.PERMISSION))) {
			return true;
		}
		if ((norm2.getDeonticConcept().equals(DeonticConcept.PROHIBITION) && norm1
				.getDeonticConcept().equals(DeonticConcept.OBLIGATION))
				|| (norm2.getDeonticConcept()
						.equals(DeonticConcept.PROHIBITION) && norm1
						.getDeonticConcept().equals(DeonticConcept.PERMISSION))) {
			return true;
		}

		return false;
	}
}
