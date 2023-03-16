package com.example.testtimer;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.testtimer.databinding.FragmentCouponBinding;
import com.example.testtimer.databinding.FragmentRestBinding;
import com.example.testtimer.objects.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CouponFragment extends Fragment {

    private FragmentCouponBinding binding;
    private int points_int, redeem_int;
    private String[] getRedeem;

    private FirebaseUser user;
    private DatabaseReference ref;
    private String userID;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = FirebaseAuth.getInstance().getCurrentUser();
        ref = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        binding = FragmentCouponBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        //buttons on home screen
        binding.btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(CouponFragment.this)
                        .navigate(R.id.action_couponFragment_to_homeFragment2);
            }
        });

        points_int = Integer.parseInt(binding.points.getText().toString());

        ref.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User u = snapshot.getValue(User.class);

                points_int = u.points;
                binding.points.setText(Integer.toString(points_int));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.redeem.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {


                   getRedeem = binding.usePoint.getText().toString().split(" ");
                   redeem_int = Integer.parseInt(getRedeem[0]);
                   checkTransaction(redeem_int);
                   binding.points.setText(points_int+"");

               }
           });

        binding.redeem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
//                alert.setTitle("Please show this redemption code to the merchant staff.");
//                alert.setMessage("\n Q1Plyk \n");
//                AlertDialog dialog = alert.show();
//
//                TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
//                messageView.setGravity(Gravity.CENTER);
//                messageView.setTextSize(40);

                getRedeem = binding.usePoint1.getText().toString().split(" ");
                redeem_int = Integer.parseInt(getRedeem[0]);
                checkTransaction(redeem_int);
                binding.points.setText(points_int+"");
            }
        });

        binding.redeem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
//                alert.setTitle("Please show this redemption code to the merchant staff.");
//                alert.setMessage("\n Mko3sr \n");
//                AlertDialog dialog = alert.show();
//
//                TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
//                messageView.setGravity(Gravity.CENTER);
//                messageView.setTextSize(40);

                getRedeem = binding.usePoint2.getText().toString().split(" ");
                redeem_int = Integer.parseInt(getRedeem[0]);
                checkTransaction(redeem_int);
                binding.points.setText(points_int+"");
            }
        });

        binding.redeem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
//                alert.setTitle("Please show this redemption code to the merchant staff.");
//                alert.setMessage("\n Ipkioj \n");
//                AlertDialog dialog = alert.show();
//
//                TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
//                messageView.setGravity(Gravity.CENTER);
//                messageView.setTextSize(40);

                getRedeem = binding.usePoint3.getText().toString().split(" ");
                redeem_int = Integer.parseInt(getRedeem[0]);
                checkTransaction(redeem_int);
                binding.points.setText(points_int+"");
            }
        });

    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //methods regarding transaction
    public void checkTransaction(int redeemPts){

        ref.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User u = snapshot.getValue(User.class);

                if(u.points<redeemPts){
                    Toast.makeText(getContext(),"Insufficient points.",Toast.LENGTH_SHORT).show();
                }else{
                    u.points-=redeemPts;
                    points_int=u.points;
                    ref.child(userID).setValue(u);

                    AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                    alert.setTitle("Please show this redemption code to the merchant staff.");
                    alert.setMessage("\n Lkjdef \n");
                    AlertDialog dialog = alert.show();

                    TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
                    messageView.setGravity(Gravity.CENTER);
                    messageView.setTextSize(40);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"Cannot fetch data. Please try again later.",Toast.LENGTH_SHORT).show();
            }
        });



    }
}
