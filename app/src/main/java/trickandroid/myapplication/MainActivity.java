package trickandroid.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etUserName, etPassword;
    Button btnLogin, btnClear;

    String emptyString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntializeControls();
        RegisterListeners();
    }

    public  void IntializeControls() {
        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnClear = (Button) findViewById(R.id.btnClear);
    }

    public  void RegisterListeners(){
        btnClear.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                String userName = etUserName.getText().toString();
                String password = etPassword.getText().toString();
                break;
            case R.id.btnClear:
                etUserName.setText(emptyString);
                etPassword.setText(emptyString);
                break;
        }
    }

    public  void ShowMessage(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }
}
