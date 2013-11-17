package com.aaronhill.quotesdream;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Parcelable;
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

	@Override
	protected Parcelable onSaveInstanceState() {
	    final Parcelable superState = super.onSaveInstanceState();
	    // Check whether this Preference is persistent (continually saved)
	    if (isPersistent()) {
	        // No need to save instance state since it's persistent, use superclass state
	        return superState;
	    }

	    // Create instance of custom BaseSavedState
	    final SavedState myState = new SavedState(superState);
	    // Set the state's value with the class member that holds current setting value
	    myState.value = picker.getValue();
	    return myState;
	}

	@Override
	protected void onRestoreInstanceState(Parcelable state) {
	    // Check whether we saved the state in onSaveInstanceState
	    if (state == null || !state.getClass().equals(SavedState.class)) {
	        // Didn't save the state, so call superclass
	        super.onRestoreInstanceState(state);
	        return;
	    }

	    // Cast state to custom BaseSavedState and pass to superclass
	    SavedState myState = (SavedState) state;
	    super.onRestoreInstanceState(myState.getSuperState());

	    // Set this Preference's widget to reflect the restored state
	    picker.setValue(myState.value);
	}
}