package com.project.nice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class JoinActivity extends AppCompatActivity {

    DatabaseReference database;
    EditText jev1,jev2,jev3,jev4,jev5;
    Button jbt1, jbt2;
    int i = 1; //userId값 count하기 위한 변수
    ArrayList<User> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        jev1 = findViewById(R.id.jev1);
        jev2 = findViewById(R.id.jev2);
        jev3 = findViewById(R.id.jev3);


        jbt1 = findViewById(R.id.jbt1);
        jbt2 = findViewById(R.id.jbt2);

        database = FirebaseDatabase.getInstance().getReference("User");
        Log.d("파이어베이스>> ", database + " ");

        //db에서 가지고 오는 유저들의 목록을 넣을 공간
        arrayList = new ArrayList<>();

        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //유저들의 목록을 가지고 오는 메서드
                arrayList.clear();

                Log.d("파이어베이스>> ", "member 아래의 자식들의 개수: " + snapshot.getChildrenCount());
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    //snapshot.getChildren() => users아래에 있는 user목록을 다 가지고 온다.
                    //DataSnapshot => for문으로 목록에 들어있는 user를 한명씩 꺼내줌.
                    //user의 값들을 가지고와서, User vo에 넣는다.
                    //getValue(User.class) => 해당하는 멤버변수과 동일한 set메서드를 자동을 부른다.
                    Log.d("파이어베이스>> ", "member 1명: " + snapshot1);
                    User user;
                    user = snapshot1.getValue(User.class);
                    arrayList.add(user);
                    Log.d("파이어베이스>> ", "member 1명: " + user);


                }
                i = arrayList.size();
                Log.d("파이어베이스>> ", "member list: " + arrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        jbt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = jev1.getText().toString();
                String password = jev2.getText().toString();
                String name = jev3.getText().toString();

                i++;
                Intent intent = new Intent(JoinActivity.this,MainActivity.class);

                User user = new User(email,password,name);

                database.child(String.valueOf(i)).setValue(user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(JoinActivity.this, "회원가입 되셨습니다.",
                                        Toast.LENGTH_SHORT).show();

                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(JoinActivity.this, "다시 시도해주세요.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }



        });

        jbt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JoinActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}