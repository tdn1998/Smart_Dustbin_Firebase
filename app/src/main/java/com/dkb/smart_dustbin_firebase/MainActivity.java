package com.dkb.smart_dustbin_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import me.itangqi.waveloadingview.WaveLoadingView;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Percent Filled");
    WaveLoadingView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = (WaveLoadingView)findViewById(R.id.loading_screen);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String val = dataSnapshot.getValue(String.class);
                int value = Integer.parseInt(val);

                view.setProgressValue(value);
                if(value<=50){
                    view.setBottomTitle(String.format("%d%%",value));
                    view.setCenterTitle("");
                    view.setTopTitle("");
                }
                else if(value<80){
                    view.setBottomTitle("");
                    view.setCenterTitle(String.format("%d%%",value));
                    view.setTopTitle("");
                }
                else{
                    view.setBottomTitle("");
                    view.setCenterTitle("");
                    view.setTopTitle(String.format("%d%%",value));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
