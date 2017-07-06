package com.app.strkita.mum;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 赤ちゃんプロフィールフラグメント
 * Created by strkita on 2017/07/05.
 */

public class ProfileFragment extends android.support.v4.app.Fragment {
    DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
    private static Constellation[] constellations;
    private EditText editBirthday;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_profile, null);

        editBirthday = (EditText) root.findViewById(R.id.editBirthday);
        editBirthday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                TextView editConstellation = (TextView)getView().findViewById(R.id.editConstellation);
                editConstellation.setText(getConstellation(editBirthday.getText().toString()));
            }
        });

        return root;
    }

    /**
     * 日付から該当する星座を判定する
     * @param birthday
     * @return constellation
     */
    private String getConstellation(String birthday) {

        // 星座リストの初期化
        initConstellations();

        try {
            Date dateBirthday = format.parse(birthday);

            for (Constellation c : constellations) {
                if (c.between(dateBirthday)) {
                    return c.getName();
                }
            }
        } catch (ParseException e) {
            Log.e("Parse exceptions", "Parse exceptions");
            return null;
        }
        return null;
    }

    /**
     * 星座リストを初期化する
     */
    private static void initConstellations() {
        constellations = new Constellation[12];
        int i = 0;
        constellations[i++] = new Constellation("みずがめ座", "1/20", "2/18");
        constellations[i++] = new Constellation("うお座", "2/19", "3/20");
        constellations[i++] = new Constellation("おひつじ座", "3/21", "4/19");
        constellations[i++] = new Constellation("おうし座", "4/20", "5/20");
        constellations[i++] = new Constellation("ふたご座", "5/21", "6/21");
        constellations[i++] = new Constellation("かに座", "6/22", "7/22");
        constellations[i++] = new Constellation("しし座", "7/23", "8/22");
        constellations[i++] = new Constellation("おとめ座", "8/23", "9/22");
        constellations[i++] = new Constellation("てんびん座", "9/23", "10/23");
        constellations[i++] = new Constellation("さそり座", "10/24", "11/22");
        constellations[i++] = new Constellation("いて座", "11/23", "12/21");
        constellations[i++] = new Constellation("やぎ座", "12/22", "1/19");
    }
}
