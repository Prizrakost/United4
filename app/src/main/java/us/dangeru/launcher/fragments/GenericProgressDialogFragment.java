package us.dangeru.launcher.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.Bundle;

/**
 * Created by Niles on 8/20/17.
 */

public class GenericProgressDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String message = getArguments().getString("text");
        ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setCancelable(false);
        dialog.setTitle(message);
        dialog.setMessage(message);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        return dialog;
    }

    public static void newInstance(String s, FragmentManager manager) {
        DialogFragment f = new GenericProgressDialogFragment();
        Bundle args = new Bundle();
        args.putString("text", s);
        f.setArguments(args);
        f.show(manager, "progress_dialog");
    }

    public static void dismiss(FragmentManager fragmentManager) {
        DialogFragment f = (DialogFragment) fragmentManager.findFragmentByTag("progress_dialog");
        f.dismiss();
    }
}
