package jp.co.sparkworks.testhelper.datastruct;

public enum ColumnType {
	DIGIT(1), STRING(2), DATETIME(3);

	private final int id;

	private ColumnType(final int id) {
		this.id = id;
	}

	public static ColumnType valueOf(int id) {

		for (ColumnType num : values()) {
			if (num.getId() == id) {
				return num;
			}
		}
		throw new IllegalArgumentException("no such enum object for the id: " + id);
	}

	public int getId() {
		return id;
	}
}
