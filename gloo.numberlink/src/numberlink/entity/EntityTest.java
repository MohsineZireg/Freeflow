package numberlink.entity;

import static org.junit.Assert.*;


import org.junit.Test;

public class EntityTest {

	@Test
	public void testGrid() {
		assertNotNull(new Grid(5, 5, 5, new int[][] { { 0, 0 }, { 1, 4 }, { 1, 2 }, { 0, 2 }, { 0, 4 } }, new int[][] { { 4, 1 }, { 4, 3 }, { 4, 2 }, { 3, 1 }, { 3, 3 } })
		);
	}
	
	@Test
	public void testCell() {
		assertNotNull(new Cell(0,0, false, null));
	}
	
	@Test
	public void testPath() {
		assertNotNull(new Path(new Tag()));
	}
	
	@Test
	public void testEnd() {
		assertNotNull(new End(new Tag()));
	}
	
	@Test
	public void testTag() {
		assertNotNull(new Tag());
	}

}


