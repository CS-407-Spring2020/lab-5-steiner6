package c.sakshi.lab5;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);

        if (!sharedPreferences.getString("username", "").equals("")) {
            String str = sharedPreferences.getString("username", "");
            goToActivity2(str);
        } else {
            setContentView(R.layout.activity_main);
            login = (Button) findViewById(R.id.loginbutton);
            login.setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        EditText usernameTextField = (EditText) findViewById(R.id.username);
        String str = usernameTextField.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", str).apply();
        goToActivity2(str);

    }

    public void goToActivity2(String str) {
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("message", str);
        startActivity(intent);
    }

}
