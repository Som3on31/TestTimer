package com.example.testtimer;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testtimer.databinding.FragmentBlueLightBinding;
import com.example.testtimer.databinding.FragmentLoginBinding;


public class LoginFragment extends Fragment {

    FragmentLoginBinding binding;

    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private Button mLoginButton;
    private DatabaseHelper mDatabaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        binding.register2.setOnClickListener(View -> {
            NavHostFragment.findNavController(LoginFragment.this)
                    .navigate(R.id.action_loginFragment_to_registerFragment);
        });

        binding.forgot.setOnClickListener(View -> {
            NavHostFragment.findNavController(LoginFragment.this)
                    .navigate(R.id.action_loginFragment_to_forgotFragment);
        });

//        binding.submit.setOnClickListener(View -> {
//            // placeholder
//            boolean passwordCorrect = true;
//
//            // logic for managing when the user attempts to log into the app
//            if(passwordCorrect){
//                NavHostFragment.findNavController(LoginFragment.this)
//                        .navigate(R.id.action_loginFragment_to_homeFragment);
//            }else{
//
//            }
//        });

//        mUsernameEditText = view.findViewById(R.id.login_username);
//        mPasswordEditText = view.findViewById(R.id.login_password);
//        mLoginButton = view.findViewById(R.id.submit);

        mDatabaseHelper = new DatabaseHelper(getContext());

        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.loginUsername.getText().toString().trim();
                String password = binding.loginPassword.getText().toString().trim();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(getContext(), "Please enter username and password", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean loginSuccessful = checkLoginCredentials(username, password);

                if (loginSuccessful) {
                    Toast.makeText(getContext(), "Login successful", Toast.LENGTH_SHORT).show();
                    NavHostFragment.findNavController(LoginFragment.this)
                        .navigate(R.id.action_loginFragment_to_homeFragment);
                } else {
                    Toast.makeText(getContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_login, container, false);
//
//        mUsernameEditText = view.findViewById(R.id.login_username);
//        mPasswordEditText = view.findViewById(R.id.login_password);
//        mLoginButton = view.findViewById(R.id.submit);
//
//        mDatabaseHelper = new DatabaseHelper(getContext());
//
//        mLoginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String username = mUsernameEditText.getText().toString().trim();
//                String password = mPasswordEditText.getText().toString().trim();
//
//                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
//                    Toast.makeText(getContext(), "Please enter username and password", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                boolean loginSuccessful = checkLoginCredentials(username, password);
//
//                if (loginSuccessful) {
//                    Toast.makeText(getContext(), "Login successful", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        return view;
//    }

    private boolean checkLoginCredentials(String username, String password) {
        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();

        String[] projection = {
                DatabaseHelper.COLUMN_ID
        };

        String selection = DatabaseHelper.COLUMN_USERNAME + " = ? AND " + DatabaseHelper.COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = { username, password };

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_USERS,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        int count = cursor.getCount();

        cursor.close();

        return count > 0;
    }



}

//public class LoginFragment extends Fragment {
//
//    FragmentLoginBinding binding;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        binding =  FragmentLoginBinding.inflate(inflater, container, false);
//        return binding.getRoot();
//    }
//
//    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
//        super.onViewCreated(view,savedInstanceState);
//
//        binding.register2.setOnClickListener(View -> {
//            NavHostFragment.findNavController(LoginFragment.this)
//                    .navigate(R.id.action_loginFragment_to_registerFragment);
//        });
//
//        binding.forgot.setOnClickListener(View -> {
//            NavHostFragment.findNavController(LoginFragment.this)
//                    .navigate(R.id.action_loginFragment_to_forgotFragment);
//        });
//
//        binding.submit.setOnClickListener(View -> {
//            // placeholder
//            boolean passwordCorrect = true;
//
//            // logic for managing when the user attempts to log into the app
//            if(passwordCorrect){
//                NavHostFragment.findNavController(LoginFragment.this)
//                        .navigate(R.id.action_loginFragment_to_homeFragment);
//            }else{
//
//            }
//        });
//    }
//}