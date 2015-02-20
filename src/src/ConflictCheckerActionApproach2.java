package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

public class ConflictCheckerActionApproach2 {
	
	public void verifyConflicts(List<Norm> norms) {
		
		List<List<Norm>> normsNtoN = this.generateAllCombinations(norms, 2);
		
		for (int i = 0; i < normsNtoN.size(); i++) {
		 
			List<Norm> normsTemp = normsNtoN.get(i);
			
			Norm norm1 = normsTemp.get(0);
			Norm norm2 = normsTemp.get(1);
			
			boolean conflictCheckerReturn = this.verifyConflictsCase2(norm1, norm2);
			
		    if (conflictCheckerReturn) {
		    	System.out.println(norm1);
		    	System.out.println(norm2);
		    	System.out.println();
		    } 
		}
	}
	
	private boolean verifyConflictsCase2(Norm norm1, Norm norm2) {
		if (!this.deonticConceptChecker(norm1, norm2)) {
			return false;
		}
		
		if (!this.isThereEquivalenceBetweenNorms(norm1, norm2)) {
			return false;
		}
		
		Map<String,Set<String>> mapNorm1 = norm1.getBehavior().getMap();
		Map<String,Set<String>> mapNorm2 = norm2.getBehavior().getMap();
				
				
		for (Map.Entry<String,Set<String>> entry : mapNorm1.entrySet()) {
			String key = entry.getKey();
			if (mapNorm2.containsKey(key)) {
				return true;
			}
		}		
		return false;
	}
	
	private List<List<Norm>> generateAllCombinations(List<Norm> norms, int i) {
		//System.out.println("Análise será feita com  o seguinte número de normas: " + i);
		ICombinatoricsVector<Norm> initialVector = Factory.createVector(norms);
		   
		Generator<Norm> gen = Factory.createSimpleCombinationGenerator(initialVector, i);
		
		List<List<Norm>> r = new ArrayList<List<Norm>>();
		
		for (ICombinatoricsVector<Norm> combination : gen) {
			r.add(combination.getVector());
		}
		return r;
	}

	private boolean isThereEquivalenceBetweenNorms(Norm norm1, Norm norm2) {

		// returns true if the context of the norms are the same
		boolean conflictContext = contextChecker(norm1, norm2);
		if (!conflictContext) {
			return false;
		}

		// returns true if the if the entities are the same OR one is ALL
		boolean conflictEntity = entityChecker(norm1, norm2);
		if (!conflictEntity) {
			return false;
		}

		// returns true if there is not conflict between activation and deactivation constraint
		boolean conflictConstraint = constraintChecker(norm1, norm2);
		if (!conflictConstraint) {
			return false;
		}

		// returns true if the action are the same
		boolean conflictAction = actionChecker(norm1, norm2);
		if (!conflictAction) {
			return false;
		}

		// at this moment all conditions are valid and the norms are in conflict
		return true;
	}
	
