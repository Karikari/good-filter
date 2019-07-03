package com.karikari.goodfilter2;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SingleFilterWidget extends LinearLayout {

    public static final String TAG = SingleFilterWidget.class.getSimpleName();
    private Context context;

    private FilterChangeListener listener;
    private int orientation = 0;
    private float textSize = 0;
    private Drawable background_active;
    private Drawable background_selected;
    private List<String> items = new ArrayList<>();
    private List<TextView> textViews = new ArrayList<>();
    private LinearLayout mPillLayout;
    private LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    private int text_color;
    private int text_color_selected;
    private Typeface typeface;
    private String selected_valued;

    private String font_type;

    public SingleFilterWidget(Context context) {
        super(context);
        initView(context);
    }

    public SingleFilterWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttributes(context, attrs);
        initView(context);
    }

    public SingleFilterWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributes(context, attrs);
        initView(context);

    }

    private void initAttributes(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SingleFilterWidget, 0, 0);
        try {
            this.orientation = typedArray.getInt(R.styleable.SingleFilterWidget_orientation, 0);
            this.textSize = typedArray.getDimensionPixelSize(R.styleable.SingleFilterWidget_textSize, 18);
            this.background_active = typedArray.getDrawable(R.styleable.SingleFilterWidget_active_background);
            this.background_selected = typedArray.getDrawable(R.styleable.SingleFilterWidget_selected_background);
            this.text_color = typedArray.getColor(R.styleable.SingleFilterWidget_text_color, getResources().getColor(R.color.black_olive));
            this.text_color_selected = typedArray.getColor(R.styleable.SingleFilterWidget_selected_text_color, getResources().getColor(R.color.colorPrimaryDark));
            this.font_type = typedArray.getString(R.styleable.SingleFilterWidget_font_family);


            if (this.background_active == null) {
                this.background_active = getResources().getDrawable(R.drawable.pill_bg);
            }

            if (this.background_selected == null) {
                this.background_selected = getResources().getDrawable(R.drawable.pill_selected_bg);
            }
            if (!TextUtils.isEmpty(font_type)) {
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/" + font_type + ".ttf");
            }

        } finally {
            typedArray.recycle();
        }
    }

    private void initView(Context context) {
        this.context = context;
        View view;
        if (this.orientation == 0) {
            view = LayoutInflater.from(context).inflate(R.layout.pills_layout_vertical, this);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.pills_layout_horizontal, this);
        }
        mPillLayout = view.findViewById(R.id.pill_layout);

    }


    private void setListPills() {
        for (String s : items) {
            params.setMargins(0, 10, 0, 5);
            TextView textView = new TextView(context);
            textView.setText(s);
            textView.setTextColor(text_color);
            if (typeface != null) {
                textView.setTypeface(typeface);
            }
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            if (orientation == 1) {
                params.setMargins(0, 10, 30, 5);
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }
            textView.setBackground(background_active);
            textView.setLayoutParams(params);
            textViews.add(textView);
            mPillLayout.addView(textView);
        }
    }

    private void setListeners() {
        for (TextView textView : textViews) {
            textView.setOnClickListener(v -> {
                String value = textView.getText().toString();
                if (this.listener != null) {
                    listener.onFiltered(value);
                    setChanges(value);
                } else {
                    setChanges(value);
                }
                Log.d(TAG, "ITEM : " + value);

            });
        }
    }

    private void setChanges(String text) {
        for (TextView textView : textViews) {

            if (TextUtils.equals(text, textView.getText().toString())) {
                this.selected_valued = text;
                textView.setBackground(null);
                textView.setTextColor(text_color_selected);
                textView.setBackground(background_selected);
                textView.setLayoutParams(params);
            } else {
                textView.setBackground(null);
                textView.setTextColor(text_color);
                textView.setBackground(background_active);
                textView.setLayoutParams(params);
            }
            if (orientation == 1) {
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }
        }
    }

    public void setItems(List<String> list) {
        this.items = list;
        setListPills();
        setListeners();
    }

    public String getSelected_valued() {
        return selected_valued;
    }

    public void setSelected_valued(String selected_valued) {
        this.selected_valued = selected_valued;
        setChanges(this.selected_valued);
    }

    public void setOnFilterChangeListener(FilterChangeListener listener) {
        this.listener = listener;
    }

    public interface FilterChangeListener {
        void onFiltered(String v);
    }


}
