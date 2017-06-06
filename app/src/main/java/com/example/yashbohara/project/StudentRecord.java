package com.example.yashbohara.project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.yashbohara.project.M1.dbhandler;

public class StudentRecord extends AppCompatActivity {
    Spinner s81;
    Spinner s82;
    Spinner s83;
    public  String a;
  public static ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_record);
         }
    public void View_Clicked(View view)
    {
        final EditText e83=(EditText) findViewById(R.id.e83);
        final EditText editText=(EditText) findViewById(R.id.editText3);
        s81=(Spinner) findViewById(R.id.j81);
        s82=(Spinner) findViewById(R.id.j82);
        s83=(Spinner) findViewById(R.id.j83);
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        progressDialog=new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Loading Please Wait....");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        DatabaseReference databaseReference=firebaseDatabase.getReference("Student"+s82.getSelectedItem().toString()+s83.getSelectedItem().toString()+s81.getSelectedItem().toString());
        databaseReference.child(e83.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               try {
                a=dataSnapshot.child("Password").getValue().toString();
                Log.e("1",a);
                 ai(a,editText.getText().toString());
        }
            catch (NullPointerException e)
            {
                progressDialog.dismiss();
                ak();

            }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /*if(e81.getText().toString().equals(dbhandler.RetrieveStudent(s81.getSelectedItem().toString(), s82.getSelectedItem().toString(), s83.getSelectedItem().toString(), e82.getText().toString()))&&e81.getText().toString()!=null)
       {
           if(e81.getText().toString().equals(""))
           {
               Toast.makeText(this,"Invalid Name or Roll Number",Toast.LENGTH_LONG).show();
           }
           else {
           Toast.makeText(this, "Valid Roll Number", Toast.LENGTH_LONG).show();
        startActivity(intent);
    }
       }
        else {
          Toast.makeText(this, "Invalid Roll Number", Toast.LENGTH_LONG).show();
      }
    */
}
    public void ak()
    {
        Toast.makeText(this, "Invalid Roll Number or Password", Toast.LENGTH_LONG).show();
    }

    public void ai(String b,String c)
    {
    EditText e81=(EditText) findViewById(R.id.e81);
    EditText e82=(EditText) findViewById(R.id.e83);
    Spinner s81=(Spinner) findViewById(R.id.j81);
    Spinner s82=(Spinner) findViewById(R.id.j82);
    Spinner s83=(Spinner) findViewById(R.id.j83);
    if (c.equals(b))
    {
        progressDialog.dismiss();
        Intent intent=new Intent(this,StudentDetails.class);
        intent.putExtra("i1",e81.getText().toString());
        intent.putExtra("i3",e82.getText().toString());
        intent.putExtra("i2",s81.getSelectedItem().toString());
        intent.putExtra("i4",s82.getSelectedItem().toString());
        intent.putExtra("i5",s83.getSelectedItem().toString());
        this.finish();
        startActivity(intent);
    }
    else
    {
        progressDialog.dismiss();
        Toast.makeText(this,"Invalid id or Password",Toast.LENGTH_LONG).show();
    }
}
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
