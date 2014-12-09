package arthur.ddmo.mobilebudget.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import arthur.ddmo.mobilebudget.R;

/**
 * Created by arthur on 08/12/14.
 */
public class ConfirmDeleteSingleTransactionDialog extends DialogFragment {

    public interface ConfirmDeleteTransactionListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    ConfirmDeleteTransactionListener listener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (ConfirmDeleteTransactionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement ConfirmDeleteTransactionListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_clean_single_transaction_title)
                .setMessage(R.string.dialog_clean_database_message)
                .setPositiveButton(R.string.dialog_clean_database_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onDialogPositiveClick(ConfirmDeleteSingleTransactionDialog.this);
                    }
                })
                .setNegativeButton(R.string.dialog_clean_database_negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onDialogNegativeClick(ConfirmDeleteSingleTransactionDialog.this);
                    }
                });
        return builder.create();
    }
}