	public boolean deonticConceptChecker(Norm norm1, Norm norm2) {
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
	

	private boolean contextChecker(Norm norm1, Norm norm2) {
		Context c1 = norm1.getContext();
		Context c2 = norm2.getContext();

		if (c1 == null || c1.getName() == null || c1.getContextType() == null) {
			c1 = new Context("context", ContextType.ORGANIZATION);
			norm1.setContext(c1);
		}

		if (c2 == null || c2.getName() == null || c2.getContextType() == null) {
			c2 = new Context("context", ContextType.ORGANIZATION);
			norm2.setContext(c2);
		}

		if (norm1.getContext().equals(norm2.getContext())) {
			return true;
		}
		return false;
	}
	
	private boolean entityChecker(Norm norm1, Norm norm2) {

		Entity e1 = norm1.getEntity();
		Entity e2 = norm2.getEntity();
		//it is implementation is different from the last version. Here
		//we consider if the entity is null it can be modified
		
		boolean flag1 = false;
		boolean flag2 = false;

		if (e1 == null || e1.getName() == null || e1.getEntityType() == null) {
			e1 = new Entity("entity", EntityType.ALL);
			norm1.setEntity(e1);
			flag1 = true;
		}
		if (e2 == null || e2.getName() == null || e2.getEntityType() == null) {
			e2 = new Entity("entity", EntityType.ALL);
			norm1.setEntity(e1);
			flag2 = true;
		}
		
		if (flag1 && flag2) {
			return true;
		}
		//if flag's are false
		// if the execution arrived here means that all fields are filled
		if (e1.getEntityType().equals(EntityType.ALL)) {
			e1.setEntityType(e2.getEntityType());
			norm2.setEntity(e2);
		}
		if (e2.getEntityType().equals(EntityType.ALL)) {
			e2.setEntityType(e1.getEntityType());
			norm2.setEntity(e2);
		}

		// if the entities are equal
		if (norm1.getEntity().equals(norm2.getEntity())) {
			return true;
		}

		return false;
	}

	private boolean actionChecker(Norm norm1, Norm norm2) {
		//its implementation was changed from the last version
		if (norm1.getBehavior() == null || norm2.getBehavior() == null) {
			return false;
		}
		String actionName1 = norm1.getBehavior().getName();
		String actionName2 = norm2.getBehavior().getName();
		
		if (actionName1.equals(actionName2)) {
			return true;
		}
		return false;
		//its missing new cases for new behaviors
	}
	
	private boolean constraintChecker(Norm norm1, Norm norm2) {
		
		//for realize the comparisons all the fields must be filled, if one field is null then we don't have problem
		if (norm1.getActivationConstraint() == null || norm1.getDeactivationConstraint() == null ||
			norm2.getActivationConstraint() == null || norm2.getDeactivationConstraint() == null) {
			
			norm1.setActivationConstraint(null);
			norm2.setActivationConstraint(null);
			norm1.setDeactivationConstraint(null);
			norm2.setDeactivationConstraint(null);
			
			return true;
		}
		
		ConstraintType na1 = norm1.getActivationConstraint().getConstraintType();
		ConstraintType nd1 = norm1.getDeactivationConstraint().getConstraintType();
		
		ConstraintType na2 = norm2.getActivationConstraint().getConstraintType();
		ConstraintType nd2 = norm2.getDeactivationConstraint().getConstraintType();
		
		//it is necessary only 3 tests
		if (!na1.equals(nd1) || !na2.equals(nd2) || !na1.equals(na2)) {
			norm1.setActivationConstraint(null);
			norm2.setActivationConstraint(null);
			norm1.setDeactivationConstraint(null);
			norm2.setDeactivationConstraint(null);
			
			return true;
		}
		
		// If the activation conditions are actions
		if (norm1.getActivationConstraint().getConstraintType().equals(ConstraintType.ACTIONTYPE)
				&& norm1.getActivationConstraint().getConstraintType().equals(ConstraintType.ACTIONTYPE)) {

			//todo...o tratamento vai ser realizado no futuro, caso necessário
				
			return true;
		}
		
		//
		//at this moment the constrainttype are both DATETYPE, so it is not necessary more comparisons
		//
		
		DateTime d1Begin = ((ConstraintDate) norm1.getActivationConstraint()).getDate();
		DateTime d1End = ((ConstraintDate) norm1.getDeactivationConstraint()).getDate();
		DateTime d2Begin = ((ConstraintDate) norm2.getActivationConstraint()).getDate();
		DateTime d2End = ((ConstraintDate) norm2.getDeactivationConstraint()).getDate();
		
		boolean r = this.compareDateIntervals(d1Begin, d1End, d2Begin, d2End);
		return r;
	}
	
	private boolean compareDateIntervals(DateTime d1Begin, DateTime d1End, DateTime d2Begin, DateTime d2End){
		Interval i1 = new Interval(d1Begin,d1End);
		Interval i2 = new Interval(d2Begin,d2End);
		return i1.overlaps(i2);
		//http://stackoverflow.com/questions/17106670/how-to-check-a-timeperiod-is-overlapping-another-time-period-in-java
	}
}
