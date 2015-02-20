package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import src.BehaviorMultipleParameters;
import src.ConflictCheckerActionApproach1;
import src.ConflictCheckerActionApproach2;
import src.ConflictCheckerActionApproach3;
import src.ConflictCheckerActionApproach4;
import src.Constraint;
import src.ConstraintDate;
import src.ConstraintType;
import src.Context;
import src.ContextType;
import src.DeonticConcept;
import src.Entity;
import src.EntityType;
import src.Norm;
import util.Chronometer;
import util.SetUtil;

public class TestAllConflicts {

	public static void main(String[] args) {
		List<Norm> norms = buildSomeNorms();
		Map<String,List<Norm>> map = classifyNorms(norms);
		
		Chronometer.start();
		
		List<Norm> case1 = map.get("case1");
		System.out.println("The following norms are in conflict in case 1:");
		if (case1 != null && !case1.isEmpty()) {
			ConflictCheckerActionApproach1 approach1 = new ConflictCheckerActionApproach1();
			approach1.verifyConflicts(case1);
		}
		System.out.println("\n");
		
		List<Norm> case2 = map.get("case2");
		System.out.println("The following norms are in conflict in case 2:");
		if (case2 != null && !case2.isEmpty()) {
			ConflictCheckerActionApproach2 approach2 = new ConflictCheckerActionApproach2();
			approach2.verifyConflicts(case2);
		}
		System.out.println("\n");
		
		List<Norm> case3 = map.get("case3");
		System.out.println("The following norms are in conflict in case 3:");
		if (case3 != null && !case3.isEmpty()) {
			ConflictCheckerActionApproach3 approach3 = new ConflictCheckerActionApproach3();
			approach3.verifyConflicts(case3);
		}
		System.out.println("\n");
		
		List<Norm> case4 = map.get("case4");
		System.out.println("The following norms are in conflict in case 4:");
		if (case4 != null && !case4.isEmpty()) {
			ConflictCheckerActionApproach4 approach4 = new ConflictCheckerActionApproach4();
			approach4.verifyConflicts(case4);
		}
		System.out.println("\n");
		
		Chronometer.stop();
		
		long milliseconds = Chronometer.elapsedTime();
		long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds);
		
		int sumCase1 = (case1 != null) ? case1.size() : 0; 
		int sumCase2 = (case2 != null) ? case2.size() : 0; 
		int sumCase3 = (case3 != null) ? case3.size() : 0;
		int sumCase4 = (case4 != null) ? case4.size() : 0;
		int sumTotal = sumCase1 + sumCase2 + sumCase3 + sumCase4;
		
