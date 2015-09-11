package com.ihaoxue.memory;

import java.util.Date;
import java.util.HashMap;

public class DatedManagement {

	private static HashMap<String, DatedStrategy> arrStrategy = new HashMap<String, DatedStrategy>();

	public static boolean isDated(String name, long timestamp) {
		if (arrStrategy == null || !Utility.isAvailable(name)) {
			return true;
		}

		DatedStrategy strt = arrStrategy.get(name);
		if (strt == null)
			return true;

		switch (strt.getStrategyType()) {
		case DatedStrategy.STRT_CLOSE:
			return true;
		case DatedStrategy.STRT_OPEN_TRIGGER:
			return strt.getTrigger();
		case DatedStrategy.STRT_OPEN_INTERVAL:
			return (new Date().getTime() - timestamp) >= strt.getInterval();
		case DatedStrategy.STRT_OPEN_BLEND:
			if (strt.getTrigger())
				return true;
			else
				return (new Date().getTime() - timestamp) >= strt.getInterval();
		}

		return true;
	}

	public static boolean hasStrategy(String name) {
		return (arrStrategy != null && arrStrategy.containsKey(name));
	}


	public static void applyStrategy(String name) {
		if (arrStrategy == null) {
			arrStrategy = new HashMap<String, DatedStrategy>();
		}

		if (Utility.isAvailable(name))
			arrStrategy.put(name, new DatedStrategy(true));
	}


	public static void applyStrategy(String name, long interval) {
		if (arrStrategy == null) {
			arrStrategy = new HashMap<String, DatedStrategy>();
		}

		if (Utility.isAvailable(name))
			arrStrategy.put(name, new DatedStrategy(interval));
	}


	public static void applyStrategy(String name, boolean trigger, long interval) {
		if (arrStrategy == null) {
			arrStrategy = new HashMap<String, DatedStrategy>();
		}

		if (Utility.isAvailable(name))
			arrStrategy.put(name, new DatedStrategy(true, interval));
	}


	public static void onTrigger(String name) {
		if (arrStrategy == null || !Utility.isAvailable(name))
			return;

		if (arrStrategy.containsKey(name)) {
			if (arrStrategy.get(name) != null)
				arrStrategy.get(name).setTrigger(true);
		}
	}


	public static void onReset(String name) {
		if (arrStrategy == null || !Utility.isAvailable(name))
			return;

		if (arrStrategy.containsKey(name)) {
			if (arrStrategy.get(name) != null)
				arrStrategy.get(name).setTrigger(false);
		}
	}
}
