package src;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import util.SetUtil;

public class ConflictCheckerActionApproach5and6 {
		
	private Map<Integer,Norm> normsInPermitted = null;
	private Map<Integer,Norm> normsInCase5 = null;

	public ConflictCheckerActionApproach5and6() {
		normsInPermitted = new HashMap<Integer, Norm>();
		normsInCase5 = new HashMap<Integer, Norm>();
	}
	
	public List<List<Norm>> verifyConflicts(List<Norm> norms) {
		List<List<Norm>> conflicts  = new ArrayList<List<Norm>>();
		
		List<Norm> normsCase6 = transformCase5inCase6(norms);
		
		//convert all norms to permitted. this conversion is done once
		List<Norm> ret = this.convertNormsToPermitted(normsCase6);
		for (Norm n : ret) {
			normsInPermitted.put(n.getId(), n);
		}			
		//TODO change parameter to normsCase6.size()
		//change the parameter j to change the length of analysis
		for (int j = 2; j <= normsCase6.size();j++) {
			List<List<Norm>> normsNtoN = this.generateAllCombinations(normsCase6, j);
			List<List<Norm>> normsRet = this.verifyConflictsCase6(normsNtoN);
			conflicts.addAll(normsRet);
			normsNtoN.clear();
		}
		
		//here, we are gonna convert the norms to case5 again
		List<List<Norm>> conflictsFinal  = new ArrayList<List<Norm>>();
		for(List<Norm> lastNorms : conflicts) {
			List<Norm> temp = new ArrayList<Norm>();
			for (Norm n: lastNorms) {
				if (normsInCase5.containsKey(n.getId())) {
					temp.add(normsInCase5.get(n.getId()));
				} else {
					temp.add(n);
				}
			}
			conflictsFinal.add(temp);
		}
		
		return conflictsFinal;
	}

