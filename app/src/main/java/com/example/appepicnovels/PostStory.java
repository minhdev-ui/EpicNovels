package com.example.appepicnovels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appepicnovels.Firebase.DBHelperF;
import com.example.appepicnovels.models.Truyen;

public class PostStory extends AppCompatActivity {
    EditText edtTieuDe,edtNoiDung,edtAnh;
    Button btnDangBai;
    DBHelperF dbtruyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poststory);

        edtTieuDe = findViewById(R.id.dbtieude);
        edtNoiDung = findViewById(R.id.dbnoidung);
        btnDangBai = findViewById(R.id.dbdangbai);
        edtAnh = findViewById(R.id.dbimg);

        dbtruyen = new DBHelperF(this);

        btnDangBai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tentruyen = edtTieuDe.getText().toString();
                String noidung = edtNoiDung.getText().toString();
                String img = edtAnh.getText().toString();

                Truyen truyen = CreatTruyen();

                if(tentruyen.equals("") || noidung.equals("") || img.equals("")){
                    Toast.makeText(PostStory.this,"Yêu cầu nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else{
                    dbtruyen.AddTruyen(truyen);
                    Intent intent = new Intent(PostStory.this,AdminActivity.class);
                    finish();
                    startActivity(intent);
                    //Toast.makeText(ManDangBai.this,"Thêm truyện thành công",Toast.LENGTH_SHORT).show();
                    //Log.e("Thêm truyện : ","Thành công");
                }
            }
        });
    }
    private Truyen CreatTruyen(){
        String tentruyen = edtTieuDe.getText().toString();
        String noidung = edtNoiDung.getText().toString();
        String img = edtAnh.getText().toString();

        Intent intent = getIntent();
        int id = intent.getIntExtra("Id",0);

        Truyen truyen = new Truyen(tentruyen,noidung,img,id);
        return truyen;
    }
}
