package c.sakshi.lab5;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity3 extends AppCompatActivity implements View.OnClickListener {

    private Button saveButton;
    String content;
    int noteid = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3_main);

        EditText writeNoteField = (EditText) findViewById(R.id.writeNote);
        noteid = getIntent().getIntExtra("noteid", -1);

        if (noteid != -1) {
            Note note = MainActivity2.notes.get(noteid);
            String noteContent = note.getContent();
            writeNoteField.setText(noteContent);
        }

        saveButton = (Button) findViewById(R.id.save);
        saveButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        EditText contentEditText = (EditText) findViewById(R.id.writeNote);
        content = contentEditText.getText().toString();

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE, null);

        DBHelper dphelper = new DBHelper(sqLiteDatabase);

        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if (noteid == -1) {
            title = "NOTE_" + (MainActivity2.notes.size() + 1);
            dphelper.savedNotes(username, title, content, date);
        } else {
            title = "NOTE_" + (noteid + 1);
            dphelper.updateNote(title, date, content, username);
        }

        goToActivity2();
    }

    public void goToActivity2() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

}
