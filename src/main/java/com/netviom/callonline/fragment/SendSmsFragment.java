package com.netviom.callonline.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.netviom.callonline.R;
import com.netviom.callonline.activity.SmsCommonInfoActivity;
import com.netviom.callonline.activity.SmsSignaActivity;
import com.netviom.callonline.definedwidget.TopLayout;
import com.nvm.common.util.LogUtil;
import com.nvm.common.util.StringUtils;

import java.util.Calendar;

public class SendSmsFragment extends BaseFragment implements View.OnClickListener {
    private EditText ediContent;
    private Button btnSmsSigna;
    private TextView texWordsAccount;
    private TextView texSmsAccount;
    private final int SMS_CONTENT_LENGTH = 390;
    private CheckBox cheTimingSend;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private String timeSet;
    // TODO: Rename parameter arguments, choose names that match
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_send_sms, container, false);
        view.findViewById(R.id.btn_common_sms).setOnClickListener(this);
        btnSmsSigna = (Button) view.findViewById(R.id.btn_sms_signa);
        btnSmsSigna.setOnClickListener(this);
        ediContent = (EditText) view.findViewById(R.id.edi_content);
        texWordsAccount = (TextView) view.findViewById(R.id.tex_word_account);
        texSmsAccount = (TextView) view.findViewById(R.id.tex_sms_account);
        cheTimingSend = (CheckBox) view.findViewById(R.id.che_timing_send);
        int smsSignaAmount;
        smsSignaAmount = btnSmsSigna.getText().length();
        ediContent.setFilters(new InputFilter[]{new InputFilter.LengthFilter(SMS_CONTENT_LENGTH - smsSignaAmount)});
        texWordsAccount.setText(smsSignaAmount + "/" + SMS_CONTENT_LENGTH);
        initTop(view, "群发短信", false, "");
        initDateTimePicker();
        initListener();
        return view;
    }

    public void initDateTimePicker() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                timeSet = i+"-"+i2+"-"+i3+" ";
                timePickerDialog.show();
            }
        };
        LogUtil.info(" calendar.YEAR:"+ calendar.YEAR);
        datePickerDialog = new DatePickerDialog(getActivity(), onDateSetListener, calendar.get(calendar.YEAR),calendar.get( calendar.MONTH), calendar.get(calendar.DAY_OF_MONTH));
        datePickerDialog.setTitle("设置日期");
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i2) {
                timeSet = timeSet+i+":"+i2+":00";
                cheTimingSend.setText("定时发送（"+timeSet+")");
            }
        };
        timePickerDialog = new TimePickerDialog(getActivity(), onTimeSetListener, calendar.get(calendar.HOUR_OF_DAY),calendar.get(calendar.MINUTE), true);
        timePickerDialog.setTitle("设置时间");
    }

    public void initListener() {
        ediContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                int length = ediContent.getText().length() + btnSmsSigna.getText().length();
                int smsAmount = 0;
                if (length <= 70) {
                    smsAmount = 1;
                } else if (length <= (65 * 9)) {
                    if (length % 65 == 0) {
                        smsAmount = length / 65;
                    } else {
                        smsAmount = length / 65 + 1;
                    }
                } else {
                    if (length % 63 == 0) {
                        smsAmount = length / 63;
                    } else {
                        smsAmount = length / 63 + 1;
                    }
                }
                texSmsAccount.setText(smsAmount + "");
                texWordsAccount.setText(length + "/" + SMS_CONTENT_LENGTH);
            }
        });
        cheTimingSend.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                          datePickerDialog.show();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String content;
        switch (resultCode) {
            case Activity.RESULT_OK:
                content = data.getExtras().getString("content");
                if (StringUtils.isNotEmpty(content)) {
                    String s = ediContent.getText().toString();
                    ediContent.setText(s + content);
                }
                break;
            case Activity.RESULT_FIRST_USER:
                content = data.getExtras().getString("content");
                if (StringUtils.isNotEmpty(content)) {
                    btnSmsSigna.setText(content);
                    ediContent.setFilters(new InputFilter[]{new InputFilter.LengthFilter(SMS_CONTENT_LENGTH - btnSmsSigna.getText().length())});
                }
                break;

        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_common_sms:
                intent.setClass(getActivity(), SmsCommonInfoActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.btn_sms_signa:
                intent.setClass(getActivity(), SmsSignaActivity.class);
                startActivityForResult(intent, 1);
                break;
        }

    }
}
