package com.zhy.zhylib.cache;

/**
 * Created by zhangyong
 */
public class NoCache implements Cache {
	@Override
	public void clear() {
	}

	@Override
	public Entry get(String key) {
		return null;
	}

	@Override
	public void put(String key, Entry entry) {
	}

	@Override
	public void invalidate(String key) {
	}

	@Override
	public void remove(String key) {
	}

	@Override
	public void initialize() {
	}

	@Override
	public long getCacheSize() {
		return 0;
	}

	@Override
	public void releaseCache() {

	}
}
