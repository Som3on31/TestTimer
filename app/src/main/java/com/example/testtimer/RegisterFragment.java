package com.example.testtimer;

import static com.example.testtimer.MainActivity.BLANK;
import static com.example.testtimer.MainActivity.DEFAULT_REST_TIME_SECS;
import static com.example.testtimer.MainActivity.REST_TIME_TEXT;
import static com.example.testtimer.MainActivity.preference;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testtimer.databinding.FragmentLoginBinding;
import com.example.testtimer.databinding.FragmentRegisterBinding;
import com.example.testtimer.databinding.FragmentSettingsBinding;

public class RegisterFragment extends Fragment {

    FragmentRegisterBinding binding;

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText emailEditText;
    private EditText birthdayEditText;
    private Button registerButton;
    private TextView loginTextView;

    private SQLiteDatabase database;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);


        // Get references to UI elements
//        firstNameEditText = view.findViewById(R.id.reg_fname);
//        lastNameEditText = view.findViewById(R.id.edit_lname);
//        usernameEditText = view.findViewById(R.id.edit_user);
//        passwordEditText = view.findViewById(R.id.edit_password);
//        emailEditText = view.findViewById(R.id.edit_email);
//        birthdayEditText = view.findViewById(R.id.edit_bd);
//        registerButton = view.findViewById(R.id.register);
//        loginTextView = view.findViewById(R.id.register);

        // Initialize database
        database = new DatabaseHelper(getActivity()).getWritableDatabase();

        // Set click listeners
        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input
                String firstName = binding.editFname.getText().toString();
                String lastName = binding.editLname.getText().toString();
                String username = binding.editUser.getText().toString();
                String password = binding.editPassword.getText().toString();
                String email = binding.editEmail.getText().toString();
                String birthday = binding.editBd.getText().toString();

                // Insert user input into database
                ContentValues values = new ContentValues();
                values.put(DatabaseHelper.COLUMN_FIRST_NAME, firstName);
                values.put(DatabaseHelper.COLUMN_LAST_NAME, lastName);
                values.put(DatabaseHelper.COLUMN_USERNAME, username);
                values.put(DatabaseHelper.COLUMN_PASSWORD, password);
                values.put(DatabaseHelper.COLUMN_EMAIL, email);
                values.put(DatabaseHelper.COLUMN_BIRTHDAY, birthday);
                database.insert(DatabaseHelper.TABLE_USERS, null, values);

                // Clear input fields
                binding.editFname.setText("");
                binding.editLname.setText("");
                binding.editUser.setText("");
                binding.editPassword.setText("");
                binding.editEmail.setText("");
                binding.editBd.setText("");

                // Show toast message
                Toast.makeText(getActivity(), "Registration successful", Toast.LENGTH_SHORT).show();

                //go to loginFragment
                NavHostFragment.findNavController(RegisterFragment.this)
                        .navigate(R.id.action_registerFragment_to_loginFragment);
            }
        });


//        loginTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Switch to login fragment
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.fragment_container, new LoginFragment());
//                transaction.addToBackStack(null);
//                transaction.commit();
//            }
//        });

    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_register, container, false);
//
//
//        // Get references to UI elements
//        firstNameEditText = view.findViewById(R.id.reg_fname);
//        lastNameEditText = view.findViewById(R.id.edit_lname);
//        usernameEditText = view.findViewById(R.id.edit_user);
//        passwordEditText = view.findViewById(R.id.edit_password);
//        emailEditText = view.findViewById(R.id.edit_email);
//        birthdayEditText = view.findViewById(R.id.edit_bd);
//        registerButton = view.findViewById(R.id.register);
//        loginTextView = view.findViewById(R.id.register);
//
//        // Initialize database
//        database = new DatabaseHelper(getActivity()).getWritableDatabase();
//
//        // Set click listeners
//        registerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Get user input
//                String firstName = firstNameEditText.getText().toString();
//                String lastName = lastNameEditText.getText().toString();
//                String username = usernameEditText.getText().toString();
//                String password = passwordEditText.getText().toString();
//                String email = emailEditText.getText().toString();
//                String birthday = birthdayEditText.getText().toString();
//
//                // Insert user input into database
//                ContentValues values = new ContentValues();
//                values.put(DatabaseHelper.COLUMN_FIRST_NAME, firstName);
//                values.put(DatabaseHelper.COLUMN_LAST_NAME, lastName);
//                values.put(DatabaseHelper.COLUMN_USERNAME, username);
//                values.put(DatabaseHelper.COLUMN_PASSWORD, password);
//                values.put(DatabaseHelper.COLUMN_EMAIL, email);
//                values.put(DatabaseHelper.COLUMN_BIRTHDAY, birthday);
//                database.insert(DatabaseHelper.TABLE_USERS, null, values);
//
//                // Clear input fields
//                firstNameEditText.setText("");
//                lastNameEditText.setText("");
//                usernameEditText.setText("");
//                passwordEditText.setText("");
//                emailEditText.setText("");
//                birthdayEditText.setText("");
//
//                // Show toast message
//                Toast.makeText(getActivity(), "Registration successful", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        loginTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Switch to login fragment
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.fragment_container, new LoginFragment());
//                transaction.addToBackStack(null);
//                transaction.commit();
//            }
//        });
//
//        return view;
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Close database when fragment is destroyed
        database.close();
    }
}
