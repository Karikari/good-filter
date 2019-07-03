package com.karikari.goodfilter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MultiFilterWidget extends LinearLayout {

    public static final String TAG = MultiFilterWidget.class.getSimpleName();
    private Context context;

    private FilterChangeListener listener;
    private int orientation = 0;
    private float textSize = 0;
    private Drawable background_active;
    private Drawable background_selected;
    private List<String> items = new ArrayList<>();
    private List<TextView> textViews = new ArrayList<>();
    private List<ComboView> comboViews = new ArrayList<>();
    private List<String> selectedItems = new ArrayList<>();
    private LinearLayout mPillLayout;
    private int text_color;
    private int text_color_selected;
    private Typeface typeface;
    private Drawable icon_active;
    private Drawable icon_selected;
    private String font_type;
    private List<String> defaultItems = new ArrayList<>();

    public MultiFilterWidget(Context context) {
        super(context);
        initView(context);
    }

    public MultiFilterWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttributes(context, attrs);
        initView(context);
    }

    public MultiFilterWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributes(context, attrs);
        initView(context);

    }

    private void initAttributes(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MultiFilterWidget, 0, 0);
        try {
            this.orientation = typedArray.getInt(R.styleable.MultiFilterWidget_orientation, 0);
            this.textSize = typedArray.getDimensionPixelSize(R.styleable.MultiFilterWidget_textSize, 18);
            this.background_active = typedArray.getDrawable(R.styleable.MultiFilterWidget_active_background);
            this.background_selected = typedArray.getDrawable(R.styleable.MultiFilterWidget_selected_background);
            this.icon_active = typedArray.getDrawable(R.styleable.MultiFilterWidget_active_icon);
            this.icon_selected = typedArray.getDrawable(R.styleable.MultiFilterWidget_selected_icon);
            this.text_color = typedArray.getColor(R.styleable.MultiFilterWidget_text_color, getResources().getColor(R.color.black_olive));
            this.text_color_selected = typedArray.getColor(R.styleable.MultiFilterWidget_selected_text_color, getResources().getColor(R.color.colorAccent));
            this.font_type = typedArray.getString(R.styleable.MultiFilterWidget_font_family);


            if (this.background_active == null) {
                this.background_active = getResources().getDrawable(R.drawable.multi_bg);
            }

            if (this.background_selected == null) {
                this.background_selected = getResources().getDrawable(R.drawable.multi_selected_bg);
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


    private void setListItems() {
        for (String s : items) {
            ComboView comboView = new ComboView(context, orientation);
            comboView.setValue(s);
            comboView.setText_size(textSize);
            comboView.setBackground_active(background_active);
            //multiView.setBackground_selected(background_selected);
            comboView.setText_color(text_color);
            if (text_color_selected != -1)
                comboView.setSelected_text_color(text_color_selected);

            if (typeface != null) {
                comboView.setFontType(typeface);
            }

            comboView.setIcons(icon_active, icon_selected);

            comboViews.add(comboView);
            mPillLayout.addView(comboView);
        }
    }

    private void setListeners() {

        for (ComboView comboView : comboViews) {

            comboView.getmLayout().setOnClickListener(v -> {
                comboView.setValueSelect(comboView.isValueSelected());
                boolean isSelected = comboView.isValueSelected();
                String value = comboView.getValue();
                addOrRemoveItem(isSelected, value);
            });

            if(defaultItems.size()!=0){
                comboView.setDefault(defaultItems);
            }
        }
    }

    private void addOrRemoveItem(boolean op, String value){
        if(op){
            selectedItems.add(value);
        }else{
            selectedItems.remove(value);
        }
        String values = new Gson().toJson(selectedItems);
        //Log.d(TAG, "Items Selected : "+values);
        if(this.listener!=null){
            this.listener.onFiltered(values);
            this.listener.onFiltered(selectedItems);
        }
    }

    public void setItems(List<String> list) {
        this.items = list;
        setListItems();
        setListeners();
    }

    public void setDefaultItems(List<String> items){
        this.defaultItems =items;
    }


    public void setOnFilterChangeListener(FilterChangeListener listener) {
        this.listener = listener;
    }

    public interface FilterChangeListener {
        void onFiltered(String v);
        void onFiltered(List<String> items);
    }


}
