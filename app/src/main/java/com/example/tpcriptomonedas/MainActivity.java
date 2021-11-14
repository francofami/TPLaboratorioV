package com.example.tpcriptomonedas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IOnItemClick, Handler.Callback {

    List<Cripto> criptoList = new ArrayList<>();
    Integer posicionActual = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler(this);

        HiloConexion hiloCriptos = new HiloConexion(handler, false, "ethereum");
        hiloCriptos.start();

        HiloConexion hiloImg = new HiloConexion(handler, true);
        hiloImg.start();

        this.crearRecyclerView();
    }

    @Override
    public boolean handleMessage(@NonNull Message message) {

        if(message.arg1==HiloConexion.CRIPTOS) {
            // TextView tv = super.findViewById(R.id.tv);

            System.out.println("ENTRE AL HANDLEMESSAGE");

            List<Cripto> criptos = (List<Cripto>) message.obj;
            System.out.println("Entre al handle" + criptos.toString());

            Log.d("", criptos.get(0).toString());

            //tv.setText(criptos.get(0));
        } else if(message.arg1==HiloConexion.IMAGEN) {
            // ImageView iv = super.findViewById(R.id.img);
            byte[] img = (byte[]) message.obj;
            // iv.setImageBitmap(BitmapFactory.decodeByteArray(img, 0, img.length));
        }


        return false;
    }

    private void crearRecyclerView() {

        CriptoAdapter adapter = new CriptoAdapter(criptoList, this);
        RecyclerView rv = super.findViewById(R.id.rvCriptos);

        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, CalcularActivity.class);

        intent.putExtra("cripto", criptoList.get(position));

        this.posicionActual = position;

        //startActivity(intent);
        startActivityForResult(intent,999);
    }
}