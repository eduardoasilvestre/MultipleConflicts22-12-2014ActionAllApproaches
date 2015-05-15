package util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class SetUtil {

	
	// http://sysgears.com/articles/immutable-collections-java-0/
	
	/************************************************************************************/
	//EXAMPLE 1
	public final static Set<String> GARMENT_SET_ELEMENTS;
	static {
		Set<String> s = new TreeSet<String>();
		s.add("pant");
		s.add("shirt");
		GARMENT_SET_ELEMENTS = Collections.unmodifiableSet(s);

	}
	public final static Set<String> COLOR_ELEMENTS;
	static {
		Set<String> s = new TreeSet<String>();
		s.add("white");
		s.add("black");
		s.add("blue");
		s.add("red");
		s.add("lilac");
		COLOR_ELEMENTS = Collections.unmodifiableSet(s);
	}
	public final static Set<String> IRONING_ELEMENTS;
	static {
		Set<String> s = new TreeSet<String>();
		s.add("ironing");
		s.add("burned");
		s.add("crumpled");
		IRONING_ELEMENTS = Collections.unmodifiableSet(s);
	}
	public final static Set<String> PICTURE_ELEMENTS;
	static {
		Set<String> s = new TreeSet<String>();
		s.add("horizontal");
		s.add("vertical");
		s.add("smooth");
		s.add("gust");
		PICTURE_ELEMENTS = Collections.unmodifiableSet(s);
	}
	/************************************************************************************/	
	//EXAMPLE 2
	public final static Set<String> G_SET_ELEMENTS;
	static {
		Set<String> s = new TreeSet<String>();
		s.add("whistle");
		s.add("scolding");
		s.add("shoes");
		s.add("stocking");
		G_SET_ELEMENTS = Collections.unmodifiableSet(s);

	}
	public final static Set<String> C_ELEMENTS;
	static {
		Set<String> s = new TreeSet<String>();
		s.add("white");
		s.add("black");
		s.add("blue");
		s.add("red");
		s.add("lilac");
		C_ELEMENTS = Collections.unmodifiableSet(s);
	}
	public final static Set<String> BODY_ELEMENTS;
	static {
		Set<String> s = new TreeSet<String>();
		s.add("mouth");
		s.add("pocket");
		s.add("foot");
		BODY_ELEMENTS = Collections.unmodifiableSet(s);
	}
	
	/************************************************************************************/
	//EXEMPLE uncategorized
	public final static Set<String> WITH_BALLS;
	static {
		Set<String> s = new TreeSet<String>();
		s.add("small_balls");
		s.add("large_balls");
		s.add("one_ball");
		WITH_BALLS = Collections.unmodifiableSet(s);
	}
	
	/************************************************************************************/

	public static Map<String,Set<String>> getMapOfParameters() {
		Map<String,Set<String>> mapOfParameters = new HashMap<String, Set<String>>();
		mapOfParameters.put("garment", SetUtil.GARMENT_SET_ELEMENTS);
		mapOfParameters.put("color", SetUtil.COLOR_ELEMENTS);
		mapOfParameters.put("ironingtype", SetUtil.IRONING_ELEMENTS);
		mapOfParameters.put("picture", SetUtil.PICTURE_ELEMENTS);
		
		mapOfParameters.put("g", SetUtil.G_SET_ELEMENTS);
		mapOfParameters.put("c", SetUtil.C_ELEMENTS);
		mapOfParameters.put("bodypart", SetUtil.BODY_ELEMENTS);
		
		return mapOfParameters;
	}
	
	/************************************************************************************/
	public final static String CLOTHES = "clothes";
	public final static String COLORFUL = "colorful";
	public final static String IRONING_STATE = "ironing_state";
	public final static String PICTURED = "pictured";
	public final static String WITHBALLS = "withballs";
	
	/************************************************************************************/
	
	public final static Map<String, Set<String>> MAP_SETS;
	static {
		Map<String, Set<String>> ms = new HashMap<String, Set<String>>();
		ms.put(CLOTHES, GARMENT_SET_ELEMENTS);
		ms.put(COLORFUL, COLOR_ELEMENTS);
		ms.put(IRONING_STATE, IRONING_ELEMENTS);
		ms.put(PICTURED, PICTURE_ELEMENTS);
		ms.put(WITHBALLS, WITH_BALLS);
		MAP_SETS = Collections.unmodifiableMap(ms);
	}
	
	public static String getParamValue(String value) {
		if (CLOTHES.equals(value)) {
			return "garment";				
		} else if (COLORFUL.equals(value)) {
			return "color";
		} else if (IRONING_STATE.equals(value)) {
			return "ironingtype";
		} else if (PICTURED.equals(value)) {
			return "picture";
		} else if (WITHBALLS.equals(value)) {
			return "withball";
		}
		return null;
	}

	
	/************************************************************************************/
	public final static String EQUAL = "A";
	public final static String DIFF = "!A";
	/************************************************************************************/
	
	public static <T> Set<T> union(Set<T> setA, Set<T> setB) {
		Set<T> tmp = new TreeSet<T>(setA);
		tmp.addAll(setB);
		return tmp;
	}

	public static <T> Set<T> intersection(Set<T> setA, Set<T> setB) {
		Set<T> tmp = new TreeSet<T>();
		for (T x : setA)
			if (setB.contains(x))
				tmp.add(x);
		return tmp;
	}
	//new
	public static <T> Set<T> difference(Set<T> setA, T setB) {
		Set<T> tmpNew = new TreeSet<T>();
		tmpNew.add(setB);
		
		Set<T> tmp = new TreeSet<T>(setA);
		tmp.removeAll(tmpNew);
		return tmp;
	}
	
	public static <T> Set<T> difference(Set<T> setA, Set<T> setB) {
		Set<T> tmp = new TreeSet<T>(setA);
		tmp.removeAll(setB);
		return tmp;
	}

	public static <T> Set<T> symDifference(Set<T> setA, Set<T> setB) {
		Set<T> tmpA;
		Set<T> tmpB;

		tmpA = union(setA, setB);
		tmpB = intersection(setA, setB);
		return difference(tmpA, tmpB);
	}

	public static <T> boolean isSubset(Set<T> setA, Set<T> setB) {
		return setB.containsAll(setA);
	}

	public static <T> boolean isSuperset(Set<T> setA, Set<T> setB) {
		return setA.containsAll(setB);
	}

	//new
	public static <T> boolean hasOneElement(Set<T> setA) {
		return setA.size() == 1;
	}
	//new
	public static <T> boolean isEmpty(Set<T> setA) {
		return setA.isEmpty();
	}
	//new
	public static <T> Set<T> cleanSet(Set<T> setA) {
		setA.clear();
		return setA;
	}
	//new
	public static boolean isFirstInter(String parameter, Map<String, Integer> map) {
		Integer value = map.get(parameter);
		if (value.intValue() == 0) {
			return true;
		}
		return false;
	}
	//new
	public static  Map<String, Integer> addValue(String parameter, Map<String, Integer> map) {
		Integer value = map.get(parameter);
		map.put(parameter, ++value);
		return map;
	}
	
	public static boolean containsEqualDiff(Set<String> p) {
		return (p.contains("A") || p.contains("!A"));
	} 
}
