package com.example.savedatartfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.savedatartfirebase.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

ActivityMainBinding binding;
String firstName,lastname,age;//variables which will  be getting data from user

    FirebaseDatabase db;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting data that is inputed from user
                firstName = binding.firstname.getText().toString();
                lastname = binding.lastname.getText().toString();
                age = binding.age.getText().toString();

                if(!firstName.isEmpty() &&!lastname.isEmpty() && !age.isEmpty()){

                    Users users  = new Users(firstName,lastname,age);
                    db = FirebaseDatabase.getInstance();
                    reference  = db.getReference("Users");

                    //using this path reference to add data on child nodes
                    reference.child(firstName).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            //this function will be called when task is finished
                                binding.firstname.setText("");//setting all fields empty
                                binding.lastname.setText("");
                                binding.age.setText("");

                            Toast.makeText(MainActivity.this, "Sucessfully Updated", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
    }
}


//
//      Geeks for Geeks


//    private EditText employeeNameEdt, employeePhoneEdt, employeeAddressEdt;
//    private Button sendDatabtn;
//
//    FirebaseDatabase firebaseDatabase;//variables
//    // Reference for Firebase.
//    DatabaseReference databaseReference;
//
//    // creating a variable for
//    // our object class
//    EmployeeInfo employeeInfo;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // initializing our edittext and button
//        employeeNameEdt = findViewById(R.id.idEdtEmployeeName);
//        employeePhoneEdt = findViewById(R.id.idEdtEmployeePhoneNumber);
//        employeeAddressEdt = findViewById(R.id.idEdtEmployeeAddress);
//
//        // instance of our FIrebase database.
//        firebaseDatabase = FirebaseDatabase.getInstance();
//
//     // get reference for database.
//        databaseReference = firebaseDatabase.getReference("EmployeeInfo");// this will be displayed after root nood like parent node
//                                                                    //and this is the calling of class where we have set the getters and setters of objects
//                                                                    //in firebase
//
//        // initializing our object
//        // class variable.
//        employeeInfo = new EmployeeInfo();
//
//        sendDatabtn = findViewById(R.id.idBtnSendData);
//
//        sendDatabtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                // getting text from our edittext fields.
//                String name = employeeNameEdt.getText().toString();
//                String phone = employeePhoneEdt.getText().toString();
//                String address = employeeAddressEdt.getText().toString();
//
//
//                // edittext fields are empty or not.
//                if (TextUtils.isEmpty(name) && TextUtils.isEmpty(phone) && TextUtils.isEmpty(address)) {
//                    Toast.makeText(MainActivity.this, "Please add some data.", Toast.LENGTH_SHORT).show();
//                } else {
//                    addDatatoFirebase(name, phone, address);
//                }
//            }
//        });
//    }
//
//    private void addDatatoFirebase(String name, String phone, String address) {
//
//        // set data in our object class.
//        employeeInfo.setEmployeeName(name);
//        employeeInfo.setEmployeeContactNumber(phone);
//        employeeInfo.setEmployeeAddress(address);
//
//        // we are using add value event listener method
//        // which is called with database reference.
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                // inside the method of on Data change we are setting
//                // our object class to our database reference.
//                // data base reference will sends data to firebase.
//                databaseReference.setValue(employeeInfo);
//
//                Toast.makeText(MainActivity.this, "data added", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(MainActivity.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}
