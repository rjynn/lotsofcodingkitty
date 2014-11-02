package ca.ualberta.cs.cmput301t03app;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;

public class UserListsActivity extends Activity {
	
	private int userListMode;
	private TextView user_list_title;
	private PostController pc = new PostController(this);
	private MainListAdapter mla;
	private ArrayList<Question> userQuestionList;
	private ListView userListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_lists);
		
		Bundle extras = getIntent().getExtras();
		userListMode = extras.getInt("userListMode");
		user_list_title = (TextView) findViewById(R.id.user_list_title);
		userListView = (ListView) findViewById(R.id.user_question_list);
		
		switch (userListMode) {
		case 0:
			user_list_title.setText("F A V O R I T E S");
			userQuestionList = pc.getFavoriteQuestions();
			break;
		case 1:
			user_list_title.setText("C A C H E D");
			userQuestionList = pc.getReadQuestions();
			break;
		case 2:
			user_list_title.setText("T O  R E A D");
			userQuestionList = pc.getToReadQuestions();
			break;
		case 3:
			user_list_title.setText("M Y  Q U E S T I O N S");
			userQuestionList = pc.getUserPostedQuestions();
			break;
		default:
			user_list_title.setText("F A V O R I T E S");
			userQuestionList = pc.getFavoriteQuestions();
			break;
		}
		
		Log.d("lotso", "question list:"+userQuestionList);
		mla = new MainListAdapter(this, R.layout.activity_main_question_entity, userQuestionList);
		userListView.setAdapter(mla);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_lists, menu);
		return true;
	}

}
