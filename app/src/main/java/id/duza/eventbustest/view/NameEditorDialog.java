package id.duza.eventbustest.view;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

import org.greenrobot.eventbus.EventBus;

import id.duza.eventbustest.R;
import id.duza.eventbustest.event.UserEvent;
import id.duza.eventbustest.databinding.EditorDialogBinding;

/**
 * Created by dhiyaulhaqza on 4/14/17.
 */

public class NameEditorDialog extends DialogFragment {

    private EditorDialogBinding bind;
    private EventBus bus = EventBus.getDefault();
    private UserEvent event;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        bind = EditorDialogBinding.inflate(LayoutInflater.from(getActivity()), null, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(bind.getRoot())
                .setTitle(R.string.str_editor)
                .setPositiveButton(R.string.str_save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = bind.etName.getText().toString().trim();
                        event = new UserEvent(name);
                        bus.post(event);
                    }
                })
                .setNegativeButton(R.string.str_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }
}
