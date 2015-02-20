package test;

import java.util.ArrayList;
import java.util.List;

import src.BehaviorMultipleParameters;
import src.ConflictCheckerActionApproach3;
import src.Constraint;
import src.Context;
import src.ContextType;
import src.DeonticConcept;
import src.Entity;
import src.EntityType;
import src.Norm;

public class TestConflictsActionApproach3 {

	public static void main(String[] args) {
		List<Norm> normSet = buildSomeNorms();
		ConflictCheckerActionApproach3 conflictChecker = new ConflictCheckerActionApproach3();
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
		BehaviorMultipleParameters action1 = new BehaviorMultipleParameters("dress");
		action1.addElement("color","white");
		action1.addElement("ironingtype","ironing");
		action1.addElement("picture", "smooth");
		Constraint aConstraint1 = null; 
		Constraint dConstraint1 = null; 
		Norm norm1 = new Norm(1, DeonticConcept.OBLIGATION, context1, entity1, action1, aConstraint1, dConstraint1);
		normSet.add(norm1);
		
		Context context2 = new Context("home", ContextType.ORGANIZATION);
		Entity entity2 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action2 = new BehaviorMultipleParameters("dress");
		action2.addElement("color","black");
		action2.addElement("ironingtype","ironing");
		action2.addElement("picture", "smooth");
		Constraint aConstraint2 = null; 
		Constraint dConstraint2 = null; 
		Norm norm2 = new Norm(2, DeonticConcept.PERMISSION, context2, entity2, action2, aConstraint2, dConstraint2);
		normSet.add(norm2);
		
		Context context3 = new Context("home", ContextType.ORGANIZATION);
		Entity entity3 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action3 = new BehaviorMultipleParameters("dress");
		action3.addElement("color","blue");
		action3.addElement("ironingtype","ironing");
		action3.addElement("picture", "smooth");
		Constraint aConstraint3 = null; 
		Constraint dConstraint3 = null; 
		Norm norm3 = new Norm(3, DeonticConcept.PROHIBITION, context3, entity3, action3, aConstraint3, dConstraint3);
		normSet.add(norm3);*/
		
		
		
		Context context1 = new Context("home", ContextType.ORGANIZATION);
		Entity entity1 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action1 = new BehaviorMultipleParameters("dress");
		action1.addElement("color","white");
		action1.addElement("ironingtype","ironing");
		action1.addElement("picture", "smooth");
		Constraint aConstraint1 = null; 
		Constraint dConstraint1 = null; 
		Norm norm1 = new Norm(1, DeonticConcept.OBLIGATION, context1, entity1, action1, aConstraint1, dConstraint1);
		normSet.add(norm1);
		
		Context context2 = new Context("home", ContextType.ORGANIZATION);
		Entity entity2 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action2 = new BehaviorMultipleParameters("dress");
		action2.addElement("color","black");
		action2.addElement("ironingtype","ironing");
		action2.addElement("picture", "smooth");
		Constraint aConstraint2 = null; 
		Constraint dConstraint2 = null; 
		Norm norm2 = new Norm(2, DeonticConcept.PERMISSION, context2, entity2, action2, aConstraint2, dConstraint2);
		normSet.add(norm2);
		
		Context context3 = new Context("home", ContextType.ORGANIZATION);
		Entity entity3 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action3 = new BehaviorMultipleParameters("dress");
		action3.addElement("color","blue");
		action3.addElement("ironingtype","ironing");
		action3.addElement("picture", "smooth");
		Constraint aConstraint3 = null; 
		Constraint dConstraint3 = null; 
		Norm norm3 = new Norm(3, DeonticConcept.PROHIBITION, context3, entity3, action3, aConstraint3, dConstraint3);
		normSet.add(norm3);
				
		
		
		return normSet;
		
	}

}
