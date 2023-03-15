package com.example.testtimer;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.testtimer.databinding.FragmentRegisterBinding;
import com.example.testtimer.objects.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterFragment extends Fragment {

    FragmentRegisterBinding binding;

    private SQLiteDatabase database;

    private FirebaseAuth auth;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();
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

        // Initialize database
        database = new DatabaseHelper(getActivity()).getWritableDatabase();

        // Set click listeners
        binding.register.setOnClickListener(v -> {
            // Get user input
            String firstName = binding.editFname.getText().toString().trim();
            String lastName = binding.editLname.getText().toString().trim();
            String username = binding.editUser.getText().toString().trim();
            String password = binding.editPassword.getText().toString().trim();
            String email = binding.editEmail.getText().toString().trim();
            String birthday = binding.editBd.getText().toString().trim();

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

            // Register via email and password
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    User user = new User(firstName,lastName,birthday,email,username,password);

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("message");

                    myRef.setValue("Hello, World!");


                    // Send data to the database
                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(task1 -> {
                                if (task1.isSuccessful()){
                                    // Show toast message
                                    String successMsg = "Registration successful. Check your email.";
                                    Toast.makeText(getActivity(), successMsg, Toast.LENGTH_SHORT).show();
                                    FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification();


                                    //go to loginFragment
                                    NavHostFragment.findNavController(RegisterFragment.this)
                                            .navigate(R.id.action_registerFragment_to_loginFragment);
                                }else{
                                    String failMsg = "Cannot register. Please try again.";
                                    Toast.makeText(getActivity(), failMsg,Toast.LENGTH_SHORT).show();
                                }
                            });
                }else{
                    Toast.makeText(getActivity(),"Cannot register.",Toast.LENGTH_SHORT).show();
                }
            });

        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Close database when fragment is destroyed
        database.close();

        binding = null;
    }
}
