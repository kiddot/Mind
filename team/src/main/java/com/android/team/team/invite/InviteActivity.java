package com.android.team.team.invite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.common.base.componet.BaseActivity;
import com.android.common.base.componet.BaseMvpActivity;
import com.android.common.base.mvp.BaseMvpPresenter;
import com.android.team.R;
import com.android.team.R2;
import com.dd.processbutton.iml.ActionProcessButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.james.biuedittext.BiuEditText;

/**
 * Created by kiddo on 17-9-17.
 */

public class InviteActivity extends BaseMvpActivity<InvitePresenter> implements InviteContract.View{
    @BindView(R2.id.invite_et_name)
    BiuEditText mEtName;
    @BindView(R2.id.invite_btn_create)
    ActionProcessButton mBtnCreate;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, InviteActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_invite;
    }

    @Override
    protected void init(InvitePresenter presenter, Bundle savedInstanceState) {
        mEtName = (BiuEditText) findViewById(R.id.invite_et_name);
    }

    @Override
    protected InvitePresenter onCreatePresenter() {
        return new InvitePresenter(this, this);
    }

    public void invite(View view){
        getPresenter().sendInvite();
        Toast.makeText(this, "已经成功添加成员~", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public String getInviteName() {
        return mEtName.getText().toString().trim();
    }
}
