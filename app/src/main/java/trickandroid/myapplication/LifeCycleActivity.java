package trickandroid.myapplication;

import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LifeCycleActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener, SecondFragment.OnFragmentInteractionListener {

    Button btnClick,btnClick2;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();

        long pauseTime = System.currentTimeMillis();
        SharedPreferences timeOut = getSharedPreferences("TimeOut", MODE_PRIVATE);
        SharedPreferences.Editor edit = timeOut.edit();
        edit.putLong("Start",pauseTime);
        edit.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();

        long resumeTime = System.currentTimeMillis();
        SharedPreferences timeOut = getSharedPreferences("TimeOut", MODE_PRIVATE);
        long pause = timeOut.getLong("Start",0);

        if(pause != 0){
            long diff = resumeTime - pause;
            if(diff >= 100000){
                Toast.makeText(getApplicationContext(),"Session Time Out",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);
        Log.d("LifeCycle","On Create");
        btnClick = (Button) findViewById(R.id.btnClick);
        btnClick2 = (Button) findViewById(R.id.btnClick2);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent obj=new Intent(getApplicationContext(),MainActivity.class);
               // startActivity(obj);
                android.app.Fragment onjF = null;
                onjF = new BlankFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment,onjF).commit();
            }
        });

        btnClick2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.Fragment onjF = null;
                onjF = new SecondFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.popBackStack();
                fragmentManager.beginTransaction().replace(R.id.fragment,onjF).commit();
            }
        });


    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Toast.makeText(getApplicationContext(),"Sample Interaction",Toast.LENGTH_LONG).show();
    }
}
