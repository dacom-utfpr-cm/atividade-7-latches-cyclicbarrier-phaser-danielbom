package stencil1D;

import java.util.Iterator;

class Interval {
	public final int begin;
	public final int end;
	
	public Interval(int b, int e) {
		begin = b;
		end = e;
	}
	
	@Override
	public String toString() {
		return "[" + begin + "," + end + ")";
	}
}

public class Partition implements Iterable<Interval> {
	private int length;
	private int interval;
	private int last;
	
	public Partition(int length, int parts) {
		if (length < parts)
			throw new IllegalArgumentException("Length must be 'greater' then 'parts'. length(" + length + ") parts(" + parts + ")");
		this.length = length;
		this.interval = length / parts;
		this.last = parts - 1;
	}
	
	public int getFirstIndex(int i) {
		return i * interval;
	}

	public int getLastIndex(int i) {
		if (i > last)
			throw new IllegalArgumentException("Value 'i' must be in range [0," + last + "] i(" + i + ")");
		return i == last ? length : (i + 1) * interval;
	}

	@Override
	public Iterator<Interval> iterator() {
		return new Iterator<Interval>() {
			int i = 0;

			@Override
			public boolean hasNext() {
				return i <= last;
			}

			@Override
			public Interval next() {
				int k = i++;
				return new Interval(getFirstIndex(k), getLastIndex(k));
			}
		};
	}
}