	private List<List<Norm>> verifyConflictsCase6(List<List<Norm>> norms) {
		List<List<Norm>> normsRet  = new ArrayList<List<Norm>>();
		for (int i = 0; i < norms.size(); i++) {
			
			List<Norm> normsTemp = norms.get(i);
			/*
			if ((normsTemp.get(0).getId() == 172 || normsTemp.get(0).getId() == 174) && (normsTemp.get(1).getId() == 172 || normsTemp.get(1).getId() == 174)) {
				System.out.println("teste");
			}*/
			
			String basic = this.isThereBasicConflict(normsTemp);
			if ("CONFLICT".equals(basic)) {
				normsRet.add(normsTemp);
				continue;
			} else if ("WITHOUT".equals(basic)) {
				continue;
			}
			
			//get the norms in permitted form			
			List<Norm> normsPerm = new ArrayList<Norm>();
			for (Norm no : normsTemp) {
				normsPerm.add(normsInPermitted.get(no.getId()));
			}
			
			//here is UNIDENTIFIED
			if (this.isThereConflict(normsPerm)) {
				normsRet.add(normsTemp);
			}
		}
		return normsRet;
	}
	
	
	private List<Norm> transformCase5inCase6(List<Norm> norms) {
		List<Norm> r  = new ArrayList<Norm>();
		for (Norm norm : norms) {
			if("case6".equals(this.getTheBehaviorType(norm))) {
				r.add(norm);
				continue;
			}
			Norm n = (Norm)this.deepClone(norm);
			normsInCase5.put(norm.getId(), norm);
			Map<String, Set<String>> map = norm.getBehavior().getMap();
			for (Map.Entry<String,Set<String>> entry : map.entrySet()) {
				String key = entry.getKey();
				n.getBehavior().removeElement(key);
				n.getBehavior().addSetOfElements(SetUtil.getParamValue(key), SetUtil.MAP_SETS.get(key));
			}
			r.add(n);
		}
		return r;
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
	
	private boolean isThereVariableInANorm(Norm norm) {
		Map<String,Set<String>> mapParameters = norm.getBehavior().getMap();
		for (Map.Entry<String,Set<String>> entry : mapParameters.entrySet()) {
			String key = entry.getKey();
	  		Set<String> x = norm.getBehavior().getElements(key);
	  		if (SetUtil.hasOneElement(x) && SetUtil.containsEqualDiff(x) ){
	  			return true;
	  		}
	  	}
		return false;
	}
	
	private String getTheParameterWithVariableInANorm(Norm norm) {
		Map<String,Set<String>> mapParameters = norm.getBehavior().getMap();
		for (Map.Entry<String,Set<String>> entry : mapParameters.entrySet()) {
			String key = entry.getKey();
			Set<String> x = norm.getBehavior().getElements(key);
			if (SetUtil.hasOneElement(x) && SetUtil.containsEqualDiff(x) ){
				return key;
			}
		}
		return null;
	}
	
	private boolean isThereVariableInASet(List<Norm> norms) {
  		for (Norm norm : norms) {
  			if (this.isThereVariableInANorm(norm)) {
  				return true;
  			}
  		}
		return false;
	}
	
	private String isThereBasicConflict(List<Norm> norms) {
		/**		CASE 1 
		 verify the norm set in the case that all norms are permissions. Conflicts with permission can happens if:
		 1 - has variable; 2 - has not. Another cases don't have conflicts
		 In this version is not considered the expression of NOT.*/
		int normsCounter = 0;
		for (int i = 0; i < norms.size(); i++) {
			if (norms.get(i).getDeonticConcept().equals(DeonticConcept.PERMISSION)) {
				normsCounter++;
			}
		}
		if (normsCounter == norms.size()) {
			if (!this.isThereVariableInASet(norms)) {
				return "WITHOUT";
			}
		}
		
		/**CASE 2 and CASE 3 (preparation)
		after the the maps will contain the distribution of equal-diff*/
		Map <String, Integer> mapCountEqualDiff = new HashMap<String, Integer>();
		Map <String, Integer> mapCountEqualDiff2 = new HashMap<String, Integer>();
		for (Norm norm : norms) {
			String object = norm.getBehavior().getObject();
  			if (!this.isThereVariableInANorm(norm)) {
  				if(mapCountEqualDiff.get(object) == null) {
  					mapCountEqualDiff.put(object, 1);
  				} else {
  					Integer currentValue = mapCountEqualDiff.get(object);
  					mapCountEqualDiff.put(object, ++currentValue);
  				}
  			} else {
  				if(mapCountEqualDiff2.get(object) == null) {
  					mapCountEqualDiff2.put(object, 1);
  				} else {
  					Integer currentValue = mapCountEqualDiff2.get(object);
  					mapCountEqualDiff2.put(object, ++currentValue);
  				}
  			}
  		}
		
		/**	CASE 2 
		 Imagine the case...
		 my set of norms don't have variable and all objects values are different
		 in this case the algorithm must return that there isn't conflict*/
		if (norms.size() == mapCountEqualDiff.size()) { //it means the set of norms doesn't have variable
			boolean varGreaterThenOne = false;
			for (Map.Entry<String,Integer> entry : mapCountEqualDiff.entrySet()) {
				Integer value = entry.getValue();
				if (value != 1) {
					varGreaterThenOne = true;
					break; //there isn't a variable. it is necessary one object greater than 1
				}
			}
			if (!varGreaterThenOne) { //it doesn't have conflict
				return "WITHOUT";
			}
		}

		/**	CASE 3
		 There is a variable for a specified object value. But only one norm has the object.
		 It is impossible the unifcation. Automatically the set of norms has a conflict.*/
		for (Map.Entry<String,Integer> entry : mapCountEqualDiff2.entrySet()) {
			//at this moment the key represents a object with variable (the quantity doesn't matter) 
			String key = entry.getKey();

			//it means that there is at least a variable, but there isn't a norm without variable
			if (mapCountEqualDiff.get(key) == null) { 
				return "CONFLICT"; //its is impossible the unification. there is a conflict
			}
		}
		
		/**	CASE 4
		 PART 1
		 There is variable for 2 different parameters. It is not permitted
		 Imagine color = A, ironing = A and picture = !A.
		 PART 2
		 It is necessary that the parameter value be the same(A or A)*/
		String parameterName = null;
		Set<String> parameterValueHistory = null;
		for (Norm norm : norms) {
			//PART 1
			String parameter = this.getTheParameterWithVariableInANorm(norm);
			if (parameter == null) {
				continue;
			}
			//at this moment the parameter is != null
			if (parameterName != null && !parameter.equals(parameterName)) {
				return "CONFLICT";
			}
			parameterName = (String) this.deepClone(parameter);
			//PART 2
			Set<String> parameterValue = norm.getBehavior().getElements(parameter);
			if (parameterValueHistory != null) {
				if (!parameterValueHistory.containsAll(parameterValue)) { //verify the equality
					return "CONFLICT";
				}
			}
			parameterValueHistory = parameterValue;
		}
		return "UNIDENTIFIED";
	}
	
	private List<Norm> convertNormsToPermitted(List<Norm> norms) {
		List<Norm> normsCopy  = new ArrayList<Norm>(); //contains a clone of list norms
		
		List<Norm> normsPermTemp  = new ArrayList<Norm>();
		
		for (Norm norm : norms) {
			normsCopy.add((Norm)this.deepClone(norm));
		}
		
		for (Norm norm : normsCopy) {
			if (norm.getDeonticConcept().equals(DeonticConcept.PERMISSION)) {
				normsPermTemp.add(norm);
			} else if (norm.getDeonticConcept().equals(DeonticConcept.OBLIGATION)) {
				norm.setDeonticConcept(DeonticConcept.PERMISSION);
				normsPermTemp.add(norm);
			} else if (norm.getDeonticConcept().equals(DeonticConcept.PROHIBITION)) {
				Map<String,Set<String>> mapParameters = norm.getBehavior().getMap();
				
				BehaviorMultipleParameters bTemp = new BehaviorMultipleParameters(norm.getBehavior().getName(),norm.getBehavior().getObject(), norm.getBehavior().getObjectType());

				for (Map.Entry<String,Set<String>> entry : mapParameters.entrySet()) {
			  		String key = entry.getKey();
			  		Set<String> x = norm.getBehavior().getElements(key);
			  		if (SetUtil.hasOneElement(x) && SetUtil.containsEqualDiff(x) ){
			  			Set<String> vars = new HashSet<String>();
			  			if (x.contains(SetUtil.EQUAL)) {
			  				vars.add(SetUtil.DIFF);
			  			} else {
			  				vars.add(SetUtil.EQUAL);
			  			}
			  			bTemp.addSetOfElements(key, vars);
			  			continue;
			  		}
			  		
			  		Set<String> notElement = SetUtil.difference(SetUtil.getMapOfParameters().get(key), entry.getValue());
			  		bTemp.addSetOfElements(key, notElement);				
				}
				norm.setDeonticConcept(DeonticConcept.PERMISSION);
				norm.setBehavior(bTemp);
				normsPermTemp.add(norm);
			}
		}
		return normsPermTemp;
	}

	private boolean isThereConflict(List<Norm> norms) {
		norms = this.removeDuplicateNorms(norms);	//remove the norms with the same action
		
		Map<String,Set<String>> mapParameters = norms.get(0).getBehavior().getMap(); //all the norms have the same parameters
			
		//permitted one parameter with variable in a set of norms (its is permitted more than one norm with variable)
		String parameterWithVariableName = null;	//stores the parameter with variable (if exists)
		String parameterWithVariableValue = null;	//stores the value of the parameter
		
		Set<String> normObjectList = new HashSet<>();	//stores the values contained in object
		
		String parameterNormsEqualDiff = null;	//stores the parameter with equalq/diff
		
		List<Norm> normsWithVariables = new ArrayList<Norm>(); //stores the norms that contain variables
		
		Set<String> objectValues = new HashSet<String>(); //stores the object values of the norms with variables
		
		//stores in a list the norms the have parameter
		//PS.: To safely remove from a collection while iterating over it you should use an Iterator.
		Iterator<Norm> iterator = norms.iterator();
		while(iterator.hasNext()) {
			Norm ned = iterator.next();
			
			parameterNormsEqualDiff = this.getTheParameterWithVariableInANorm(ned);
			if (parameterNormsEqualDiff == null) { //it means that the norm doesn't have parameter
				normObjectList.add(ned.getBehavior().getObject());
				continue;
			}
			//here means the the norm has variable
	    	Norm normEqualDiff = (Norm)deepClone(ned);
	    	normsWithVariables.add(normEqualDiff);
	    	
	    	objectValues.add(normEqualDiff.getBehavior().getObject());
	    	
	    	iterator.remove();
	    	parameterNormsEqualDiff = null;
		}

		//this code iterates over the norms and makes the intersection between the parameters values.
		//all the norms here have parameter. The parameter is the same
		Map<String,Set<String>> intersectionsEqualDiff = new HashMap<>();
		Map<String,Map<String,Set<String>>> intersectionsVariables = new HashMap<String, Map<String,Set<String>>>();
		
		if (!normsWithVariables.isEmpty()) {
			Set<String> intersectionParameter = new HashSet<String>();
			String parameterEqualDiff = this.getTheParameterWithVariableInANorm(normsWithVariables.get(0));//all norms have same parameter
			
			parameterWithVariableName = parameterEqualDiff;
			parameterWithVariableValue = normsWithVariables.get(0).getBehavior().getElements(parameterWithVariableName).toString();//the result of this operation is:equal, diff, less than, ... 
			
			Iterator<String> itera = objectValues.iterator();
		    while (itera.hasNext()) {
		    	String objectValue = itera.next();
		    	
		    	for (Map.Entry<String,Set<String>> entry : mapParameters.entrySet()) {
			  		String key = entry.getKey();
			  	    //if the parameter with variable is the parameter select in for continue because this situation doesn't need intersections
			  		if (parameterEqualDiff.equals(key)) {
			  			continue;
			  		}
			  		for (Norm norm : normsWithVariables) {
						boolean firstTime = true;
						Set<String> values = norm.getBehavior().getElements(key);
						if (firstTime) {
				    		intersectionParameter = SetUtil.union(intersectionParameter, values);
				    		firstTime = false;
				    	} else {
				    		intersectionParameter = SetUtil.intersection(intersectionParameter, values);
				    	}
					}
				    Set<String> copy = new HashSet<String>();
				    Iterator<String> it = intersectionParameter.iterator();
				    while (it.hasNext()) {
				    	copy.add((String)this.deepClone(it.next()));
				    }
				    intersectionsEqualDiff.put(key, copy);
			  		intersectionParameter = SetUtil.cleanSet(intersectionParameter);
				}
		    	intersectionsVariables.put(objectValue, intersectionsEqualDiff);
		    }
		}
  		//The variables are all A or !A. It is important identify how many different values of object there are
  		Norm normT = null;
  		if (!normsWithVariables.isEmpty()) {
  			normT = normsWithVariables.get(0); //gets the first object
  		}
  		boolean varEqualDiff = false;
  		for(Norm norm : normsWithVariables) {
  			int counter = 0;
  			String normObject = norm.getBehavior().getObject();
  			String normTObject = normT.getBehavior().getObject();
  			
  			if (!normObject.equals(normTObject)) {
  				counter++;
  			}
  			if (counter > 0) {
  				varEqualDiff = true;
  				break;
  			}
  		}
		
		Map<String,Integer> mapCounter = new HashMap<String,Integer>();
		Map<String,Map<String,Set<String>>> intersections = new HashMap<String, Map<String,Set<String>>>();

		//iterates over each parameter that doesn't have equal or diff
		Iterator<String> it = normObjectList.iterator();
	    while (it.hasNext()) {
	    	String objectElement = it.next();
	    	
	    	//inserts in normTemp each norm that has 'parameter'
	    	List<Norm> normsTemp  = new ArrayList<>();
	    	for (Norm normTemp: norms)  {
	    		String objectCurrent = normTemp.getBehavior().getObject();
	    		if (objectElement.equals(objectCurrent)) {
	    			normsTemp.add(normTemp);
	    		}
	    	}
	    	//if the normsTemp for a specified parameter has at least one element
	    	if (normsTemp.size() > 0) {
	    		Map<String,Set<String>> mapTemp = this.createIntersections(normsTemp); 
	    		intersections.put(objectElement, mapTemp);
	    		
	    		//stores the quantity of norms that interaction was done
	    		int normsTempSize = normsTemp.size();
	    		mapCounter.put(objectElement, normsTempSize);
	    	}
	    }
	    /**
	     * 3 cases of conflicts in parameters
	     * 1 - conflict with variable and one object value. Can be only A
	     * 2 - conflict with variable and several object values.
	     * 3 - conflict with no variable 
	     * */
	    
	    Set<String> intersectionParameter = new HashSet<String>();
	    
	  	boolean varDiffIntersection = true; //this variable marks if some partial set is different of 1. This case is necessary for the analysis of DIFF
	  	
	  	for (Map.Entry<String,Set<String>> entry : mapParameters.entrySet()) {	  	//iteraction over all parameters
	  		
	  		String key = entry.getKey();

	  		boolean parameterWithVariableIsValid = parameterWithVariableName != null && parameterWithVariableName.equals(key);
	  		
	  		if (!varEqualDiff && parameterWithVariableIsValid) {
		  		/*****************************************************************
		  		 * 							CASE 1 OF CONFLICT
		  		*****************************************************************/	
	  			
	  			String valueTemp = normT.getBehavior().getObject(); //all objects with variable have the same value
	  			Map<String, Set<String>> mapCurrent = intersections.get(valueTemp);
	  			intersectionParameter = mapCurrent.get(parameterWithVariableName);
	  			
	  			if(intersectionParameter.isEmpty()) {
	  				return true;
	  			}
	  		} else if (!normsWithVariables.isEmpty() && parameterWithVariableIsValid) {
		  		/*****************************************************************
		  		 * 							CASE 2 OF CONFLICT
		  		*****************************************************************/	
	  			
	  			Map<String,String> mapParamEqualDiff = new HashMap<String, String>();
	  			for (Norm norm : normsWithVariables) {
	  				String objectValue = norm.getBehavior().getObject();
	  				mapParamEqualDiff.put(objectValue, objectValue);
	  			}

	  			int count = 0;
				for (Map.Entry<String,String> entryParameterEqualDiff : mapParamEqualDiff.entrySet()) {
	  				Map<String, Set<String>> mapCurrent = intersections.get(entryParameterEqualDiff.getValue());  //map of shirt, pant, ....
		  			Set<String> values = mapCurrent.get(parameterWithVariableName);
		  			if (count++ == 0) {
		  				intersectionParameter = SetUtil.union(intersectionParameter, values);
		  			} else {
		  				intersectionParameter = SetUtil.intersection(intersectionParameter, values);
		  			}
	  			}
				if (parameterWithVariableValue.contains(SetUtil.EQUAL)) {
					if (SetUtil.isEmpty(intersectionParameter)) {
						return true;
					}
				} else if (SetUtil.hasOneElement(intersectionParameter) && varDiffIntersection) {
					//TODO must be implemented
					return true; //DIFF 
				}
			} else {
				/*****************************************************************
				 * 							CASE 3 OF CONFLICT
				*****************************************************************/
				
				for (Map.Entry<String,Map<String,Set<String>>> entryParameter : intersections.entrySet()) {	//iteraction over the values of the parameter
		  			String keyParameter = entryParameter.getKey();
			  		Map<String,Set<String>> interTemp = entryParameter.getValue();
			  		
					if (mapCounter.get(keyParameter) == 1 && intersectionsVariables.get(keyParameter) == null) {
						continue;
					}
					intersectionParameter = interTemp.get(key);
					
					if (!normsWithVariables.isEmpty() && intersectionsVariables.get(keyParameter) != null) {
						Map<String, Set<String>> mapVariables = intersectionsVariables.get(keyParameter);
						if (mapVariables != null) {
							Set<String> valuesEqualDiff = intersectionsVariables.get(keyParameter).get(key);
							intersectionParameter = SetUtil.intersection(intersectionParameter, valuesEqualDiff);
						}
					}
		  			
					if (SetUtil.isEmpty(intersectionParameter)) {
						return true;
					}
		  		}
			}
			varDiffIntersection = true;
			intersectionParameter = SetUtil.cleanSet(intersectionParameter);
	  	}
		return false;
	}
	
	private List<Norm> removeDuplicateNorms(List<Norm> norms) {
		List<Norm> normsCopy  = new ArrayList<Norm>(); //contains a clone of list norms
		
		for (Norm norm : norms) {
			normsCopy.add((Norm)this.deepClone(norm));
		}
		
		List<Norm> normsR  = new ArrayList<Norm>();
		
		Set<Norm> set = new HashSet<>();
		for (Norm norm : normsCopy) {
			norm.setId(1);
			set.add(norm);
		}
		normsR.addAll(set);
		return (normsR.size()  != norms.size()) ? normsR : norms; 
	}
	
	//Makes all intersections
	private Map<String,Set<String>> createIntersections(List<Norm> norms) {
		Map<String,Set<String>> intersections = new HashMap<String, Set<String>>();
		
		Map<String,Set<String>> mapParameters = norms.get(0).getBehavior().getMap(); //all the norms have the same parameters
				
		Set<String> intersectionParameter = new HashSet<String>();
		int countParameters = 0;
		
		for (Map.Entry<String,Set<String>> entry : mapParameters.entrySet()) { //runs map of parameters
		    String key = entry.getKey(); //take a parameter
		    
		    for (Norm n: norms) {
		    	Map<String,Set<String>> mapTemp = n.getBehavior().getMap();
		    	Set<String> values = mapTemp.get(key); //take the set with parameter name 'key'
		    	
		    	//realizes the intersection between the values of a specific parameter
		    	if (countParameters++ == 0) {
		    		intersectionParameter = SetUtil.union(intersectionParameter, values);
		    	} else {
		    		intersectionParameter = SetUtil.intersection(intersectionParameter, values);
		    	}
		    }
		    Set<String> copy = new HashSet<String>();
		    Iterator<String> it = intersectionParameter.iterator();
		    while (it.hasNext()) {
		    	copy.add((String)this.deepClone(it.next()));
		    }
		    intersections.put(key, copy);
		    countParameters = 0;
		    intersectionParameter = SetUtil.cleanSet(intersectionParameter);
		}	
		return intersections;
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
