package app.test.com.testapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
    private TextView helloWorldTextView;
    private Button changedTextButton;
    private Button startSecondActivity;

    private int clicks = 0;
    private static final int REQUEST_CODE = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helloWorldTextView = (TextView) findViewById(R.id.helloTextView);
        changedTextButton = (Button) findViewById(R.id.changedTextButton);
        startSecondActivity = (Button) findViewById(R.id.startActivity);

        changedTextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(clicks % 2 == 0)
                    helloWorldTextView.setText("changed");
                else
                    helloWorldTextView.setText("unchanged");
                clicks++;
                Toast.makeText(getApplicationContext(),
                        "Button is clicked" + clicks, Toast.LENGTH_SHORT).show();
            }
        });

        startSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ActivityTwo.class);
                i.putExtra("Value1", "This value one for ActivityTwo ");
                i.putExtra("Value2", "This value two ActivityTwo");
                // set the request code to any code you like,
                // you can identify the callback via this code
                startActivityForResult(i, REQUEST_CODE);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE){
            if(data.hasExtra("returnKey1")){
                Toast.makeText(getApplicationContext(),data.getExtras().getString("returnKey1"),Toast.LENGTH_LONG).show();
            }
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
