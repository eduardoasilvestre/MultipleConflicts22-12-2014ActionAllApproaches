package test;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import src.BehaviorMultipleParameters;
import src.ConflictChecker;
import src.Constraint;
import src.ConstraintDate;
import src.ConstraintType;
import src.Context;
import src.ContextType;
import src.DeonticConcept;
import src.Entity;
import src.EntityType;
import src.Norm;
import util.SetUtil;

public class TestAllConflicts {

	public static void main(String[] args) {
		
		//This method is used for analysis exclusively the relationship among the norms
		//List<Norm> norms = buildSomeNorms2();
		
		//This method is used for analysis the relationship and the conflicts with norms of the same case
		List<Norm> norms = buildSomeNorms();
		System.out.println("*****************************************************");
		System.out.println("The total of norms was examined was " + norms.size() + ".");
		System.out.println("*****************************************************");
		ConflictChecker checker = new ConflictChecker();
		List<List<Norm>> conflicts = checker.conflictChecker(norms);
		printNorms(conflicts);
	}
	
	public static void printNorms(List<List<Norm>> norms) {
		int sum = 0;
		for(List<Norm> list: norms) {
			for(Norm norm: list) {
				System.out.println(norm.toString());
			}
			System.out.println();
			sum++;
		}
		System.out.println("*****************************************************");
		System.out.println("The total of conflicts found was " + sum + ".");
		System.out.println("*****************************************************");
	}
	
	/*public static void printNorms(List<List<Norm>> norms) {
		int[] counter = new int[norms.size()];
		
		for(List<Norm> list: norms) {
			for(Norm norm: list) {
				System.out.println(norm.toString());
			}
			System.out.println();
			counter[list.size()]++;
		}
		System.out.println("\n");
		int sum = 0;
		for (int i = 0; i < counter.length; i++) {
			if (counter[i] != 0) {
				sum+=counter[i];
				System.out.println("O total de conflitos entre " + i + " normas foi de " + counter[i] + ".");
			}
		}
		System.out.println("\nO total de conflitos encontrados foi " + sum + ".");
	}*/
	
	/*public static void printNorms(List<List<Norm>> norms) {
		int[] counter = new int[norms.size()];
		
		for(List<Norm> list: norms) {
			for(Norm norm: list) {
				System.out.println(norm.toString());
			}
			System.out.println();
			counter[list.size()]++;
		}
		System.out.println("\n");
		int sum = 0;
		for (int i = 0; i < counter.length; i++) {
			if (counter[i] != 0) {
				sum+=counter[i];
				System.out.println("O total de conflitos entre " + i + " normas foi de " + counter[i] + ".");
			}
		}
		System.out.println("\nO total de conflitos encontrados foi " + sum + ".");
	}*/
	
