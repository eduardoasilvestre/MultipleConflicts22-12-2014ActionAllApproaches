package test;

import java.util.ArrayList;
import java.util.List;

import src.BehaviorMultipleParameters;
import src.ConflictCheckerActionApproach2;
import src.Constraint;
import src.Context;
import src.ContextType;
import src.DeonticConcept;
import src.Entity;
import src.EntityType;
import src.Norm;

public class TestConflictsActionApproach2 {

	public static void main(String[] args) {
		List<Norm> normSet = buildSomeNorms();
		ConflictCheckerActionApproach2 conflictChecker = new ConflictCheckerActionApproach2();
		conflictChecker.verifyConflicts(normSet);
	}
	
	/**
	 * This method builds a set of norms por the analysis of conflicts
	 * @author eduardo.silvestre
	 */
	private static List<Norm> buildSomeNorms() {
		List<Norm> normSet = new ArrayList<>();
		/*
		Context context1 = new Context("home", ContextType.ORGANIZATION);
		Entity entity1 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action1 = new BehaviorMultipleParameters("attend");
		action1.addElement("computer_science",null);
		action1.addElement("math",null);
		action1.addElement("physics", null);
		Constraint aConstraint1 = null; 
		Constraint dConstraint1 = null; 
		Norm norm1 = new Norm(1, DeonticConcept.PERMISSION, context1, entity1, action1, aConstraint1, dConstraint1);
		normSet.add(norm1);
		
		Context context2 = new Context("home", ContextType.ORGANIZATION);
		Entity entity2 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action2 = new BehaviorMultipleParameters("attend");
		action2.addElement("medicine",null);
		action2.addElement("nursing",null);
		action2.addElement("odontology", null);
		Constraint aConstraint2 = null; 
		Constraint dConstraint2 = null; 
		Norm norm2 = new Norm(2, DeonticConcept.PROHIBITION, context2, entity2, action2, aConstraint2, dConstraint2);
		normSet.add(norm2);*/

		
		
		Context context3 = new Context("home", ContextType.ORGANIZATION);
		Entity entity3 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action3 = new BehaviorMultipleParameters("attend");
		action3.addElement("computer_science", null);
		action3.addElement("math", null);
		action3.addElement("physics", null);
		Constraint aConstraint3 = null; 
		Constraint dConstraint3 = null; 
		Norm norm3 = new Norm(3, DeonticConcept.OBLIGATION, context3, entity3, action3, aConstraint3, dConstraint3);
		normSet.add(norm3);
				
		Context context6 = new Context("home", ContextType.ORGANIZATION);
		Entity entity6 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action6 = new BehaviorMultipleParameters("attend");
		action6.addElement("medicine",null);
		action6.addElement("physics",null);
		action6.addElement("odontolty", null);
		Constraint aConstraint6 = null; 
		Constraint dConstraint6 = null; 
		Norm norm6 = new Norm(6, DeonticConcept.PROHIBITION, context6, entity6, action6, aConstraint6, dConstraint6);
		normSet.add(norm6);
		
		
		
		/*
		Context context1 = new Context("home", ContextType.ORGANIZATION);
		Entity entity1 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action1 = new BehaviorMultipleParameters("eat");
		action1.addElement("potato",null);
		action1.addElement("biscuit",null);
		action1.addElement("rice", null);
		action1.addElement("juice", null);
		Constraint aConstraint1 = null; 
		Constraint dConstraint1 = null; 
		Norm norm1 = new Norm(1, DeonticConcept.PERMISSION, context1, entity1, action1, aConstraint1, dConstraint1);
		normSet.add(norm1);
		
		Context context2 = new Context("home", ContextType.ORGANIZATION);
		Entity entity2 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action2 = new BehaviorMultipleParameters("eat");
		action2.addElement("avocado",null);
		action2.addElement("crackling",null);
		action2.addElement("olive_oil",null);
		action2.addElement("soda", null);
		Constraint aConstraint2 = null; 
		Constraint dConstraint2 = null; 
		Norm norm2 = new Norm(2, DeonticConcept.PROHIBITION, context2, entity2, action2, aConstraint2, dConstraint2);
		normSet.add(norm2);*/
		
		/*
		Context context3 = new Context("home", ContextType.ORGANIZATION);
		Entity entity3 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action3 = new BehaviorMultipleParameters("eat");
		action3.addElement("potato", null);
		action3.addElement("avocado", null);
		action3.addElement("chicken", null);
		Constraint aConstraint3 = null; 
		Constraint dConstraint3 = null; 
		Norm norm3 = new Norm(3, DeonticConcept.OBLIGATION, context3, entity3, action3, aConstraint3, dConstraint3);
		normSet.add(norm3);
				
		Context context6 = new Context("home", ContextType.ORGANIZATION);
		Entity entity6 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action6 = new BehaviorMultipleParameters("eat");
		action6.addElement("biscuit",null);
		action6.addElement("crackling",null);
		action6.addElement("soy", null);
		Constraint aConstraint6 = null; 
		Constraint dConstraint6 = null; 
		Norm norm6 = new Norm(6, DeonticConcept.PROHIBITION, context6, entity6, action6, aConstraint6, dConstraint6s);
		normSet.add(norm6);*/

		return normSet;
		
	}

}
