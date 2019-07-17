package com.karikari.goodfilter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.karikari.goodfilter.adapters.MultiADP;
import com.karikari.goodfilter.model.Item;
import com.karikari.goodfilter.model.SelectableItem;

import java.util.ArrayList;
import java.util.List;

public class MultiFilterWidget extends LinearLayout implements MultiADP.OnItemSelectedListener {

    public static final String TAG = MultiFilterWidget.class.getSimpleName();
    private Context context;

    private FilterChangeListener listener;
    private int orientation = 0;
    private float textSize = 0;
    private int background_active;
    private int background_selected;
    private int text_color;
    private int text_color_selected;
    private Typeface typeface;
    private int icon_active;
    private int icon_selected;
    private String font_type;
    private List<String> defaultItems = new ArrayList<>();
    private RecyclerView mRecyclerView;

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
            this.background_active = typedArray.getResourceId(R.styleable.MultiFilterWidget_active_background, -1);
            this.background_selected = typedArray.getResourceId(R.styleable.MultiFilterWidget_selected_background, -1);
            this.icon_active = typedArray.getResourceId(R.styleable.MultiFilterWidget_active_icon, -1);
            this.icon_selected = typedArray.getResourceId(R.styleable.MultiFilterWidget_selected_icon, -1);
            this.text_color = typedArray.getColor(R.styleable.MultiFilterWidget_text_color, getResources().getColor(R.color.black_olive));
            this.text_color_selected = typedArray.getColor(R.styleable.MultiFilterWidget_selected_text_color, getResources().getColor(R.color.colorAccent));
            this.font_type = typedArray.getString(R.styleable.MultiFilterWidget_font_family);

            if (this.background_active == -1) {
                this.background_active = R.drawable.multi_bg;
            }

            if (this.background_selected == -1) {
                this.background_selected = R.drawable.multi_selected_bg;
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
        View view = LayoutInflater.from(context).inflate(R.layout.base_layout, this);
        mRecyclerView = view.findViewById(R.id.single_recycler);

    }

    private void setItemsToRecycerler(List<SelectableItem> list) {
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.Adapter mAdapter;
        RecyclerView.LayoutManager mLayoutManager;

        if (this.orientation == 1) {
            mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        } else {
            mLayoutManager = new LinearLayoutManager(context);
        }
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MultiADP(context, list, this,
                background_active,
                background_selected,
                orientation,
                text_color,
                text_color_selected,
                typeface,
                textSize,
                icon_active,
                icon_selected);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemSelected(String item) {
        if(listener!=null){
            listener.onFiltered(item);
        }
    }

    public void setStringValues(List<String> list) {
        List<SelectableItem> items = new ArrayList<>();
        for (String s : list) {
            items.add(new SelectableItem(new Item(s), false));
        }
        setItemsToRecycerler(items);
    }


    public void setOnFilterChangeListener(FilterChangeListener listener) {
        this.listener = listener;
    }

    public interface FilterChangeListener {
        void onFiltered(String v);
    }


}
