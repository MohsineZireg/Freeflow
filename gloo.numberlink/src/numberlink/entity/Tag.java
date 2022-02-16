package numberlink.entity;


public class Tag {

	private static int count = 0;
	private int id = 0;

	public Tag() {
		super();
		this.id = count++ % Grid.getNbTags()  ;
	}

	public Path createNewPath() {
		Path pt = new Path(this);
		return pt;
	}

	public int getId() {
		return id;
	}

}
