package com.test.persistencebestpractice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.test.broadcastbestpractice.R;

/**
 * Created by Ivan on 2015/6/13.
 */
public class LoginActivity extends BaseActivity {
    private EditText accountEdit;

    private EditText passwordEdit;

    private CheckBox isRememberPass;

    private Button login;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        isRememberPass = (CheckBox) findViewById(R.id.remember_pass);
        login = (Button) findViewById(R.id.login);

        if (sharedPreferences.getBoolean("isRememberPass", false)) {
            accountEdit.setText(sharedPreferences.getString("account", ""));
            passwordEdit.setText(sharedPreferences.getString("password", ""));
            isRememberPass.setChecked(true);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                if (account.equals("admin") && password.equals("123456")) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    if (isRememberPass.isChecked()) {
                        editor.putBoolean("isRememberPass", true);
                        editor.putString("account", accountEdit.getText().toString());
                        editor.putString("password", passwordEdit.getText().toString());
                    } else {
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "account or password is invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
