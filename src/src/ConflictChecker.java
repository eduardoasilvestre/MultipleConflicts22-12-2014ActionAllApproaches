package src;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import util.SetUtil;

public class ConflictChecker {
	
	public ConflictChecker() {
	}
	
	public List<List<Norm>> conflictChecker(List<Norm> normsSet) {
		List<List<Norm>> listOfnormsSet = this.classifyNormsInSets(normsSet);
		List<List<Norm>> conflicts = this.checkForConflicts(listOfnormsSet);
		return conflicts;
	}
	
	private List<List<Norm>> checkForConflicts(List<List<Norm>> normsSet) {
		List<List<Norm>> conflicts = new ArrayList<List<Norm>>();
		for (List<Norm> normsTemp : normsSet) {
			List<List<Norm>> conflictsTemp = new ArrayList<List<Norm>>();
			if(normsTemp.size() < 2) {
				continue;
			}
			
			//verify if the norms really intersect
			//TODO this code is commented because the norms not all norms analysed have the period defined
			/*if (!this.normIntersects(normsTemp)) {
				continue;
			}*/
			
			//checks the conflicts between all possible combination of cases 1..6
			List<List<Norm>> normsNtoN = Combinations.generateAllCombinations(normsTemp, 2);
			conflictsTemp = this.verifyConflictsAmongDiffCases(normsNtoN);
			conflicts.addAll(conflictsTemp);
			normsNtoN.clear();
			conflictsTemp.clear();
			
			//checkes the conflicts between the norms of cases 1..6 (here only norms of same case can be checked together)
			conflictsTemp = this.verifyConflictsAmongEqualCases(normsTemp);
			conflicts.addAll(conflictsTemp);
			conflictsTemp.clear();
		}
		return conflicts;
	}
	
	private List<List<Norm>> verifyConflictsAmongDiffCases(List<List<Norm>> normsNtoN) {
		List<List<Norm>> conflicts = new ArrayList<List<Norm>>();
		
		for (List<Norm> normsTemp : normsNtoN) {
			
			//gets the type of norms (from 1...6)
			String n1type = this.getTheBehaviorType(normsTemp.get(0));
			String n2type = this.getTheBehaviorType(normsTemp.get(1));
			
			//after this only different types of norms (except case 5 and case 6)
			if ("NONE".equals(n1type) || "NONE".equals(n2type) || n1type.equals(n2type) || (n1type.equals("case5") && n2type.equals("case6")) || (n1type.equals("case6") && n2type.equals("case5"))) {
				continue;
			}

			//after this one norm permits (obliges) and another one forbids
			if (!this.deonticConceptChecker(normsTemp.get(0), normsTemp.get(1))) {
				continue;
			}
			//prepare the norms of case 3 and case 5 for analysis
			List<Norm> convertedNorms = this.transformNorms(normsTemp);
			
			Norm norm1 = convertedNorms.get(0);
			Norm norm2 = convertedNorms.get(1);
			
			if ("case1".equals(n1type) || "case1".equals(n2type)) {
				String action1Name = norm1.getBehavior().getName();
				String action2Name = norm2.getBehavior().getName();
				if (action1Name.equals(action2Name)) {
					conflicts.add(normsTemp);
				}
				
			} else if ("case2".equals(n1type) || "case2".equals(n2type)) {
				String action1Name = norm1.getBehavior().getName();
				String action2Name = norm2.getBehavior().getName();
				if (action1Name.equals(action2Name)) {
					String object1Name = norm1.getBehavior().getObject(); 
					String object2Name = norm2.getBehavior().getObject();
					if (object1Name != null && object2Name != null && object1Name.equals(object2Name)) {
						conflicts.add(normsTemp);
					}
				}
			} else  {
				Map<String, Set<String>> map1 = norm1.getBehavior().getMap();
				Map<String, Set<String>> map2 = norm2.getBehavior().getMap();
				
				for (Map.Entry<String,Set<String>> entry1 : map1.entrySet()) {
					String key1 = entry1.getKey();
					Set<String> value1 = entry1.getValue();
					
					Set<String> value2 = map2.get(key1);
					
					if(value1 != null && value2 != null && SetUtil.intersection(value1, value2).size() > 0) {
						conflicts.add(normsTemp);
					}
				}
			}				
		}
		return conflicts;
	}

