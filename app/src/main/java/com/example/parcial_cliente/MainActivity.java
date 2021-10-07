package com.example.parcial_cliente;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parcial_cliente.modelo.Particula;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    private EditText nombreText;
    private EditText particulasText;
    private EditText xText;
    private EditText yText;

    Button rojoButton, verdeButton, azulButton;
    Button crearButton, borrarButton;

    int r,g,b;
    boolean crear;
    boolean borrar;

    private Socket socket;
    BufferedWriter writer;
    BufferedReader reader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombreText = findViewById(R.id.nombreText);
        particulasText = findViewById(R.id.particulasText);
        xText = findViewById(R.id.xText);
        yText = findViewById(R.id.yText);

        rojoButton = findViewById(R.id.rojoButton);
        verdeButton = findViewById(R.id.verdeButton);
        azulButton = findViewById(R.id.azulButton);
                  
        crearButton = findViewById(R.id.crearButton);
        borrarButton = findViewById(R.id.borrarButton);

        crear=false;
        borrar=false;

        initClient();

        rojoButton.setOnClickListener(
                (view) ->
                {
                    r = 245;
                    g = 183;
                    b = 177;
                }
        );
        verdeButton.setOnClickListener(
                (view) ->
                {
                    r = 171;
                    g = 235;
                    b = 198;
                }
        );
        azulButton.setOnClickListener(
                (view) ->
                {
                    r = 174;
                    g = 241;
                    b = 241;
                }
        );

        crearButton.setOnClickListener(
                (view)->
                {

                        Gson gson = new Gson();

                        String nombre = nombreText.getText().toString();
                        int particulas = Integer.parseInt(particulasText.getText().toString());
                        int x = Integer.parseInt(xText.getText().toString());
                        int y = Integer.parseInt(yText.getText().toString());
                        crear= true;
                        borrar= false;

                        Particula particula = new Particula(x, y, nombre, particulas, r, g, b, crear, borrar);

                        String json = gson.toJson(particula);

                        enviarMensaje(json);

                }
        );

        borrarButton.setOnClickListener(
                (view)->
                {
                    Gson gson = new Gson();

                    String nombre = nombreText.getText().toString();
                    int particulas = Integer.parseInt(particulasText.getText().toString());
                    int x = Integer.parseInt(xText.getText().toString());
                    int y = Integer.parseInt(yText.getText().toString());
                    crear= false;
                    borrar= true;

                    Particula particula = new Particula(x, y, nombre, particulas, r, g, b, crear, borrar);

                    String json = gson.toJson(particula);

                    enviarMensaje(json);


                }
        );



    }

    public void initClient()
    {
        new Thread(
                () ->{
                    try {
                        System.out.println("Connecting to server...");
                        socket = new Socket("192.168.1.58", 7000);
                        System.out.println("Established connection to server");

                        InputStream is = socket.getInputStream();
                        InputStreamReader isr = new InputStreamReader(is);
                        reader = new BufferedReader(isr);

                        OutputStream os = socket.getOutputStream();
                        OutputStreamWriter osw = new OutputStreamWriter(os);
                        writer = new BufferedWriter(osw);

                        while(true)
                        {
                            System.out.println("Awaiting message...");
                            String line = reader.readLine();
                            System.out.println("Received message: " + line);


                        }
                    } catch (UnknownHostException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
        ).start();
    }

    public void enviarMensaje(String msg)
    {
        new Thread(
                () ->
                {
                    try {
                        writer.write(msg + "\n");
                        writer.flush();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }).start();
    }

}