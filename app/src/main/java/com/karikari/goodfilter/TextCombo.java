package com.karikari.goodfilter;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.List;

public class TextCombo extends LinearLayout {

    public static final String TAG = TextCombo.class.getSimpleName();
    private String value;
    private Boolean valueSelect = false;
    private int icon;
    private Drawable background_active;
    private Drawable background_selected;
    private Typeface fontType;
    private int text_color;
    private float text_size;
    private int selected_text_color = -1;
    private Drawable active_icon;
    private Drawable selected_icon;

    private TextView mValue;
    private ImageButton mIcon;
    private LinearLayout mLayout;
    private int orientation = 0;

    public TextCombo(Context context) {
        super(context);
        init(context);
    }

    public TextCombo(Context context, int orientation) {
        super(context);
        this.orientation = orientation;
        init(context);
    }

    public TextCombo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextCombo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context){
        View view;
        if(this.orientation == 0){
            view = inflate(context, R.layout.item_row_text, this);
        }else{
            view = inflate(context, R.layout.item_row_text_horizontal, this);

        }
        this.mValue = view.findViewById(R.id.text);
        this.mLayout = view.findViewById(R.id.item_layout);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        this.mValue.setText(this.value);
    }

    public Boolean isValueSelected() {
        return valueSelect;
    }

    public void setValueSelect(Boolean valueSelect) {

        Log.d(TAG, "Value is : "+valueSelect);

        if(!valueSelect){
            if(selected_icon !=null){
                mIcon.setImageDrawable(selected_icon);
            }
            if(background_selected!=null){
                mLayout.setBackground(background_selected);
            }
            if(getSelected_text_color() != -1){
                mValue.setTextColor(getSelected_text_color());
            }
            this.valueSelect = true;
        }else{
            if(active_icon!=null){
                mIcon.setImageDrawable(active_icon);
            }
            if(background_active!=null){
                mLayout.setBackground(background_active);
            }
            mValue.setTextColor(getText_color());
            this.valueSelect = false;
        }
    }


    public LinearLayout getmLayout() {
        return mLayout;
    }

    public void setmLayout(LinearLayout mLayout) {
        this.mLayout = mLayout;
    }

    public Drawable getBackground_active() {
        return background_active;
    }

    public void setBackground_active(Drawable background_active) {
        this.background_active = background_active;
        mLayout.setBackground(this.background_active);
    }

    public Drawable getBackground_selected() {
        return background_selected;
    }

    public void setBackground_selected(Drawable background_selected) {
        this.background_selected = background_selected;
    }

    public Typeface getFontType() {
        return fontType;
    }

    public void setFontType(Typeface fontType) {
        this.fontType = fontType;
        if(fontType!=null){
            this.mValue.setTypeface(fontType);
        }
    }

    public int getText_color() {
        return text_color;
    }

    public void setText_color(int text_color) {
        this.text_color = text_color;
        this.mValue.setTextColor(this.text_color);
    }

    public int getSelected_text_color() {
        return selected_text_color;
    }

    public void setSelected_text_color(int selected_text_color) {
        this.selected_text_color = selected_text_color;
    }

    public void setIcons(Drawable active_icon, Drawable selected_icon){
        this.active_icon = active_icon;
        this.selected_icon = selected_icon;

        if(this.active_icon != null && this.selected_icon!=null){
            mIcon.setImageDrawable(active_icon);
            mIcon.setVisibility(VISIBLE);
        }
    }

    public float getText_size() {
        return text_size;
    }

    public void setText_size(float text_size) {
        this.text_size = text_size;
        mValue.setTextSize(TypedValue.COMPLEX_UNIT_PX, text_size);
    }

    public void setDefault(List<String> items){
        for(String s : items){
            if(TextUtils.equals(value, s)){

                if(selected_icon !=null){
                    mIcon.setImageDrawable(selected_icon);
                }
                if(background_selected!=null){
                    mLayout.setBackground(background_selected);
                }
                if(getSelected_text_color() != -1){
                    mValue.setTextColor(getSelected_text_color());
                }
                this.valueSelect = true;
            }
        }
    }
}
