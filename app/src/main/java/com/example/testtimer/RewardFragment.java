package com.example.testtimer;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.testtimer.databinding.FragmentRewardBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RewardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RewardFragment extends Fragment {

    private FragmentRewardBinding  binding;
    private TextView tw;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        binding = FragmentRewardBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        binding.button.setVisibility(View.VISIBLE);
        binding.button.setOnClickListener(View -> {
            NavHostFragment.findNavController(RewardFragment.this)
                    .navigate(R.id.action_rewardFragment_to_homeFragment);
        });
    }
}