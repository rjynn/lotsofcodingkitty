import ca.ualberta.cs.cmput301t03app.models.Answer;
import ca.ualberta.cs.cmput301t03app.models.Comment;
import ca.ualberta.cs.cmput301t03app.views.MainActivity;
import android.test.ActivityInstrumentationTestCase2;

/**
 * This test suite is used to test all the functionalities of the Answer model
 * @author Eric
 *
 */
public class AnswerModelTest extends ActivityInstrumentationTestCase2<MainActivity> {

	public AnswerModelTest() {
		super(MainActivity.class);
	}
	/**
	 * Tests the getter methods of the
	 * Answer Object and that they return the 
	 * proper and correct fields when called
	 */
	public void testAnswerConstructor() {
		Answer a1 = new Answer("a body", "a author","1");
		assertEquals("Answer string doesn't return expected value", "a body", a1.getAnswer());
		assertEquals("Author of answer not equal to expected author",  "a author", a1.getAuthor());
		assertEquals("Answer rating not expected rating", 0, a1.getRating());
		assertTrue("ID is not a string",a1.getId() instanceof String);
	}
	
	/**
	 * Tests the method upRating in the answer Object
	 * That when called, the rating attribute of the comment will be incremented by
	 * one point.
	 */
	public void testIncreaseAnswerScore() {
		Answer a1 = new Answer("a body", "a author","1");
		assertEquals("Rating not initialized properly", 0, a1.getRating());
		a1.upRating();
		assertEquals("Rating not incremented properly", 1, a1.getRating());
	}
	/**
	 * Tests the method upRating works for multiple 
	 * calls.
	 */
	public void testIncreaseAnswerScoreby30() {
		Answer a1 = new Answer("a body", "a author","1");
		assertEquals("Rating not initialized properly", 0, a1.getRating());
		for(int i = 0; i<=30; i++){
		a1.upRating();}
		assertEquals("Rating not incremented properly", 31, a1.getRating());
	}
	/**
	 * Tests that if two comments with same attributes are added as separate objects
	 * Checks if a comment can be added by seeing if the object created is the same object in the comment list of the answer
	 */
	public void testAddComment() {
		Answer a1 = new Answer("a body", "a author","1");
		Comment c1 = new Comment("a comment", "different author");
		a1.addComment(c1);
		assertSame("Comment object returned not equal to object created", c1, a1.getComments().get(0));
		
		Comment c2 = new Comment("a comment", "author");
		a1.addComment(c2);
		assertNotSame("two different objects somehow are the same object", c1, c2);  // Check to ensure objects with same attributes are different instances
		assertNotSame("c1 is c2", a1.getComments().get(0), a1.getComments().get(1)); // Same check for objects in the list
		
		a1.addComment(c2);
		assertSame("index 1 and 2 don't have the same comment object", a1.getComments().get(1), a1.getComments().get(2));
	}
	/**
	 * Tests that adding comments will change the comment count attribute
	 * in the correct answer object
	 */
	public void testGetCommentCount(){
		Answer a1 = new Answer("a body", "an author", "1");
		Comment c1 = new Comment("a comment", "Some author");
		assertTrue("Answers contain comments",a1.countAnswerComments()==0);
		a1.addComment(c1);
		assertTrue("There isn't one comment in the answer",a1.countAnswerComments()==1);
	}
	
}
