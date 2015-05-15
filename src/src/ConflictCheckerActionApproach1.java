package src;

import java.util.ArrayList;
import java.util.List;

public class ConflictCheckerActionApproach1 {

	public List<List<Norm>> verifyConflicts(List<Norm> norms) {
		List<List<Norm>> conflicts = new ArrayList<List<Norm>>();

		List<List<Norm>> normsNtoN = Combinations.generateAllCombinations(norms, 2);

		for (List<Norm> normsTemp : normsNtoN) {
			boolean conflictCheckerReturn = this.verifyConflictsCase1(normsTemp);
			if (conflictCheckerReturn) {
				conflicts.add(normsTemp);
			}
		}
		return conflicts;
	}

	private boolean verifyConflictsCase1(List<Norm> norms) {
		Norm norm1 = norms.get(0);
		Norm norm2 = norms.get(1);

		boolean conflictConcept = deonticConceptChecker(norm1, norm2);
		if (!conflictConcept) {
			return false;
		}
		return true;
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
