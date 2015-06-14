package com.test.uiwidgettest;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;

    private Button editTextButton;

    private ImageView imageView;

    private int imageViewResouceId;

    private Button imageViewButton;

    private ProgressBar progressBar;

    private Button progressBarToggleButton;

    private Button progressBarProgressButton;

    private Button alertDialogButton;

    private Button progressDialogButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        button = (Button) findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 在此处添加逻辑
//            }
//        });
        editText = (EditText) findViewById(R.id.edit_text);
        editTextButton = (Button) findViewById(R.id.eidt_text_button);
        editTextButton.setOnClickListener(this);

        imageView = (ImageView) findViewById(R.id.image_view);
        imageViewButton = (Button) findViewById(R.id.image_view_button);
        imageViewButton.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBarToggleButton = (Button) findViewById(R.id.progress_bar_toggle_button);
        progressBarToggleButton.setOnClickListener(this);
        progressBarProgressButton = (Button) findViewById(R.id.progress_bar_progress_button);
        progressBarProgressButton.setOnClickListener(this);

        alertDialogButton = (Button) findViewById(R.id.alert_dialog_button);
        alertDialogButton.setOnClickListener(this);

        progressDialogButton = (Button) findViewById(R.id.progress_dialog_button);
        progressDialogButton.setOnClickListener(this);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.eidt_text_button:
                String inputText = editText.getText().toString();
                Toast.makeText(MainActivity.this, inputText, Toast.LENGTH_SHORT).show();
                break;
            case R.id.image_view_button:
                if (imageViewResouceId == R.drawable.jelly_bean) {
                    imageViewResouceId = R.drawable.ic_launcher;
                    imageView.setImageResource(R.drawable.ic_launcher);
                } else {
                    imageViewResouceId = R.drawable.jelly_bean;
                    imageView.setImageResource(R.drawable.jelly_bean);
                }
                break;
            case R.id.progress_bar_toggle_button:
                if (progressBar.getVisibility() == View.GONE) {
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    progressBar.setVisibility(View.GONE);
                }
                break;
            case R.id.progress_bar_progress_button:
                progressBar.setProgress(progressBar.getProgress() + 10);
                break;
            case R.id.alert_dialog_button:
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("This is Dialog");
                dialog.setMessage("Something important.");
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, which + "", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, which + "", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
                break;
            case R.id.progress_dialog_button:
                ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("This is ProgressDialog");
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(true);
                progressDialog.show();
                break;
            default:
                break;
        }
    }
}
