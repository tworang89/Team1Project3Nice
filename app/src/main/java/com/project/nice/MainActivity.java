package com.project.nice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseReference database;
    EditText net1, net2;
    Button nbt1, nbt2;
    int i = 1; //userId값 count하기 위한 변수
    ArrayList<User> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        net1 = findViewById(R.id.net1);
        net2 = findViewById(R.id.net2);
        nbt1 = findViewById(R.id.nbt1);
        nbt2 = findViewById(R.id.nbt2);

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
                    //member member;
                    //member = snapshot1.getValue(member.class);
                    //arrayList.add(member);
                    //Log.d("파이어베이스>> ", "member 1명: " + member);

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

        nbt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id = net1.getText().toString();
                final String pw = net2.getText().toString();

                database.child(id).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.getValue(User.class);
                        Log.d("파이어베이스>> ", id + ": Id 상세정보: " + user);
                        Log.d("파이어베이스>> ", pw + ": Pw 상세정보: " + user);

                        if (id.equals(id) && pw.equals(pw)){

                            Intent intent = new Intent(MainActivity.this, JoinActivity.class);
                            startActivity(intent);

                        }else {

                            Log.d(id,pw);

                        }






                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("파이어베이스>> ", id + ": userId 없음");
                    }
                });
            }
        });

        nbt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, JoinActivity.class);
                startActivity(intent);
            }
        });


    }


}
