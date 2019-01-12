package projects.com.amirahmadadibi.ipcimplementationwithaidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    IAddition iAddition;
    boolean isServiceConnected = false;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iAddition = IAddition.Stub.asInterface(service);
            isServiceConnected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this,ServiceAddition.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this,ServiceAddition.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public void Run(View view) {
        if(isServiceConnected){
            try {
                Toast.makeText(this, iAddition.add(10,10) + "", Toast.LENGTH_SHORT).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(this, "service is Disconnected", Toast.LENGTH_SHORT).show();
        }
    }
}