	private static List<Norm> buildSomeNorms() {
		List<Norm> normSet = new ArrayList<>();
		
		/************************************************************************************/
		//TODO case1
		/************************************************************************************/
		
		Context context1 = new Context("university", ContextType.ORGANIZATION);
		Entity entity1 = new Entity ("student", EntityType.ROLE);
		BehaviorMultipleParameters action1 = new BehaviorMultipleParameters("study");
		Constraint aConstraint1 = null; 
		Constraint dConstraint1 = null; 
		Norm norm1 = new Norm(1,DeonticConcept.OBLIGATION, context1, entity1, action1, aConstraint1, dConstraint1);
		normSet.add(norm1);
		
		Context context2 = new Context("university", ContextType.ORGANIZATION);
		Entity entity2 = new Entity ("student", EntityType.ROLE);
		BehaviorMultipleParameters action2 = new BehaviorMultipleParameters("study");
		Constraint aConstraint2 = null; 
		Constraint dConstraint2 = null; 
		Norm norm2 = new Norm(2,DeonticConcept.PROHIBITION, context2, entity2, action2, aConstraint2, dConstraint2);
		normSet.add(norm2);
		
		Context context3 = new Context("university", ContextType.ORGANIZATION);
		Entity entity3 = new Entity ("student", EntityType.ROLE);
		BehaviorMultipleParameters action3 = new BehaviorMultipleParameters("study");
		Constraint aConstraint3 = null; 
		Constraint dConstraint3 = null; 
		Norm norm3 = new Norm(3,DeonticConcept.PERMISSION, context3, entity3, action3, aConstraint3, dConstraint3);
		normSet.add(norm3);
		
		Context context4 = new Context("university", ContextType.ORGANIZATION);
		Entity entity4 = new Entity ("student", EntityType.ROLE);
		BehaviorMultipleParameters action4 = new BehaviorMultipleParameters("study");
		Constraint aConstraint4 = null;
		Constraint dConstraint4 = null;
		Norm norm4 = new Norm(4,DeonticConcept.PROHIBITION, context4, entity4, action4, aConstraint4, dConstraint4);
		normSet.add(norm4);
		
		Context context5 = new Context("university", ContextType.ORGANIZATION);
		Entity entity5 = new Entity ("student", EntityType.ROLE);
		BehaviorMultipleParameters action5 = new BehaviorMultipleParameters("have lunch");
		Constraint aConstraint5 = null; 
		Constraint dConstraint5 = null; 
		Norm norm5 = new Norm(5,DeonticConcept.OBLIGATION, context5, entity5, action5, aConstraint5, dConstraint5);
		normSet.add(norm5);
		
		Context context6 = new Context("team", ContextType.ORGANIZATION);
		Entity entity6 = new Entity ("player", EntityType.ROLE);
		BehaviorMultipleParameters action6 = new BehaviorMultipleParameters("play");
		Constraint aConstraint6 = null; 
		Constraint dConstraint6 = null; 
		Norm norm6 = new Norm(6,DeonticConcept.PROHIBITION, context6, entity6, action6, aConstraint6, dConstraint6);
		normSet.add(norm6);
		
		Context context7 = new Context("team", ContextType.ORGANIZATION);
		Entity entity7 = new Entity ("manager", EntityType.ROLE);
		BehaviorMultipleParameters action7 = new BehaviorMultipleParameters("play");
		Constraint aConstraint7 = null; 
		Constraint dConstraint7 = null; 
		Norm norm7 = new Norm(7,DeonticConcept.PROHIBITION, context7, entity7, action7, aConstraint7, dConstraint7);
		normSet.add(norm7);
		
		Context context8 = new Context("team", ContextType.ENVIRONMENT);
		Entity entity8 = new Entity ("manager", EntityType.ROLE);
		BehaviorMultipleParameters action8 = new BehaviorMultipleParameters("manage");
		Constraint aConstraint8 = null; 
		Constraint dConstraint8 = null; 
		Norm norm8 = new Norm(8,DeonticConcept.PERMISSION, context8, entity8, action8, aConstraint8, dConstraint8);
		normSet.add(norm8);
		
		Context context9 = new Context("company", ContextType.ORGANIZATION);
		Entity entity9 = new Entity ("manager", EntityType.ROLE);
		BehaviorMultipleParameters action9 = new BehaviorMultipleParameters("manage");
		Constraint aConstraint9 = null; 
		Constraint dConstraint9 = null; 
		Norm norm9 = new Norm(9,DeonticConcept.PROHIBITION, context9, entity9, action9, aConstraint9, dConstraint9);
		normSet.add(norm9);
		
		Context context10 = new Context("company", ContextType.ORGANIZATION);
		Entity entity10 = new Entity ("manager", EntityType.ROLE);
		BehaviorMultipleParameters action10 = new BehaviorMultipleParameters("manage");
		Constraint aConstraint10 = null; 
		Constraint dConstraint10 = null; 
		Norm norm10 = new Norm(10,DeonticConcept.PROHIBITION, context10, entity10, action10, aConstraint10, dConstraint10);
		normSet.add(norm10);
		
		Context context11 = new Context("company", ContextType.ORGANIZATION);
		Entity entity11 = new Entity ("manager", EntityType.ROLE);
		BehaviorMultipleParameters action11 = new BehaviorMultipleParameters("manage");
		Constraint aConstraint11 = null; 
		Constraint dConstraint11 = null; 
		Norm norm11 = new Norm(11,DeonticConcept.PROHIBITION, context11, entity11, action11, aConstraint11, dConstraint11);
		normSet.add(norm11);

		Context context12 = new Context("company", ContextType.ORGANIZATION);
		Entity entity12 = new Entity ("manager", EntityType.ROLE);
		BehaviorMultipleParameters action12 = new BehaviorMultipleParameters("manage");
		Constraint aConstraint12 = null; 
		Constraint dConstraint12 = null; 
		Norm norm12 = new Norm(12,DeonticConcept.PERMISSION, context12, entity12, action12, aConstraint12, dConstraint12);
		normSet.add(norm12);
		
		Context context13 = new Context("home", ContextType.ORGANIZATION);
		Entity entity13 = new Entity ("mother", EntityType.ROLE);
		BehaviorMultipleParameters action13 = new BehaviorMultipleParameters("cook");
		Constraint aConstraint13 = new ConstraintDate(ConstraintType.DATETYPE, buildDateTime("18/07/2014"));
		Constraint dConstraint13 = new ConstraintDate(ConstraintType.DATETYPE, buildDateTime("20/07/2014"));
		Norm norm13 = new Norm(13,DeonticConcept.OBLIGATION, context13, entity13, action13, aConstraint13, dConstraint13);
		normSet.add(norm13);
		
		Context context14 = new Context("home", ContextType.ORGANIZATION);
		Entity entity14 = new Entity ("mother", EntityType.ROLE);
		BehaviorMultipleParameters action14 = new BehaviorMultipleParameters("cook");
		Constraint aConstraint14 = new ConstraintDate(ConstraintType.DATETYPE, buildDateTime("15/07/2014"));
		Constraint dConstraint14 = new ConstraintDate(ConstraintType.DATETYPE, buildDateTime("17/07/2014"));
		Norm norm14 = new Norm(14,DeonticConcept.PROHIBITION, context14, entity14, action14, aConstraint14, dConstraint14);
		normSet.add(norm14);
		
		Context context15 = new Context("home", ContextType.ORGANIZATION);
		Entity entity15 = new Entity ("mother", EntityType.ROLE);
		BehaviorMultipleParameters action15 = new BehaviorMultipleParameters("cook");
		Constraint aConstraint15 = new ConstraintDate(ConstraintType.DATETYPE, buildDateTime("19/07/2014"));
		Constraint dConstraint15 = new ConstraintDate(ConstraintType.DATETYPE, buildDateTime("20/07/2014"));
		Norm norm15 = new Norm(15,DeonticConcept.PROHIBITION, context15, entity15, action15, aConstraint15, dConstraint15);
		normSet.add(norm15);
		
		Context context16 = new Context("home", ContextType.ORGANIZATION);
		Entity entity16 = new Entity ("mother", EntityType.ROLE);
		BehaviorMultipleParameters action16 = new BehaviorMultipleParameters("dress");
		Constraint aConstraint16 = new ConstraintDate(ConstraintType.DATETYPE, buildDateTime("19/07/2014"));
		Constraint dConstraint16 = new ConstraintDate(ConstraintType.DATETYPE, buildDateTime("20/07/2014"));
		Norm norm16 = new Norm(16,DeonticConcept.PROHIBITION, context16, entity16, action16, aConstraint16, dConstraint16);
		normSet.add(norm16);
		
		Context context17 = new Context("home", ContextType.ORGANIZATION);
		Entity entity17 = new Entity ("mother", EntityType.ROLE);
		BehaviorMultipleParameters action17 = new BehaviorMultipleParameters("dress");
		Constraint aConstraint17 = new ConstraintDate(ConstraintType.DATETYPE, buildDateTime("19/07/2014"));
		Constraint dConstraint17 = new ConstraintDate(ConstraintType.DATETYPE, buildDateTime("20/07/2014"));
		Norm norm17 = new Norm(17,DeonticConcept.PERMISSION, context17, entity17, action17, aConstraint17, dConstraint17);
		normSet.add(norm17);
		
		
		/************************************************************************************/
		//TODO case2
		/************************************************************************************/

		Context context101 = new Context("home", ContextType.ORGANIZATION);
		Entity entity101 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action101 = new BehaviorMultipleParameters("dress", "shirt");
		Constraint aConstraint101 = null; 
		Constraint dConstraint101 = null; 
		Norm norm101 = new Norm(101, DeonticConcept.PERMISSION, context101, entity101, action101, aConstraint101, dConstraint101);
		normSet.add(norm101);
		
		Context context102 = new Context("home", ContextType.ORGANIZATION);
		Entity entity102 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action102 = new BehaviorMultipleParameters("dress", "shirt");
		Constraint aConstraint102 = null; 
		Constraint dConstraint102 = null; 
		Norm norm102 = new Norm(102, DeonticConcept.PROHIBITION, context102, entity102, action102, aConstraint102, dConstraint102);
		normSet.add(norm102);
		
		Context context103 = new Context("home", ContextType.ORGANIZATION);
		Entity entity103 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action103 = new BehaviorMultipleParameters("dress", "pant");
		Constraint aConstraint103 = null; 
		Constraint dConstraint103 = null; 
		Norm norm103 = new Norm(103, DeonticConcept.OBLIGATION, context103, entity103, action103, aConstraint103, dConstraint103);
		normSet.add(norm103);
				
		Context context104 = new Context("home", ContextType.ORGANIZATION);
		Entity entity104 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action104 = new BehaviorMultipleParameters("dress", "pant");
		Constraint aConstraint104 = null; 
		Constraint dConstraint104 = null; 
		Norm norm104 = new Norm(104, DeonticConcept.PROHIBITION, context104, entity104, action104, aConstraint104, dConstraint104);
		normSet.add(norm104);
		
		Context context105 = new Context("home", ContextType.ORGANIZATION);
		Entity entity105 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action105 = new BehaviorMultipleParameters("dress", "gown");
		Constraint aConstraint105 = null; 
		Constraint dConstraint105 = null; 
		Norm norm105 = new Norm(105, DeonticConcept.PERMISSION, context105, entity105, action105, aConstraint105, dConstraint105);
		normSet.add(norm105);
		
		Context context106 = new Context("home", ContextType.ORGANIZATION);
		Entity entity106 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action106 = new BehaviorMultipleParameters("dress", "gown");
		Constraint aConstraint106 = null; 
		Constraint dConstraint106 = null; 
		Norm norm106 = new Norm(106, DeonticConcept.PROHIBITION, context106, entity106, action106, aConstraint106, dConstraint106);
		normSet.add(norm106);
		
		Context context107 = new Context("home", ContextType.ORGANIZATION);
		Entity entity107 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action107 = new BehaviorMultipleParameters("dress", "gown");
		Constraint aConstraint107 = null; 
		Constraint dConstraint107 = null; 
		Norm norm107 = new Norm(107, DeonticConcept.OBLIGATION, context107, entity107, action107, aConstraint107, dConstraint107);
		normSet.add(norm107);
		
		
		/************************************************************************************/
		//TODO case3
		/************************************************************************************/
		
		Context context110 = new Context("home", ContextType.ORGANIZATION);
		Entity entity110 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action110 = new BehaviorMultipleParameters("dress");
		action110.addElement(SetUtil.COLORFUL,null);
		Constraint aConstraint110 = null; 
		Constraint dConstraint110 = null; 
		Norm norm110 = new Norm(110, DeonticConcept.OBLIGATION, context110, entity110, action110, aConstraint110, dConstraint110);
		normSet.add(norm110);
		
		Context context111 = new Context("home", ContextType.ORGANIZATION);
		Entity entity111 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action111 = new BehaviorMultipleParameters("dress");
		action111.addElement(SetUtil.COLORFUL,null);
		Constraint aConstraint111 = null; 
		Constraint dConstraint111 = null; 
		Norm norm111 = new Norm(111, DeonticConcept.PERMISSION, context111, entity111, action111, aConstraint111, dConstraint111);
		normSet.add(norm111);
		
		Context context112 = new Context("home", ContextType.ORGANIZATION);
		Entity entity112 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action112 = new BehaviorMultipleParameters("dress");
		action112.addElement(SetUtil.COLORFUL,null);
		Constraint aConstraint112 = null; 
		Constraint dConstraint112 = null; 
		Norm norm112 = new Norm(112, DeonticConcept.PROHIBITION, context112, entity112, action112, aConstraint112, dConstraint112);
		normSet.add(norm112);
		
		
		/************************************************************************************/
		//TODO case4 		//130 a 160
		/************************************************************************************/
		
		Context context131 = new Context("home", ContextType.ORGANIZATION);
		Entity entity131 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action131 = new BehaviorMultipleParameters("dress");
		action131.addElement("color","white");
		action131.addElement("picture", "smooth");
		action131.addElement("ironingtype", "ironing");
		Constraint aConstraint131 = null; 
		Constraint dConstraint131 = null; 
		Norm norm131 = new Norm(131, DeonticConcept.OBLIGATION, context131, entity131, action131, aConstraint131, dConstraint131);
		normSet.add(norm131);
		
		Context context132 = new Context("home", ContextType.ORGANIZATION);
		Entity entity132 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action132 = new BehaviorMultipleParameters("dress");
		action132.addElement("color","white");
		action132.addElement("picture", "vertical");
		action132.addElement("ironingtype", "ironing");
		Constraint aConstraint132 = null; 
		Constraint dConstraint132 = null; 
		Norm norm132 = new Norm(132, DeonticConcept.PROHIBITION, context132, entity132, action132, aConstraint132, dConstraint132);
		normSet.add(norm132);

		Context context133 = new Context("home", ContextType.ORGANIZATION);
		Entity entity133 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action133 = new BehaviorMultipleParameters("dress");
		action133.addElement("color","black");
		action133.addElement("picture", "horizontal");
		action133.addElement("ironingtype", "ironing");
		Constraint aConstraint133 = null; 
		Constraint dConstraint133 = null; 
		Norm norm133 = new Norm(133, DeonticConcept.PROHIBITION, context133, entity133, action133, aConstraint133, dConstraint133);
		normSet.add(norm133);
				
		Context context134 = new Context("home", ContextType.ORGANIZATION);
		Entity entity134 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action134 = new BehaviorMultipleParameters("dress");
		action134.addElement("color", "black");
		action134.addElement("picture", "smooth");
		action134.addElement("ironingtype", "ironing");
		Constraint aConstraint134 = null; 
		Constraint dConstraint134 = null; 
		Norm norm134 = new Norm(134, DeonticConcept.OBLIGATION, context134, entity134, action134, aConstraint134, dConstraint134);
		normSet.add(norm134);
		
		Context context135 = new Context("home", ContextType.ORGANIZATION);
		Entity entity135 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action135 = new BehaviorMultipleParameters("dress");
		action135.addElement("garment","pant");
		action135.addElement("color", "yellow");
		action135.addElement("picture", "smooth");
		action135.addElement("ironingtype", "ironing");
		Constraint aConstraint135 = null; 
		Constraint dConstraint135 = null; 
		Norm norm135 = new Norm(135, DeonticConcept.PERMISSION, context135, entity135, action135, aConstraint135, dConstraint135);
		normSet.add(norm135);
		
		Context context141 = new Context("home", ContextType.ORGANIZATION);
		Entity entity141 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action141 = new BehaviorMultipleParameters("dress");
		action141.addElement("color","white");
		action141.addElement("picture", "smooth");
		action141.addElement("ironingtype", "crumpled");
		Constraint aConstraint141 = null; 
		Constraint dConstraint141 = null; 
		Norm norm141 = new Norm(141, DeonticConcept.OBLIGATION, context141, entity141, action141, aConstraint141, dConstraint141);
		normSet.add(norm141);
		
		Context context142 = new Context("home", ContextType.ORGANIZATION);
		Entity entity142 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action142 = new BehaviorMultipleParameters("dress");
		action142.addElement("color","blue");
		action142.addElement("picture", "vertical");
		action142.addElement("ironingtype", "crumpled");
		Constraint aConstraint142 = null; 
		Constraint dConstraint142 = null; 
		Norm norm142 = new Norm(142, DeonticConcept.PROHIBITION, context142, entity142, action142, aConstraint142, dConstraint142);
		normSet.add(norm142);

		Context context143 = new Context("home", ContextType.ORGANIZATION);
		Entity entity143 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action143 = new BehaviorMultipleParameters("dress");
		action143.addElement("color","black");
		action143.addElement("picture", "horizontal");
		action143.addElement("ironingtype", "crumpled");
		Constraint aConstraint143 = null; 
		Constraint dConstraint143 = null; 
		Norm norm143 = new Norm(143, DeonticConcept.PROHIBITION, context143, entity143, action143, aConstraint143, dConstraint143);
		normSet.add(norm143);
				
		Context context144 = new Context("home", ContextType.ORGANIZATION);
		Entity entity144 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action144 = new BehaviorMultipleParameters("dress");
		action144.addElement("color", "blue");
		action144.addElement("picture", "smooth");
		action144.addElement("ironingtype", "crumpled");
		Constraint aConstraint144 = null; 
		Constraint dConstraint144 = null; 
		Norm norm144 = new Norm(144, DeonticConcept.OBLIGATION, context144, entity144, action144, aConstraint144, dConstraint144);
		normSet.add(norm144);
		
		/*
		Context context145 = new Context("home", ContextType.ORGANIZATION);
		Entity entity145 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action145 = new BehaviorMultipleParameters("dress");
		action145.addElement("color", "yellow");
		action145.addElement("picture", "smooth");
		action145.addElement("ironingtype", "crumpled");
		Constraint aConstraint145 = null; 
		Constraint dConstraint145 = null; 
		Norm norm145 = new Norm(145, DeonticConcept.PERMISSION, context145, entity145, action145, aConstraint145, dConstraint145);
		normSet.add(norm145);
		
		Context context146 = new Context("home", ContextType.ORGANIZATION);
		Entity entity146 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action146 = new BehaviorMultipleParameters("dress");
		action146.addElement("color","white");
		action146.addElement("picture", "horizontal");
		action146.addElement("ironingtype", "crumpled");
		Constraint aConstraint146 = null; 
		Constraint dConstraint146 = null; 
		Norm norm146 = new Norm(146, DeonticConcept.OBLIGATION, context146, entity146, action146, aConstraint146, dConstraint146);
		normSet.add(norm146);
		
		Context context147 = new Context("home", ContextType.ORGANIZATION);
		Entity entity147 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action147 = new BehaviorMultipleParameters("dress");
		action147.addElement("color","blue");
		action147.addElement("picture", "vertical");
		action147.addElement("ironingtype", "crumpled");
		Constraint aConstraint147 = null; 
		Constraint dConstraint147 = null; 
		Norm norm147 = new Norm(147, DeonticConcept.PROHIBITION, context147, entity147, action147, aConstraint147, dConstraint147);
		normSet.add(norm147);

		Context context148 = new Context("home", ContextType.ORGANIZATION);
		Entity entity148 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action148 = new BehaviorMultipleParameters("dress");
		action148.addElement("color","black");
		action148.addElement("picture", "gust");
		action148.addElement("ironingtype", "crumpled");
		Constraint aConstraint148 = null; 
		Constraint dConstraint148 = null; 
		Norm norm148 = new Norm(148, DeonticConcept.PROHIBITION, context148, entity148, action148, aConstraint148, dConstraint148);
		normSet.add(norm148);
				
		Context context149 = new Context("home", ContextType.ORGANIZATION);
		Entity entity149 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action149 = new BehaviorMultipleParameters("dress");
		action149.addElement("color", "blue");
		action149.addElement("picture", "gust");
		action149.addElement("ironingtype", "crumpled");
		Constraint aConstraint149 = null; 
		Constraint dConstraint149 = null; 
		Norm norm149 = new Norm(149, DeonticConcept.OBLIGATION, context149, entity149, action149, aConstraint149, dConstraint149);
		normSet.add(norm149);
		*/
		/************************************************************************************/
		//TODO case5 170 to 200
		/************************************************************************************/
		
		Context context171 = new Context("home", ContextType.ORGANIZATION);
		Entity entity171 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action171 = new BehaviorMultipleParameters("dress","shirt");
		action171.addElement(SetUtil.COLORFUL, null);
		action171.addElement(SetUtil.IRONING_STATE, null);
		action171.addElement(SetUtil.PICTURED, null);
		Constraint aConstraint171 = null; 
		Constraint dConstraint171 = null; 
		Norm norm171 = new Norm(171, DeonticConcept.OBLIGATION, context171, entity171, action171, aConstraint171, dConstraint171);
		normSet.add(norm171);
		
		Context context172 = new Context("home", ContextType.ORGANIZATION);
		Entity entity172 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action172 = new BehaviorMultipleParameters("dress","shirt");
		action172.addElement(SetUtil.COLORFUL, null);
		action172.addElement(SetUtil.IRONING_STATE, null);
		action172.addElement(SetUtil.PICTURED, null);
		Constraint aConstraint172 = null; 
		Constraint dConstraint172 = null; 
		Norm norm172 = new Norm(172, DeonticConcept.PROHIBITION, context172, entity172, action172, aConstraint172, dConstraint172);
		normSet.add(norm172);

		Context context173 = new Context("home", ContextType.ORGANIZATION);
		Entity entity173 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action173 = new BehaviorMultipleParameters("dress","pant");
		action173.addElement(SetUtil.COLORFUL, null);
		action173.addElement(SetUtil.IRONING_STATE, null);
		action173.addElement(SetUtil.PICTURED, null);
		Constraint aConstraint173 = null; 
		Constraint dConstraint173 = null; 
		Norm norm173 = new Norm(173, DeonticConcept.PERMISSION, context173, entity173, action173, aConstraint173, dConstraint173);
		normSet.add(norm173);
		
		Context context174 = new Context("home", ContextType.ORGANIZATION);
		Entity entity174 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action174 = new BehaviorMultipleParameters("dress","shirt");
		action174.addElement(SetUtil.COLORFUL, null);
		action174.addElement(SetUtil.IRONING_STATE, null);
		action174.addElement(SetUtil.PICTURED, null);
		Constraint aConstraint174 = null; 
		Constraint dConstraint174 = null; 
		Norm norm174 = new Norm(174, DeonticConcept.PROHIBITION, context174, entity174, action174, aConstraint174, dConstraint174);
		normSet.add(norm174);
		
		
		
		/*
		Context context175 = new Context("home", ContextType.ORGANIZATION);
		Entity entity175 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action175 = new BehaviorMultipleParameters("dress","garment");
		action175.addElement("garment","pant");
		action175.addElement("color", ConflictChecker_APV.EQUAL);
		action175.addElement("picture", "smooth");
		action175.addElement("ironingtype", "ironing");
		Constraint aConstraint175 = null; 
		Constraint dConstraint175 = null; 
		Norm norm175 = new Norm(175, DeonticConcept.PERMISSION, context175, entity175, action175, aConstraint175, dConstraint175);
		normSet.add(norm175);
		
		
		
		Context context181 = new Context("home", ContextType.ORGANIZATION);
		Entity entity181 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action181 = new BehaviorMultipleParameters("dress","garment");
		action181.addElement("garment","shirt");
		action181.addElement("color","white");
		action181.addElement("picture", "smooth");
		action181.addElement("ironingtype", "crumpled");
		Constraint aConstraint181 = null; 
		Constraint dConstraint181 = null; 
		Norm norm181 = new Norm(181, DeonticConcept.OBLIGATION, context181, entity181, action181, aConstraint181, dConstraint181);
		normSet.add(norm181);
		
		Context context182 = new Context("home", ContextType.ORGANIZATION);
		Entity entity182 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action182 = new BehaviorMultipleParameters("dress","garment");
		action182.addElement("garment","shirt");
		action182.addElement("color","blue");
		action182.addElement("picture", "vertical");
		action182.addElement("ironingtype", "crumpled");
		Constraint aConstraint182 = null; 
		Constraint dConstraint182 = null; 
		Norm norm182 = new Norm(182, DeonticConcept.PROHIBITION, context182, entity182, action182, aConstraint182, dConstraint182);
		normSet.add(norm182);

		Context context183 = new Context("home", ContextType.ORGANIZATION);
		Entity entity183 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action183 = new BehaviorMultipleParameters("dress","garment");
		action183.addElement("garment","pant");
		action183.addElement("color","black");
		action183.addElement("picture", "horizontal");
		action183.addElement("ironingtype", "crumpled");
		Constraint aConstraint183 = null; 
		Constraint dConstraint183 = null; 
		Norm norm183 = new Norm(183, DeonticConcept.PROHIBITION, context183, entity183, action183, aConstraint183, dConstraint183);
		normSet.add(norm183);
				
		Context context184 = new Context("home", ContextType.ORGANIZATION);
		Entity entity184 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action184 = new BehaviorMultipleParameters("dress","garment");
		action184.addElement("garment","shirt");
		action184.addElement("color", ConflictChecker_APV.EQUAL);
		action184.addElement("picture", "smooth");
		action184.addElement("ironingtype", "crumpled");
		Constraint aConstraint184 = null; 
		Constraint dConstraint184 = null; 
		Norm norm184 = new Norm(184, DeonticConcept.OBLIGATION, context184, entity184, action184, aConstraint184, dConstraint184);
		normSet.add(norm184);
		
		Context context185 = new Context("home", ContextType.ORGANIZATION);
		Entity entity185 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action185 = new BehaviorMultipleParameters("dress","garment");
		action185.addElement("garment","pant");
		action185.addElement("color", ConflictChecker_APV.EQUAL);
		action185.addElement("picture", "smooth");
		action185.addElement("ironingtype", "crumpled");
		Constraint aConstraint185 = null; 
		Constraint dConstraint185 = null; 
		Norm norm185 = new Norm(185, DeonticConcept.PERMISSION, context185, entity185, action185, aConstraint185, dConstraint185);
		normSet.add(norm185);
		
		Context context186 = new Context("home", ContextType.ORGANIZATION);
		Entity entity186 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action186 = new BehaviorMultipleParameters("dress","garment");
		action186.addElement("garment","shirt");
		action186.addElement("color","white");
		action186.addElement("picture", "horizontal");
		action186.addElement("ironingtype", "crumpled");
		Constraint aConstraint186 = null; 
		Constraint dConstraint186 = null; 
		Norm norm186 = new Norm(186, DeonticConcept.OBLIGATION, context186, entity186, action186, aConstraint186, dConstraint186);
		normSet.add(norm186);
		
		Context context187 = new Context("home", ContextType.ORGANIZATION);
		Entity entity187 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action187 = new BehaviorMultipleParameters("dress","garment");
		action187.addElement("garment","shirt");
		action187.addElement("color","blue");
		action187.addElement("picture", "vertical");
		action187.addElement("ironingtype", "crumpled");
		Constraint aConstraint187 = null; 
		Constraint dConstraint187 = null; 
		Norm norm187 = new Norm(187, DeonticConcept.PROHIBITION, context187, entity187, action187, aConstraint187, dConstraint187);
		normSet.add(norm187);

		Context context188 = new Context("home", ContextType.ORGANIZATION);
		Entity entity188 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action188 = new BehaviorMultipleParameters("dress","garment");
		action188.addElement("garment","pant");
		action188.addElement("color","black");
		action188.addElement("picture", "gust");
		action188.addElement("ironingtype", "crumpled");
		Constraint aConstraint188 = null; 
		Constraint dConstraint188 = null; 
		Norm norm188 = new Norm(188, DeonticConcept.PROHIBITION, context188, entity188, action188, aConstraint188, dConstraint188);
		normSet.add(norm188);
				
		Context context189 = new Context("home", ContextType.ORGANIZATION);
		Entity entity189 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action189 = new BehaviorMultipleParameters("dress","garment");
		action189.addElement("garment","shirt");
		action189.addElement("color", ConflictChecker_APV.EQUAL);
		action189.addElement("picture", "gust");
		action189.addElement("ironingtype", "crumpled");
		Constraint aConstraint189 = null; 
		Constraint dConstraint189 = null; 
		Norm norm189 = new Norm(189, DeonticConcept.OBLIGATION, context189, entity189, action189, aConstraint189, dConstraint189);
		normSet.add(norm189);
		
		Context context190 = new Context("home", ContextType.ORGANIZATION);
		Entity entity190 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action190 = new BehaviorMultipleParameters("dress","garment");
		action190.addElement("garment","pant");
		action190.addElement("color", ConflictChecker_APV.EQUAL);
		action190.addElement("picture", "vertical");
		action190.addElement("ironingtype", "crumpled");
		Constraint aConstraint190 = null; 
		Constraint dConstraint190 = null; 
		Norm norm190 = new Norm(190, DeonticConcept.PERMISSION, context190, entity190, action190, aConstraint190, dConstraint190);
		normSet.add(norm190);
		
		Context context191 = new Context("home", ContextType.ORGANIZATION);
		Entity entity191 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action191 = new BehaviorMultipleParameters("dress","garment");
		action191.addElement("garment","shirt");
		action191.addElement("color","white");
		action191.addElement("picture", "horizontal");
		action191.addElement("ironingtype", "crumpled");
		Constraint aConstraint191 = null; 
		Constraint dConstraint191 = null; 
		Norm norm191 = new Norm(191, DeonticConcept.PERMISSION, context191, entity191, action191, aConstraint191, dConstraint191);
		normSet.add(norm191);
		
		Context context192 = new Context("home", ContextType.ORGANIZATION);
		Entity entity192 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action192 = new BehaviorMultipleParameters("dress","garment");
		action192.addElement("garment","shirt");
		action192.addElement("color","blue");
		action192.addElement("picture", "vertical");
		action192.addElement("ironingtype", "crumpled");
		Constraint aConstraint192 = null; 
		Constraint dConstraint192 = null; 
		Norm norm192 = new Norm(192, DeonticConcept.PERMISSION, context192, entity192, action192, aConstraint192, dConstraint192);
		normSet.add(norm192);

		Context context193 = new Context("home", ContextType.ORGANIZATION);
		Entity entity193 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action193 = new BehaviorMultipleParameters("dress","garment");
		action193.addElement("garment","pant");
		action193.addElement("color","black");
		action193.addElement("picture", "gust");
		action193.addElement("ironingtype", "crumpled");
		Constraint aConstraint193 = null; 
		Constraint dConstraint193 = null; 
		Norm norm193 = new Norm(193, DeonticConcept.PERMISSION, context193, entity193, action193, aConstraint193, dConstraint193);
		normSet.add(norm193);
				
		Context context194 = new Context("home", ContextType.ORGANIZATION);
		Entity entity194 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action194 = new BehaviorMultipleParameters("dress","garment");
		action194.addElement("garment","shirt");
		action194.addElement("color", ConflictChecker_APV.EQUAL);
		action194.addElement("picture", "gust");
		action194.addElement("ironingtype", "crumpled");
		Constraint aConstraint194 = null; 
		Constraint dConstraint194 = null; 
		Norm norm194 = new Norm(194, DeonticConcept.PROHIBITION, context194, entity194, action194, aConstraint194, dConstraint194);
		normSet.add(norm194);
		
		Context context195 = new Context("home", ContextType.ORGANIZATION);
		Entity entity195 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action195 = new BehaviorMultipleParameters("dress","garment");
		action195.addElement("garment","pant");
		action195.addElement("color", ConflictChecker_APV.EQUAL);
		action195.addElement("picture", "vertical");
		action195.addElement("ironingtype", "crumpled");
		Constraint aConstraint195 = null; 
		Constraint dConstraint195 = null; 
		Norm norm195 = new Norm(195, DeonticConcept.PROHIBITION, context195, entity195, action195, aConstraint195, dConstraint195);
		normSet.add(norm195);
		
		//
		
		Context context196 = new Context("field", ContextType.ORGANIZATION);
		Entity entity196 = new Entity ("player", EntityType.ROLE);
		BehaviorMultipleParameters action196 = new BehaviorMultipleParameters("use","g");
		action196.addElement("g","whistle");
		action196.addElement("color","white");
		action196.addElement("bodypart", "mouth");
		Constraint aConstraint196 = null; 
		Constraint dConstraint196 = null; 
		Norm norm196 = new Norm(196, DeonticConcept.OBLIGATION, context196, entity196, action196, aConstraint196, dConstraint196);
		normSet.add(norm196);
		
		Context context197 = new Context("field", ContextType.ORGANIZATION);
		Entity entity197 = new Entity ("player", EntityType.ROLE);
		BehaviorMultipleParameters action197 = new BehaviorMultipleParameters("use","g");
		action197.addElement("g","shoes");
		action197.addElement("color","red");
		action197.addElement("bodypart", "foot");
		Constraint aConstraint197 = null; 
		Constraint dConstraint197 = null; 
		Norm norm197 = new Norm(197, DeonticConcept.OBLIGATION, context197, entity197, action197, aConstraint197, dConstraint197);
		normSet.add(norm197);
		
		Context context198 = new Context("field", ContextType.ORGANIZATION);
		Entity entity198 = new Entity ("player", EntityType.ROLE);
		BehaviorMultipleParameters action198 = new BehaviorMultipleParameters("use","g");
		action198.addElement("g","whistle");
		action198.addElement("color","blue");
		action198.addElement("bodypart", "mouth");
		Constraint aConstraint198 = null; 
		Constraint dConstraint198 = null; 
		Norm norm198 = new Norm(198, DeonticConcept.PROHIBITION, context198, entity198, action198, aConstraint198, dConstraint198);
		normSet.add(norm198);
		
		Context context199 = new Context("field", ContextType.ORGANIZATION);
		Entity entity199 = new Entity ("player", EntityType.ROLE);
		BehaviorMultipleParameters action199 = new BehaviorMultipleParameters("use","g");
		action199.addElement("g","whistle");
		action199.addElement("color","red");
		action199.addElement("bodypart", "mouth");
		Constraint aConstraint199 = null; 
		Constraint dConstraint199 = null; 
		Norm norm199 = new Norm(199, DeonticConcept.PERMISSION, context199, entity199, action199, aConstraint199, dConstraint199);
		normSet.add(norm199);
		
		Context context200 = new Context("field", ContextType.ORGANIZATION);
		Entity entity200 = new Entity ("player", EntityType.ROLE);
		BehaviorMultipleParameters action200 = new BehaviorMultipleParameters("use","g");
		action200.addElement("g","whistle");
		action200.addElement("color","white");
		action200.addElement("bodypart", ConflictChecker_APV.EQUAL);
		Constraint aConstraint200 = null; 
		Constraint dConstraint200 = null; 
		Norm norm200 = new Norm(200, DeonticConcept.OBLIGATION, context200, entity200, action200, aConstraint200, dConstraint200);
		normSet.add(norm200);*/
		
		
		/************************************************************************************/
		//TODO case6
		/************************************************************************************/
		
		Context context201 = new Context("home", ContextType.ORGANIZATION);
		Entity entity201 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action201 = new BehaviorMultipleParameters("dress","shirt");
		action201.addElement("color","white");
		action201.addElement("picture", "smooth");
		action201.addElement("ironingtype", "ironing");
		Constraint aConstraint201 = null; 
		Constraint dConstraint201 = null; 
		Norm norm201 = new Norm(201, DeonticConcept.OBLIGATION, context201, entity201, action201, aConstraint201, dConstraint201);
		normSet.add(norm201);
		
		Context context202 = new Context("home", ContextType.ORGANIZATION);
		Entity entity202 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action202 = new BehaviorMultipleParameters("dress","shirt");
		action202.addElement("color","blue");
		action202.addElement("picture", "vertical");
		action202.addElement("ironingtype", "ironing");
		Constraint aConstraint202 = null; 
		Constraint dConstraint202 = null; 
		Norm norm202 = new Norm(202, DeonticConcept.PROHIBITION, context202, entity202, action202, aConstraint202, dConstraint202);
		normSet.add(norm202);

		Context context203 = new Context("home", ContextType.ORGANIZATION);
		Entity entity203 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action203 = new BehaviorMultipleParameters("dress","pant");
		action203.addElement("color","black");
		action203.addElement("picture", "horizontal");
		action203.addElement("ironingtype", "ironing");
		Constraint aConstraint203 = null; 
		Constraint dConstraint203 = null; 
		Norm norm203 = new Norm(203, DeonticConcept.PROHIBITION, context203, entity203, action203, aConstraint203, dConstraint203);
		normSet.add(norm203);
				
		Context context204 = new Context("home", ContextType.ORGANIZATION);
		Entity entity204 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action204 = new BehaviorMultipleParameters("dress","shirt");
		action204.addElement("color", SetUtil.EQUAL);
		action204.addElement("picture", "smooth");
		action204.addElement("ironingtype", "ironing");
		Constraint aConstraint204 = null; 
		Constraint dConstraint204 = null; 
		Norm norm204 = new Norm(204, DeonticConcept.OBLIGATION, context204, entity204, action204, aConstraint204, dConstraint204);
		normSet.add(norm204);
		
		
		/*
		Context context205 = new Context("home", ContextType.ORGANIZATION);
		Entity entity205 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action205 = new BehaviorMultipleParameters("dress","garment");
		action205.addElement("garment","pant");
		action205.addElement("color", ConflictChecker_APV.EQUAL);
		action205.addElement("picture", "smooth");
		action205.addElement("ironingtype", "ironing");
		Constraint aConstraint205 = null; 
		Constraint dConstraint205 = null; 
		Norm norm205 = new Norm(205, DeonticConcept.PERMISSION, context205, entity205, action205, aConstraint205, dConstraint205);
		normSet.add(norm205);
		
		
		
		Context context301 = new Context("home", ContextType.ORGANIZATION);
		Entity entity301 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action301 = new BehaviorMultipleParameters("dress","garment");
		action301.addElement("garment","shirt");
		action301.addElement("color","white");
		action301.addElement("picture", "smooth");
		action301.addElement("ironingtype", "crumpled");
		Constraint aConstraint301 = null; 
		Constraint dConstraint301 = null; 
		Norm norm301 = new Norm(301, DeonticConcept.OBLIGATION, context301, entity301, action301, aConstraint301, dConstraint301);
		normSet.add(norm301);
		
		Context context302 = new Context("home", ContextType.ORGANIZATION);
		Entity entity302 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action302 = new BehaviorMultipleParameters("dress","garment");
		action302.addElement("garment","shirt");
		action302.addElement("color","blue");
		action302.addElement("picture", "vertical");
		action302.addElement("ironingtype", "crumpled");
		Constraint aConstraint302 = null; 
		Constraint dConstraint302 = null; 
		Norm norm302 = new Norm(302, DeonticConcept.PROHIBITION, context302, entity302, action302, aConstraint302, dConstraint302);
		normSet.add(norm302);

		Context context303 = new Context("home", ContextType.ORGANIZATION);
		Entity entity303 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action303 = new BehaviorMultipleParameters("dress","garment");
		action303.addElement("garment","pant");
		action303.addElement("color","black");
		action303.addElement("picture", "horizontal");
		action303.addElement("ironingtype", "crumpled");
		Constraint aConstraint303 = null; 
		Constraint dConstraint303 = null; 
		Norm norm303 = new Norm(303, DeonticConcept.PROHIBITION, context303, entity303, action303, aConstraint303, dConstraint303);
		normSet.add(norm303);
				
		Context context304 = new Context("home", ContextType.ORGANIZATION);
		Entity entity304 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action304 = new BehaviorMultipleParameters("dress","garment");
		action304.addElement("garment","shirt");
		action304.addElement("color", ConflictChecker_APV.EQUAL);
		action304.addElement("picture", "smooth");
		action304.addElement("ironingtype", "crumpled");
		Constraint aConstraint304 = null; 
		Constraint dConstraint304 = null; 
		Norm norm304 = new Norm(304, DeonticConcept.OBLIGATION, context304, entity304, action304, aConstraint304, dConstraint304);
		normSet.add(norm304);
		
		Context context305 = new Context("home", ContextType.ORGANIZATION);
		Entity entity305 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action305 = new BehaviorMultipleParameters("dress","garment");
		action305.addElement("garment","pant");
		action305.addElement("color", ConflictChecker_APV.EQUAL);
		action305.addElement("picture", "smooth");
		action305.addElement("ironingtype", "crumpled");
		Constraint aConstraint305 = null; 
		Constraint dConstraint305 = null; 
		Norm norm305 = new Norm(305, DeonticConcept.PERMISSION, context305, entity305, action305, aConstraint305, dConstraint305);
		normSet.add(norm305);
		
		Context context306 = new Context("home", ContextType.ORGANIZATION);
		Entity entity306 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action306 = new BehaviorMultipleParameters("dress","garment");
		action306.addElement("garment","shirt");
		action306.addElement("color","white");
		action306.addElement("picture", "horizontal");
		action306.addElement("ironingtype", "crumpled");
		Constraint aConstraint306 = null; 
		Constraint dConstraint306 = null; 
		Norm norm306 = new Norm(306, DeonticConcept.OBLIGATION, context306, entity306, action306, aConstraint306, dConstraint306);
		normSet.add(norm306);
		
		Context context307 = new Context("home", ContextType.ORGANIZATION);
		Entity entity307 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action307 = new BehaviorMultipleParameters("dress","garment");
		action307.addElement("garment","shirt");
		action307.addElement("color","blue");
		action307.addElement("picture", "vertical");
		action307.addElement("ironingtype", "crumpled");
		Constraint aConstraint307 = null; 
		Constraint dConstraint307 = null; 
		Norm norm307 = new Norm(307, DeonticConcept.PROHIBITION, context307, entity307, action307, aConstraint307, dConstraint307);
		normSet.add(norm307);

		Context context308 = new Context("home", ContextType.ORGANIZATION);
		Entity entity308 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action308 = new BehaviorMultipleParameters("dress","garment");
		action308.addElement("garment","pant");
		action308.addElement("color","black");
		action308.addElement("picture", "gust");
		action308.addElement("ironingtype", "crumpled");
		Constraint aConstraint308 = null; 
		Constraint dConstraint308 = null; 
		Norm norm308 = new Norm(308, DeonticConcept.PROHIBITION, context308, entity308, action308, aConstraint308, dConstraint308);
		normSet.add(norm308);
				
		Context context309 = new Context("home", ContextType.ORGANIZATION);
		Entity entity309 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action309 = new BehaviorMultipleParameters("dress","garment");
		action309.addElement("garment","shirt");
		action309.addElement("color", ConflictChecker_APV.EQUAL);
		action309.addElement("picture", "gust");
		action309.addElement("ironingtype", "crumpled");
		Constraint aConstraint309 = null; 
		Constraint dConstraint309 = null; 
		Norm norm309 = new Norm(309, DeonticConcept.OBLIGATION, context309, entity309, action309, aConstraint309, dConstraint309);
		normSet.add(norm309);
		
		Context context310 = new Context("home", ContextType.ORGANIZATION);
		Entity entity310 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action310 = new BehaviorMultipleParameters("dress","garment");
		action310.addElement("garment","pant");
		action310.addElement("color", ConflictChecker_APV.EQUAL);
		action310.addElement("picture", "vertical");
		action310.addElement("ironingtype", "crumpled");
		Constraint aConstraint310 = null; 
		Constraint dConstraint310 = null; 
		Norm norm310 = new Norm(310, DeonticConcept.PERMISSION, context310, entity310, action310, aConstraint310, dConstraint310);
		normSet.add(norm310);
		
		Context context311 = new Context("home", ContextType.ORGANIZATION);
		Entity entity311 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action311 = new BehaviorMultipleParameters("dress","garment");
		action311.addElement("garment","shirt");
		action311.addElement("color","white");
		action311.addElement("picture", "horizontal");
		action311.addElement("ironingtype", "crumpled");
		Constraint aConstraint311 = null; 
		Constraint dConstraint311 = null; 
		Norm norm311 = new Norm(311, DeonticConcept.PERMISSION, context311, entity311, action311, aConstraint311, dConstraint311);
		normSet.add(norm311);
		
		Context context312 = new Context("home", ContextType.ORGANIZATION);
		Entity entity312 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action312 = new BehaviorMultipleParameters("dress","garment");
		action312.addElement("garment","shirt");
		action312.addElement("color","blue");
		action312.addElement("picture", "vertical");
		action312.addElement("ironingtype", "crumpled");
		Constraint aConstraint312 = null; 
		Constraint dConstraint312 = null; 
		Norm norm312 = new Norm(312, DeonticConcept.PERMISSION, context312, entity312, action312, aConstraint312, dConstraint312);
		normSet.add(norm312);

		Context context313 = new Context("home", ContextType.ORGANIZATION);
		Entity entity313 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action313 = new BehaviorMultipleParameters("dress","garment");
		action313.addElement("garment","pant");
		action313.addElement("color","black");
		action313.addElement("picture", "gust");
		action313.addElement("ironingtype", "crumpled");
		Constraint aConstraint313 = null; 
		Constraint dConstraint313 = null; 
		Norm norm313 = new Norm(313, DeonticConcept.PERMISSION, context313, entity313, action313, aConstraint313, dConstraint313);
		normSet.add(norm313);
				
		Context context314 = new Context("home", ContextType.ORGANIZATION);
		Entity entity314 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action314 = new BehaviorMultipleParameters("dress","garment");
		action314.addElement("garment","shirt");
		action314.addElement("color", ConflictChecker_APV.EQUAL);
		action314.addElement("picture", "gust");
		action314.addElement("ironingtype", "crumpled");
		Constraint aConstraint314 = null; 
		Constraint dConstraint314 = null; 
		Norm norm314 = new Norm(314, DeonticConcept.PROHIBITION, context314, entity314, action314, aConstraint314, dConstraint314);
		normSet.add(norm314);
		
		Context context315 = new Context("home", ContextType.ORGANIZATION);
		Entity entity315 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action315 = new BehaviorMultipleParameters("dress","garment");
		action315.addElement("garment","pant");
		action315.addElement("color", ConflictChecker_APV.EQUAL);
		action315.addElement("picture", "vertical");
		action315.addElement("ironingtype", "crumpled");
		Constraint aConstraint315 = null; 
		Constraint dConstraint315 = null; 
		Norm norm315 = new Norm(315, DeonticConcept.PROHIBITION, context315, entity315, action315, aConstraint315, dConstraint315);
		normSet.add(norm315);
		
		//
		
		Context context320 = new Context("field", ContextType.ORGANIZATION);
		Entity entity320 = new Entity ("player", EntityType.ROLE);
		BehaviorMultipleParameters action320 = new BehaviorMultipleParameters("use","g");
		action320.addElement("g","whistle");
		action320.addElement("color","white");
		action320.addElement("bodypart", "mouth");
		Constraint aConstraint320 = null; 
		Constraint dConstraint320 = null; 
		Norm norm320 = new Norm(320, DeonticConcept.OBLIGATION, context320, entity320, action320, aConstraint320, dConstraint320);
		normSet.add(norm320);
		
		Context context321 = new Context("field", ContextType.ORGANIZATION);
		Entity entity321 = new Entity ("player", EntityType.ROLE);
		BehaviorMultipleParameters action321 = new BehaviorMultipleParameters("use","g");
		action321.addElement("g","shoes");
		action321.addElement("color","red");
		action321.addElement("bodypart", "foot");
		Constraint aConstraint321 = null; 
		Constraint dConstraint321 = null; 
		Norm norm321 = new Norm(321, DeonticConcept.OBLIGATION, context321, entity321, action321, aConstraint321, dConstraint321);
		normSet.add(norm321);
		
		Context context322 = new Context("field", ContextType.ORGANIZATION);
		Entity entity322 = new Entity ("player", EntityType.ROLE);
		BehaviorMultipleParameters action322 = new BehaviorMultipleParameters("use","g");
		action322.addElement("g","whistle");
		action322.addElement("color","blue");
		action322.addElement("bodypart", "mouth");
		Constraint aConstraint322 = null; 
		Constraint dConstraint322 = null; 
		Norm norm322 = new Norm(322, DeonticConcept.PROHIBITION, context322, entity322, action322, aConstraint322, dConstraint322);
		normSet.add(norm322);
		
		Context context323 = new Context("field", ContextType.ORGANIZATION);
		Entity entity323 = new Entity ("player", EntityType.ROLE);
		BehaviorMultipleParameters action323 = new BehaviorMultipleParameters("use","g");
		action323.addElement("g","whistle");
		action323.addElement("color","red");
		action323.addElement("bodypart", "mouth");
		Constraint aConstraint323 = null; 
		Constraint dConstraint323 = null; 
		Norm norm323 = new Norm(323, DeonticConcept.PERMISSION, context323, entity323, action323, aConstraint323, dConstraint323);
		normSet.add(norm323);
		
		Context context324 = new Context("field", ContextType.ORGANIZATION);
		Entity entity324 = new Entity ("player", EntityType.ROLE);
		BehaviorMultipleParameters action324 = new BehaviorMultipleParameters("use","g");
		action324.addElement("g","whistle");
		action324.addElement("color","white");
		action324.addElement("bodypart", ConflictChecker_APV.EQUAL);
		Constraint aConstraint324 = null; 
		Constraint dConstraint324 = null; 
		Norm norm324 = new Norm(324, DeonticConcept.OBLIGATION, context324, entity324, action324, aConstraint324, dConstraint324);
		normSet.add(norm324);*/
		
		return normSet;
	}
	
