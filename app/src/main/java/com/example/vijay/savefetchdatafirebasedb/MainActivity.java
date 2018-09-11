package com.example.vijay.savefetchdatafirebasedb;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    EditText name_et, mobile_et, fetchmobile;
    TextView fetchname;
    Button save, fetch;
    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbref = FirebaseDatabase.getInstance().getReference("Users");
        name_et = findViewById(R.id.name_et);
        mobile_et = findViewById(R.id.mobile_et);
        fetchmobile = findViewById(R.id.fetchmobile);

        fetchname = findViewById(R.id.fetchname);

        save = findViewById(R.id.save);
        fetch = findViewById(R.id.fetch);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDetails();
            }
        });

        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchUser();
            }
        });

    }

    private void fetchUser() {
        final String Mobile = fetchmobile.getText().toString();

        if (!Mobile.isEmpty()) {
            dbref.orderByChild("userMobile").equalTo(Mobile).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // Toast.makeText(MainActivity.this, dataSnapshot.toString(), Toast.LENGTH_SHORT).show();
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        String name = data.child("userName").getValue().toString();
                        fetchname.setText(name);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(this, "Enter Mobile Number....", Toast.LENGTH_SHORT).show();
        }

    }

    private void saveDetails() {

        String Name = name_et.getText().toString();
        String Mobile = mobile_et.getText().toString();

        if (!TextUtils.isEmpty(Name) && !Mobile.isEmpty()) {

            String id = dbref.push().getKey();
            Users users = new Users(id, Name, Mobile);
            dbref.child(id).setValue(users);
            Toast.makeText(this, "User Details Saved Successfully", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Fill the User Details..", Toast.LENGTH_SHORT).show();
        }

    }
}
