package com.aaronhill.quotesdream;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.NumberPicker;

public class NumberPickerPreference extends DialogPreference {

	NumberPicker picker;
	Integer initialValue;

	public NumberPickerPreference(Context context, AttributeSet attrs) {
		super(context, attrs);

		setDialogLayoutResource(R.layout.number_pref);
	}

	@Override
	protected void onBindDialogView(View view) {
		super.onBindDialogView(view);
		this.picker = (NumberPicker)view.findViewById(R.id.pref_num_picker);
		// TODO this should be an XML parameter:
		picker.setMinValue(1);
		picker.setMaxValue(20);
		if ( this.initialValue != null ) picker.setValue(initialValue);
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		super.onClick(dialog, which);
		if ( which == DialogInterface.BUTTON_POSITIVE ) {
			this.initialValue = (Integer) picker.getValue();
			persistInt( initialValue );
			callChangeListener( initialValue );
		}
	}

	@Override
	protected void onSetInitialValue(boolean restorePersistedValue,
			Object defaultValue) {
		if ( restorePersistedValue ) {
			this.initialValue = getPersistedInt(1);
		}
		else {
			this.initialValue = (Integer)defaultValue;
			persistInt(this.initialValue);
		}
	}

	@Override
	protected Object onGetDefaultValue(TypedArray a, int index) {
		return a.getInt(index, 1);
	}
}