	private static List<Norm> buildSomeNorms2() {
		List<Norm> normSet = new ArrayList<>();
		
		/************************************************************************************/
		//TODO case1
		/************************************************************************************/
		
		Context context1 = new Context("home", ContextType.ORGANIZATION);
		Entity entity1 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action1 = new BehaviorMultipleParameters("dress");
		Constraint aConstraint1 = null; 
		Constraint dConstraint1 = null; 
		Norm norm1 = new Norm(1,DeonticConcept.OBLIGATION, context1, entity1, action1, aConstraint1, dConstraint1);
		normSet.add(norm1);
		
		Context context2 = new Context("home", ContextType.ORGANIZATION);
		Entity entity2 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action2 = new BehaviorMultipleParameters("dress");
		Constraint aConstraint2 = null; 
		Constraint dConstraint2 = null; 
		Norm norm2 = new Norm(2,DeonticConcept.PROHIBITION, context2, entity2, action2, aConstraint2, dConstraint2);
		normSet.add(norm2);
		

		
		
		/************************************************************************************/
		//TODO case2
		/************************************************************************************/
		
		Context context101 = new Context("home", ContextType.ORGANIZATION);
		Entity entity101 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action101 = new BehaviorMultipleParameters("dress", "shirt");
		Constraint aConstraint101 = null; 
		Constraint dConstraint101 = null; 
		Norm norm101 = new Norm(101, DeonticConcept.PERMISSION, context101, entity101, action101, aConstraint101, dConstraint101);
		normSet.add(norm101);
		
		Context context102 = new Context("home", ContextType.ORGANIZATION);
		Entity entity102 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action102 = new BehaviorMultipleParameters("dress", "shirt");
		Constraint aConstraint102 = null; 
		Constraint dConstraint102 = null; 
		Norm norm102 = new Norm(102, DeonticConcept.PROHIBITION, context102, entity102, action102, aConstraint102, dConstraint102);
		normSet.add(norm102);
		
		
		
		/************************************************************************************/
		//TODO case3
		/************************************************************************************/
		
		Context context110 = new Context("home", ContextType.ORGANIZATION);
		Entity entity110 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action110 = new BehaviorMultipleParameters("dress");
		action110.addElement(SetUtil.COLORFUL,null);
		Constraint aConstraint110 = null; 
		Constraint dConstraint110 = null; 
		Norm norm110 = new Norm(110, DeonticConcept.OBLIGATION, context110, entity110, action110, aConstraint110, dConstraint110);
		normSet.add(norm110);
		
		Context context111 = new Context("home", ContextType.ORGANIZATION);
		Entity entity111 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action111 = new BehaviorMultipleParameters("dress");
		action111.addElement(SetUtil.COLORFUL,null);
		Constraint aConstraint111 = null; 
		Constraint dConstraint111 = null; 
		Norm norm111 = new Norm(111, DeonticConcept.PROHIBITION, context111, entity111, action111, aConstraint111, dConstraint111);
		normSet.add(norm111);
		

		
		/************************************************************************************/
		//TODO case4 		//130 a 160
		/************************************************************************************/
		
		Context context131 = new Context("home", ContextType.ORGANIZATION);
		Entity entity131 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action131 = new BehaviorMultipleParameters("dress");
		action131.addElement("color","white");
		action131.addElement("picture", "smooth");
		action131.addElement("ironingtype", "ironing");
		Constraint aConstraint131 = null; 
		Constraint dConstraint131 = null; 
		Norm norm131 = new Norm(131, DeonticConcept.OBLIGATION, context131, entity131, action131, aConstraint131, dConstraint131);
		normSet.add(norm131);
		
		Context context132 = new Context("home", ContextType.ORGANIZATION);
		Entity entity132 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action132 = new BehaviorMultipleParameters("dress");
		action132.addElement("color","white");
		action132.addElement("picture", "vertical");
		action132.addElement("ironingtype", "ironing");
		Constraint aConstraint132 = null; 
		Constraint dConstraint132 = null; 
		Norm norm132 = new Norm(132, DeonticConcept.PROHIBITION, context132, entity132, action132, aConstraint132, dConstraint132);
		normSet.add(norm132);
		
		/************************************************************************************/
		//TODO case5
		/************************************************************************************/
		
		Context context171 = new Context("home", ContextType.ORGANIZATION);
		Entity entity171 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action171 = new BehaviorMultipleParameters("dress","shirt");
		action171.addElement(SetUtil.COLORFUL, null);
		action171.addElement(SetUtil.IRONING_STATE, null);
		action171.addElement(SetUtil.PICTURED, null);
		Constraint aConstraint171 = null; 
		Constraint dConstraint171 = null; 
		Norm norm171 = new Norm(171, DeonticConcept.OBLIGATION, context171, entity171, action171, aConstraint171, dConstraint171);
		normSet.add(norm171);
		
		Context context172 = new Context("home", ContextType.ORGANIZATION);
		Entity entity172 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action172 = new BehaviorMultipleParameters("dress","shirt");
		action172.addElement(SetUtil.COLORFUL, null);
		action172.addElement(SetUtil.IRONING_STATE, null);
		action172.addElement(SetUtil.PICTURED, null);
		Constraint aConstraint172 = null; 
		Constraint dConstraint172 = null; 
		Norm norm172 = new Norm(172, DeonticConcept.PROHIBITION, context172, entity172, action172, aConstraint172, dConstraint172);
		normSet.add(norm172);
		
		Context context173 = new Context("home", ContextType.ORGANIZATION);
		Entity entity173 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action173 = new BehaviorMultipleParameters("dress","pant");
		action173.addElement(SetUtil.COLORFUL, null);
		action173.addElement(SetUtil.IRONING_STATE, null);
		action173.addElement(SetUtil.PICTURED, null);
		Constraint aConstraint173 = null; 
		Constraint dConstraint173 = null; 
		Norm norm173 = new Norm(173, DeonticConcept.PERMISSION, context173, entity173, action173, aConstraint173, dConstraint173);
		normSet.add(norm173);
		
		
		
		/************************************************************************************/
		//TODO case6
		/************************************************************************************/
		
		Context context201 = new Context("home", ContextType.ORGANIZATION);
		Entity entity201 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action201 = new BehaviorMultipleParameters("dress","shirt");
		action201.addElement("color","white");
		action201.addElement("picture", "smooth");
		action201.addElement("ironingtype", "ironing");
		Constraint aConstraint201 = null; 
		Constraint dConstraint201 = null; 
		Norm norm201 = new Norm(201, DeonticConcept.OBLIGATION, context201, entity201, action201, aConstraint201, dConstraint201);
		normSet.add(norm201);
		
		Context context202 = new Context("home", ContextType.ORGANIZATION);
		Entity entity202 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action202 = new BehaviorMultipleParameters("dress","shirt");
		action202.addElement("color","blue");
		action202.addElement("picture", "vertical");
		action202.addElement("ironingtype", "ironing");
		Constraint aConstraint202 = null; 
		Constraint dConstraint202 = null; 
		Norm norm202 = new Norm(202, DeonticConcept.PROHIBITION, context202, entity202, action202, aConstraint202, dConstraint202);
		normSet.add(norm202);
		
		Context context203 = new Context("home", ContextType.ORGANIZATION);
		Entity entity203 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action203 = new BehaviorMultipleParameters("dress","pant");
		action203.addElement("color","black");
		action203.addElement("picture", "horizontal");
		action203.addElement("ironingtype", "ironing");
		Constraint aConstraint203 = null; 
		Constraint dConstraint203 = null; 
		Norm norm203 = new Norm(203, DeonticConcept.PROHIBITION, context203, entity203, action203, aConstraint203, dConstraint203);
		normSet.add(norm203);
		
		Context context204 = new Context("home", ContextType.ORGANIZATION);
		Entity entity204 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action204 = new BehaviorMultipleParameters("dress","shirt");
		action204.addElement("color", SetUtil.EQUAL);
		action204.addElement("picture", "smooth");
		action204.addElement("ironingtype", "ironing");
		Constraint aConstraint204 = null; 
		Constraint dConstraint204 = null; 
		Norm norm204 = new Norm(204, DeonticConcept.OBLIGATION, context204, entity204, action204, aConstraint204, dConstraint204);
		normSet.add(norm204);
		
		return normSet;
	}
	
	private static DateTime buildDateTime(String data) {
		DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy");
		DateTime d = dtf.parseDateTime(data);
		return d;
	}
}
