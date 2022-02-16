package numberlink.entity;

public class End {

	private Tag tag;

	public End(Tag tag) {
		super();
		this.tag = tag;
	}

	public Path createNewPath() {
		return tag.createNewPath();
	}

	public boolean sameTag(Tag tag) {
		return this.tag.equals(tag);
	}

}
