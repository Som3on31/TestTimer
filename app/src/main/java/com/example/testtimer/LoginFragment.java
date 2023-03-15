package com.example.testtimer;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.testtimer.databinding.FragmentLoginBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginFragment extends Fragment {

    FragmentLoginBinding binding;

    private DatabaseHelper mDatabaseHelper;

    private FirebaseAuth auth;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();
    }

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
//                String username = binding.loginUsername.getText().toString().trim();
//                String password = binding.loginPassword.getText().toString().trim();
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
//                    NavHostFragment.findNavController(LoginFragment.this)
//                        .navigate(R.id.action_loginFragment_to_homeFragment);
//                } else {
//                    Toast.makeText(getContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
//                }

                login();
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

    private void login(){
        String email = binding.loginUsername.getText().toString().trim();
        String password = binding.loginPassword.getText().toString();

        if (!checkEmail(email)) return;
        if (!checkPassword(password)) return;

        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(Task -> {
            if (Task.isSuccessful()){
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if(user!=null){
                    if(user.isEmailVerified()){
                        Toast.makeText(getContext(),"Login successful",Toast.LENGTH_SHORT).show();

                        NavHostFragment.findNavController(LoginFragment.this)
                                .navigate(R.id.action_loginFragment_to_homeFragment);
                    }else{
                        Toast.makeText(getContext(),"Please check your email for verification.",Toast.LENGTH_SHORT)
                                .show();
                    }
                }else{
                    Toast.makeText(getContext(), "Unexpected error occurred, try again.", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(getContext(),"Incorrect email or password.",Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Checks the validity of an email. Returns true if the field is not empty and has a valid form of email,
     * returns false otherwise.
     * @param email an email to be checked.
     * @return a boolean
     */
    private boolean checkEmail(String email){
        if (email.isEmpty()){
            Toast.makeText(getContext(), "Email cannot be empty.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(getContext(),"Please enter a valid email.",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * Check the validity of a password. Returns true of all conditions are met, false otherwise.
     *
     *
     * @param password a password to be checked
     * @return a boolean
     */
    private boolean checkPassword(String password){
        if (password.length()==0){
            Toast.makeText(getContext(),"Password cannot be empty.",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}