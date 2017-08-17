package com.android.teamtask.task.publish;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.common.base.componet.BaseMvpActivity;
import com.android.common.base.mvp.BaseMvpPresenter;
import com.android.teamtask.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kiddo on 17-8-17.
 */
public class PublishActivity extends BaseMvpActivity implements PublishContract.View,
        DatePickerDialog.OnDateSetListener {

    private static final String TAG = "PublishActivity";
    @BindView(R.id.write_tv_date)
    TextView mTvDate;

    private String mDate;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, PublishActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void init(BaseMvpPresenter presenter, Bundle savedInstanceState) {

    }

    @Override
    protected BaseMvpPresenter onCreatePresenter() {
        return new PublishPresenter(this, this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_publish;
    }

    public void onClickDate(View view) {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getFragmentManager(), "DatePickerDialog");
    }

    public void onClickPriority(View view) {

    }

    public void onClickReceiver(View view) {

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Log.d(TAG, "onDateSet: " + year + monthOfYear + dayOfMonth);
        String date = (monthOfYear + 1) + "." + dayOfMonth;
        mTvDate.setText(date);
        mDate = date;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
