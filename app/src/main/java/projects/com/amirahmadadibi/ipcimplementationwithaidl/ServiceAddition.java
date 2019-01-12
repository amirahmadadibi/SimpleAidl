package projects.com.amirahmadadibi.ipcimplementationwithaidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class ServiceAddition extends Service {
    public ServiceAddition() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    IAddition.Stub mBinder = new IAddition.Stub() {
        @Override
        public int add(int x, int y) throws RemoteException {
            return x + y;
        }
    };


}
