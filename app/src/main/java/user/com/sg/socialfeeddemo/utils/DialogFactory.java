package user.com.sg.socialfeeddemo.utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

import user.com.sg.socialfeeddemo.R;

/**
 * Created by ianicasiano on 2/26/16.
 */
public abstract class DialogFactory {

    public enum Type {
        LOADING
    }

    public static Dialog show (Type type, Context context) {
        switch (type) {
            case LOADING:
                String title, message;
                title = context.getString(R.string.dialog_loading_title);
                message = context.getString(R.string.dialog_loading_message);
                ProgressDialog dialog = ProgressDialog.show(context, title, message);

                return dialog;
            default:

                return null;
        }
    }

}
