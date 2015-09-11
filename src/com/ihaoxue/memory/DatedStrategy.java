package com.ihaoxue.memory;
public class DatedStrategy {
	private int type;
	private boolean trigger;
	private long interval;

	public static final int STRT_CLOSE = 0;
	public static final int STRT_OPEN_TRIGGER = 1;
	public static final int STRT_OPEN_INTERVAL = 2;
	public static final int STRT_OPEN_BLEND = 3;

	public DatedStrategy(boolean trigger) {
		setStrategyType(STRT_OPEN_TRIGGER);
		setTrigger(trigger);
	}

	public DatedStrategy(long interval) {
		setStrategyType(STRT_OPEN_INTERVAL);
		setInterval(interval);
	}

	public DatedStrategy(boolean trigger, long interval) {
		setStrategyType(STRT_OPEN_BLEND);
		setTrigger(trigger);
		setInterval(interval);
	}

	public int getStrategyType() {
		return type;
	}

	public void setStrategyType(int strategyType) {
		this.type = strategyType;
	}

	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}

	public boolean getTrigger() {
		return trigger;
	}

	public void setTrigger(boolean trigger) {
		this.trigger = trigger;
	}
}