		System.out.println("****************************************************************************");
		System.out.println("This program uses " + sumTotal + " inputs (total)");
		System.out.println("This program uses " + sumCase1 + " n inputs for case 1");
		System.out.println("This program uses " + sumCase2 + " n inputs for case 2");
		System.out.println("This program uses " + sumCase3 + " n inputs for case 3");
		System.out.println("This program uses " + sumCase4 + " n inputs for case 4\n");
		System.out.println("The number of possibilities tested in case 4 was: "+ ConflictCheckerActionApproach4.counter);
		System.out.println("The total time for the execution of algorithms and printing is: " + milliseconds + " ms!!"); 
		System.out.println("The total time for the execution of algorithms and printing is: " + seconds + " s!!"); 
		System.out.println("The total time for the execution of algorithms and printing is: " + minutes + " min!!"); 
		System.out.println("****************************************************************************");
	}
	
	private static Map<String,List<Norm>> classifyNorms(List<Norm> norms) {
		Map<String,List<Norm>> map = new HashMap<String, List<Norm>>();
		List<Norm> case1 = new ArrayList<Norm>();
		List<Norm> case2 = new ArrayList<Norm>();
		List<Norm> case3 = new ArrayList<Norm>();
		List<Norm> case4 = new ArrayList<Norm>();

		for (Norm norm : norms) {
			BehaviorMultipleParameters b = norm.getBehavior();
			if (b.getObject() != null) {
				case4.add(norm);
			} else if (b.getMap().isEmpty()) {
				case1.add(norm);
			} else {
				Map<String, Set<String>> p = b.getMap();
				for (Map.Entry<String,Set<String>> entry : p.entrySet()) {
					String key = entry.getKey();
			  		Set<String> x = norm.getBehavior().getElements(key);
			  		if(x == null || SetUtil.isEmpty(x) || x.contains(null)) {
			  			case2.add(norm);
			  		} else {
			  			case3.add(norm);
			  		}
			  		break;
				}
			}
		}
		map.put("case1", case1);
		map.put("case2", case2);
		map.put("case3", case3);
		map.put("case4", case4);
		
		return map;
	}
	
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
		BehaviorMultipleParameters action101 = new BehaviorMultipleParameters("attend");
		action101.addElement("computer_science",null);
		action101.addElement("math",null);
		action101.addElement("physics", null);
		Constraint aConstraint101 = null; 
		Constraint dConstraint101 = null; 
		Norm norm101 = new Norm(101, DeonticConcept.PERMISSION, context101, entity101, action101, aConstraint101, dConstraint101);
		normSet.add(norm101);
		
		Context context102 = new Context("home", ContextType.ORGANIZATION);
		Entity entity102 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action102 = new BehaviorMultipleParameters("attend");
		action102.addElement("medicine",null);
		action102.addElement("nursing",null);
		action102.addElement("odontology", null);
		Constraint aConstraint102 = null; 
		Constraint dConstraint102 = null; 
		Norm norm102 = new Norm(102, DeonticConcept.PROHIBITION, context102, entity102, action102, aConstraint102, dConstraint102);
		normSet.add(norm102);
		
		Context context103 = new Context("home", ContextType.ORGANIZATION);
		Entity entity103 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action103 = new BehaviorMultipleParameters("attend");
		action103.addElement("computer_science", null);
		action103.addElement("math", null);
		action103.addElement("physics", null);
		Constraint aConstraint103 = null; 
		Constraint dConstraint103 = null; 
		Norm norm103 = new Norm(103, DeonticConcept.OBLIGATION, context103, entity103, action103, aConstraint103, dConstraint103);
		normSet.add(norm103);
				
		Context context104 = new Context("home", ContextType.ORGANIZATION);
		Entity entity104 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action104 = new BehaviorMultipleParameters("attend");
		action104.addElement("medicine",null);
		action104.addElement("physics",null);
		action104.addElement("odontolty", null);
		Constraint aConstraint104 = null; 
		Constraint dConstraint104 = null; 
		Norm norm104 = new Norm(104, DeonticConcept.PROHIBITION, context104, entity104, action104, aConstraint104, dConstraint104);
		normSet.add(norm104);
		
		Context context105 = new Context("home", ContextType.ORGANIZATION);
		Entity entity105 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action105 = new BehaviorMultipleParameters("eat");
		action105.addElement("potato",null);
		action105.addElement("biscuit",null);
		action105.addElement("rice", null);
		action105.addElement("juice", null);
		Constraint aConstraint105 = null; 
		Constraint dConstraint105 = null; 
		Norm norm105 = new Norm(105, DeonticConcept.PERMISSION, context105, entity105, action105, aConstraint105, dConstraint105);
		normSet.add(norm105);
		
		Context context106 = new Context("home", ContextType.ORGANIZATION);
		Entity entity106 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action106 = new BehaviorMultipleParameters("eat");
		action106.addElement("avocado",null);
		action106.addElement("crackling",null);
		action106.addElement("olive_oil",null);
		action106.addElement("soda", null);
		Constraint aConstraint106 = null; 
		Constraint dConstraint106 = null; 
		Norm norm106 = new Norm(106, DeonticConcept.PROHIBITION, context106, entity106, action106, aConstraint106, dConstraint106);
		normSet.add(norm106);
		
		Context context107 = new Context("home", ContextType.ORGANIZATION);
		Entity entity107 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action107 = new BehaviorMultipleParameters("eat");
		action107.addElement("potato", null);
		action107.addElement("avocado", null);
		action107.addElement("chicken", null);
		Constraint aConstraint107 = null; 
		Constraint dConstraint107 = null; 
		Norm norm107 = new Norm(107, DeonticConcept.OBLIGATION, context107, entity107, action107, aConstraint107, dConstraint107);
		normSet.add(norm107);
				
		Context context108 = new Context("home", ContextType.ORGANIZATION);
		Entity entity108 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action108 = new BehaviorMultipleParameters("eat");
		action108.addElement("biscuit",null);
		action108.addElement("crackling",null);
		action108.addElement("soy", null);
		Constraint aConstraint108 = null; 
		Constraint dConstraint108 = null; 
		Norm norm108 = new Norm(108, DeonticConcept.PROHIBITION, context108, entity108, action108, aConstraint108, dConstraint108);
		normSet.add(norm108);
		
		
		/************************************************************************************/
		//TODO case3
		/************************************************************************************/
		Context context110 = new Context("home", ContextType.ORGANIZATION);
		Entity entity110 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action110 = new BehaviorMultipleParameters("dress");
		action110.addElement("color","white");
		action110.addElement("ironingtype","ironing");
		action110.addElement("picture", "smooth");
		Constraint aConstraint110 = null; 
		Constraint dConstraint110 = null; 
		Norm norm110 = new Norm(110, DeonticConcept.OBLIGATION, context110, entity110, action110, aConstraint110, dConstraint110);
		normSet.add(norm110);
		
		Context context111 = new Context("home", ContextType.ORGANIZATION);
		Entity entity111 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action111 = new BehaviorMultipleParameters("dress");
		action111.addElement("color","black");
		action111.addElement("ironingtype","ironing");
		action111.addElement("picture", "smooth");
		Constraint aConstraint111 = null; 
		Constraint dConstraint111 = null; 
		Norm norm111 = new Norm(111, DeonticConcept.PERMISSION, context111, entity111, action111, aConstraint111, dConstraint111);
		normSet.add(norm111);
		
		Context context112 = new Context("home", ContextType.ORGANIZATION);
		Entity entity112 = new Entity ("person", EntityType.ROLE);
		BehaviorMultipleParameters action112 = new BehaviorMultipleParameters("dress");
		action112.addElement("color","blue");
		action112.addElement("ironingtype","ironing");
		action112.addElement("picture", "smooth");
		Constraint aConstraint112 = null; 
		Constraint dConstraint112 = null; 
		Norm norm112 = new Norm(112, DeonticConcept.PROHIBITION, context112, entity112, action112, aConstraint112, dConstraint112);
		normSet.add(norm112);
		
		
		
		/************************************************************************************/
		//TODO case4
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
		action204.addElement("color", ConflictCheckerActionApproach4.EQUAL);
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
	
	private static DateTime buildDateTime(String data) {
		DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy");
		DateTime d = dtf.parseDateTime(data);
		return d;
	}
}
