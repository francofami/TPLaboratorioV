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
    List<Arbitraje> arbitrajeList = new ArrayList<>();

    Integer posicionActual = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler(this);

        HiloConexion hiloEthereum = new HiloConexion(handler, false, "ethereum");
        hiloEthereum.start();

        HiloConexion hiloBitcoin = new HiloConexion(handler, false, "bitcoin");
        hiloBitcoin.start();

        HiloConexion hiloDai = new HiloConexion(handler, false, "dai");
        hiloDai.start();

        HiloConexion hiloUsdt = new HiloConexion(handler, false, "usdt");
        hiloUsdt.start();

        HiloConexion hiloUsdc = new HiloConexion(handler, false, "usdc");
        hiloUsdc.start();



        /*HiloConexion hiloImg = new HiloConexion(handler, true);
        hiloImg.start();*/

        this.crearRecyclerView();
    }

    @Override
    public boolean handleMessage(@NonNull Message message) {

        if(message.arg1==HiloConexion.CRIPTOS) {
            // TextView tv = super.findViewById(R.id.tv);

            System.out.println("ENTRE AL HANDLEMESSAGE");

            List<Cripto> criptos = (List<Cripto>) message.obj;
            System.out.println("Entre al handle" + criptos.toString());

            for (Cripto cripto : criptos) {
                System.out.println("Entré al for");
                criptoList.add(cripto);
            }

        } else if(message.arg1==HiloConexion.IMAGEN) {
            // ImageView iv = super.findViewById(R.id.img);
            byte[] img = (byte[]) message.obj;
            // iv.setImageBitmap(BitmapFactory.decodeByteArray(img, 0, img.length));
        }

        this.arbitrajeList.addAll(Arbitraje.CalcularArbitrajes(this.criptoList));

        this.arbitrajeList = Arbitraje.EliminarDuplicados(this.arbitrajeList);

        Arbitraje.ordenarArbitrajes(this.arbitrajeList);

        this.crearRecyclerView();

        // Log.d("Mejor arbitraje: ", this.arbitrajeList.get(0).toString());

        return false;
    }

    private void actualizarCripto(Cripto cripto) {
        this.criptoList.set(this.posicionActual, cripto);
        this.crearRecyclerView();
    }

    private void crearRecyclerView() {

        ArbitrajeAdapter adapter = new ArbitrajeAdapter(arbitrajeList, this);
        RecyclerView rv = super.findViewById(R.id.rvCriptos);

        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, CalcularActivity.class);

        intent.putExtra("arbitraje", arbitrajeList.get(position));

        this.posicionActual = position;

        //startActivity(intent);
        startActivityForResult(intent,999);
    }
}