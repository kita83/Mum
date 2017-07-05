package com.app.strkita.mum;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

/**
 *
 * Created by kitada on 2017/05/28.
 */

public class DatePickerDialogFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {
    private DatePickerDialog datePickerDialog;
    private EditText editBirthday;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(
                getActivity(),
                this,
                year,
                month,
                dayOfMonth
        );
        return datePickerDialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        editBirthday = (EditText) getActivity().findViewById(R.id.editBirthday);
        String date = editBirthday.getText().toString();

        // 再表示時に日付を反映する
        if (date.length() != 0) {
            int year = Integer.parseInt(date.substring(0, date.indexOf("/")));
            int month = Integer.parseInt(date.substring(5, date.indexOf("/", 5)));
            int dayOfMonth = Integer.parseInt(date.substring(date.lastIndexOf("/") + 1));

            datePickerDialog = (DatePickerDialog) getDialog();
            datePickerDialog.updateDate(year, month - 1, dayOfMonth);
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        editBirthday.setText(
                Integer.toString(year) +
                getString(R.string.slash) +
                Integer.toString(month + 1) +
                getString(R.string.slash) +
                Integer.toString(dayOfMonth)
        );
    }
}
