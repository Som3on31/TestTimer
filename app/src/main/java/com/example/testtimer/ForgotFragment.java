package com.example.testtimer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.testtimer.databinding.FragmentForgotBinding;
import com.example.testtimer.databinding.FragmentRegisterBinding;
import com.google.firebase.auth.FirebaseAuth;


public class ForgotFragment extends Fragment {

    FragmentForgotBinding binding;

    FirebaseAuth auth;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentForgotBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        //TODO: make it send an email to the des
        binding.submit.setOnClickListener(View -> {
            resetPassword(binding.editEmail.getText().toString().trim());


            NavHostFragment.findNavController(ForgotFragment.this)
                    .navigate(R.id.action_forgotFragment_to_loginFragment);
        });
    }

    public void onDestroyView() {
        super.onDestroyView();

        binding = null;
    }

    public void resetPassword(String email){
        if(!isEmailVaild(email)) return;

        auth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                Toast.makeText(getContext(), "Check your email to reset your password.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getContext(),"Unexpected error. Please try again.",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean isEmailVaild(String email){
        if (email.length()==0) {
            Toast.makeText(getContext(),"Email cannot be empty.",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(getContext(),"Please enter a valid email.",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}