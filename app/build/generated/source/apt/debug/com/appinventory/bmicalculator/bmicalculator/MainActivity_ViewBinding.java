// Generated code from Butter Knife. Do not modify!
package com.appinventory.bmicalculator.bmicalculator;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding<T extends MainActivity> implements Unbinder {
  protected T target;

  @UiThread
  public MainActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.weightEdtTxt = Utils.findRequiredViewAsType(source, R.id.editText, "field 'weightEdtTxt'", EditText.class);
    target.heightEdtTxt = Utils.findRequiredViewAsType(source, R.id.editText2, "field 'heightEdtTxt'", EditText.class);
    target.weightSpinner = Utils.findRequiredViewAsType(source, R.id.spinner, "field 'weightSpinner'", Spinner.class);
    target.heightSpinner = Utils.findRequiredViewAsType(source, R.id.spinner2, "field 'heightSpinner'", Spinner.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.weightEdtTxt = null;
    target.heightEdtTxt = null;
    target.weightSpinner = null;
    target.heightSpinner = null;

    this.target = null;
  }
}
