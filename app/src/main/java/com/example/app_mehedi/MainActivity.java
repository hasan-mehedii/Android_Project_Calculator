package com.example.app_mehedi;

import android.os.Bundle;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.app_mehedi.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView solution_textview, result_textview;
    MaterialButton button1, button2, button3, button4, button5,
            button6, button7, button8, button9, button0,
            button_plus, button_minus, button_multiply, button_divide, button_equal,
            button_c, button_point, button_del, button_open_bracket, button_close_bracket;

    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result_textview = findViewById(R.id.result_textview);
        solution_textview = findViewById(R.id.solution_textview);

        assignID(button_c, R.id.button_c);
        assignID(button_point, R.id.button_point);
        assignID(button_del, R.id.button_persent);
        assignID(button_open_bracket, R.id.button_open_bracket);
        assignID(button_close_bracket, R.id.button_clsoe_bracket);
        assignID(button1, R.id.button_1);
        assignID(button2, R.id.button_2);
        assignID(button3, R.id.button_3);
        assignID(button4, R.id.button_4);
        assignID(button5, R.id.button_5);
        assignID(button6, R.id.button_6);
        assignID(button7, R.id.button_7);
        assignID(button8, R.id.button_8);
        assignID(button9, R.id.button_9);
        assignID(button0, R.id.button_0);
        assignID(button_equal, R.id.button_equal);
        assignID(button_plus, R.id.button_plus);
        assignID(button_minus, R.id.button_minus);
        assignID(button_multiply, R.id.button_multiply);
        assignID(button_divide, R.id.button_divide);
    }

    void assignID(MaterialButton button, int buttonID) {
        button = findViewById(buttonID);
        button.setOnClickListener(this);
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

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onClick(View v) {
        MaterialButton button = (MaterialButton) v;
        String buttonText = button.getText().toString();
        //solution_textview.setText(buttonText);
        String datatoCalculate = solution_textview.getText().toString();

        if(buttonText.equals("C")){
            solution_textview.setText("");
            result_textview.setText("0");
            return;
        }

        if(buttonText.equals("D")){
            if(datatoCalculate.length() > 0){
                datatoCalculate = datatoCalculate.substring(0, datatoCalculate.length() - 1);
            }
            solution_textview.setText(datatoCalculate);
            return;
        }

        if(buttonText.equals("=")){
            solution_textview.setText(result_textview.getText());
            return;
        }else{
            datatoCalculate = datatoCalculate + buttonText;
        }
        solution_textview.setText(datatoCalculate);

        String finalResult = calculateResult(datatoCalculate);
        if(!finalResult.equals("Error")){
            result_textview.setText(finalResult);
        }
    }

    String calculateResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String final_result = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            return final_result;
        } catch (Exception e) {
            return "Error";
        }
    }
}

