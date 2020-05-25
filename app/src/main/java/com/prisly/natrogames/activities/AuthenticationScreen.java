package com.prisly.natrogames.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.prisly.natrogames.databinding.ActivityAuthenticationScreenBinding;
import com.prisly.natrogames.user.User;
import com.prisly.natrogames.user.UserAuth;

public class AuthenticationScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityAuthenticationScreenBinding AuthenticationScreen = ActivityAuthenticationScreenBinding.inflate(getLayoutInflater());
        setContentView(AuthenticationScreen.getRoot());

        //final User user = new User(getApplicationContext(), "abcd");
        final UserAuth userAuth = new UserAuth(getApplicationContext());

        final String[] userAuthKey = new String[1];

        AuthenticationScreen.LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userAuthKey[0] = userAuth.LoginUser(AuthenticationScreen.EmailInput.getText().toString(), AuthenticationScreen.PasswordInput.getText().toString());
            }
        });

        AuthenticationScreen.SwitchToSingUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthenticationScreen.SignUpScreen.setVisibility(View.VISIBLE);
                AuthenticationScreen.LoginScreen.setVisibility(View.GONE);
            }
        });
    }
}