	private List<List<Norm>> verifyConflictsAmongEqualCases(List<Norm> listOfNorms) {
		List<List<Norm>> conflicts = new ArrayList<List<Norm>>();
		Map<String,List<Norm>> map = this.classifyNorms(listOfNorms);
		
		List<Norm> case1 = map.get("case1");
		if (case1 != null && case1.size() > 1) {
			ConflictCheckerActionApproach1 approach1 = new ConflictCheckerActionApproach1();
			List<List<Norm>> normsRet = approach1.verifyConflicts(case1);
			conflicts.addAll(normsRet);
		}
		
		
		List<Norm> case2 = map.get("case2");
		if (case2 != null && case2.size() > 1) {
			ConflictCheckerActionApproach2 approach2 = new ConflictCheckerActionApproach2();
			List<List<Norm>> normsRet = approach2.verifyConflicts(case2);
			conflicts.addAll(normsRet);
		}
		
		
		List<Norm> case3 = map.get("case3");
		if (case3 != null && case3.size() > 1) {
			ConflictCheckerActionApproach3 approach3 = new ConflictCheckerActionApproach3();
			List<List<Norm>> normsRet = approach3.verifyConflicts(case3);
			conflicts.addAll(normsRet);
		}
		
		
		List<Norm> case4 = map.get("case4");
		if (case4 != null && case4.size() > 1) {
			ConflictCheckerActionApproach4 approach4 = new ConflictCheckerActionApproach4();
			List<List<Norm>> normsRet = approach4.verifyConflicts(case4);
			conflicts.addAll(normsRet);
		}
		
		
		List<Norm> case5and6 = map.get("case5and6");
		if (case5and6 != null && case5and6.size() > 1) {
			ConflictCheckerActionApproach5and6 approach5and6 = new ConflictCheckerActionApproach5and6();
			List<List<Norm>> normsRet = approach5and6.verifyConflicts(case5and6);
			conflicts.addAll(normsRet);
		}

		return conflicts;
	}
		
	private String getTheBehaviorType(Norm norm ) {
		BehaviorMultipleParameters b = norm.getBehavior();

		/**
		CASO 1 - action		
		CASO 2 - action, object
		CASO 3 - action (param1, param2, param3, ...)
		CASO 4 - action {param1 = {value1}, param2 = {value2}, ...}
		CASO 5 - action, object (param1, param2, param3, ...);
		CASO 6 - action, object (param1 = {value1}, param2 = {value2}, ...)
		*/
		if (b.getObject() == null && b.getMap().size() == 0) {
			return "case1";
		}
		if (b.getObject() != null && b.getMap().size() == 0) {
			return "case2";
		}
		
		Map<String, Set<String>> p = b.getMap();
		for (Map.Entry<String,Set<String>> entry : p.entrySet()) {
			String key = entry.getKey();
	  		Set<String> x = norm.getBehavior().getElements(key);
	  		if(x == null || SetUtil.isEmpty(x) || x.contains(null)) {
	  			if (b.getObject() == null) {
	  				return "case3";
	  			} else {
	  				return "case5";
	  			}
	  		} else {
	  			if (b.getObject() == null) {
	  				return "case4";
	  			} else {
	  				return "case6";
	  			}
	  		}
		}
		return "NONE";
	}
	
	private List<Norm> transformNorms(List<Norm> norms) {
		List<Norm> r  = new ArrayList<Norm>();
		
		for (Norm norm : norms) {
			String type = this.getTheBehaviorType(norm);
			if("case3".equals(type) || "case5".equals(type)) {
				Norm n = (Norm)this.deepClone(norm);
				Map<String, Set<String>> map = norm.getBehavior().getMap();
				for (Map.Entry<String,Set<String>> entry : map.entrySet()) {
					String key = entry.getKey();
					n.getBehavior().removeElement(key);
					n.getBehavior().addSetOfElements(SetUtil.getParamValue(key), SetUtil.MAP_SETS.get(key));
				}
				r.add(n);

			} else {
				r.add(norm);
			}
		}
		return r;
	}
	
	private Map<String,List<Norm>> classifyNorms(List<Norm> norms) {
		Map<String,List<Norm>> map = new HashMap<String, List<Norm>>();
		List<Norm> case1 = new ArrayList<Norm>();
		List<Norm> case2 = new ArrayList<Norm>();
		List<Norm> case3 = new ArrayList<Norm>();
		List<Norm> case4 = new ArrayList<Norm>();
		List<Norm> case5and6 = new ArrayList<Norm>();

		for (Norm norm : norms) {
			String type = this.getTheBehaviorType(norm);

			switch(type) {
				case "case1": 
					case1.add(norm);
					break;
				case "case2": 
					case2.add(norm);
					break;
				case "case3": 
					case3.add(norm);
					break;
				case "case4": 
					case4.add(norm);
					break;
				case "case5": 
					case5and6.add(norm);
					break;
				case "case6": 
					case5and6.add(norm);
					break;
				default:
					break;
			}
		}
		map.put("case1", case1);
		map.put("case2", case2);
		map.put("case3", case3);
		map.put("case4", case4);
		map.put("case5and6", case5and6);
		
		return map;
	}	
	
