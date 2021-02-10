package com.example.Arduino_Car_App;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import com.example.boldi.bluetoothcarcontroller.R;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private final UUID PORT_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

    private BluetoothDevice device;
    private BluetoothSocket socket;
    private OutputStream outputStream;

    // Deklaracja typu wszystkich przycisków znajdujących się aplikacji
    Button forward_btn, forward_left_btn, forward_right_btn, reverse_btn, bluetooth_connect_btn, auto_mode_btn;
    String command; // Deklaracja zmiennej typu string która będzie przechowywać wartośći do przesłania przez bluetooth

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Deklaracja zmiennych przycisków
        forward_btn = (Button) findViewById(R.id.forward_btn);
        forward_left_btn = (Button) findViewById(R.id.forward_left_btn);
        forward_right_btn = (Button) findViewById(R.id.forward_right_btn);
        reverse_btn = (Button) findViewById(R.id.reverse_btn);
        bluetooth_connect_btn = (Button) findViewById(R.id.bluetooth_connect_btn);
        Switch auto_mode_btn = (Switch) findViewById(R.id.auto_mode_btn);

        // Nasłuch działania przycisku (switch) auto_mode_btn, jazda autonomiczna
        auto_mode_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    command = "k";
                    // Jeśli stan przycisku jest aktywny to przypisuje "k" do zmiennej command
                    // i podejmuje próbe wysłania zawartości tej zmiennej w postaci bajtów
                    try
                    {
                        outputStream.write(command.getBytes());
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                else{
                    command = "c";
                    // w przeciwnym wypadku czyli gdy przycisk nie jest aktywny to przypisuje "c" do zmiennej command
                    // i podejmuje próbe wysłania zawartości tej zmiennej w postaci bajtów
                    try
                    {
                        outputStream.write(command.getBytes());
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });
        // Nasłuch działania przycisku (button) forward_btn, jazda do przodu
        forward_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    command = "w";
                    // Jeśli stan przycisku jest aktywny to przypisuje "w" do zmiennej command
                    // i podejmuje próbe wysłania zawartości tej zmiennej w postaci bajtów
                    try
                    {
                        outputStream.write(command.getBytes()); //transmits the value of command to the bluetooth module
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                else if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    command = "c";
                    // w przeciwnym wypadku czyli gdy przycisk nie jest aktywny to przypisuje "c" do zmiennej command
                    // i podejmuje próbe wysłania zawartości tej zmiennej w postaci bajtów
                    try
                    {
                        outputStream.write(command.getBytes());
                    }
                    catch(IOException e)
                    {
                        e.printStackTrace();
                    }

                }

                return false;
            }

        });

        // Nasłuch działania przycisku (button) reverse_btn, jazda w tył
        reverse_btn.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    command = "s";
                    // Jeśli stan przycisku jest aktywny to przypisuje "s" do zmiennej command
                    // i podejmuje próbe wysłania zawartości tej zmiennej w postaci bajtów
                    try
                    {
                        outputStream.write(command.getBytes());
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                else if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    command = "c";
                    // w przeciwnym wypadku czyli gdy przycisk nie jest aktywny to przypisuje "c" do zmiennej command
                    // i podejmuje próbe wysłania zawartości tej zmiennej w postaci bajtów
                    try
                    {
                        outputStream.write(command.getBytes());
                    }
                    catch(IOException e)
                    {

                    }

                }
                return false;
            }
        });

        // Nasłuch działania przycisku (button) forward_left_btn, jazda w lewo
        forward_left_btn.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    command = "a";
                    // Jeśli stan przycisku jest aktywny to przypisuje "a" do zmiennej command
                    // i podejmuje próbe wysłania zawartości tej zmiennej w postaci bajtów
                    try
                    {
                        outputStream.write(command.getBytes());
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                else if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    command = "c";
                    // w przeciwnym wypadku czyli gdy przycisk nie jest aktywny to przypisuje "c" do zmiennej command
                    // i podejmuje próbe wysłania zawartości tej zmiennej w postaci bajtów
                    try
                    {
                        outputStream.write(command.getBytes());
                    }
                    catch(IOException e)
                    {

                    }

                }
                return false;
            }
        });

        // Nasłuch działania przycisku (button) forward_right_btn, jazda w prawo
        forward_right_btn.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    command = "d";
                    // Jeśli stan przycisku jest aktywny to przypisuje "d" do zmiennej command
                    // i podejmuje próbe wysłania zawartości tej zmiennej w postaci bajtów
                    try
                    {
                        outputStream.write(command.getBytes());
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                else if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    command = "c";
                    // w przeciwnym wypadku czyli gdy przycisk nie jest aktywny to przypisuje "c" do zmiennej command
                    // i podejmuje próbe wysłania zawartości tej zmiennej w postaci bajtów
                    try
                    {
                        outputStream.write(command.getBytes());
                    }
                    catch(IOException e)
                    {
                        e.printStackTrace();
                    }

                }
                return false;
            }
        });

        // Nasłuch przycisku od połączenia bluteooth
        bluetooth_connect_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(BTinit()) // Funkcja inicjalizująca jeśli zwróci true przechodzi do funkcji BTconnect
                {
                    BTconnect();
                }

            }
        });

    }

    //Inicjalizacja modułu bluetooth
    public boolean BTinit()
    {
        boolean found = false;

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if(bluetoothAdapter == null)
        {
            Toast.makeText(getApplicationContext(), "Device doesn't support bluetooth", Toast.LENGTH_SHORT).show();
        }

        if(!bluetoothAdapter.isEnabled()) // Sprawdza czy bluetooth jest włączony jeśli nie to aplikacja poprosi użytkownika o włączenie
        {
            Intent enableAdapter = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableAdapter,0);

            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();

        if(bondedDevices.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Please pair the device first", Toast.LENGTH_SHORT).show();
        }
        else
        {
            for(BluetoothDevice iterator : bondedDevices)
            {
                // Adres mac modułu bluetooth z którym się łączymy
                String DEVICE_ADDRESS = "00:19:10:08:3E:FC";
                if(iterator.getAddress().equals(DEVICE_ADDRESS))
                {
                    device = iterator;
                    found = true;
                    break;
                }
            }
        }

        return found;
    }

    public void BTconnect()
    {
        boolean connected = true;

        try
        {
            socket = device.createRfcommSocketToServiceRecord(PORT_UUID); // Tworzy socket do obsługi wyjścia
            socket.connect();

            Toast.makeText(getApplicationContext(),
                    "Connection to bluetooth device successful", Toast.LENGTH_LONG).show();
        }
        catch(IOException e)
        {
            e.printStackTrace();
            connected = false;
        }

        if(connected)
        {
            try
            {
                outputStream = socket.getOutputStream();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }

    }

}