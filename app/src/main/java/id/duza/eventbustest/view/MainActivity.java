package id.duza.eventbustest.view;

import android.app.DialogFragment;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import id.duza.eventbustest.R;
import id.duza.eventbustest.event.UserEvent;
import id.duza.eventbustest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding bind;
    private EventBus bus = EventBus.getDefault();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_main);

    }

    public void addName(View view) {
        DialogFragment nameEditor = new NameEditorDialog();
        nameEditor.show(getFragmentManager(), "fragment");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTextOneChanged(UserEvent event) {
        bind.tvMainOne.setText("Hello, ".concat(event.name));
    }

    @Override
    protected void onStart() {
        super.onStart();
       bus.register(this);
    }

    @Override
    protected void onStop() {
        bus.unregister(this);
        super.onStop();
    }
}