	private List<List<Norm>> classifyNormsInSets(List<Norm> normsSet) {
		List<List<Norm>> listOfNormsSets = new ArrayList<List<Norm>>();
		
		for (Norm norm: normsSet) {
			boolean included = false;
			
			int n = listOfNormsSets.size();
			if (n == 0) {
				List<Norm> temp = new ArrayList<Norm>();
				temp.add(norm);
				listOfNormsSets.add(temp);
				continue;
			}
			
			for (int i = 0; i < listOfNormsSets.size(); i++) {
				List<Norm> temp = listOfNormsSets.get(i);
				if (this.isThereEquivalenceBetweenNorms(temp.get(0), norm)) {
					listOfNormsSets.get(i).add(norm);
					included = true;
				}
			}
			
			if (!included) {
				List<Norm> temp = new ArrayList<Norm>();
				temp.add(norm);
				listOfNormsSets.add(temp);
			}
		}
		return listOfNormsSets;
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

		// at this moment all conditions are valid and the norms are equivalent
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
		if (norm1.getBehavior() == null || norm2.getBehavior() == null) {
			return false;
		}
		String actionName1 = norm1.getBehavior().getName();
		String actionName2 = norm2.getBehavior().getName();
		
		if (actionName1.equals(actionName2)) {
			return true;
		}
		return false;
		//its missing new cases for new behaviors (states)
	}
		
	private boolean constraintChecker(Norm norm1, Norm norm2) {
		//approach 1: permitted null norms
		if (norm1.getActivationConstraint() == null || norm1.getDeactivationConstraint() == null ||
			norm2.getActivationConstraint() == null || norm2.getDeactivationConstraint() == null) {
			
			norm1.setActivationConstraint(null);
			norm2.setActivationConstraint(null);
			norm1.setDeactivationConstraint(null);
			norm2.setDeactivationConstraint(null);
			
			return true;
		}
		
		
		//approach 2: not permitted null norms
		/*if (norm1.getActivationConstraint() == null || norm1.getDeactivationConstraint() == null ||
			norm2.getActivationConstraint() == null || norm2.getDeactivationConstraint() == null) {
			return false;
		}*/
		
		ConstraintType na1 = norm1.getActivationConstraint().getConstraintType();
		ConstraintType nd1 = norm1.getDeactivationConstraint().getConstraintType();
		
		ConstraintType na2 = norm2.getActivationConstraint().getConstraintType();
		ConstraintType nd2 = norm2.getDeactivationConstraint().getConstraintType();
		
		//it is necessary only 3 tests
		if (!na1.equals(nd1) || !na2.equals(nd2) || !na1.equals(na2)) {
			return false;
		}
		
		// If the activation conditions are actions
		if (na1.equals(ConstraintType.ACTIONTYPE)) {
			//todo...action type will be handled in future
			return true;
		}
		
		//at this moment the constrainttype are both DATETYPE, so it is not necessary more comparisons
		
		DateTime d1Begin = ((ConstraintDate) norm1.getActivationConstraint()).getDate();
		DateTime d1End = ((ConstraintDate) norm1.getDeactivationConstraint()).getDate();
		DateTime d2Begin = ((ConstraintDate) norm2.getActivationConstraint()).getDate();
		DateTime d2End = ((ConstraintDate) norm2.getDeactivationConstraint()).getDate();
		
		boolean r = this.compareDateIntervals(d1Begin, d1End, d2Begin, d2End);
		return r;
	}
	
	private boolean normIntersects(List<Norm> norms) {
		int myObjListSize = norms.size();
		
		for (int outerIndex = 0; outerIndex < myObjListSize ; outerIndex++){
			DateTime d1Begin = ((ConstraintDate) norms.get(outerIndex).getActivationConstraint()).getDate();
			DateTime d1End = ((ConstraintDate) norms.get(outerIndex).getDeactivationConstraint()).getDate();
			
		    for(int innerIndex = outerIndex; innerIndex < myObjListSize ; innerIndex++) {
		    
		    	DateTime d2Begin = ((ConstraintDate) norms.get(innerIndex).getActivationConstraint()).getDate();
		    	DateTime d2End = ((ConstraintDate) norms.get(innerIndex).getDeactivationConstraint()).getDate();
		        
		    	boolean ret = this.compareDateIntervals(d1Begin, d1End, d2Begin, d2End);
		        if (!ret) {
		        	return false;
		        }
		    }
		}
		return true;
	}

	private boolean compareDateIntervals(DateTime d1Begin, DateTime d1End, DateTime d2Begin, DateTime d2End){
		Interval i1 = new Interval(d1Begin,d1End);
		Interval i2 = new Interval(d2Begin,d2End);
		return i1.overlaps(i2);
		//http://stackoverflow.com/questions/17106670/how-to-check-a-timeperiod-is-overlapping-another-time-period-in-java
	}
	
	private Object deepClone(Object object) {
	    try {
	      ByteArrayOutputStream baos = new ByteArrayOutputStream();
	      ObjectOutputStream oos = new ObjectOutputStream(baos);
	      oos.writeObject(object);
	      ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
	      ObjectInputStream ois = new ObjectInputStream(bais);
	      return ois.readObject();
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	      return null;
	    }
	}
	
}
